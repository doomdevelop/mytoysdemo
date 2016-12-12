package org.kozlowski.mytoysdemo.ui.base;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by and on 12.12.16.
 */

public abstract class Presenter<T extends Presenter.View> {

    protected T view;
    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

    public interface View {
        void finish();
    }
}
