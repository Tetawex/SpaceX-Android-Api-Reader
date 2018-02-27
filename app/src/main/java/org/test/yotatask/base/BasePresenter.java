package org.test.yotatask.base;

/**
 * Created by Tetawex on 15.02.2018.
 */

public abstract class BasePresenter<V extends BaseView> {
    //Used for state management
    private ViewState<V> viewState;
    //Used to relay commands to view
    private V viewRelay;

    public abstract ViewState<V> createViewState();

    public BasePresenter() {
        ViewState<V> state = createViewState();
        viewState = state;
        viewRelay = (V)state;
    }

    protected abstract void onFirstViewAttached();

    public void onViewAttached(V view) {
        viewState.attachView(view);
        viewState.restoreState(view);
    }

    public void onViewDetached() {
        viewState.detachView();
    }

    public ViewState<V> getViewState() {
        return viewState;
    }

    public V getViewRelay() {
        return viewRelay;
    }
}
