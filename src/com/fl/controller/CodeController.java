package com.fl.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/16/21:39
 * @Description:生成验证码的controller
 */
@WebServlet("/code")
public class CodeController extends BaseServlet{
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码对象
        //int width, int height, int codeCount, int lineCount
        ValidateCode validateCode=new ValidateCode(100,35,3,15);
        //将验证码放入session中
       String code= validateCode.getCode();
       request.getSession().setAttribute("code",code);
        //向页面传回验证码
      ServletOutputStream outputStream= response.getOutputStream();
      validateCode.write(outputStream);
    }

}
