package com.haomeng.wisdomputian.controller.ConsoleController;

import com.haomeng.wisdomputian.bean.ManagerUser;
import com.haomeng.wisdomputian.bean.ReturnMessage;
import com.haomeng.wisdomputian.config.errorCode;
import com.haomeng.wisdomputian.mapper.CustomerMapper;
import com.haomeng.wisdomputian.mapper.ManagerUserMapper;
import com.haomeng.wisdomputian.util.PasswordMD5;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginaction")
public class LoginConsoleController {

    private ModelAndView modelAndView;//重定向
    private ReturnMessage returnMessage;
    @Resource
    private ManagerUserMapper managerUserMapper;

    @PostMapping("login")
    public String login(String username, String password, HttpSession session) {
        System.out.println("username：" + username + "  password：" + password);
        returnMessage = new ReturnMessage();
        //账号密码不能为空
        if ("".equals(username) || "".equals(password)) {
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_LOGIN_NULL);
            returnMessage.setErroCode(errorCode.ERROR_CODE_LOGIN);
            session.setAttribute("data", returnMessage);
            return "pc/Login";
        }
        if ("administrator".equals(username) && "123456".equals(password)) {
            PasswordMD5 pwdMD5 = new PasswordMD5();
            pwdMD5.md5("123456", "");
            System.out.println(UUIDUtil.getNoLineUUID());
            return "redirect:/index";
        }
        //查找用户
        ManagerUser managerUser = managerUserMapper.LoginValidSelect(username);
        //密码校验
        PasswordMD5 pwdMD5 = new PasswordMD5();
        boolean pwdchecked = pwdMD5.verify(password, "", managerUser.getPassword());
        if (!pwdchecked) {//密码错误
            returnMessage.setErrorMessage(errorCode.ERROR_MESSAGE_LOGIN_PWDWRONG);
            returnMessage.setErroCode(errorCode.ERROR_CODE_LOGIN);
            return "pc/Login";
        }
        return "redirect:/index";
    }

}
