import pandas as pd
from openai import OpenAI
import os
from dotenv import load_dotenv

load_dotenv()
client = OpenAI(
    api_key=os.getenv("DASHSCOPE_API_KEY"),
    base_url="https://dashscope.aliyuncs.com/compatible-mode/v1",
)

INPUT_FILE = "sample_imdb.csv"
OUTPUT_FILE = "labeled_imdb.csv"

df = pd.read_csv(INPUT_FILE)

# 存储模型标注
df["model_label"] = ""


# 定义情感分类提示词
def classify_sentiment(text):
    prompt = f"""
你是一个情感分类模型。请阅读以下电影评论，并判断情感倾向。
只允许输出以下两种标签之一：

positive
negative

不要输出其他文字。

评论内容: {text}
"""

    completion = client.chat.completions.create(
        model="qwen3-max",
        messages=[
            {"role": "system", "content": "You classify sentiment only as positive or negative."},
            {"role": "user", "content": prompt},
        ],
        stream=False
    )

    label = getattr(completion.choices[0].message, "content", None).strip().lower()

    if label not in ["positive", "negative"]:
        label = "positive" if "good" in text.lower() else "negative"

    return label


# 遍历每条影评进行自动标注
results = []
for i, row in df.iterrows():
    review_text = row["review"]

    label = classify_sentiment(review_text)
    df.at[i, "model_label"] = label
    results.append((review_text, label))

# 保存结果
df.to_csv(OUTPUT_FILE, index=False)
