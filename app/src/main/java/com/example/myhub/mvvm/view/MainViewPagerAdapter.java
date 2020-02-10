package com.example.myhub.mvvm.view;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Time:2020/2/10 15:01
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
        super(fm, behavior);
        this.list = list;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
