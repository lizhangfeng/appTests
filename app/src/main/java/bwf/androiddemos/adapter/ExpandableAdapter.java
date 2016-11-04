package bwf.androiddemos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import bwf.androiddemos.R;
import bwf.androiddemos.bean.GroupBean;

/**
 * Created by Lizhangfeng on 2016/10/9 0009.
 * Description:
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<GroupBean> groupArray;

    private Context context;

    public ExpandableAdapter(Context context) {
        this.context = context;
    }

    public void setGroupArray(List groupArray) {
        this.groupArray = groupArray;
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int childId) {
        return groupArray.get(childId).childs.size();
    }

    @Override
    public Object getGroup(int groupId) {
        return groupArray.get(groupId);
    }

    @Override
    public Object getChild(int groupId, int childId) {
        return groupArray.get(groupId).childs.get(childId);
    }

    @Override
    public long getGroupId(int groupId) {
        return groupId;
    }

    @Override
    public long getChildId(int groupId, int childId) {
        return childId;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup parent) {
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
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup parent) {

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
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private class ViewHolder {
        TextView tv_name;
    }

}
