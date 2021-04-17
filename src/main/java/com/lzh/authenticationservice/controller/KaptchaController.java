/**
 * 
 */
package com.lzh.authenticationservice.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author 51937
 *
 */
@RestController
public class KaptchaController {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Producer captchaProducer;

	@Autowired
	public KaptchaController(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	@GetMapping("/image/code")
	public void kaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		logger.info("输出验证码：[{}]", code);

		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("验证码模块错误...");
		}finally{
			out.close();
		}
	}
}
