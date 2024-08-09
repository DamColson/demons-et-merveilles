package asso.lh.dm.dto.requests;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmMemberRequest {

	@NotBlank
	private String login;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
	message = "Le mot de passe doit contenir 8 caractère, une majuscule, une minuscule, un chiffre et un caractère spécial")
	private String password;
	
	@NotBlank
	@Email
	private String mail;
	
	@NotBlank
	private String role;
	
	private List<Integer> tablesId;
	
	private List<Integer> playersTableId;
}
