package bwf.androiddemos.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import bwf.androiddemos.MyApplication;
import bwf.androiddemos.R;

/**
 * Created by Lizhangfeng on 2016/8/17 0017.
 * Description: 进度条显示
 */
public class LoadingView extends PopupWindow {

    private static LoadingView loadingView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public static LoadingView getInstance() {

        if (loadingView == null)
            loadingView = new LoadingView(MyApplication.getAppContext());

        return loadingView;
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        View view = View.inflate(context, R.layout.loading_view, null);
        //popWindow相关设置
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());

        ImageView img_loading = (ImageView) view.findViewById(R.id.img_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) img_loading.getDrawable();
        animationDrawable.start();
    }

    private View showView;

    /**
     * 显示popwindow
     *
     * @param view
     */
    public void showPopWindow(View view) {
        if (!isShowing()) {
            showView = view;
             this.showAtLocation(view, Gravity.TOP, 0, 0);//可以显示在指定view的指定位置
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
