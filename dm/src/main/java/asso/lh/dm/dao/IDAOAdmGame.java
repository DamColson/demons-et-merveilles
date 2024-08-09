package asso.lh.dm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmTheme;

public interface IDAOAdmGame extends JpaRepository<AdmGame, Integer> {

	Optional<AdmGame> findByName(String name);
	
	@Query("select g from AdmGame g left join fetch g.themes where :theme MEMBER OF g.themes")
	List<AdmGame> findByTheme(@Param("theme") AdmTheme theme);
	
	@Query("select g from AdmGame g left join fetch g.themes where g.id=:id")
	Optional<AdmGame> findByIdWithTheme(@Param("id") Integer id);	
}
