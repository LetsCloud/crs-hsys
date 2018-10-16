/**
 * 
 */
package io.crs.hsys.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author robi
 *
 */
@RestController
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		
		return "Hello World! Welcome to visit waylau.com!";
	}
}
