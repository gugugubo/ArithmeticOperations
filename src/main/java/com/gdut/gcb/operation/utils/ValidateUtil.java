package com.gdut.gcb.operation.utils;


import com.gdut.gcb.operation.bo.SymbolConstant;

import java.io.File;



/**
 * @Author 古春波
 * @Description 检验类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class ValidateUtil {

    /**
     * 参数检查 只有两种合法输入
     * @param command 输入
     * @return 返回定好顺序的命令数组
     */
    public static String[] checkParams(String command) {
        String[] s = command.split(" ");
        //其实只能输入长度为4的指令
        if (s.length == 4){
            String first = s[0];String third = s[2];String second = s[1];String fourth = s[3];
            //1. -n i -r j / -r i -n j
            if(("-n".equals(first)&& "-r".equals(third)) || ("-e".equals(first)&& "-a".equals(third))){
                return s;
            }
            if(("-r".equals(first)&& "-n".equals(third)) || ("-a".equals(first)&& "-e".equals(third))){
                s[0] = third;
                s[1] = fourth;
                s[2] = first;
                s[3] = second;
                return s;
            }
            return null;
        }else {
            return null;
        }
    }

    /**
     * 对绝对路径和相对路径的支持
     * @param path 径
     * @return 正确路径
     */
    public static String improvePath(String path) {
        if (path.contains("/")){
            path = path.replace("/", File.separator);
        }
        String p;
        if (path.indexOf(":") > 0) {
            p = path;
        } else {//相对路径
            p = SymbolConstant.PRINT_FILE_URL + File.separator + path;
        }
        return p;
    }
}
