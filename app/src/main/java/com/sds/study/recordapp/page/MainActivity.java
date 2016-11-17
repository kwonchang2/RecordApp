package com.sds.study.recordapp.page;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sds.study.recordapp.R;

public class MainActivity extends AppCompatActivity {
    String TAG;

    ViewPager viewPager;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

    }

    public void btnClick(View view){
        switch(view.getId()){

            case R.id.bt_a: viewPager.setCurrentItem(0);
                Log.d(TAG,"view.getId()" + view.getId());
                Log.d(TAG,"R.id.bt_a" + R.id.bt_a);
            case R.id.bt_b: viewPager.setCurrentItem(1);
                Log.d(TAG,"view.getId()" + view.getId());
                Log.d(TAG,"R.id.bt_b" + R.id.bt_b);
            case R.id.bt_c: viewPager.setCurrentItem(2);
                Log.d(TAG,"view.getId()" + view.getId());
                Log.d(TAG,"R.id.bt_c" + R.id.bt_c);
        }
    }
}
