package asso.lh.dm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asso.lh.dm.dao.IDAOAdmPlayerTable;
import asso.lh.dm.dao.IDAOAdmTable;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;

@Service
public class AdmTableService {

	@Autowired
	private IDAOAdmTable daoAdmTable;
	@Autowired
	private IDAOAdmPlayerTable daoPlayerTable;
	
	public List<AdmTable> getAll(){
		return daoAdmTable.findAll();
	}
	
	public AdmTable getById(Integer id) {
		if(id == null) {
			throw new RuntimeException("id null");
		}
		return daoAdmTable.findById(id).orElseThrow(()->new RuntimeException("Aucune table n'existe pour cet id"));
	}
	
	public List<AdmTable> getByDate(LocalDate date){
		if(date == null) {
			throw new RuntimeException("date null");
		}
		return daoAdmTable.findByDate(date);
	}
	
	public List<AdmTable> getByGame(AdmGame game){
		if(game == null) {
			throw new RuntimeException("game null");
		}
		return daoAdmTable.findByGame(game);
	}
	
	public List<AdmTable> getByGameMaster(AdmMember gameMaster) {
		if(gameMaster==null) {
			throw new RuntimeException("game master null");
		}
		return daoAdmTable.findByGameMaster(gameMaster);
	}
	
	public AdmTable insert(AdmTable table) {
		if(table == null) {
			throw new RuntimeException("table null");
		}
		return daoAdmTable.save(table);
	}
	
	public AdmTable update(AdmTable table) {
		if(table == null) {
			throw new RuntimeException("table null");
		}
		if(table.getId()==null) {
			throw new RuntimeException("Aucune table n'existe pour cet id");
		}
		return daoAdmTable.save(table);
	}
	
	public void delete(AdmTable table) {
		List<AdmPlayerTable> playersTable = daoPlayerTable.findAllByTable(table);
		daoPlayerTable.deleteAll(playersTable);
		daoAdmTable.delete(table);
		
	}
}
