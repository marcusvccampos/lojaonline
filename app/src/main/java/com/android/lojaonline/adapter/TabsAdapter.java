package com.android.lojaonline.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.lojaonline.fragment.ProdutoFragment;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    int tabsCount;


    public TabsAdapter(Context context, FragmentManager fm, int tabsCount) {
        super(fm);

        this.tabsCount = tabsCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle args = new Bundle();

        args.putString("categoria", String.valueOf(position + 1));

        Fragment f = new ProdutoFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public int getCount() {
        return this.tabsCount;
    }
}
