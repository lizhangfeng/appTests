package bwf.androiddemos;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.event.MessageEvent;
import bwf.androiddemos.utils.DisplayUtil;
import bwf.androiddemos.utils.IntentAndroidUtils;
import bwf.androiddemos.utils.QQUtils;
import bwf.androiddemos.utils.ToastUtil;

import static android.R.attr.id;
import static bwf.androiddemos.R.id.img_user;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.btn_move_view)
    Button btnMoveView;
    @Bind(R.id.btn_move_view2)
    Button btnMoveView2;
    @Bind(R.id.btn_clipe)
    Button btnClipe;
    @Bind(R.id.btn_title_fade)
    Button btnTitleFade;
    @Bind(R.id.btn_wx_shake)
    Button btnWxShake;
    @Bind(R.id.btn_datepicker)
    Button btnDatepicker;
    @Bind(R.id.btn_round_progressbar)
    Button btnRoundProgressbar;
    @Bind(R.id.btn_qq_conversation)
    Button btnQqConversation;
    @Bind(R.id.btn_expandablelistview)
    Button btnExpandablelistview;
    @Bind(R.id.btn_take_photo)
    Button btnTakePhoto;
    @Bind(R.id.img_main_bg)
    ImageView img_main_bg;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.ll_title)
    LinearLayout ll_title;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {
        useDefaultTitleBarColor = false;
        //注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        //手动设置宽高
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayUtil.getDensity_Width(this), DisplayUtil.getDensity_Height(this));
        scrollView.setLayoutParams(layoutParams);
        //手动设置宽高
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DisplayUtil.getDensity_Width(this), RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll_title.setLayoutParams(layoutParams2);
    }

    @Override
    public void initData() {
        animBg(img_main_bg);
        //设置NavigationView的item点击事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick({R.id.btn_move_view, R.id.btn_move_view2, R.id.btn_qq_conversation, R.id.btn_datepicker, R.id.btn_round_progressbar,
            R.id.btn_clipe, R.id.btn_title_fade, R.id.btn_wx_shake, R.id.btn_expandablelistview, R.id.btn_take_photo, R.id.btn_parabola
            , R.id.btn_percent_layout, R.id.btn_eventBus, R.id.btn_jazzyViewpager, R.id.btn_3d_roate, R.id.btn_rxjava_and_retrofit, R.id.img_user
            , R.id.btn_retrofit, R.id.btn_rxjava, R.id.btn_achartengine})
    public void onClick(View view) {
        switch (view.getId()) {
            case img_user://左侧栏
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.btn_move_view://view跟随手势移动
                startActivity(new Intent(this, MoveViewActivity.class));
                break;
            case R.id.btn_move_view2://view跟随手势移动并且靠边悬浮
                startActivity(new Intent(this, MoveView2Activity.class));
                break;
            case R.id.btn_clipe://clipToPadding的使用
                startActivity(new Intent(this, ClipeDemoActivity.class));
                break;
            case R.id.btn_title_fade://滑动标题淡入淡出效果
                startActivity(new Intent(this, TitleFadeActivity.class));
                break;
            case R.id.btn_wx_shake://微信摇一摇
                startActivity(new Intent(this, WXShakeActivity.class));
                break;
            case R.id.btn_datepicker://选择时间和城市的滚轮效果
                startActivity(new Intent(this, DatePickerActivity.class));
                break;
            case R.id.btn_round_progressbar://圆形进度条
                startActivity(new Intent(this, RoungProgressActivity.class));
                break;
            case R.id.btn_qq_conversation://QQ指定临时会话
                QQUtils.converstaionToQQ(this, "9322804234");
                break;
            case R.id.btn_expandablelistview://带展开收缩动画的ExpandableListView
                startActivity(new Intent(this, MyExpandableListViewActivity.class));
                break;
            case R.id.btn_take_photo://跳转照相机拍照
                startActivity(new Intent(this, TakePhotoActivity.class));
                break;
            case R.id.btn_parabola://view抛物线动画
                startActivity(new Intent(this, ParabolaAnimationActivity.class));
                break;
            case R.id.btn_percent_layout://百分比布局
                startActivity(new Intent(this, PercentLayoutActivity.class));
                break;
            case R.id.btn_eventBus://EventBus的使用
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            case R.id.btn_jazzyViewpager://jazzyViewPager的使用
                startActivity(new Intent(this, JazzyViewpagerActivity.class));
                break;
            case R.id.btn_3d_roate://3D旋转动画
                startActivity(new Intent(this, Roate3DActivity.class));
                break;
            case R.id.btn_rxjava://Rxjava的使用
                startActivity(new Intent(this, RxJavaDemoActivity.class));
                break;
            case R.id.btn_retrofit://Retrofit的使用
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.btn_rxjava_and_retrofit://RxJava+Retrofit的使用
                startActivity(new Intent(this, RxJavaAndRetrofitActivity.class));
                break;
            case R.id.btn_achartengine://统计图的绘制
                startActivity(new Intent(this, ChartDemo.class));
                break;

        }
    }

    private ObjectAnimator animator;

    /**
     * 背景图动画
     */
    public void animBg(View view) {
        animator = ObjectAnimator.ofFloat(view, "translationX", 0, DisplayUtil.getDensity_Width(this) - getResources().getDimension(R.dimen.bg_main), 0);
        animator.setDuration(10000);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ObjectAnimator.INFINITE);
        animator.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        if (event != null) {
            ToastUtil.showToast("MainActivity收到事件：" + event.name);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStop() {
        super.onStop();
        if (animator != null)
            animator.pause();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        if (animator != null)
            animator.resume();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            IntentAndroidUtils.intentToCamera(this);
        } else if (id == R.id.nav_gallery) {
            IntentAndroidUtils.intentToGallery(this);
        } else if (id == R.id.nav_slideshow) {
            IntentAndroidUtils.intentToVideo(this);
        } else if (id == R.id.nav_manage) {
            IntentAndroidUtils.intentToSettings(this);
        } else if (id == R.id.nav_share) {
            IntentAndroidUtils.intentToShare(this, "text/plain", "分享", "我正在浏览这个,觉得真不错,推荐给你哦~ 地址:\" + \"http://www.google.com.hk/", "share");
        } else if (id == R.id.nav_send) {
            IntentAndroidUtils.intentToShare(this, "mailto:xxx@abc.com", "有急事找你", "text/plain", "选择邮箱");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
