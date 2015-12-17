package xyz.yxwzyyk.bandwagoncontrol.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by yyk on 12/12/15.
 */
public class OkHttpUtils {
    private final String TAG = this.getClass().getName();

    private String mUrl;
    private HttpCallBack mHttpCallBack;

    public OkHttpUtils setCallBack(HttpCallBack callBack) {
        mHttpCallBack = callBack;
        return this;
    }

    public OkHttpUtils setUrl(String url) {
        mUrl = url;
        return this;
    }

    public void post(RequestBody formBody) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(mUrl).post(formBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                Mlog.logI(TAG, "请求失败:" + e.toString());
                new Delivery().MainThreadRun(new Runnable() {
                    @Override
                    public void run() {
                        mHttpCallBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                Mlog.logI(TAG,"请求成功:"+result);
                new Delivery().MainThreadRun(new Runnable() {
                    @Override
                    public void run() {
                        mHttpCallBack.onOk(result);
                    }
                });
            }
        });
    }

    public abstract interface HttpCallBack {
        public abstract void onFail(Exception e);

        public abstract void onOk(String msg);
    }
}
