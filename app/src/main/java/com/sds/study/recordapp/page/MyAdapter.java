package com.sds.study.recordapp.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by wbhlkc0 on 2016-11-17.
 */

/*
* ViewPager 는 껍데기에 불과하므로, 뷰페이저에 보여질 프래그먼트가 총 몇개고, 어떤 페이즈를 보여줘야 할지를 관리해주는 어댑터를 이용해야한다.
* */
public class MyAdapter extends FragmentStatePagerAdapter{

    /*앱에서 사용할 Fragment를 준비해 놓자.!!*/
    Fragment[] fragments = new Fragment[3];

    public MyAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new FragmentA();
        fragments[1] = new FragmentB();
        fragments[2] = new FragmentC();

    }

    // 총 몇 페이지 인즈를 의미
    public int getCount() {
        return fragments.length;
    }

    //어느 것을 보여줄 것인지를 결정
    public Fragment getItem(int position) {//position은fragment의 번쨰를 의미한다.
        return fragments[position];
    }


}
