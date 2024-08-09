package asso.lh.dm.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "adm_theme")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmTheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "themes")
	private List<AdmGame> games;
	
	public AdmTheme(String name) {
		this.name = name;
	}
}
