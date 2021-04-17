/**
 * 
 */
package com.lzh.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 51937
 *
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "heeeeello ";
	}
}
