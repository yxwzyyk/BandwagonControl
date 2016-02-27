package xyz.yxwzyyk.bandwagoncontrol.host;


import okhttp3.FormBody;
import okhttp3.RequestBody;
import xyz.yxwzyyk.bandwagoncontrol.app.Configure;
import xyz.yxwzyyk.bandwagoncontrol.utils.OkHttpUtils;

/**
 * Created by yyk on 12/4/15.
 */
public class Command {

    private OkHttpUtils mHttpUtils;
    private String mID;
    private String mKey;

    public Command(String id, String key) {
        mID = id;
        mKey = key;
        mHttpUtils = new OkHttpUtils();
    }

    public void getInfo(OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.GET_INFO).setCallBack(callBack);
        mHttpUtils.post(setIdKey());
    }

    public void start(OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.START).setCallBack(callBack);
        mHttpUtils.post(setIdKey());
    }

    public void reboot(OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.REBOOT).setCallBack(callBack);
        mHttpUtils.post(setIdKey());
    }

    public void stop(OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.STOP).setCallBack(callBack);
        mHttpUtils.post(setIdKey());
    }

    public void basicShell(String command, OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.BASICSHELL).setCallBack(callBack);
        RequestBody body = new FormBody.Builder()
                .add("veid", mID)
                .add("api_key", mKey)
                .add("command", command)
                .build();
        mHttpUtils.post(body);
    }

    public void basicShellCD(String currentDir, String newDir, OkHttpUtils.HttpCallBack callBack) {
        mHttpUtils.setUrl(Configure.HOST_URL + Configure.BASICSHELLCD).setCallBack(callBack);
        RequestBody body = new FormBody.Builder()
                .add("veid", mID)
                .add("api_key", mKey)
                .add("currentDir", currentDir)
                .add("newDir", newDir)
                .build();
        mHttpUtils.post(body);
    }

    private RequestBody setIdKey() {
        RequestBody body = new FormBody.Builder()
                .add("veid", mID)
                .add("api_key", mKey)
                .build();
        return body;
    }

}
