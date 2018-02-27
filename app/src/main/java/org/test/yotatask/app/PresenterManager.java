package org.test.yotatask.app;

import org.test.yotatask.base.BasePresenter;
import org.test.yotatask.app.launchfeed.LaunchFeedPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tetawex on 26.02.2018.
 */

public class PresenterManager {
    public static final String LAUNCH_FEED_TAG = "launch_feed";

    private Map<String, BasePresenter> presenterMap;

    public PresenterManager() {
        presenterMap = new HashMap<>(1);
    }

    public BasePresenter getPresenter(String tag) {
        if (!presenterMap.containsKey(tag)) {
            instantiatePresenterByTag(tag);
        }
        return presenterMap.get(tag);
    }

    public void disposePresenter(String tag) {
        presenterMap.remove(tag);
    }

    private void instantiatePresenterByTag(String tag) {
        switch (tag) {
            case LAUNCH_FEED_TAG:
                presenterMap.put(tag, new LaunchFeedPresenter());
                break;
        }
    }
}
