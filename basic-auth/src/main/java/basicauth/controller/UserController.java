package basicauth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import basicauth.dao.UserDao;
import basicauth.entity.User;
import basicauth.entity.dto.UserDto;
import basicauth.entity.dto.mapper.UserDtoMapper;
import basicauth.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserDtoMapper userDtoMapper;

	/*****/
	@Autowired
	UserDao userDao;

	@GetMapping("/user")
	public List<User> otherUserHome() {
		return userDao.findAll();
	}

	/*****/

	@PostMapping("/user")
	public String saveUser(@Valid @RequestBody UserDto userDto) {
		User user = userDtoMapper.getUserFromUserDto(userDto);
		userService.saveUser(user);
		return "User saved successfully user name - " + user.getUserName();
	}

}
