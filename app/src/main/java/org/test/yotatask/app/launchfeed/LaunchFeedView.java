package org.test.yotatask.app.launchfeed;

import org.test.yotatask.base.BaseView;

import java.util.List;

/**
 * Created by Tetawex on 15.02.2018.
 */

public interface LaunchFeedView extends BaseView {
    void appendFeed(List<LaunchData> list);

    void setFeed(List<LaunchData> list);
}
