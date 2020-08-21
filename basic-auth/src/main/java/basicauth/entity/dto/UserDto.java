package basicauth.entity.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Length(min = 5, message = "Your user name must have at least 5 characters")
	@NotEmpty(message = "Please provide a user name")
	private String userName;

	@Email(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Please provide a valid Email")
	@NotEmpty(message = "Please provide an email")
	private String email;

	@Length(min = 5, message = "Your password must have at least 5 characters")
	@NotEmpty(message = "Please provide password")
	private String password;

	@NotEmpty(message = "Please provide roles")
	private List<String> roles;
}
