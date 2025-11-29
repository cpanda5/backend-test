from fastapi import FastAPI
from fastapi.responses import StreamingResponse
import time

app = FastAPI()


def stream_generator():
    for i in range(10):
        yield f"data: Hello {i}\n\n"
        time.sleep(1)


@app.get("/stream")
def stream():
    return StreamingResponse(stream_generator(), media_type="text/event-stream")
