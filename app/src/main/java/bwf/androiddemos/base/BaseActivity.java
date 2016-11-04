package bwf.androiddemos.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import bwf.androiddemos.R;
import bwf.androiddemos.tools.AppManager;
import bwf.androiddemos.tools.SystemStatusManager;


/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description: Activity基本
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected boolean useDefaultTitleBarColor;//状态栏颜色是否使用默认颜色

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            //被杀死之后重新创建
            getSaveData(savedInstanceState);
        }

        setContentView(getContentViewId());

        //注解bind当前activity
        ButterKnife.bind(this);

        //如果存在actionBar，就隐藏(也可以通过主题AppTheme.NoActionBar隐藏)
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        //将新建的activity添加到stack里进行管理
        AppManager.getInstance().addActivity(this);

        //设置状态栏透明
        setTranslucentStatus();

        useDefaultTitleBarColor = true;

        beforeInitView();

        if (useDefaultTitleBarColor) {
            //改变状态栏颜色;注意：此处一旦设置 android:fitsSystemWindows="false"将无效
            setTitleBarColor(R.color.colorPrimary);
        }

        initView();
        initData();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //此处activity被杀死可以保存数据
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * 获取activity被杀死时候保存的数据
     *
     * @param savedInstanceState
     */
    public void getSaveData(Bundle savedInstanceState) {

    }


    public abstract int getContentViewId();//放layoutId

    public abstract void beforeInitView();//初始化View之前做的事

    public abstract void initView();//初始化View

    public abstract void initData();//初始化数据

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 设置状态栏背景颜色
     */
    public void setTitleBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);
    }

    /**
     * 不用强制转换的findviewbyid
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    public void setOnClick(Object... objects) {

        for (Object object : objects) {
            if (object instanceof View) {
                ((View) object).setOnClickListener(this);
            }

            if (object instanceof Integer) {
                findViewById((Integer) object).setOnClickListener(this);
            }

        }
    }


    /**
     * 本段代码用来处理如果输入法还显示的话就消失掉输入键盘
     */
    protected void dismissSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    protected void showKeyboard(View view) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        //注：unregister官方是放入到onStop方法中，真实开发一般是放入onDestroy即当被销毁才取消注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
