package org.test.yotatask.app.launchfeed;

import org.test.yotatask.base.ViewState;

import java.util.List;

/**
 * Created by Tetawex on 15.02.2018.
 */

public class LaunchFeedViewState extends ViewState<LaunchFeedView> implements LaunchFeedView {

    @Override
    public void appendFeed(final List<LaunchData> list) {
        submitCommand(new Command<LaunchFeedView>() {
            @Override
            public void run(LaunchFeedView view) {
                view.appendFeed(list);
            }
        });
    }

    @Override
    public void setFeed(final List<LaunchData> list) {
        submitCommand(new Command<LaunchFeedView>() {
            @Override
            public void run(LaunchFeedView view) {
                view.setFeed(list);
            }
        });
    }

    @Override
    public void setYearSelectorValue(final LaunchYearType type) {
        saveCommand(new Command<LaunchFeedView>() {
            @Override
            public void run(LaunchFeedView view) {
                view.setYearSelectorValue(type);
            }
        });
    }

    @Override
    public void showProgressbar() {
        submitCommand(new Command<LaunchFeedView>() {
            @Override
            public void run(LaunchFeedView view) {
                view.showProgressbar();
            }
        });
    }

    @Override
    public void hideProgressBar() {
        submitCommand(new Command<LaunchFeedView>() {
            @Override
            public void run(LaunchFeedView view) {
                view.hideProgressBar();
            }
        });
    }
}
