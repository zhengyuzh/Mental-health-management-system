package com.mentalhealth.project.system.user.controller;

import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * wx登录验证
 * 
 * @author ruoyi
 */
@Controller
public class WXLoginController extends BaseController
{

    @RequestMapping("/WXlogin")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password,HttpSession session)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            session.setAttribute("userName",username);
            System.out.println(session.getAttribute("userName"));
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

}
