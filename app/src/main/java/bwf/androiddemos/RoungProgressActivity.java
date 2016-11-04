package bwf.androiddemos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import bwf.androiddemos.widget.RoundProgressBar;

public class RoungProgressActivity extends AppCompatActivity implements Handler.Callback{

    //进度条
    private RoundProgressBar roundProgressBar;
    private TextView tv_progress;

    private int progress = 0;//进度条进度

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roung_progress);

        handler = new Handler(this);

        roundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
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
}
