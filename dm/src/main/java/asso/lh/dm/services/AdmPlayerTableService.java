package asso.lh.dm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asso.lh.dm.dao.IDAOAdmPlayerTable;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.MemberTableKey;

@Service
public class AdmPlayerTableService {
	
	@Autowired
	private IDAOAdmPlayerTable daoPlayerTable;
	
	public List<AdmPlayerTable> getAll(){
		return daoPlayerTable.findAll();
	}
	
	public AdmPlayerTable getById(MemberTableKey id) {
		if(id==null) {
			throw new RuntimeException("id null");
		}
		return daoPlayerTable.findById(id).orElseThrow(()->new RuntimeException("aucun résultat pour cet id"));
	}
	
	public List<AdmPlayerTable> areSelected(){
		return daoPlayerTable.findBySelectedTrue();
	}
	
	public List<AdmPlayerTable> areNotSelected(){
		return daoPlayerTable.findBySelectedFalse();
	}
	
	public AdmPlayerTable insert(AdmPlayerTable playerTable) {
		if(playerTable==null) {
			throw new RuntimeException("élément null");
		}
		
		MemberTableKey key = new MemberTableKey();
		key.setMemberId(playerTable.getMember().getId());
		key.setTableId(playerTable.getTable().getId());
		playerTable.setId(key);
		playerTable.setSelected(false);
		
		return daoPlayerTable.save(playerTable);
	}
	
	public AdmPlayerTable update(AdmPlayerTable playerTable) {
		if(playerTable==null) {
			throw new RuntimeException("élément null");
		}
		
		if(playerTable.getId()==null) {
			throw new RuntimeException("id null");
		}
		
		return daoPlayerTable.save(playerTable);
	}
	
	public void delete(AdmPlayerTable playerTable) {
		
	}

}
