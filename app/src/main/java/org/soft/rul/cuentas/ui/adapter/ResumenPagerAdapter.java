package org.soft.rul.cuentas.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.soft.rul.cuentas.ui.fragment.ResumenSectionFragment;

/**
 * Created by rgonzalez on 18/09/2014.
 */
public class ResumenPagerAdapter extends FragmentPagerAdapter {

    public ResumenPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ResumenSectionFragment();
        Bundle args = new Bundle();
        args.putInt(ResumenSectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Section " + (position + 1);
    }
}