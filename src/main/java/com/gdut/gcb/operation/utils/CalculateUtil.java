package com.gdut.gcb.operation.utils;


import com.gdut.gcb.operation.bo.Fraction;

import java.util.Stack;


/**
 * @Author 古春波
 * @Description 运算工具类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class CalculateUtil {


    /**
     * 采用调度场算法，获取指定运算式的结果值
     * @param express 运算式
     * @return 返回值
     */
    public static String getExpressValue(String express){
        //运算符栈，用于存放运算符包括 +、-、*、÷、(、)
        Stack<Character>  operators = new Stack<>();
        //操作数栈,用于存放操作数
        Stack<Fraction> fractions = new Stack<>();
        //将表达式字符串转成字符数组
        char[] chars = express.toCharArray();
        //遍历获取处理
        for (int i=0;i<chars.length;i++) {
            //获取当前的字符
            char c = chars[i];
            //如果是左括号，入栈
            if(c=='('){
                operators.push(c);
            }
            //当前字符为右括号
            else if(c==')'){
                //当运算符栈顶的元素不为‘(’,则继续
                while(operators.peek()!='('){
                    //拿取操作栈中的两个分数
                    Fraction fraction1 = fractions.pop();
                    Fraction fraction2 = fractions.pop();
                    //获取计算后的值
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    //保证运算过程不出现负数
                    if(result.getNumerator()<0){
                        return  "#";
                    }
                    //将结果压入栈中
                    fractions.push(result);
                }
                //将左括号出栈
                operators.pop();
            }
            //是运算符
            else if(c=='+'||c=='-'||c=='*'||c=='÷'){
                //当运算符栈不为空，且当前运算符优先级小于栈顶运算符优先级
                while(!operators.empty()&&!priority(c, operators.peek())){
                    //拿取操作栈中的两个分数
                    Fraction fraction1 = fractions.pop();
                    Fraction  fraction2 = fractions.pop();
                    //获取计算后的值
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    if(result.getNumerator()<0){
                        return  "#";
                    }
                    //将结果压入栈中
                    fractions.push(result);
                }
                //将运算符入栈
                operators.push(c);
            }else{//是操作数
                if(c>='0'&&c<='9'){
                    StringBuilder buf = new StringBuilder();
                    //这一步主要是取出一个完整的数值 比如 2/5、9、9/12
                    while(i<chars.length&&(chars[i]=='/'||((chars[i]>='0')&&chars[i]<='9'))){
                        buf.append(chars[i]);
                        i++;
                    }
                    i--;
                    //到此 buf里面是一个操作数
                    String val = buf.toString();
                    //标记‘/’的位置
                    int flag = val.length();
                    for(int k=0;k<val.length();k++){
                        if(val.charAt(k)=='/'){//当获取的数值存在/则标记/的位置，便于接下来划分分子和分母生成分数对象
                            flag = k;
                        }
                    }
                    //分子
                    StringBuilder numeratorBuf = new StringBuilder();
                    //分母
                    StringBuilder denominatorBuf = new StringBuilder();
                    for(int j=0;j<flag;j++){
                        numeratorBuf.append(val.charAt(j));
                    }
                    //判断是否为分数
                    if(flag!=val.length()){
                        for(int q=flag+1;q<val.length();q++){
                            denominatorBuf.append(val.charAt(q));
                        }
                    }else{//如果不是分数则分母计为1
                        denominatorBuf.append('1');
                    }
                    //入栈
                    fractions.push(new Fraction(Integer.parseInt(numeratorBuf.toString()), Integer.parseInt(denominatorBuf.toString())));
                }
            }
        }

        while(!operators.empty()){
            Fraction fraction1 = fractions.pop();
            Fraction  fraction2 = fractions.pop();

            //获取计算后的值
            Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                    fraction2.getNumerator(), fraction2.getDenominator());
            if(result.getNumerator()<0){
                return "#";
            }
            //将结果压入栈中
            fractions.push(result);
        }

        //计算结果
        Fraction result = fractions.pop();
        //获取最终的结果(将分数进行约分)
        return getFinalResult(result);

    }

    private static String getFinalResult(Fraction result) {
        int denominator = result.getDenominator();
        int numerator = result.getNumerator();
        if(denominator==0){
            return "0";
        }
        //获取最大公约数
        int gcd = gcd(numerator,denominator);
        //分母为1
        if(denominator/gcd==1){
            return String.valueOf(numerator/gcd);
        }else{
            //如果分子大于分母则化成真分数的形式
            if(result.getNumerator()>denominator){
                result = getRealFraction(result);
                return result.getInter()+"'"+result.getNumerator()/gcd+"/"+result.getDenominator()/gcd;
            }else{
                return numerator/gcd+"/"+denominator/gcd;
            }
        }
    }

    /**
     * 化成真分数
     * @param result 结果
     * @return 返回值
     */
    private static Fraction getRealFraction(Fraction result){
        int numerator = result.getNumerator();
        int denominator = result.getDenominator();
        //计算分子部分
        int newNumerator = numerator % denominator;
        //计算整数部分
        int inter = numerator/denominator;
        Fraction fraction = new Fraction(newNumerator, denominator);
        fraction.setInter(inter);
        return fraction;
    }

    /**
     * 判断两个运算符的优先级
     * 当opt1的优先级大于opt2时返回true
     * 这是根据调度场算法给出的实现
     * @return 返回值
     */
    private static boolean priority(char opt1,char opt2){
        if((opt1=='+'||opt1=='-')&&(opt2=='*'||opt2=='÷')){
            return false;
        }else if((opt1=='+'||opt1=='-')&&(opt2=='+'||opt2=='-')){
            return false;
        }else if((opt1=='*'||opt1=='÷')&&(opt2=='*'||opt2=='÷')){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 对两个分数进行相应的运算，获取结果
     * @param opt 运算符
     * @param num1 分子1
     * @param den1 分母1
     * @param num2 分子2
     * @param den2 分母2
     * @return 结果
     */
    private static Fraction calculate(Character opt, int num1, int den1, int num2, int den2){
        //结果数组,存放结果的分子与分母
        int[] result = new int[2];
        /*
         * 这里有一个陷阱，因为用于计算的两个数是通过栈来存储，所以取出计算结果的时候两个数的顺序会颠倒
         * 比如式子 1/2*9/12 我取出来的时候其实是 9/12 和 1/2 所以调用此函数的时候是calculate('*',9,12,1,2),所以下面的实现要注意不要踩坑
         */
        switch (opt){
            case'+':
                result[0] = num1*den2 + num2*den1; result[1]= den1*den2;
                break;
            case '-':
                result[0] = num2*den1 - num1*den2; result[1]= den1*den2;
                break;
            case '*':
                result[0] = num1*num2; result[1] = den1*den2;
                break;
            case '÷':
                result[0] = num2*den1; result[1] = num1*den2;
                break;
        }
        return new Fraction(result[0],result[1]);
    }

    /**
     * 获取分子分母的最大公约数，辗转相除法
     * @param numerator 分子
     * @param denominator 分母
     * @return 最大公约数
     */
    private static int gcd(int numerator,int denominator){
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        while (denominator != 0) {
            // 求余
            int remainder = numerator % denominator;
            // 交换数，等同递归调用
            numerator = denominator;
            denominator = remainder;
        }
        return numerator;
    }
}
