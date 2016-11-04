package bwf.androiddemos.utils.raote3d;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
public class Rotate3dAnimation extends Animation{//动画的主要接口，其中主要定义了动画的 �?��属�?比如�?��时间，持续时间，是否重复播放�?
   //�?��角度  
   private final float mFromDegrees;  
   //结束角度  
   private final float mToDegrees;  
   //中心�? 
   private final float mCenterX;  
   private final float mCenterY;  
   private final float mDepthZ;  
   //是否�?��扭曲  
   private final boolean mReverse;  
   //摄像�? 
   private Camera mCamera;  
   
   public Rotate3dAnimation(float fromDegrees, float toDegrees,  
           float centerX, float centerY, float depthZ, boolean reverse) {  
       mFromDegrees = fromDegrees;  
       mToDegrees = toDegrees;  
       mCenterX = centerX;  
       mCenterY = centerY;  
       mDepthZ = depthZ;  
       mReverse = reverse;  
   }  
   
   @Override
   public void initialize(int width, int height, int parentWidth,
   		int parentHeight) {
   	super.initialize(width, height, parentWidth, parentHeight);
   	mCamera = new Camera();  
   }
   
   @Override
   protected void applyTransformation(
   		float interpolatedTime,//其中第一个参数就是�?过getTransformation函数传�?的差 指点,
   		//然后我们根据这个差�?通过线�?差�?算法计算出一个中间角度degrees
   		Transformation t) {
   	 super.applyTransformation(interpolatedTime, t);
   	 final float fromDegrees = mFromDegrees;  
   	 	//生成中间角度  
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);  
 
        final float centerX = mCenterX;  
        final float centerY = mCenterY;  
        final Camera camera = mCamera; //Camera类是用来实现绕Y轴旋转后透视投影�?
 
        final Matrix matrix = t.getMatrix(); //取得当前的矩�?
 
        camera.save();  
        if (mReverse) {  
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);  //camera.translate来对矩阵进行平移变换操作
        } else {  
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));  
        }  
        camera.rotateY(degrees);  //camera.rotateY进行�?�?
        //取得变换后的矩阵  
        camera.getMatrix(matrix);  
        camera.restore();  
 
        matrix.preTranslate(-centerX, -centerY);  
        matrix.postTranslate(centerX, centerY);  
   }
}