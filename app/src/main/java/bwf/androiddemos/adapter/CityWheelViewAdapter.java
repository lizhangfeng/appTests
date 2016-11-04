package bwf.androiddemos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwf.androiddemos.R;
import bwf.androiddemos.bean.City;
import bwf.androiddemos.widget.wheel.adapter.AbstractWheelTextAdapter;

/**
 * Created by lizhangfeng on 16/4/11.
 * description: 城市滚轮adapter
 */
public class CityWheelViewAdapter extends AbstractWheelTextAdapter {
    private List<City> cityList;
    private int selectPosition;

    public CityWheelViewAdapter(Context context, List<City> cityList) {
        super(context, R.layout.nation_layout, NO_RESOURCE);
        setItemTextResource(R.id.nation_name);
        this.cityList = cityList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
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
        if (cityList == null || cityList.get(index) == null)
            return view;
        TextView tv = (TextView) view.findViewById(R.id.nation_name);
        tv.setText(cityList.get(index).CityName);
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
        return cityList.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return cityList.get(index).CityName;
    }
}
