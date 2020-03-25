package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class ProjectTabAdapter extends FragmentPagerAdapter {
    private final ArrayList<String> tabs;
    private final ArrayList<SupportFragment> fragments;

    public ProjectTabAdapter(@NonNull FragmentManager fm, int behavior, final ArrayList<SupportFragment> fragments, final ArrayList<String> tabs) {
        super(fm, behavior);
        this.fragments = new ArrayList<>(fragments);
        this.tabs = new ArrayList<>(tabs);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
