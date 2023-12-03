package com.mentalhealth.project.system.user.controller;

import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.project.system.role.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mentalhealth.framework.shiro.service.RegisterService;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.project.system.config.service.IConfigService;
import com.mentalhealth.project.system.user.domain.User;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
public class RegisterController extends BaseController
{
    @Autowired
    private RegisterService registerService;


    @Autowired
    private IConfigService configService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(User user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
//        Role role1 = new Role();
//        Long roleStu = role1.setRoleId(3);
//
//
//                String loginName = ShiroUtils.getLoginName();
//        mentalKnowledge.setLoginName(loginName);
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
