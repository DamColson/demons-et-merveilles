package asso.lh.dm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.MemberTableKey;

public interface IDAOAdmPlayerTable extends JpaRepository<AdmPlayerTable, MemberTableKey>{
	
	List<AdmPlayerTable> findBySelectedTrue();
	List<AdmPlayerTable> findBySelectedFalse();

}
