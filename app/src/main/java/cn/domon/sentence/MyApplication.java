package cn.domon.sentence;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Domon on 16-11-28.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PgyCrashManager.register(this);
    }
}
