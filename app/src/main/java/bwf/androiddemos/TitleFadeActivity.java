package bwf.androiddemos;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import bwf.androiddemos.adapter.ClipeAdapter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.DisplayUtil;
import bwf.androiddemos.widget.MyListView;

public class TitleFadeActivity extends BaseActivity {

    @Bind(R.id.list_fade)
    MyListView listFade;
    @Bind(R.id.ll_title)
    LinearLayout llTitle;

    private ClipeAdapter adapter;

    private View headerView;

    private int titleHeight;

    @Override
    public int getContentViewId() {
        return R.layout.activity_title_fade;
    }

    @Override
    public void beforeInitView() {
        titleHeight = DisplayUtil.dip2px(TitleFadeActivity.this, 50);
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
        llTitle.setPadding(0, h + 50, 0, 0);
        listFade.setOnScrollChangedListener(new MyListView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {

                int hh = headerView.getHeight();
                if (hh - y <= titleHeight) {
                    llTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }else {
                    llTitle.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

}
