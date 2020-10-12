package com.gdut.gcb.operation.utils;


import com.gdut.gcb.operation.bo.SymbolConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author 古春波
 * @Description 生成文件的工具类
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class PrintFileUtil {

    /**
     * 根据运算式子生成练习文件和答案文件
     *
     */
    public static void printExerciseFileAndAnswerFile(Map<String, String> questionAndResultMap) {
        File dir = new File(SymbolConstant.PRINT_FILE_URL);
        //解决FileNotFound
        if (!dir.exists()) { 
            dir.mkdir();
        }
        File exerciseFile = new File(SymbolConstant.PRINT_FILE_URL, "Exercises.txt");
        File answerFile = new File(SymbolConstant.PRINT_FILE_URL, "Answers.txt");
        try {
            OutputStream exerciseFileOutputStream = new FileOutputStream(exerciseFile);
            OutputStream answerFileOutputStream = new FileOutputStream(answerFile);
            StringBuilder exerciseBuffer = new StringBuilder();
            StringBuilder answerFileBuffer = new StringBuilder();
            System.out.println("正在写出到文件...");

            for(Map.Entry<String, String> entry:questionAndResultMap.entrySet()){
                exerciseBuffer.append(entry.getKey()).append("\r\n");
                answerFileBuffer.append(entry.getValue()).append("\r\n");
            }
            exerciseFileOutputStream.write(exerciseBuffer.toString().getBytes());
            answerFileOutputStream.write(answerFileBuffer.toString().getBytes());
            exerciseFileOutputStream.close();
            answerFileOutputStream.close();
            System.out.println("操作成功！！！");
        }
        catch (IOException e) {
            System.out.println("文件操作异常，请重试");
        }


    }

    /**
     * 验证答案的正确率
     */
    public static void validateAnswerFile(String exerciseFileUrl, String answerFileUrl) throws IOException {
        //SymbolConstant.PRINT_FILE_URL, exerciseFileUrl);
        File exerciseFile = new File(ValidateUtil.improvePath(exerciseFileUrl));
        File answerFile = new File(ValidateUtil.improvePath(answerFileUrl));
        File gradeFile = new File(SymbolConstant.PRINT_FILE_URL, "Grade.txt");
        if (exerciseFile.isFile() && answerFile.isFile()) {
            BufferedReader exerciseReader = null;
            BufferedReader answerReader = null;
            OutputStream gradeFileOutputStream = null;
            List<Integer> correct = new ArrayList<>();
            List<Integer> wrong = new ArrayList<>();
            try {
                exerciseReader = new BufferedReader(new InputStreamReader(new FileInputStream(exerciseFile)));
                answerReader = new BufferedReader(new InputStreamReader(new FileInputStream(answerFile)));
                String exerciseStr;
                String answerStr;
                //记录行数
                int line = 0;
                System.out.println("开始验证...");
                while ((exerciseStr = exerciseReader.readLine()) != null && (answerStr = answerReader.readLine()) != null) {
                    //获取运算式的正确答案
                    String realAnswer = CalculateUtil.getExpressValue(exerciseStr);
                    if (realAnswer.equals(answerStr)) {
                        line++;
                        correct.add(line);
                    } else {
                        line++;
                        wrong.add(line);
                    }
                }
                String result = "Correct:" + correct.size() + correct + "\r\n" + "Wrong:" + wrong.size() + wrong;
                //保存成绩文件
                gradeFileOutputStream = new FileOutputStream(gradeFile);
                gradeFileOutputStream.write(result.getBytes());
                //打印结果
                System.out.print(result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (exerciseReader != null) {
                    try {
                        exerciseReader.close();
                    } catch (IOException ignored) {
                    }
                }
                if (answerReader != null) {
                    try {
                        answerReader.close();
                    } catch (IOException ignored) {
                    }
                }
                if (gradeFileOutputStream != null) {
                    try {
                        gradeFileOutputStream.close();
                    } catch (IOException ignored) {
                    }
                }
            }

        } else {
            System.out.println("文件不存在！！！");
        }
    }

}
