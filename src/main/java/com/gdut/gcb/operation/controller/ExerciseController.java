package com.gdut.gcb.operation.controller;

import com.gdut.gcb.operation.bo.Subject;
import com.gdut.gcb.operation.common.api.CommonResult;
import com.gdut.gcb.operation.utils.MathExpressionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author 古春波
 * @Description 生成练习题
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/

@RestController
@Api(tags = "ExerciseController", description = "生成练习题")
@RequestMapping("/test")
@Validated
@Slf4j
public class ExerciseController {


    @ApiOperation("生成练习题")
    @PostMapping("/create")
    public CommonResult<List<Subject>> generate(
            @Pattern(regexp = "^[1-9][0-9]*$", message = "num格式错误")
            @RequestParam("num") String num,
            @Pattern(regexp = "^[1-9][0-9]*$", message = "round格式错误")
            @RequestParam("round") String round){
        List<Subject> questionAndResultMap = MathExpressionUtil.generateLitst(new Integer(num), new Integer(round));
        return CommonResult.success(questionAndResultMap);
    }
    
}
