package org.fkit.fileuploadtest.service;

import java.util.Date;

import org.fkit.fileuploadtest.domain.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

	public String getToken(User user) {
		Date start = new Date();
		//设置有效时间（一小时有效时间）
		long currentTime = System.currentTimeMillis() + 30 * 1000;
		Date end = new Date(currentTime);
		String token = "";
		
		token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(user.getPassword()));
		return token;
	}
}
