package bwf.androiddemos.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * Created by lizhangfeng on 16/4/29.
 * description: SpannableStringBuilder字体
 */
public class SpannableStringUtil {

    /**
     * 根据format位置颜色组成新的字体
     *
     * @param str
     * @param formatString
     * @param color
     * @return
     */
    public static SpannableStringBuilder getSpannableString(String str, String formatString, int color) {

        String text = String.format(str, formatString);
        int index[] = new int[1];
        index[0] = text.indexOf(formatString);
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#6CA5FF")), index[0], index[0] + formatString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        return style;

    }

}
