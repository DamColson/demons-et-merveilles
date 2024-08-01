package asso.lh.dm.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adm_player_table")
public class AdmPlayerTable {

	@EmbeddedId
	private MemberTableKey id;
	
	@ManyToOne
	@MapsId("tableId")
	@JoinColumn(name="table_id")
	private AdmTable table;
	
	@ManyToOne
	@MapsId("memberId")
	@JoinColumn(name="member_id")
	private AdmMember member;
	
	@Column(nullable=false)
	private boolean selected;
	
}
