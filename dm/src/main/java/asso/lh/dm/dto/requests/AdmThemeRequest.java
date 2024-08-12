package asso.lh.dm.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmThemeRequest {

	@NotBlank
	private String name;
	
	private List<Integer> gamesId;
}
