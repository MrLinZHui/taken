package org.fkit.fileuploadtest.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.fkit.fileuploadtest.Interceptor.UserLoginToken;
import org.fkit.fileuploadtest.domain.User;
import org.fkit.fileuploadtest.service.TokenService;
import org.fkit.fileuploadtest.service.UserService;
import org.fkit.fileuploadtest.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	TokenService tokenService;

	// 登录
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public Object login(User user, HttpServletResponse response) {
		System.out.println("Controller==========login");
		JSONObject jsonObject = new JSONObject();
		User userForBase = new User();
		userForBase.setId(userService.findByUsername(user).getId());
		userForBase.setUsername(userService.findByUsername(user).getUsername());
		userForBase.setPassword(userService.findByUsername(user).getPassword());
		if (!userForBase.getPassword().equals(user.getPassword())) {
			jsonObject.put("message", "登录失败,密码错误");
			return jsonObject;
		} else {
			String token = tokenService.getToken(userForBase);
			jsonObject.put("token", token);

			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			response.addCookie(cookie);

			return jsonObject;

		}
	}
	/***
	 * 这个请求需要验证token才能访问
	 */
	@UserLoginToken
	//@Apio(value = "获取信息", notes = "获取信息")
	@RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
	public String getMessage() {
		System.out.println("Controller==========getMessage");
		// 取出token中带的用户id 进行操作
		System.out.println(TokenUtil.getTokenUserId());

		return "您已通过验证";
	}
}
