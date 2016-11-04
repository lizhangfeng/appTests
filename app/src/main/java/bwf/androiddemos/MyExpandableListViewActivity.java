package bwf.androiddemos;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.adapter.ExampleAdapter;
import bwf.androiddemos.adapter.ExpandableAdapter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.bean.ChildBean;
import bwf.androiddemos.bean.GroupBean;
import bwf.androiddemos.widget.AnimatedExpandableListView;

/**
 * 可展开listView演示
 */
public class MyExpandableListViewActivity extends BaseActivity {

    @Bind(R.id.expand_listview)
    ExpandableListView expandedListview;

    @Bind(R.id.custom_expand_listview)
    AnimatedExpandableListView customExpandListview;

    private ExpandableAdapter adapter;
    private ExampleAdapter customAdapter;

    private List<GroupBean> groupArray;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_expandable_list_view;
    }

    @Override
    public void beforeInitView() {
        groupArray = new ArrayList<GroupBean>();

        for (int i = 0; i < 10; i++) {
            GroupBean groupBean = new GroupBean();
            groupBean.groupName = "第" + (i + 1) + "行";
            groupBean.childs = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ChildBean childBean = new ChildBean("child " + (j + 1));
                groupBean.childs.add(childBean);
            }
            groupArray.add(groupBean);
        }

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initDefaultExpand();
        initCustomExpand();
    }

    private void initDefaultExpand() {
        adapter = new ExpandableAdapter(this);
        adapter.setGroupArray(groupArray);
        expandedListview.setAdapter(adapter);

        //group点击监听
        expandedListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupId, long l) {

                ImageView img_arrow = (ImageView) view.findViewById(R.id.img_arrow);
                if (!expandableListView.isGroupExpanded(groupId)) {
                    img_arrow.setImageResource(R.mipmap.arrow_down);
                } else {
                    img_arrow.setImageResource(R.mipmap.arrow_right);
                }
                return false;
            }
        });
    }

    private void initCustomExpand() {
        customAdapter = new ExampleAdapter(this);
        customAdapter.setGroupArray(groupArray);
        customExpandListview.setAdapter(customAdapter);
        customExpandListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (customExpandListview.isGroupExpanded(groupPosition)) {
                    customExpandListview.collapseGroupWithAnimation(groupPosition);
                } else {
                    customExpandListview.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });
    }


    @OnClick({R.id.btn_default, R.id.btn_custom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_default:
                expandedListview.setVisibility(View.VISIBLE);
                customExpandListview.setVisibility(View.GONE);
                break;
            case R.id.btn_custom:
                expandedListview.setVisibility(View.GONE);
                customExpandListview.setVisibility(View.VISIBLE);
                break;
        }
    }
}
