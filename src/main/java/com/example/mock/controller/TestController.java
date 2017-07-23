package com.example.mock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by luotj on 2017/7/23.
 */

@Controller
public class TestController {

    private static final String token = "absdgaskdgjsdkjgkds";
    private static final String name = "ltj";
    private static final String pwd = "123456";

    @RequestMapping(path = "/token", method = RequestMethod.POST)
    @ResponseBody
    public String home(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        JSONObject object = (JSONObject) JSON.parse(wholeStr);
        String userName = object.getString("name");
        String userPwd = object.getString("pwd");
        if (userName.equals(name) && userPwd.equals(pwd)) {
            response.addHeader("token",token);
        }
        return "hello world!";
    }

    @RequestMapping(path = "/mrs", method = RequestMethod.GET)
    @ResponseBody
    public String mrsList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userToken = request.getHeader("token");

        if (userToken != null && userToken.equals(token)) {
            return "msr list!";
        }
        return "token error!";
    }
}
