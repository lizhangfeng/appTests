package bwf.androiddemos;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;

public class MoveViewActivity extends BaseActivity implements View.OnTouchListener {

    @Bind(R.id.move_view)
    Button moveView;
    private float lastX, lastY;
    float offSetX;
    float offSetY;

    @Override
    public int getContentViewId() {
        return R.layout.activity_move_view;
    }

    @Override
    public void beforeInitView() {

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

                offSetX += tempOffSetX;
                offSetY += tempOffSetY;

                lastX = tempX;
                lastY = tempY;

                view.layout((int) (view.getLeft() + offSetX), (int) (view.getTop() + offSetY), (int) (view.getRight() + offSetX), (int) (view.getBottom() + offSetY));

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return false;
    }


    @Override
    public void onClick(View view) {

    }

    @OnClick(R.id.move_view)
    public void onClick() {
        Toast.makeText(MoveViewActivity.this, "onClick", Toast.LENGTH_SHORT).show();
    }
}
