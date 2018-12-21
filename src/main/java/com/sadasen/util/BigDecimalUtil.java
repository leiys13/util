package com.sadasen.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class BigDecimalUtil {
	
	/**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }
	
	/**
     * 提供精确的除法运算
     *
     * @param v1 除数
     * @param v2 被除数
     * @return 两个参数的积
     */
    public static BigDecimal div(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, 2, RoundingMode.HALF_UP);
    }

}
