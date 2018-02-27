package org.test.yotatask.app.launchfeed;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.test.yotatask.base.BaseActivity;
import org.test.yotatask.app.PresenterManager;
import org.test.yotatask.R;

import java.util.List;

public class LaunchFeedActivity
        extends BaseActivity<LaunchFeedPresenter>
        implements LaunchFeedView {

    private final String KEY_RECYCLER_STATE = "recycler_state";

    private RecyclerView recyclerView;
    private LaunchFeedRecyclerAdapter launchFeedRecyclerAdapter;
    private Spinner spinner;
    private ProgressBar progressbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String getPresenterTag() {
        return PresenterManager.LAUNCH_FEED_TAG;
    }

    @Override
    public void setupViews() {
        launchFeedRecyclerAdapter = new LaunchFeedRecyclerAdapter(this);

        recyclerView = findViewById(R.id.rv_launches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(launchFeedRecyclerAdapter);
        launchFeedRecyclerAdapter.setClickListener(new LaunchFeedRecyclerAdapter.LaunchClickListener() {
            @Override
            public void onClick(String actionUrl) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionUrl));
                //Check if user has a web-browser
                PackageManager packageManager = getBaseContext().getPackageManager();
                ComponentName componentName = browserIntent.resolveActivity(
                        getBaseContext().getPackageManager());
                if (componentName != null)
                    startActivity(browserIntent);
                else
                    Toast.makeText(getBaseContext(),
                            R.string.err_no_browser, Toast.LENGTH_SHORT)
                            .show();
            }
        });

        progressbar = findViewById(R.id.progressbar);

        setupSpinner();
    }

    private void setupSpinner() {
        spinner = findViewById(R.id.spinner_years);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.years_array, R.layout.spinneritem_year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                launchFeedRecyclerAdapter.clearImageLoader();
                getPresenter().onYearTypeChanged(spinnerPosToYearType(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void appendFeed(List<LaunchData> list) {
        launchFeedRecyclerAdapter.appendDataWithNotify(list);
    }

    @Override
    public void setFeed(List<LaunchData> list) {
        launchFeedRecyclerAdapter.replaceDataWithNotify(list);
    }

    @Override
    public void setYearSelectorValue(LaunchYearType type) {
        spinner.setSelection(yearTypeToSpinnerPos(type));
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(state);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        savedInstanceState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    public void onDestroy() {
        launchFeedRecyclerAdapter.clearImageLoader();
        super.onDestroy();
    }

    private LaunchYearType spinnerPosToYearType(int pos) {
        switch (pos) {
            case 1:
                return LaunchYearType.ALL;
            case 0:
                return LaunchYearType.Y2017;
        }
        return LaunchYearType.Y2017;
    }

    private int yearTypeToSpinnerPos(LaunchYearType type) {
        switch (type) {
            case ALL:
                return 1;
            case Y2017:
                return 0;
        }
        return 0;
    }

    @Override
    public void showProgressbar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }
}
