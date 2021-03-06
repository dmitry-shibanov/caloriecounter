package com.example.caloriecounter.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriecounter.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class PersonStatistics extends Fragment {

    private ViewPager viewPager;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private Fragment fragment1;
    private Fragment fragment2;

    private PersonStatistics(Fragment fragment1, Fragment fragment2){
        this.fragment1 = fragment1;
        this.fragment2 = fragment2;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Fragment newInstance(Fragment fragment1, Fragment fragment2){
        Fragment fragment = new PersonStatistics(fragment1, fragment2);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_statistics,container,false);
        setHasOptionsMenu(true);
        viewPager = (ViewPager)view.findViewById(R.id.view_page);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new GrapghFragment1(), "График 1");
        adapter.addFragment(new Graph2(), "График 2");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    public class TabAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        TabAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
