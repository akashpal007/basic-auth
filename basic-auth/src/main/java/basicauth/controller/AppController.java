package basicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AppController {
	
	@GetMapping("/admin/home")
	public String adminHome() {
		return "Admin Home.";
	}
	
	@GetMapping("/user/home")
	public String otherUserHome() {
		return "All User Home.";
	}
	
	@GetMapping(value = {"/home","/"})
	public String home() {
		return "Home without Role and AUTH.";
	}

}
