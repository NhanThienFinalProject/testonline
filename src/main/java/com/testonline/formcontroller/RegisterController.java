
package com.testonline.formcontroller;

import com.testonline.entity.UserEntity;
import com.testonline.service.impl.UserService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

 @Controller
public class RegisterController {
    @Autowired
    private UserService userSV;
     
    @GetMapping("/form-register")
    public String showFormRegister(Model theModel) {
        String roleId = "student";
        theModel.addAttribute("newUser", new UserEntity());
	return "web/register";
    }
    
    @PostMapping("/form-save")
    public String saveNewUser(Model theModel, @ModelAttribute("newUser") UserEntity newUser) {
        if(userSV.isExistedUsername(newUser.getUserName())){
            return "web/register";
        }else{
        String password = newUser.getPassword();
        String passwordMD5 = md5(password);
        newUser.setPassword(passwordMD5);
        newUser.setCreateDate(new Date());
        userSV.saveNewUser(newUser);
	return "web/login";
        }

    }
    public static String md5(String str){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
