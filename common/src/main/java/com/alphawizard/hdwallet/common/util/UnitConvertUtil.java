package com.alphawizard.hdwallet.common.util;

import android.content.Context;

public class UnitConvertUtil {
    /**
     * @param context Context
     * @param dpValue （DisplayMetrics类中属性scaledDensity）
     * @return 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param context Context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context Context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * @param context Context
     *                //     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
