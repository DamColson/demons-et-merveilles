package asso.lh.dm.dto.responses;

import com.fasterxml.jackson.annotation.JsonView;

public class AdmMemberResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String login;
	@JsonView(CustomJsonViews.Common.class)
	private String mail;
	@JsonView(CustomJsonViews.Common.class)
	private String role;
	
//	private List<AdmTableResponse> tablesResponse;
//	private List<AdmPlayerTableResponse> playersTableResponse;
}
