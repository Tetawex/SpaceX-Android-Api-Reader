package org.test.yotatask.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.test.yotatask.R;
import org.test.yotatask.app.App;

import java.io.IOException;

/**
 * Created by tetawex on 27.02.2018.
 */

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity
        implements BaseView {

    public abstract int getLayoutId();

    public abstract String getPresenterTag();

    public abstract void setupViews();

    private P presenter;

    private boolean firstAttach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupViews();
        attachPresenter();

        if (savedInstanceState == null)
            firstAttach = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        attachPresenter();

        if (firstAttach)
            presenter.onFirstViewAttached();
    }

    protected void onStop() {
        super.onStop();
        detachPresenter();
    }


    protected void attachPresenter() {
        try {
            //Crashes if presenter does not match
            presenter = (P) App.getApp().getPresenterManager().getPresenter(getPresenterTag());
            presenter.onViewAttached(this);
        } catch (ClassCastException cce) {
            cce.printStackTrace();
            showToast(R.string.err_presenter);
        }
    }

    protected void detachPresenter() {
        presenter = null;
    }

    //Removes presenter from the map
    public void dispose() {
        App.getApp().getPresenterManager().disposePresenter(getPresenterTag());
    }

    @Override
    public void showError(Throwable t) {
        if (t instanceof IOException)
            showToast(R.string.err_no_internet);
        showToast(R.string.err_generic);
    }

    public void showToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    public P getPresenter() {
        return presenter;
    }
}
