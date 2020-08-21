package basicauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import basicauth.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	Role findByRole(String string);

}
