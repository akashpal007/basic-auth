package basicauth.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import basicauth.dao.RoleDao;
import basicauth.dao.UserDao;
import basicauth.entity.Role;
import basicauth.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User findUserByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public User saveUser(User user) {
		Set<Role> roles = new HashSet<Role>();
		
		if(Objects.nonNull(findUserByEmail(user.getEmail()))) {
			throw new InvalidDataAccessApiUsageException("System already have the email- " + user.getEmail());
		}
		
		for (Role role : user.getRoles()) {
			Role newRole = roleDao.findByRole(role.getRole());
			if (Objects.isNull(newRole)) {
				throw new InvalidDataAccessApiUsageException("System does not have any role named- " + role.getRole());
			}
			roles.add(newRole);
		}
		user.setRoles(roles);
		
		return userDao.save(user);
	}
}
