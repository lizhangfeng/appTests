package bwf.androiddemos;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.DisplayUtil;

public class MoveView2Activity extends BaseActivity implements View.OnTouchListener {

    @Bind(R.id.move_view)
    Button moveView;
    private float lastX, lastY;
    private int screen_width;

    @Override
    public int getContentViewId() {
        return R.layout.activity_move_view;
    }

    @Override
    public void beforeInitView() {
        screen_width = DisplayUtil.getDensity_Width(this);
    }

    @Override
    public void initView() {
        moveView.setOnTouchListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = e.getY();
                lastX = e.getX();

                break;

            case MotionEvent.ACTION_MOVE:
                float tempX = e.getX();
                float tempY = e.getY();

                float tempOffSetX = tempX - lastX;
                float tempOffSetY = tempY - lastY;

                view.layout((int) (view.getLeft() + tempOffSetX), (int) (view.getTop() + tempOffSetY), (int) (view.getRight() + tempOffSetX), (int) (view.getBottom() + tempOffSetY));

                break;
            case MotionEvent.ACTION_UP:
                float curX = e.getRawX();
                float curY = e.getY();
                float moveY = curY - lastY;
                if (curX >= screen_width / 2) {
                    view.layout(screen_width - view.getWidth(), (int) (view.getTop() + moveY), screen_width, (int) (view.getBottom() + moveY));
                } else {
                    view.layout(0, (int) (view.getTop() + moveY), view.getWidth(), (int) (view.getBottom() + moveY));
                }
                break;
        }

        return false;
    }

    @Override
    public void onClick(View view) {

    }

}
