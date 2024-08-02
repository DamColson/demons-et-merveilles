package asso.lh.dm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.Theme;

public interface IDAOAdmGame extends JpaRepository<AdmGame, Integer> {

	Optional<AdmGame> findByName(String name);
	
	@Query("select g from AdmGame g where g.themes = :theme")
	List<AdmGame> findByTheme(@Param("theme") Theme theme);
}
