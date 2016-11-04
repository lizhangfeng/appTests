package bwf.androiddemos.utils.raote3d;

import android.view.View;
import android.view.animation.Animation;

public class DisplayNextView implements Animation.AnimationListener{

	private final int mPosition;
	private final boolean mend;
	private View tv_title;

	public DisplayNextView(int position, boolean end,View view) {
		mPosition = position;
		mend = end;
		this.tv_title = view;
	}

	public void onAnimationStart(Animation animation) {
		
	}

	public void onAnimationEnd(Animation animation) {
		if (!mend) {
			tv_title.post(new SwapViews(mPosition,tv_title));// 线程转
		}
	}

	public void onAnimationRepeat(Animation animation) {
		
	}
	
}
