package bwf.androiddemos.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import bwf.androiddemos.MainActivity;
import bwf.androiddemos.R;
import bwf.androiddemos.base.BaseFragment;
import bwf.androiddemos.utils.ToastUtil;
import bwf.androiddemos.widget.CustomVideoView;


/**
 * Created by lizhangfeng on 16/5/16.
 * <p>
 * description: 引导页
 */
public class WelcomeFragment extends BaseFragment  {

    private CustomVideoView videoView;

    private boolean isPause;

    @Override
    protected int getResource() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        videoView = findViewByIdNoCast(R.id.videoview);
    }


    @Override
    protected void initData() {

        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.intro));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //videoView.start();   /* 循环播放 */
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });


        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                mediaPlayer.reset();
                ToastUtil.showToast("播放视频出错"+extra);
                return true;//如果设置true就可以防止他弹出错误的提示框！
            }
        });

    }


    /**
     * 播放视频
     */
    public void startVideo() {

        if (videoView != null) {
            videoView.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPause) {
            startVideo();
            isPause = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopVideo();
    }

    /**
     * 停止播放
     */
    public void stopVideo() {
        isPause = true;
        if (videoView != null)
            videoView.pause();
    }

    @Override
    public void onDestroy() {
        if (videoView != null)
            videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }

}
