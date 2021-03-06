package com.example.retrofitdemo.http;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by xsl on 2016/7/1.
 * @author  Celery
 */
public abstract class CeleryRequestBody extends RequestBody {

    private final RequestBody requestBody;
    private BufferedSink bufferedSink;

    public CeleryRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        requestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
        bufferedSink.close();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            private long current;
            private long total;
            private int last = 0;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (total == 0) {
                    total = contentLength();
                }
                current += byteCount;
                int now = (int) (current * 100 / total);
                if (last < now) {
                    Message message = handler.obtainMessage();
                    message.what = 200;
                    message.obj = last;
                    if (total == current){
                        message.arg1 = 1;
                    }else {
                        message.arg1 = 0;
                    }
                    message.sendToTarget();

                    last = now;
                }
            }
        };
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    loading((int)msg.obj, 100, msg.arg1==1 ? true:false);
                    break;
            }
        }
    };

    public abstract void loading(long current, long total, boolean done);
}
