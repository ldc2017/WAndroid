package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ProjectsAdapter extends FragmentStatePagerAdapter {
    private final List<SupportFragment> dts;
    private final List<String> tabs;

    public ProjectsAdapter(@NonNull FragmentManager fm, int behavior, List<SupportFragment> ds, List<String> tabs) {
        super(fm, behavior);
        this.dts = new ArrayList<>(ds);
        this.tabs = new ArrayList<>(tabs);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return dts.get(position);
    }

    @Override
    public int getCount() {
        return null == dts ? 0 : dts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }

}
