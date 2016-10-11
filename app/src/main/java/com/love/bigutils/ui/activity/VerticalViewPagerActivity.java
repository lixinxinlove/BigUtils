package com.love.bigutils.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.love.bigutils.R;
import com.love.bigutils.ui.fragment.BaseFragment;
import com.love.bigutils.ui.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kaelaela.verticalviewpager.VerticalViewPager;

public class VerticalViewPagerActivity extends BaseActivity {

    @BindView(R.id.vertical_view_pager)
    VerticalViewPager verticalViewPager;

    List<BaseFragment> fragmentLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_view_pager);
        ButterKnife.bind(this);
        fragmentLists.add(new MyFragment());
        fragmentLists.add(new MyFragment());

        verticalViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));


    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentLists.get(position);
        }

        @Override
        public int getCount() {
            return fragmentLists.size();
        }
    }


}
