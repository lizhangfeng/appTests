package bwf.androiddemos;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;

/**
 * 抛物线动画
 */
public class ParabolaAnimationActivity extends BaseActivity {

    @Bind(R.id.tv_start_anim)
    TextView tv_start_anim;//动画的起始位置
    @Bind(R.id.tv_end_anim)
    TextView tv_end_anim;//动画结束的位置
    private ViewGroup anim_mask_layout;//动画层

    //此处控制偏移量,可以手动设置一个偏移量
    private int offsetX;
    private int offsetY;

    @Override
    public int getContentViewId() {
        return R.layout.activity_parabola_animation;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.btn_start_anim)
    public void onClick() {
        playReadingAnimation();
    }

    /**
     * 抛物线动画(此动画可以封装)
     */
    @TargetApi(11)
    public void playReadingAnimation() {
        final ImageView moveingIV = (ImageView) getLayoutInflater().inflate(R.layout.animator_image, null);
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(moveingIV);//把动画图片添加到动画层
        //获取view起始位置相对窗口的坐标
        int[] start_location = new int[2];
        tv_start_anim.getLocationInWindow(start_location);
        final int start_x = start_location[0];
        final int start_y = start_location[1];
        //设置动画图片的起始位置
        addViewToAnimLayout(moveingIV, start_location);

        //获取终点坐标
        int[] location = new int[2];
        tv_end_anim.getLocationInWindow(location);
        final int end_x = location[0];
        final int end_y = location[1];

        //抛物线动画，原理：两个位移动画，一个横向匀速移动，一个纵向变速移动，两个动画同时执行，就有了抛物线的效果。
        ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(moveingIV, "translationX", 0, end_x - start_x - offsetX);
        translateAnimationX.setDuration(800);
        translateAnimationX.setInterpolator(new LinearInterpolator());//匀速动画
        ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(moveingIV, "translationY", 0, end_y - start_y + offsetY);
        translateAnimationY.setDuration(800);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());//变速动画(加速动画)
        //到终点后的缩小动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(moveingIV, "scaleX", 1, 0);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(moveingIV, "scaleY", 1, 0);
        scaleY.setDuration(200);
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                anim_mask_layout.removeView(moveingIV); //动画结束后移除动画图片
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        //设置动画播放顺序
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translateAnimationX).with(translateAnimationY);//同时执行translateAnimationX，translateAnimationY动画
        animatorSet.play(scaleX).with(scaleY).after(translateAnimationX);//在translateAnimationX执行完之后同时执行scaleX和scaleY（缩放动画）
        animatorSet.start();
    }

    /**
     * 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private void addViewToAnimLayout(final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
    }

}
