package com.TabExample3;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eduadministrative.mytabs.R;

public class MainActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new Fragment_Contact(),"Contact");
        pagerAdapter.addFragment(new Fragment_Call(),"Call");
        pagerAdapter.addFragment(new Fragment_Fav(),"Favourite");

        viewpager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}