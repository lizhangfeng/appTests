package bwf.androiddemos.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import bwf.androiddemos.R;
import bwf.androiddemos.widget.jazzyviewpager.JazzyViewPager;
import bwf.androiddemos.widget.jazzyviewpager.OutlineContainer;

/**
 * Created by Lizhangfeng on 2016/10/18 0018.
 * Description:
 */
public class JazzyViewPagerAadpter extends PagerAdapter {

    private Context context;

    private JazzyViewPager mJazzy;

    private Integer[] imgs;

    public JazzyViewPagerAadpter(Context context, JazzyViewPager mJazzy) {
        this.context = context;
        this.mJazzy = mJazzy;
    }

    public void setImgs(Integer[] imgs) {
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view = (ImageView) View.inflate(context, R.layout.item_jazzy, null);
        view.setImageResource(imgs[position]);
        mJazzy.setObjectForPosition(view, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object obj) {
        container.removeView(mJazzy.findViewFromObject(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        if (view instanceof OutlineContainer) {
            return ((OutlineContainer) view).getChildAt(0) == obj;
        } else {
            return view == obj;
        }
    }

}
