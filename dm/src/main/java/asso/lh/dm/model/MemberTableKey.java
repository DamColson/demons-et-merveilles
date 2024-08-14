package asso.lh.dm.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.dto.responses.CustomJsonViews;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTableKey implements Serializable {


	private static final long serialVersionUID = 1L;

	@Column(name="table_id")
	@JsonView(CustomJsonViews.Common.class)
	private Integer tableId;
	
	@Column(name="member_id")
	@JsonView(CustomJsonViews.Common.class)
	private Integer memberId;
	
	
}
