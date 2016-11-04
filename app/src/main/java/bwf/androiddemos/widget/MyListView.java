package bwf.androiddemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Lizhangfeng on 2016/9/18 0018.
 * Description:
 */
public class MyListView extends ListView {

    private OnScrollChangedListener onScrollChangedListener;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onScrollChangedListener != null)
            onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

}
