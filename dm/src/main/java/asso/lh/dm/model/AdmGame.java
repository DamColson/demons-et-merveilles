package asso.lh.dm.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "adm_game")
public class AdmGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="game_id")
	private Integer id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	@Lob
	@Column(nullable = false, length = 256)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Theme.class)
	@CollectionTable(name="game_theme",joinColumns = @JoinColumn(name="game_id",referencedColumnName = "game_id"))
	@Column(nullable = false, name="theme")
	private List<Theme> themes;
	
	@OneToMany(mappedBy = "game")
	private List<AdmTable> tables;
}
