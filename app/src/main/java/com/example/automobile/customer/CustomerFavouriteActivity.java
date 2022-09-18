package com.example.automobile.customer;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.automobile.R;
import com.example.automobile.adapter.FavVPAdapter;
import com.google.android.material.tabs.TabLayout;

public class CustomerFavouriteActivity extends AppCompatActivity {

    TabLayout M_fav_tab_id;
    ViewPager m_fav_view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_favourite);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        M_fav_tab_id = findViewById(R.id.M_fav_tab_id);
        m_fav_view_pager = findViewById(R.id.m_fav_view_pager);
        FavVPAdapter favVPAdapter = new FavVPAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //favVPAdapter.addFragment(new MechanicFavouriteFragment(), "MECHANIC");
        //favVPAdapter.addFragment(new WorkshopFavouriteFragment(), "WORKSHOP");
        m_fav_view_pager.setAdapter(favVPAdapter);
    }
}