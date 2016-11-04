package bwf.androiddemos.utils;

import java.util.Random;

/**
 * 随机生成32位
 */
public class StringRandom {

    public static Object getStringRandom;

    // 生成随机数字和字母,
    public static String getStringRandom() {
        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < 32; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母
                // int temp = random.nextInt(2) % 2 == 0 ? 65 : 97 ;
                val += (char) (random.nextInt(25) + 65);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
