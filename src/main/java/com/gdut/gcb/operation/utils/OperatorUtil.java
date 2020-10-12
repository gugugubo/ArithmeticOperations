package com.gdut.gcb.operation.utils;


import com.gdut.gcb.operation.bo.SymbolConstant;


/**
 * @Author 古春波
 * @Description 运算操作工具类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class OperatorUtil {

    private final static Character[] operatorTypes = new Character[]{SymbolConstant.PLUS,SymbolConstant.MINUS,SymbolConstant.MULTIPLY,SymbolConstant.DIVIDE};

    /**
     * 随机获取num个运算符的数组
     */
    static Character[] getOperators(int num) {

        Character[] operators = new Character[num];

        for (int i = 0; i < num; i++) {
            //随机获取运算符的类型(0~3 代表4个运算符的类型)
            int operatorTypeIndex = (int)(Math.random()*4);
            Character operatorType = operatorTypes[operatorTypeIndex];
            operators[i] = operatorType;
        }

        return operators;
    }


}
