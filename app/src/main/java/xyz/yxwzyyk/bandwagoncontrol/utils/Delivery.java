package xyz.yxwzyyk.bandwagoncontrol.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Handler事件分发器
 * Created by yyk on 12/12/15.
 */
public class Delivery {
    private final Executor mExecutor;

    public Delivery() {
        mExecutor = command -> new Handler(Looper.getMainLooper()).post(command);
    }

    public void MainThreadRun(Runnable r) {
        mExecutor.execute(r);
    }
}
