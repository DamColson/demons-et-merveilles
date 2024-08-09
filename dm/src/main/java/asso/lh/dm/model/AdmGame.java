package asso.lh.dm.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
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
	@Column(name = "game_id")
	private Integer id;

	@Column(nullable = false, unique = true)
	private String name;

	@Lob
	@Column(nullable = false, length = 256)
	private String description;

	@ManyToMany
	@JoinTable(name = "game_theme", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "theme_id"))
	private List<AdmTheme> themes;

	@OneToMany(mappedBy = "game")
	private List<AdmTable> tables;
	
	
}
