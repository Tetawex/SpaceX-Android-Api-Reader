package org.test.yotatask.app.launchfeed;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public abstract class SpinnerInteractionListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    boolean userSelect = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (userSelect) {
            onItemSelectedByUser(parent, view, pos, id);
            userSelect = false;
        }
    }

    public abstract void onItemSelectedByUser(AdapterView<?> parent, View view, int pos, long id);

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}