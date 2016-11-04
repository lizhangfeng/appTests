package bwf.androiddemos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bwf.androiddemos.R;

/**
 * Created by Lizhangfeng on 2016/9/7 0007.
 * Description:
 */
public class ClipeAdapter extends BaseAdapter {

    private Context context;

    public ClipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHodler viewHodler;

        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.item_clipe, null);
            viewHodler.tv_test = (TextView) convertView.findViewById(R.id.tv_test);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHodler {
        TextView tv_test;
    }

}
