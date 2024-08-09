package asso.lh.dm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asso.lh.dm.dao.IDAOAdmGame;
import asso.lh.dm.dao.IDAOAdmTheme;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmTheme;

@Service
public class AdmThemeService {

	@Autowired
	private IDAOAdmTheme daoAdmTheme;
	@Autowired
	private IDAOAdmGame daoAdmGame;
	
	public List<AdmTheme> getAll(){
		return daoAdmTheme.findAll();
	}
	
	public AdmTheme getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id null");
		}
		return daoAdmTheme.findById(id).orElseThrow(()->new RuntimeException("Aucun theme n'existe pour cet id"));
	}
	
	public AdmTheme getByName(String name) {
		if(name == null) {
			throw new RuntimeException("nom null");
		}
		return daoAdmTheme.findByName(name).orElseThrow(()->new RuntimeException("Aucun theme n'existe pour ce nom"));
	}
	
	public AdmTheme insert(AdmTheme theme) {
		if(theme == null) {
			throw new RuntimeException("theme null");
		}
		return daoAdmTheme.save(theme);
	}
	
	public AdmTheme update(AdmTheme theme) {
		if(theme == null) {
			throw new RuntimeException("theme null");
		}
		if(theme.getId() == null) {
			throw new RuntimeException("id null");
		}
		return daoAdmTheme.save(theme);
	}
	
	public void delete(AdmTheme theme) {
		
		
		List<AdmGame> games = daoAdmGame.findByTheme(theme);	
		
		games = games.stream().peek(game->game.getThemes().remove(theme)).collect(Collectors.toList());
			
		daoAdmGame.saveAll(games);
		
		daoAdmTheme.delete(theme);
	}
}
