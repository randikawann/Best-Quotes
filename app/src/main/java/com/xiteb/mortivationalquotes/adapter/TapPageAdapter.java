package com.xiteb.mortivationalquotes.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiteb.mortivationalquotes.fragment.MortivationFragment;
import com.xiteb.mortivationalquotes.fragment.OtherFragment;

public class TapPageAdapter extends FragmentPagerAdapter {

    private FragmentManager mfragmentManager;
    private Context mContext;


    public TapPageAdapter(FragmentManager fm) {
        super(fm);
        mfragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                MortivationFragment mortivationFragment = new MortivationFragment();
                return mortivationFragment;
            case 1:
                OtherFragment otherFragment = new OtherFragment();
                return otherFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle (int position){
        switch (position){
            case 0:
                return "Motivational";
            case 1:
                return "Other";
            default:
                return null;
        }
    }

}
