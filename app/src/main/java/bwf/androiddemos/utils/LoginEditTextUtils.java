package bwf.androiddemos.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by lizhangfeng on 16/5/13.
 * description:
 */
public class LoginEditTextUtils {

    /**
     * 点击隐藏和现实输入法
     * @param context
     * @param et_password
     */
    public static void passwordEdit(final Context context, final EditText et_password) {
        et_password.setOnTouchListener(new View.OnTouchListener() {

            boolean isPassword = true;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //getCompoundDrawables() 可以获取一个长度为4的数组，
                //存放drawableLeft，Right，Top，Bottom四个图片资源对象
                //index=2 表示的是 drawableRight 图片资源对象
                Drawable drawable = et_password.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                //drawable.getIntrinsicWidth() 获取drawable资源图片呈现的宽度
                if (event.getX() > et_password.getWidth() - et_password.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    if (isPassword) {
                        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        isPassword = false;
//                        DrawableUtils.drawableRight(context, et_password, R.mipmap.open_psd);
                    } else {
                        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        isPassword = true;
//                        DrawableUtils.drawableRight(context, et_password, R.mipmap.close_psd);
                    }
                }
                return false;
            }
        });
    }

}
