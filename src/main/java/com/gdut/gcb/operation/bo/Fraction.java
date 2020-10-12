package com.gdut.gcb.operation.bo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author 古春波
 * @Description 分数类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
public class Fraction {
    /**
     * 整数部分
     */
    private int inter;

    /**
     * 分子
     */
    private int numerator;

    /**
     * 分母
     */
    private int denominator;


    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
}
