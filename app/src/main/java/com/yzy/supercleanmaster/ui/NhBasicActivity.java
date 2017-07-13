package com.yzy.supercleanmaster.ui;


import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseActivity;
import com.yzy.supercleanmaster.fragment.DayNhSpentFragment;
import com.yzy.supercleanmaster.fragment.MonthNhSpentFragment;
import com.yzy.supercleanmaster.fragment.YearNhSpentFragment;

import butterknife.InjectView;

public class NhBasicActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager fragmentManager;
    @InjectView(R.id.rg_tab)
    public RadioGroup radioGroup;
    @InjectView(R.id.rb_day)
    public RadioButton radioButton;

    private DayNhSpentFragment dayNhSpentFragment;
    private MonthNhSpentFragment monthNhSpentFragment;
    private YearNhSpentFragment yearNhSpentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nh_basic);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //fragmentManager = getFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        radioButton.performClick();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.rb_day:
                hideFragments(transaction);
                if(dayNhSpentFragment == null){
                    dayNhSpentFragment = new DayNhSpentFragment();
                    transaction.add(R.id.framelayout_content, dayNhSpentFragment);
                    transaction.show(dayNhSpentFragment);
                }else{
                    transaction.show(dayNhSpentFragment);
                }
                break;
            case R.id.rb_month:
                hideFragments(transaction);
                if(monthNhSpentFragment == null){
                    monthNhSpentFragment = new MonthNhSpentFragment();
                    transaction.add(R.id.framelayout_content, monthNhSpentFragment);
                    transaction.show(monthNhSpentFragment);
                }else{
                    transaction.show(monthNhSpentFragment);
                }
                break;
            case R.id.rb_year:
                hideFragments(transaction);
                if(yearNhSpentFragment == null){
                    yearNhSpentFragment = new YearNhSpentFragment();
                    transaction.add(R.id.framelayout_content, yearNhSpentFragment);
                    transaction.show(yearNhSpentFragment);
                }else{
                    transaction.show(yearNhSpentFragment);
                }
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (dayNhSpentFragment != null) {
            transaction.hide(dayNhSpentFragment);
        }
        if (monthNhSpentFragment != null) {
            transaction.hide(monthNhSpentFragment);
        }
        if (yearNhSpentFragment != null) {
            transaction.hide(yearNhSpentFragment);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
