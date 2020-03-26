package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class WeChatNumberAdapter extends FragmentPagerAdapter {
    private ArrayList<SupportFragment> fragments;
    private ArrayList<String> tabs;

    public WeChatNumberAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    public void setNewData(ArrayList<SupportFragment> fragments, ArrayList<String> tabs) {
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
