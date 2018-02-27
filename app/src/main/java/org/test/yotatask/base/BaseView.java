package org.test.yotatask.base;

/**
 * Created by tetawex on 26.02.2018.
 */
public interface BaseView {
    void showError(Throwable t);
    //Signals that view's state should be dropped
    void dispose();

    void showProgressbar();
    void hideProgressBar();
}
