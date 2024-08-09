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
	@NotBlank.List(value = { @NotBlank })
	private List<String> themes;
	
	private List<Integer> tablesId;
}
