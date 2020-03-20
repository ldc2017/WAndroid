package com.ldc.wandroid.common;

import android.view.View;

public interface ViewItemClick<T> {
    void onItemClick(View v,T dt);
}
