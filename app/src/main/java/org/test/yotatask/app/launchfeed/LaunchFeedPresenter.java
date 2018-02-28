package org.test.yotatask.app.launchfeed;

import org.test.yotatask.app.App;
import org.test.yotatask.app.Repository;
import org.test.yotatask.base.BasePresenter;
import org.test.yotatask.base.ViewState;

import java.util.List;

/**
 * Created by Tetawex on 15.02.2018.
 */

public class LaunchFeedPresenter extends BasePresenter<LaunchFeedView> {
    private LaunchYearType launchYearType = LaunchYearType.Y2017;

    @Override
    public ViewState<LaunchFeedView> createViewState() {
        return new LaunchFeedViewState();
    }

    @Override
    protected void onFirstViewAttached() {
        loadData();
    }

    private void loadData() {
        getViewRelay().showProgressbar();
        App.getApp().getRepository().getLaunchesData(launchYearType,
                new Repository.LaunchesCallback() {
                    @Override
                    public void onReceived(List<LaunchData> launchesList) {
                        getViewRelay().setFeed(launchesList);
                        getViewRelay().hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        getViewRelay().showError(t);
                        getViewRelay().hideProgressBar();
                    }
                });
    }

    public void onYearTypeChanged(LaunchYearType type) {
        launchYearType = type;
        loadData();
    }
}
