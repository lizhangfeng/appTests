package bwf.androiddemos;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import bwf.androiddemos.listener.ShakeListener;

/**
 * 微信摇一摇
 */
public class WXShakeActivity extends AppCompatActivity {

    //动画时间
    private final static int ANIM_DUITION = 2000;
    private ShakeListener mShakeListener = null;
    private Vibrator mVibrator;

    private ImageView img_circle;
    private ImageView img_hand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acativity_wx_shake);

        //drawerSet ();//设置  drawer监听    切换 按钮的方向
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        img_circle = (ImageView) findViewById(R.id.img_circle);
        img_hand = (ImageView) findViewById(R.id.img_hand);

        mShakeListener = new ShakeListener(WXShakeActivity.this);
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                startAnim();  //开始 摇一摇手掌动画
                mShakeListener.stop();

                startVibrato(R.raw.audio_1); //开始 震动
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startVibrato(R.raw.aw); //开始 震动
                        Toast.makeText(WXShakeActivity.this,
                                "抱歉，暂时没有找到\n在同一时刻摇一摇的人。\n再试一次吧！", Toast.LENGTH_SHORT).show();
                        mVibrator.cancel();
                        mShakeListener.start();
                    }
                }, ANIM_DUITION);
            }
        });
    }

    //定义摇一摇动画动画
    public void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_circle);
        img_circle.startAnimation(animation);


//        ObjectAnimator animator = ObjectAnimator.ofFloat(img_hand, "rotation", 0, 45,45,0);
//        animator.setDuration(1000);
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ObjectAnimator.INFINITE);
//        animator.start();

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_hand_01);
        animation2.setFillAfter(true);
        img_hand.startAnimation(animation2);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation animation3 = AnimationUtils.loadAnimation(WXShakeActivity.this, R.anim.rotate_hand_02);
                img_hand.startAnimation(animation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private MediaPlayer player;

    public void startVibrato(int resId) {
        player = MediaPlayer.create(this, resId);
        player.setLooping(false);
        player.start();


        //定义震动
        mVibrator.vibrate(new long[]{500, 200, 500, 200}, -1); //第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1俄日从pattern的指定下标开始重复
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }
}