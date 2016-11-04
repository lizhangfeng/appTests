package bwf.androiddemos;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.raote3d.DisplayNextView;
import bwf.androiddemos.utils.raote3d.Rotate3dAnimation;

/**
 * 3D旋转动画
 */
public class Roate3DActivity extends BaseActivity implements Handler.Callback{

    @Bind(R.id.img_roate)
    ImageView img_roate;

    private AtomicInteger positive_negative = new AtomicInteger(0);// 确定当前是正反面：0是正-1是反

    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.activity_roate3_d;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 正反效果
     *
     * @param position
     * @param start
     * @param end
     */
    private void applyRotation(int position, float start, float end) {
        final float centerX = img_roate.getWidth() / 2.0f;
        final float centerY = img_roate.getHeight() / 2.0f;
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);// 变true改为false 不影响
        rotation.setDuration(500);
        rotation.setFillAfter(true);// 变true改为false 不影响
//        rotation.setInterpolator(new AccelerateDecelerateInterpolator());// 变速
		rotation.setInterpolator(new LinearInterpolator());// 匀速
        rotation.setAnimationListener(new DisplayNextView(position, false, img_roate));// 监听动画
        img_roate.startAnimation(rotation);
        if (end == 90) {
            handler.sendEmptyMessageDelayed(2, 1500);
        } else if (end == -90) {
            handler.sendEmptyMessageDelayed(1, 8000);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                applyRotation(positive_negative.get(), 0, 90);
                positive_negative.set(-1);
                break;
            case 2:
                applyRotation(positive_negative.get(), 0, -90);
                positive_negative.set(0);
                break;

            default:
                break;
        }
        return false;
    }
}
