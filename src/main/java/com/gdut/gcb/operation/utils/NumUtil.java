package com.gdut.gcb.operation.utils;

import java.util.Random;


/**
 * @Author 古春波
 * @Description 操作数工具类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
class NumUtil {

    /**
     * 随机获取num个操作数的数组
     */
    static String[] getNumbers(int num, int round) {

        Random random = new Random();
        String[] numbers = new String[num];

        for (int i = 0; i < num; i++) {
            //用于判断生成整数还是分数
            int flag = (int)(Math.random()*10) % 2;
            //生成整数
            if(flag==0){
                int n = random.nextInt(round);
                numbers[i] = (n==0?1:n)+"";
            }else{//生成分数
                //随机生成分子和分母，为了避免分子分母生成0进行了+1/+2的改进
                int numerator = (random.nextInt(round))+1;
                int denominator = (random.nextInt(round))+1;
                //判断是否为真分数，且不能生成带0的分数
                while(numerator>=denominator||numerator==0||denominator==0){
                    numerator = (random.nextInt(round))+1;
                    denominator = (random.nextInt(round))+1;
                }
                //拼装成分数形式
                numbers[i] = numerator+"/"+denominator;
            }
        }
        return numbers;
    }

}
