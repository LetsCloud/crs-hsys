/**
 * 
 */
package io.crs.hsys.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author robi
 *
 */
@RestController
public class HelloController {
	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello() {

		return "Hello World! Welcome to visit waylau.com!";
	}
}
