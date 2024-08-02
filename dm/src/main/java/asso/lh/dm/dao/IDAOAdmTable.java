package asso.lh.dm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmTable;

public interface IDAOAdmTable extends JpaRepository<AdmTable, Integer> {

	List<AdmTable> findByGameMaster(AdmMember gameMaster);
	
	List<AdmTable> findByGame(AdmGame game);
	
	List<AdmTable> findByDate(LocalDate date);
}
