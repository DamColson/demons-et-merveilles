package asso.lh.dm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmTable;
import jakarta.transaction.Transactional;

public interface IDAOAdmTable extends JpaRepository<AdmTable, Integer> {

	List<AdmTable> findByGameMaster(AdmMember gameMaster);
	
	List<AdmTable> findByGame(AdmGame game);
	
	List<AdmTable> findByDate(LocalDate date);
	
	@Transactional
	@Modifying
	@Query("update AdmTable t set t.game=null where t.game = :game")
	void setGameNull(@Param("game") AdmGame game);
	
	@Transactional
	@Modifying
	@Query("update AdmTable t set t.gameMaster=:anonymous where t.gameMaster = :member")
	void setGameMasterAnonymous(@Param("member") AdmMember member, @Param("anonymous") AdmMember anonymous);
	
}
