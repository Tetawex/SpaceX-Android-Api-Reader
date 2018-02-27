package org.test.yotatask.app;

import org.test.yotatask.app.launchfeed.LaunchData;
import org.test.yotatask.app.launchfeed.LaunchYearType;

import java.util.List;

/**
 * Created by Tetawex on 15.02.2018.
 */

public interface Repository {
    interface Callback {
        void onError(Throwable t);
    }

    interface LaunchesCallback extends Callback {
        void onReceived(List<LaunchData> launchesList);
    }

    void getLaunchesData(LaunchYearType launchYearType, LaunchesCallback callback);
}
