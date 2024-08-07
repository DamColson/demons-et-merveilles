package asso.lh.dm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asso.lh.dm.dao.IDAOAdmGame;
import asso.lh.dm.dao.IDAOAdmTable;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.Theme;

@Service
public class AdmGameService {
	
	@Autowired
	private IDAOAdmGame daoAdmGame;
	@Autowired
	private IDAOAdmTable daoAdmTable;
	
	public List<AdmGame> getAll(){
		return daoAdmGame.findAll();
	}
	
	public AdmGame getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id null");
		}
		return daoAdmGame.findById(id).orElseThrow(()->new RuntimeException("Aucun jeu n'existe pour cet id"));
	}
	
	public AdmGame getByName(String name) {
		if(name==null) {
			throw new RuntimeException("nom null");
		}
		return daoAdmGame.findByName(name).orElseThrow(()->new RuntimeException("Aucun jeu n'existe pour ce nom"));
	}
	
	public List<AdmGame> getByTheme(Theme theme) {
		if(theme==null) {
			throw new RuntimeException("theme null");
		}
		return daoAdmGame.findByTheme(theme);
	}
	
	public AdmGame insert(AdmGame game) {
		if(game==null) {
			throw new RuntimeException("jeu null");
		}
		return daoAdmGame.save(game);
	}
	
	public AdmGame update(AdmGame game) {
		if(game==null) {
			throw new RuntimeException("jeu null");
		}
		if(game.getId()==null) {
			throw new RuntimeException("Aucun jeu n'existe pour cet id");
		}
		return daoAdmGame.save(game);
	}
	
	public void delete(AdmGame game) {
		daoAdmTable.setGameNull(game);
		daoAdmGame.delete(game);
	}
}
