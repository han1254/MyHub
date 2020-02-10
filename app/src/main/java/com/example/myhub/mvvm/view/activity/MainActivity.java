package com.example.myhub.mvvm.view.activity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import com.example.base.nettype.type.NetworkDetailType;
import com.example.base.view.activity.MvvmNetworkActivity;
import com.example.myhub.R;
import com.example.myhub.databinding.ActivityMainBinding;
import com.example.myhub.mvvm.view.MainViewPagerAdapter;
import com.example.myhub.mvvm.view.fragment.GlobalNewsFragment;
import com.example.myhub.mvvm.view.fragment.MessageFragment;
import com.example.myhub.mvvm.view.fragment.MineFragment;
import com.example.myhub.mvvm.view.fragment.SearchFragment;
import com.example.myhub.mvvm.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends MvvmNetworkActivity<ActivityMainBinding, MainViewModel> {

    private List<Fragment> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Class<? extends ViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void initParameters() {

    }

    @Override
    protected void initDataAndView() {

        GlobalNewsFragment globalNewsFragment = new GlobalNewsFragment();
        SearchFragment searchFragment = new SearchFragment();
        MessageFragment messageFragment = new MessageFragment();
        MineFragment mineFragment = new MineFragment();
        list.add(globalNewsFragment);
        list.add(searchFragment);
        list.add(messageFragment);
        list.add(mineFragment);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, list);

        mViewDataBinding.mainViewpager.setAdapter(adapter);
        NavigationController controller = mViewDataBinding.mainTab.material()
                .addItem(R.drawable.ic_earth, "环球")
                .addItem(R.drawable.ic_searchhub, "搜索")
                .addItem(R.drawable.ic_message, "消息")
                .addItem(R.drawable.ic_mine, "我的")
                .setDefaultColor(0x89FFFFFF)
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR | MaterialMode.HIDE_TEXT)
                .build();

        controller.setupWithViewPager(mViewDataBinding.mainViewpager);
    }

    @Override
    protected void onNetWorkChange(NetworkDetailType networkDetailType) {
        super.onNetWorkChange(networkDetailType);

    }
}
