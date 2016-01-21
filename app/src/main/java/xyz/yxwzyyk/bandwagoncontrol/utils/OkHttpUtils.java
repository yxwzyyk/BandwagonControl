package xyz.yxwzyyk.bandwagoncontrol.utils;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
            public void onFailure(Call call, IOException e) {
                Mlog.logI(TAG, "请求失败:" + e.toString());
                new Delivery().MainThreadRun(() -> mHttpCallBack.onFail(e));

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Mlog.logI(TAG,"请求成功:"+result);
                new Delivery().MainThreadRun(() -> mHttpCallBack.onOk(result));
            }

        });
    }

    public abstract interface HttpCallBack {
        public abstract void onFail(Exception e);

        public abstract void onOk(String msg);
    }
}
