package com.android.lojaonline.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lojaonline.R;
import com.android.lojaonline.adapter.TabsAdapter;
import com.android.lojaonline.basededados.BaseDeDados;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class ProdutosTabFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int countTabs;
    public SQLiteDatabase db;
    public BaseDeDados banco;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        countTabs = 0;
        banco = new BaseDeDados(getContext());

        View view = inflater.inflate(R.layout.fragment_tab_produtos, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.removeAllTabs();
        addTabs();
        //tabLayout.setOnTabSelectedListener(this);
        tabLayout.addOnTabSelectedListener(this);

        mViewPager = view.findViewById(R.id.viewPagerProdutos);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager(), countTabs));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void addTabs(){

        db = banco.getWritableDatabase();

        Cursor c = db.query("Categoria", null, null, null, null, null, "_id");

        //c.moveToFirst();
        while (c.moveToNext()){

            tabLayout.addTab(tabLayout.newTab().setText(c.getString(c.getColumnIndex("NomeCateg"))));

            countTabs = countTabs +1;

        }

    }


}
