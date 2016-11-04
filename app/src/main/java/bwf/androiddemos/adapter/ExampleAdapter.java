package bwf.androiddemos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwf.androiddemos.R;
import bwf.androiddemos.bean.GroupBean;
import bwf.androiddemos.widget.AnimatedExpandableListView;

/**
 * Created by Lizhangfeng on 2016/10/9 0009.
 * Description:
 */
public class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private Context context;

    private List<GroupBean> groupArray;

    public ExampleAdapter(Context context) {
        this.context = context;
    }

    public void setGroupArray(List groupArray) {
        this.groupArray = groupArray;
    }

    @Override
    public Object getChild(int groupId, int childId) {
        return groupArray.get(groupId).childs.get(childId);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_expand_child, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_child_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(groupArray.get(groupPosition).childs.get(childPosition).childName);
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return groupArray.get(groupPosition).childs.size();
    }

    @Override
    public Object getGroup(int groupId) {
        return groupArray.get(groupId);
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_expand_group, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(groupArray.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    private class ViewHolder {
        TextView tv_name;
    }

}
