package org.test.yotatask.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by Tetawex on 15.02.2018.
 */

public class App extends Application {
    private static App app;

    private PresenterManager presenterManager;
    private Repository repository;

    public static App getApp() {
        return app;
    }

    public PresenterManager getPresenterManager() {
        return presenterManager;
    }

    public void onCreate() {
        super.onCreate();
        app = this;
        presenterManager = new PresenterManager();
        repository = new RestRepository();
    }

    public Repository getRepository() {
        return repository;
    }
}
