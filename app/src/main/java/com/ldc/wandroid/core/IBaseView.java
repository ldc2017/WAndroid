package com.ldc.wandroid.core;

public interface IBaseView {
    void show_toast(final String message);

    void show_loading(final String message);

    void hide_loading();
}
