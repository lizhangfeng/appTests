package bwf.androiddemos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwf.androiddemos.R;
import bwf.androiddemos.bean.Province;
import bwf.androiddemos.widget.wheel.adapter.AbstractWheelTextAdapter;

/**
 * Created by lizhangfeng on 16/4/11.
 * description: 省滚轮adapter
 */
public class ProvinceWheelViewAdapter extends AbstractWheelTextAdapter {
    private List<Province> provinceList;
    private int selectPosition;

    public ProvinceWheelViewAdapter(Context context, List<Province> provinceList) {
        super(context, R.layout.nation_layout, NO_RESOURCE);
        setItemTextResource(R.id.nation_name);
        this.provinceList = provinceList;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        this.notifyDataChangedEvent();
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {

        View view = super.getItem(index, convertView, parent);

        if (provinceList == null || provinceList.get(index) == null)
            return view;

        TextView tv = (TextView) view.findViewById(R.id.nation_name);
        tv.setText(provinceList.get(index).ProName);
        if (index == selectPosition) {
            tv.setTextColor(Color.BLACK);
        } else if (index - selectPosition == 1 || index - selectPosition == -1) {
            tv.setTextColor(Color.parseColor("#B4B4B4"));
        } else {
            tv.setTextColor(Color.parseColor("#D8D8D8"));
        }
        return view;
    }

    @Override
    public int getItemsCount() {
        return provinceList.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return provinceList.get(index).ProName;
    }
}
