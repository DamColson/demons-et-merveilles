package asso.lh.dm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.Role;

public interface IDAOAdmMember extends JpaRepository<AdmMember, Integer>{

	Optional<AdmMember> findByLogin(String login);
	
	Optional<AdmMember> findByMail(String mail);
	
	List<AdmMember> findByRole(Role role);
}
