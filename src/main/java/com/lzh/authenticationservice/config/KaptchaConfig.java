/**
 * 
 */
package com.lzh.authenticationservice.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.lzh.authenticationservice.config.kaptcha.KaptchaNoise;
import com.lzh.authenticationservice.config.kaptcha.KaptchaWordRenderer;

/**
 * @author 51937
 *
 */
@Configuration
public class KaptchaConfig {
 
//    @Bean
//    public Producer KaptchaProducer() {
//        Properties kaptchaProperties = new Properties();
//        kaptchaProperties.put("kaptcha.border", "no");  //图片边框  no yes 
//        kaptchaProperties.put("kaptcha.textproducer.char.length","4");  // 验证码长度
//        kaptchaProperties.put("kaptcha.textproducer.font.color","black");  //字体颜色，合法值： r,g,b 或者 white,black,blue.
//        kaptchaProperties.put("kaptcha.textproducer.font.size","40");    //字体大小 	40px.
//        kaptchaProperties.put("kaptcha.textproducer.char.string","acdefhkmnprtwxy2345678");   //文本集合，验证码值从此集合中获取
//        kaptchaProperties.put("kaptcha.textproducer.char.space","2");   //文字间隔   
//        kaptchaProperties.put("kaptcha.image.height","50");    // 验证码图片高度
//        kaptchaProperties.put("kaptcha.image.width","150");    // 验证码图片宽度
//        kaptchaProperties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");  //图片样式：<br />水纹 com.google.code.kaptcha.impl.WaterRipple <br /> 鱼眼 com.google.code.kaptcha.impl.FishEyeGimpy <br /> 阴影 com.google.code.kaptcha.impl.ShadowGimpy
//        kaptchaProperties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");  //  com.google.code.kaptcha.impl.DefaultNoise  com.google.code.kaptcha.impl.NoNoise
//        kaptchaProperties.put("kaptcha.noise.color","black");  //干扰 颜色，合法值： r,g,b 或者 white,black,blue.
// 
//        Config config = new Config(kaptchaProperties);
//        return config.getProducerImpl();
//    }
	
	@Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //图片是否有边框
        properties.setProperty("kaptcha.border", "no");
        //图片的边框颜色
        //properties.setProperty("kaptcha.border.color", "34,114,200");
        //图片的大小
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        //文字内容
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        //图片背景颜色
        //properties.setProperty("kaptcha.background.clear.from", "light grey");
        //properties.setProperty("kaptcha.background.clear.to", "white");

        properties.setProperty("kaptcha.word.impl", KaptchaWordRenderer.class.getName());
        properties.setProperty("kaptcha.noise.impl", KaptchaNoise.class.getName());

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
