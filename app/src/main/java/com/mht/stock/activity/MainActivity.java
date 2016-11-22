package com.mht.stock.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.databinding.ActivityMainBinding;
import com.mht.stock.fragment.AllStockFragment;
import com.mht.stock.fragment.BonusFragment;
import com.mht.stock.fragment.MainFragment;
import com.mht.stock.util.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    public static final int REQUEST_ADD_INVEST = 1;

    private ActivityMainBinding mBinding;

    private Map<String, Fragment> mFragmentMap = new HashMap<>();

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);
        setupDrawerContent(mBinding.navView);

        replaceFragment(R.id.layoutMain, getTypeFragment(MainFragment.TAG));

        int i = 4/0;
    }


    /************************** 菜单 start *********************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                AddInvestActivity.toActivity(this, REQUEST_ADD_INVEST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /************************** 菜单 end *********************************/




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(REQUEST_ADD_INVEST == requestCode) {
            }
        }
    }







    private Fragment getTypeFragment(String type) {
        if(mFragmentMap.containsKey(type)) {
            return mFragmentMap.get(type);
        } else {
            Fragment fragment = null;
            if(MainFragment.TAG.equals(type)) {
                fragment = MainFragment.newFragment();
            } else if(AllStockFragment.TAG.equals(type)) {
                fragment = AllStockFragment.newFragment();
            } else if(BonusFragment.TAG.equals(type)) {
                fragment = BonusFragment.newFragment();
            }
            mFragmentMap.put(type, fragment);
            return fragment;
        }
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navCapitalAnalyze:
                            replaceFragment(R.id.layoutMain, getTypeFragment(MainFragment.TAG));
                            mBinding.drawerLayout.closeDrawers();
                            break;
                        case R.id.navDeal:
                            replaceFragment(R.id.layoutMain, getTypeFragment(AllStockFragment.TAG));
                            mBinding.drawerLayout.closeDrawers();
                            break;

                        case R.id.nav_link1:
                            WebActivity.toActivity(mContext, new Intent(), getString(R.string.url_ths), "同花顺");
                            break;
                        case R.id.nav_link2:
                            WebActivity.toActivity(mContext, new Intent(), getString(R.string.url_annual_report), "公司年报");
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
    }





    private long mExitTime = 0;
    @Override
    public void onBackPressed() {
        long millis = System.currentTimeMillis();
        if(millis - mExitTime > 2000) {
            mExitTime = millis;
            CommonUtils.showToast(mContext, R.string.lable_exit);
        } else {
            super.onBackPressed();
        }
    }
}