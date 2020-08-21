package basicauth.entity.dto.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import basicauth.entity.Role;
import basicauth.entity.User;
import basicauth.entity.dto.UserDto;

@Service
public class UserDtoMapper {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUserFromUserDto(UserDto userDto) {
		User user = new User();
		Set<Role> roles = new HashSet<Role>();

		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		List<String> inputRoles = userDto.getRoles();
		for (String iterateInputRole : inputRoles) {
			roles.add(new Role(null, iterateInputRole));
		}
		user.setRoles(roles);

		return user;
	}

}
