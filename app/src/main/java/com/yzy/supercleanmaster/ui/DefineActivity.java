package com.yzy.supercleanmaster.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.MyCouponFragmentAdapter;

/**
 * 自定义tab导航
 */
public class DefineActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    // ViewPager适配器
    private MyCouponFragmentAdapter adapter = null;
    private int tabWidth;
    private DisplayMetrics dm = new DisplayMetrics();
    private ImageView img_tabLine;
    // 已消费
    private TextView tv_yet_spent;
    // 未消费
    private TextView tv_not_spending;
    private TextView tv_nh_spent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // 初始化控件
        initView();
        //初始化数据
        initData();
        // 绑定券页面
        bindPageData();
        // 注册监听
        registerListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_coupon);
        img_tabLine = (ImageView) findViewById(R.id.img_tabLine);
        tv_not_spending = (TextView) findViewById(R.id.tv_not_spending);
        tv_yet_spent = (TextView) findViewById(R.id.tv_yet_spent);
        tv_nh_spent =(TextView)findViewById(R.id.tv_nh_spent);
        tv_not_spending.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
    }
    /**
     * 初始化数据
     */
    private void initData() {
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        tabWidth = dm.widthPixels / 3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tab_line);
        // /Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, tabWidth, 8);
        // 设置tab的宽度和高度
        Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, tabWidth, 8, true);
        img_tabLine.setImageBitmap(bitmap2);
    }
    /**
     * 绑定我的券页面及数据
     */
    private void bindPageData() {
        adapter = new MyCouponFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
    }
    /**
     * 注册事件监听
     */
    private void registerListener() {
        tv_not_spending.setOnClickListener(this);
        tv_yet_spent.setOnClickListener(this);
        tv_nh_spent.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new PageListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 未消费
            case R.id.tv_not_spending: {
                resetTab();
                tv_not_spending.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                mViewPager.setCurrentItem(0);
            }
            break;
            // 已消费
            case R.id.tv_yet_spent: {
                resetTab();
                tv_yet_spent.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                mViewPager.setCurrentItem(1);
            }
            case R.id.tv_nh_spent: {
                resetTab();
                tv_nh_spent.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                mViewPager.setCurrentItem(2);
            }
            break;
            default:
                break;
        }

    }
    /**
     * viewPager滑动监听
     */
    public class PageListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // new一个矩阵
            Matrix matrix = new Matrix();
            // 设置激活条的最终位置
            switch (arg0) {
                case 0:
                    // 使用set直接设置到那个位置
                    matrix.setTranslate(0, 0);
                    break;
                case 1:
                    matrix.setTranslate(tabWidth, 0);
                    break;
                case 2:
                    matrix.setTranslate(tabWidth*2, 0);
                    break;
            }
            // 在滑动的过程中，计算出激活条应该要滑动的距离
            float t = arg1*tabWidth;
            // 使用post追加数值
            matrix.postTranslate(t, 0);
            img_tabLine.setImageMatrix(matrix);
        }

        @Override
        public void onPageSelected(int position) {
            resetTab();
            switch (position) {
                case 0: {
                    //resetTab();
                    tv_not_spending.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                    mViewPager.setCurrentItem(0);
                }
                break;
                case 1: {
                    //resetTab();
                    tv_yet_spent.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                    mViewPager.setCurrentItem(1);
                }
                case 2: {
                    //resetTab();
                    tv_nh_spent.setTextColor(getResources().getColor(R.color.textcolor_mine_red));
                    mViewPager.setCurrentItem(2);
                }
                break;
                default:
                    break;
            }
        }
    }
    /**
     * 文字全部重置为默认状态
     */
    private void resetTab() {
        tv_not_spending.setTextColor(getResources().getColor(R.color.details));
        tv_yet_spent.setTextColor(getResources().getColor(R.color.details));
        tv_nh_spent.setTextColor(getResources().getColor(R.color.details));
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
