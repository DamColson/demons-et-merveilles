package asso.lh.dm.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "adm_member")
public class AdmMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Integer id;
	
	@Column(unique = true,nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true,nullable = false)
	private String mail;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "gameMaster")
	private List<AdmTable> tables;
	
	@OneToMany(mappedBy = "member")
	private List<AdmPlayerTable> playersTable;
}
