package bwf.androiddemos;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.Bind;
import bwf.androiddemos.adapter.ClipeAdapter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.DisplayUtil;
import bwf.androiddemos.utils.LogUtils;
import bwf.androiddemos.widget.CustomScollView;
import bwf.androiddemos.widget.MyListView;

/**
 * 滑动标题栏颜色渐变效果
 */
public class TitleFadeActivity extends BaseActivity implements MyListView.OnScrollChangedListener {

    @Bind(R.id.list_fade)
    MyListView listFade;
    @Bind(R.id.tv_fade_title)
    TextView tv_fade_title;
    @Bind(R.id.scrollview)
    CustomScollView scrollview;

    private ClipeAdapter adapter;

    private View headerView;

    private int titleHeight;

    @Override
    public int getContentViewId() {
        return R.layout.activity_title_fade;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        adapter = new ClipeAdapter(this);
        headerView = View.inflate(this, R.layout.headerview_fade, null);
        listFade.addHeaderView(headerView);
        listFade.setAdapter(adapter);
        int h = DisplayUtil.getStatusBarHeight(this);
        tv_fade_title.setPadding(0, h + 50, 0, 0);

        //获取tv_fade_title的高度并添加滑动监听
        tv_fade_title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleHeight = headerView.getHeight();
                scrollview.setOnScrollChangedListener(TitleFadeActivity.this);
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        LogUtils.e("tag", "---------------------------y: " + y + "titleHeight: " + titleHeight);
        if (y <= 0) {
            tv_fade_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= titleHeight) {
            float scale = (float) y / titleHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            tv_fade_title.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {
            tv_fade_title.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }

}
