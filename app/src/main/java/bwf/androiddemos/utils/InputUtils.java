package bwf.androiddemos.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/4/18.
 */
public class InputUtils {


    /**
     * 打开输入法
     * */
    public static void openImput(Context context,EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);

    }


    /**
     * 关闭输入法
     * */
    public static void closeImput(Context context,EditText editText){
//        editText.setText("");
        editText.clearFocus();
        //close InputMethodManager
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
