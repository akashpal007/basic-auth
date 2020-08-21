package basicauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import basicauth.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByUserName(String userName);

}
