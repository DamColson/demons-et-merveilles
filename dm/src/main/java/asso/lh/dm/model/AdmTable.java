package asso.lh.dm.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "adm_table")
public class AdmTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="table_id")
	private Integer id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	private String requirement;
	
	@Column(name = "estimated_duration")
	private String estimatedDuration;
	
	@Column(name = "open_to_new_player")
	private boolean openToNewPlayer;
		
	@ManyToOne
	@JoinColumn(nullable = false,name="game_id")
	private AdmGame game;
	
	@ManyToOne
	@JoinColumn(nullable = false,name="game_master_id")
	private AdmMember gameMaster;
	
	@OneToMany(mappedBy = "table")
	private List<AdmPlayerTable> playersTable;
}
