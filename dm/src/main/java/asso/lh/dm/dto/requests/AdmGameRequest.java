package asso.lh.dm.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmGameRequest {

	@NotBlank
	private String name;
	@NotBlank
	private String Description;
	
	private List<Integer> themesId;
	
	private List<Integer> tablesId;
}
