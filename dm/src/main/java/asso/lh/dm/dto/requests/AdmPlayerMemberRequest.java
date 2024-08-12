package asso.lh.dm.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmPlayerMemberRequest {

	@NotNull
	private Integer tableId;
	@NotNull
	private Integer Memberid;
	private boolean selected;
}
