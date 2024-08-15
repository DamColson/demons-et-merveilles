package asso.lh.dm.dto.responses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.MemberTableKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmMemberResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	
	@JsonView(CustomJsonViews.Common.class)
	private String login;
	
	@JsonView(CustomJsonViews.Common.class)
	private String mail;
	
	@JsonView(CustomJsonViews.Common.class)
	private String role;
	
	@JsonView(CustomJsonViews.MemberWithTables.class)
	private List<AdmTableResponse> tablesResponse;
	
	@JsonView(CustomJsonViews.MemberWithPlayersTable.class)
	private List<AdmPlayerTableResponse> playersTableResponse;
	
	public AdmMemberResponse(AdmMember member) {
		this(member,true);
	}
	
	public AdmMemberResponse(AdmMember member,boolean bool) {
		BeanUtils.copyProperties(member, this,"role");
		this.role = member.getRole().toString();
		if(bool) {
			if(member.getPlayersTable()!=null) {
				this.playersTableResponse = member.getPlayersTable().stream().map(pt->{
					return new AdmPlayerTableResponse(pt,false);
				}).collect(Collectors.toList());
			}
			if(member.getTables()!=null) {
				this.tablesResponse = member.getTables().stream().map(table->{
					return new AdmTableResponse(table,false);
				}).collect(Collectors.toList());
			}
		}
	}
}
