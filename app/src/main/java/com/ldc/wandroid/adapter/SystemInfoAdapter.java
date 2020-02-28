package com.ldc.wandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class SystemInfoAdapter extends FragmentStatePagerAdapter {
    private List<SupportFragment> dts;

    public SystemInfoAdapter(@NonNull FragmentManager fm, int behavior, List<SupportFragment> ds) {
        super(fm, behavior);
        this.dts = ds;
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
}
