package com.gdut.gcb.operation.bo;

import java.io.File;


/**
 * @Author 古春波
 * @Description 符号常量
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class SymbolConstant {
    public static final Character PLUS = '+';
    public static final Character MINUS = '-';
    public static final Character MULTIPLY = '*';
    public static final Character DIVIDE  = '÷';
    public static final Character EQUALS  = '=';
    /**
     * "F:\\file";10.3修改生成文件地址
     */
    public static final String PRINT_FILE_URL = System.getProperty("user.dir")+ File.separator+"question_bank";

}
