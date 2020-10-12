package com.gdut.gcb.operation;

import cn.hutool.json.JSONObject;
import com.gdut.gcb.operation.bo.Subject;
import com.gdut.gcb.operation.utils.MathExpressionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class OperationApplicationTests {

    @Test
    void contextLoads() {
        String num="6";
        String round = "44";
        List<Subject> questionAndResultMap = MathExpressionUtil.generateLitst(new Integer(num), new Integer(round));
        for (Subject subject : questionAndResultMap){
            System.out.println(subject);
        }
        
    }
    
    
    

}
