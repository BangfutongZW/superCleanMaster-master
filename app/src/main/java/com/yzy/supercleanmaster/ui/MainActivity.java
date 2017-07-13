package com.yzy.supercleanmaster.ui;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.ActivityTack;
import com.yzy.supercleanmaster.base.BaseActivity;
import com.yzy.supercleanmaster.fragment.MainFragment;
import com.yzy.supercleanmaster.fragment.NavigationDrawerFragment;
import com.yzy.supercleanmaster.fragment.RelaxFragment;
import com.yzy.supercleanmaster.fragment.SettingsFragment;
import com.yzy.supercleanmaster.service.UpdateVersionService;
import com.yzy.supercleanmaster.utils.Constants;
import com.yzy.supercleanmaster.utils.HttpTool;
import com.yzy.supercleanmaster.utils.SystemBarTintManager;
import com.yzy.supercleanmaster.utils.T;
import com.yzy.supercleanmaster.utils.UIElementsHelper;
import com.igexin.sdk.PushManager;
import com.yzy.supercleanmaster.utils.UpdateUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import butterknife.InjectView;


public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @InjectView(R.id.container)
    FrameLayout container;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBar ab;
    private CharSequence mTitle;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    NavigationDrawerFragment mNavigationDrawerFragment;
    private View mFragmentContainerView;

    MainFragment mMainFragment;
    RelaxFragment mRelaxFragment;
    private Button btn_home_login;
    public static final long TWO_SECOND = 2 * 1000;
    long preTime;
    UpdateUtil util;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String msgStr=null;

            try {
                msgStr= URLDecoder.decode((String) msg.obj,"utf-8");
                Log.e("version",msgStr);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            };

            if (msgStr!= null&&msgStr.length()>10) {
                msgStr = msgStr.replaceAll("\ufeff", "");
                msgStr = msgStr.replace("\\", "");
                msgStr = msgStr.substring(msgStr.indexOf("{"),msgStr.lastIndexOf("}")+1);
                Log.e("version",msgStr);
                try {
                    JSONObject obj=new JSONObject(msgStr);
                    String code=obj.getString("versionCode");
                    String versionContent=obj.getString("message");
                    String currentCode = util.getAppVersion()+"";
                    final String versionName=obj.getString("versionName");
                    if(!currentCode.equals(code)){
                        new AlertDialog.Builder(MainActivity.this).setTitle("版本更新")
                                .setMessage(versionContent).setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(
                                                MainActivity.this,
                                                UpdateVersionService.class);
                                        intent.putExtra("Key_App_Name",
                                                "hxUpdate");
                                        intent.putExtra("Key_Down_Url",
                                                "http://119.23.37.145:8080/app-release.apk");
                                        //DLOG.log("" + versionUrl);
                                        intent.putExtra("Key_Update_Version",
                                                "励科易控" + versionName
                                                        + "下载中");
                                        startService(intent);
                                    }
                                    /*@Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        // TODO Auto-generated method stub

                                        if (!Constant.MUSTUPDATE.equals(versionCode
                                        )) {
                                        }
                                        // UpdateService.mContext =
                                        // MainFragmentActivity.this;
                                        Intent intent = new Intent(
                                                MainActivity.this,
                                                UpdateVersionService.class);
                                        intent.putExtra("Key_App_Name",
                                                "bftUpdate");
                                        intent.putExtra("Key_Down_Url",
                                                versionUrl);
                                        DLOG.log("" + versionUrl);
                                        intent.putExtra("Key_Update_Version",
                                                "大喇叭" + versionName
                                                        + "下载中");
                                        startService(intent);

                                    }*/
                                }).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // com.getui.demo.DemoPushService 为第三自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), com.getui.service.PushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.getui.service.IntentService.class);
        mFragmentContainerView = (View) findViewById(R.id.navigation_drawer);
        mTitle = getTitle();
        applyKitKatTranslucency();

        inithomelogin(mFragmentContainerView);

        onNavigationDrawerItemSelected(0);
        initDrawer();
        //检查版本更新
        util= new UpdateUtil(getApplicationContext());
        //if ("1".equals(util.getValue("updateflag"))) {
           // util.setValue("updateflag", "0");
       //} else {
            checkAppVersion(util.getAppVersion());
            // utils.setValue(Constant.UPDATEFLAG, "1");
        //}

    }

    private void checkAppVersion(int appVersion) {
        Log.e("appVersion",appVersion+"");
        String posturls = "http://119.23.37.145:8080/S2SH/getversionld.do";
        HttpTool tol = new HttpTool(posturls);
        tol.setHandler(handler);
        new Thread(tol).start();
    }

    private void inithomelogin(View v){
        btn_home_login=(Button) v.findViewById(R.id.btn_home_login);
        if(Constants.ISLOGIN){
            btn_home_login.setText("退出登录");
        }else {
            btn_home_login.setText("登录");
        }
        btn_home_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.ISLOGIN){
                    Constants.ISLOGIN=false;
                    btn_home_login.setText("登录");
                    SharedPreferences sp=getSharedPreferences("saveuser", Context.MODE_WORLD_READABLE);
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }else {
                    startActivity(LoginActivity.class);
                }
            }
        });
    }

    private void initDrawer() {
        // TODO Auto-generated method stub
        ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);// 给home icon的左边加上一个返回的图标
        ab.setHomeButtonEnabled(true);// 需要api level 14 使用home-icon 可点击

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
//        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
//                mDrawerLayout);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mFragmentContainerView)) {
                mDrawerLayout.closeDrawer(mFragmentContainerView);
            } else {
                mDrawerLayout.openDrawer(mFragmentContainerView);
            }
        }
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Apply KitKat specific translucency.
     */
    private void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            // mTintManager.setTintColor(0xF00099CC);

            mTintManager.setTintDrawable(UIElementsHelper
                    .getGeneralActionBarBackground(this));

            getActionBar().setBackgroundDrawable(
                    UIElementsHelper.getGeneralActionBarBackground(this));

        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (position) {
            case 0:
                closeDrawer();
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.container, mMainFragment);
                } else {
                    transaction.show(mMainFragment);
                }
                transaction.commit();

                break;
            case 1:
                /*closeDrawer();
                if (mRelaxFragment == null) {
                    mRelaxFragment = new RelaxFragment();
                    transaction.add(R.id.container, mRelaxFragment);
                } else {
                    transaction.show(mRelaxFragment);
                }
                transaction.commit();*/
                closeDrawer();
                Intent intent0=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent0);
                break;

            // fragment = new SettingsFragment();
            // break;
        }


    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }
        if (mRelaxFragment != null) {
            transaction.hide(mRelaxFragment);
        }

    }


    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 截获后退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();

            // 如果时间间隔大于2秒, 不处理
            if ((currentTime - preTime) > TWO_SECOND) {
                // 显示消息
                T.showShort(mContext, "再按一次退出应用程序");

                // 更新时间
                preTime = currentTime;

                // 截获事件,不再处理
                return true;
            } else {
                ActivityTack.getInstanse().exit(mContext);
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
