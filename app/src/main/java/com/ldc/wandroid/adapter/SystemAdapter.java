package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class SystemAdapter extends FragmentStatePagerAdapter {
    final List<SupportFragment> ps;
    final String[] titles;

    public SystemAdapter(@NonNull FragmentManager fm, int behavior, List<SupportFragment> ps, String[] titles) {
        super(fm, behavior);
        this.ps = new ArrayList<>(ps);
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ps.get(position);
    }

    @Override
    public int getCount() {
        return null == ps ? 0 : ps.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
