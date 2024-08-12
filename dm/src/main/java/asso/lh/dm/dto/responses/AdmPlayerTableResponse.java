package asso.lh.dm.dto.responses;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.MemberTableKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmPlayerTableResponse {

	@JsonView(CustomJsonViews.Common.class)
	private MemberTableKey id;

	@JsonView(CustomJsonViews.PlayerTableWithTable.class)
	private AdmTableResponse tableResponse;
	
	@JsonView(CustomJsonViews.PlayerTableWithMember.class)
	private AdmMemberResponse memberResponse;
	
	@JsonView(CustomJsonViews.Common.class)
	private boolean selected;
}
