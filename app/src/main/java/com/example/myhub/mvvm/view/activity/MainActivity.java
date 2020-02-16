package com.example.myhub.mvvm.view.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.base.nettype.type.NetworkDetailType;
import com.example.base.view.activity.MvvmNetworkActivity;
import com.example.myhub.AppData;
import com.example.myhub.R;
import com.example.myhub.data.AppDataBase;
import com.example.myhub.databinding.ActivityMainBinding;
import com.example.myhub.mvvm.view.FullSheetDialogFragment;
import com.example.myhub.mvvm.view.adapter.MainViewPagerAdapter;
import com.example.myhub.mvvm.view.fragment.GlobalNewsFragment;
import com.example.myhub.mvvm.view.fragment.MessageFragment;
import com.example.myhub.mvvm.view.fragment.MineFragment;
import com.example.myhub.mvvm.view.fragment.SearchFragment;
import com.example.myhub.mvvm.viewmodel.MainViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends MvvmNetworkActivity<ActivityMainBinding, MainViewModel> {

    private List<Fragment> list = new ArrayList<>();
    private ImageView mAvatar;
    private TextView mTxt;

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

        mAvatar = mViewDataBinding.navView.getHeaderView(0).findViewById(R.id.item_slide_avatar);
        mTxt = mViewDataBinding.navView.getHeaderView(0).findViewById(R.id.item_slide_name);


        GlobalNewsFragment globalNewsFragment = new GlobalNewsFragment();
        SearchFragment searchFragment = new SearchFragment();
        MessageFragment messageFragment = new MessageFragment();
        MineFragment mineFragment = new MineFragment();
        list.add(globalNewsFragment);
        list.add(searchFragment);
        list.add(mineFragment);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, list);

        mViewDataBinding.mainViewpager.setAdapter(adapter);
        NavigationController controller = mViewDataBinding.mainTab.material()
                .addItem(R.drawable.ic_earth, "环球", ContextCompat.getColor(this, R.color.colorVerdure))
                .addItem(R.drawable.ic_searchhub, "搜索",  ContextCompat.getColor(this, R.color.colorVerdure))
                .addItem(R.drawable.ic_mine, "我的",  ContextCompat.getColor(this, R.color.colorVerdure))
                .setDefaultColor(0x89FFFFFF)
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR | MaterialMode.HIDE_TEXT)
                .build();

        controller.setupWithViewPager(mViewDataBinding.mainViewpager);

        Glide.with(mAvatar.getContext())
                .load(AppData.getInstance().getAppCurrentUser().getAvatarUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mAvatar);

        mTxt.setText(AppData.getInstance().getAppCurrentUser().getLogin());

        mViewDataBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.main_about:
                        new FullSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
                        break;
                    case R.id.main_exit:
                        AppDataBase.getInstance(getApplicationContext()).getUserDao()
                                .deleteUser(AppData.getInstance().getAppCurrentUser());
                        AppData.getInstance().setAppCurrentUser(null);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onNetWorkChange(NetworkDetailType networkDetailType) {
        super.onNetWorkChange(networkDetailType);
    }



}
