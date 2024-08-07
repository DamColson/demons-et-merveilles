package asso.lh.dm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.MemberTableKey;

public interface IDAOAdmPlayerTable extends JpaRepository<AdmPlayerTable, MemberTableKey>{
	
	List<AdmPlayerTable> findBySelectedTrue();
	List<AdmPlayerTable> findBySelectedFalse();
	
	@Query("select pt from AdmPlayerTable pt left join fetch pt.member as m where m = :member")
	List<AdmPlayerTable> findAllByMember(@Param("member") AdmMember member);

	@Query("select pt from AdmPlayerTable pt left join fetch pt.table as t where t = :table")
	List<AdmPlayerTable> findAllByTable(@Param("table") AdmTable table);
}
