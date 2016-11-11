package bwf.androiddemos;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.widget.RoundProgressBar;

public class RoungProgressActivity extends BaseActivity implements Handler.Callback {

    //进度条
    @Bind(R.id.roundProgressBar)
    RoundProgressBar roundProgressBar;
    @Bind(R.id.tv_progress)
    TextView tv_progress;

    private int progress = 0;//进度条进度

    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.activity_roung_progress;
    }

    @Override
    public void beforeInitView() {
        handler = new Handler(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setProgressAnim(80);
    }

    /**
     * 设置进度条进度
     *
     * @param dprogress
     */
    public void setProgressAnim(final int dprogress) {
        new Thread() {
            @Override
            public void run() {

                while (progress < dprogress) {
                    progress += 1;
                    handler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        }.start();
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                roundProgressBar.setProgress(progress);
                tv_progress.setText(progress + "%");
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
