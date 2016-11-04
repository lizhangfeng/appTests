package bwf.androiddemos;

import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import bwf.androiddemos.adapter.JazzyViewPagerAadpter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.widget.jazzyviewpager.JazzyViewPager;

/**
 * 带动画切换的ViewPager
 */
public class JazzyViewpagerActivity extends BaseActivity {


    @Bind(R.id.jazzy_pager)
    JazzyViewPager mJazzy;

    private Timer timer;
    private int current_pos;

    private Integer[] imgs = {R.mipmap.jazzy_01, R.mipmap.jazzy_02, R.mipmap.jazzy_03, R.mipmap.jazzy_04};

    private long downTime;
    private float x = 1000, y = 0;

    @Override
    public int getContentViewId() {
        return R.layout.activity_jazzy_viewpager;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        JazzyViewPagerAadpter adapter = new JazzyViewPagerAadpter(this, mJazzy);
        adapter.setImgs(imgs);
        mJazzy.setTransitionEffect(JazzyViewPager.TransitionEffect.CubeOut);//设置动画样式
        mJazzy.setPageMargin(10);//两个页面之间的间距
        mJazzy.setFadeEnabled(true);//有淡入淡出效果
//        mJazzy.setOutlineEnabled(true);//有边框
//        mJazzy.setOutlineColor(0xff0000ff);//边框颜色
        mJazzy.setAdapter(adapter);

    }

    @Override
    public void initData() {

        simulatedGesture();

    }

    /**
     * 模拟手滑动viewPager
     */
    private void simulatedGesture() {
        timer = new Timer();
        downTime = SystemClock.currentThreadTimeMillis();
        timer.schedule(new MyTask(), 1000, 3000);

        //ViewPager滑动监听
        mJazzy.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imgs.length - 1) {//当滑动到最后一页的时候把模拟手滑移除掉
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public class MyTask extends TimerTask {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                if (x == 1000) {
                    x -= 100;
                    moveTouch(MotionEvent.ACTION_DOWN);
                } else if (x == 100) {
                    moveTouch(MotionEvent.ACTION_UP);
                    x = 1000;
                } else {
                    x -= 100;
                    moveTouch(MotionEvent.ACTION_MOVE);
                }
            }

        }

    }


    public void moveTouch(final int motionEvent) {
        SystemClock.sleep(50);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MotionEvent move = MotionEvent.obtain(downTime, SystemClock.currentThreadTimeMillis(), motionEvent, x, y, 0);
                mJazzy.dispatchTouchEvent(move);
            }
        });
    }


}
