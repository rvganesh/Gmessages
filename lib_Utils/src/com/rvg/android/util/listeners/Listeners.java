package com.rvg.android.util.listeners;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.rvg.android.util.handler.HandlerUtil;

/**
 * Utility class facilitating the implementation of the 'observer' design pattern.
 */
public class Listeners<T> implements Iterable<T> {
    private Set<T> mListeners = new HashSet<T>(3);

    public static interface Dispatcher<T> {
        void dispatch(T listener);
    }

    public static <T> Listeners<T> newInstance() {
        return new Listeners<T>();
    }

    public boolean add(T listener) {
        int prevSize = mListeners.size();
        boolean res = mListeners.add(listener);
        if (res) onListenerCountChanged(prevSize, mListeners.size());
        return res;
    }

    public boolean remove(T listener) {
        int prevSize = mListeners.size();
        boolean res = mListeners.remove(listener);
        if (res) onListenerCountChanged(prevSize, mListeners.size());
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return mListeners.iterator();
    }

    private void onListenerCountChanged(int prevSize, int newSize) {
        if (prevSize == 0 && newSize == 1) {
            onFirstListener();
        } else if (newSize == 0) {
            onNoMoreListeners();
        }
    }

    protected void onFirstListener() {}

    protected void onNoMoreListeners() {}

    /**
     * Dispatching will be done in the main/ui thread.
     */
    public void dispatch(final Dispatcher<T> dispatcher) {
        HandlerUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (T listener : mListeners) {
                    dispatcher.dispatch(listener);
                }
            }
        });
    }

    /**
     * Returns the current number of listeners.
     */
    public int size() {
        return mListeners.size();
    }
}
