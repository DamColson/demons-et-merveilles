package asso.lh.dm.dto.requests;

import java.util.List;

import asso.lh.dm.model.MemberTableKey;
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

	@NotBlank(groups = CustomValidation.Common.class)
	private String login;
	
	@NotBlank(groups = {CustomValidation.OnCreation.class,CustomValidation.OnPasswordUpdate.class})
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
	message = "Le mot de passe doit contenir 8 caractère, une majuscule, une minuscule, un chiffre et un caractère spécial",
	groups = {CustomValidation.OnCreation.class,CustomValidation.OnPasswordUpdate.class})
	private String password;
	
	@NotBlank(groups = CustomValidation.Common.class)
	@Email(groups = CustomValidation.Common.class)
	private String mail;
	
	@NotBlank(groups = CustomValidation.Common.class)
	private String role;
	
	private List<Integer> tablesId;
	
	private List<MemberTableKey> playersTableId;
}
