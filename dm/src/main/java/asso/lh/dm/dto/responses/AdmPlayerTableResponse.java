package asso.lh.dm.dto.responses;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.AdmPlayerTable;
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
	
	public AdmPlayerTableResponse(AdmPlayerTable pt) {
		this(pt,true);
	}
	
	public AdmPlayerTableResponse(AdmPlayerTable pt,boolean bool) {
		BeanUtils.copyProperties(pt, this);
		if(bool) {
			if(pt.getMember()!=null) {
				this.memberResponse = new AdmMemberResponse(pt.getMember(),false);
			}
			if(pt.getTable()!=null) {
				this.tableResponse = new AdmTableResponse(pt.getTable(),false);
			}
		}
	}
}
