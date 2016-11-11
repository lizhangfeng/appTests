package bwf.androiddemos;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.adapter.ClipeAdapter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.ToastUtil;
import bwf.androiddemos.widget.MyListView;

/**
 * clipToPadding的使用
 */
public class ClipeDemoActivity extends BaseActivity {

    @Bind(R.id.list)
    MyListView list;
    @Bind(R.id.tv_clip)
    TextView tvClip;
    private ClipeAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_clipe_demo;
    }

    @Override
    public void beforeInitView() {
        useDefaultTitleBarColor = false;
    }

    @Override
    public void initView() {
        adapter = new ClipeAdapter(this);
        list.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }


    @OnClick(R.id.tv_clip)
    public void onClick() {
        list.setSelection(0);
        ToastUtil.showToast("回到顶部");
    }
}
