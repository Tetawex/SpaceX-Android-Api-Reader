package org.test.yotatask.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tetawex on 15.02.2018.
 */

public abstract class ViewState<V extends BaseView> implements BaseView {
    private V view;
    private Queue<Command<V>> commandQueue;

    public ViewState() {
        commandQueue = new LinkedList<>();
    }

    public void restoreState(V view) {
        for (Command<V> command : commandQueue) {
            command.run(view);
        }
    }

    public void detachView() {
        view = null;
    }

    public void attachView(V view) {
        this.view = view;
    }

    //For cached commands (default behavior)
    public void submitCommand(Command<V> command) {
        saveCommand(command);
        runCommand(command);
    }

    //For 1-time commands (like error toast)
    public void runCommand(Command<V> command) {
        if (view != null) {
            command.run(view);
        }
    }

    //For cached commands that only run when state is restored
    public void saveCommand(Command<V> command) {
        commandQueue.add(command);
    }

    //That's how an implemented ViewState would look like
    @Override
    public void showError(final Throwable t) {
        //Not allowed to use libs due to task limitations
        runCommand(new Command<V>() {
            @Override
            public void run(V view) {
                view.showError(t);
            }
        });
    }
    @Override
    public void dispose() {
        submitCommand(new Command<V>() {
            @Override
            public void run(V view) {
                view.dispose();
            }
        });
    }
    //--------

    public interface Command<V> {
        void run(V view);
    }
}
