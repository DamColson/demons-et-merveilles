package asso.lh.dm.model;

import java.io.Serializable;

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
	private Integer tableId;
	
	@Column(name="member_id")
	private Integer memberId;
	
	
}
