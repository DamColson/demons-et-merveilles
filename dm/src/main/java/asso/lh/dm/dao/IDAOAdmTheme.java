package asso.lh.dm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmTheme;

public interface IDAOAdmTheme extends JpaRepository<AdmTheme, Integer>{

	public Optional<AdmTheme> findByName(String name);
	
	@Query("select t from AdmTheme t left join fetch t.games as g where g = :game")
	public List<AdmTheme> findByGame(@Param("game") AdmGame game);
}
