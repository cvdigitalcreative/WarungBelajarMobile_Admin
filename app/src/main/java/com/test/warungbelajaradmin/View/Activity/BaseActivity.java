package com.test.warungbelajaradmin.View.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.AbsensiKursus;
import com.test.warungbelajaradmin.View.Fragment.BaseFragment;
import com.test.warungbelajaradmin.View.Fragment.BuatGrupKursus;
import com.test.warungbelajaradmin.View.Fragment.KonfirmasiPembayaran;

public class BaseActivity extends AppCompatActivity {
    private TabLayout mTabLayout;

    private int[] mTabsIcons = {
            R.drawable.ic_action_home,
            R.drawable.ic_action_payment,
            R.drawable.ic_action_group,
            R.drawable.ic_action_absensi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mTabLayout != null) {
            mTabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(pagerAdapter.getTabView(i));
            }

            mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public final int PAGE_COUNT = 4;

        private final String[] mTabsTitle = {"Home", "Confirmed Payment", "Create Group","Attandence"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getTabView(int position) {

            View view = LayoutInflater.from(BaseActivity.this).inflate(R.layout.custom_tab, null);

            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:

                    return new KonfirmasiPembayaran();

                case 1:

                    return new KonfirmasiPembayaran();

                case 2:

                    return new BaseFragment();

                case 3:

                    return new AbsensiKursus();
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsTitle[position];
        }
    }
}
