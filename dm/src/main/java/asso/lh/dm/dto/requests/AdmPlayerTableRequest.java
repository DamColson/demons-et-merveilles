package asso.lh.dm.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmPlayerTableRequest {

	@NotNull
	private Integer tableId;
	@NotNull
	private Integer memberId;
	
	private boolean selected;
}
