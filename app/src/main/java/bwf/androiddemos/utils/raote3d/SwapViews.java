package bwf.androiddemos.utils.raote3d;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class SwapViews implements Runnable {

	private final int mPosition;
	private View tv_title; 
	
	public SwapViews(int position,View view) {
		mPosition = position;
		this.tv_title = view;
	}

	public void run() {
		final float centerX = tv_title.getWidth() / 2.0f;
		final float centerY = tv_title.getHeight() / 2.0f;
		Rotate3dAnimation rotation;

		if (mPosition > -1) {
			rotation = new Rotate3dAnimation(-90, 0, centerX, centerY,
					310.0f, false);
		} else {
			rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
					310.0f, false);
		}
		rotation.setDuration(500);
		rotation.setFillAfter(false);// true改成false 如果为true，整个布局调个了
		rotation.setInterpolator(new DecelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(mPosition, true,tv_title));
		tv_title.startAnimation(rotation);
	}
	
}
