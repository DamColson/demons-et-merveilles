package asso.lh.dm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import asso.lh.dm.dao.IDAOAdmMember;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.Role;

@Service
public class AdmMemberService implements UserDetailsService{

	@Autowired
	private IDAOAdmMember daoAdmMember;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public List<AdmMember> getAll(){
		return daoAdmMember.findAll();
	}
	
	public AdmMember getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id null");
		}
		return daoAdmMember.findById(id).orElseThrow(()-> new RuntimeException("Aucun membre n'existe pour cet id"));
	}
	
	public AdmMember getByLogin(String login) {
		if(login==null) {
			throw new RuntimeException("login null");
		}
		return daoAdmMember.findByLogin(login).orElseThrow(()->new RuntimeException("Aucun membre n'existe pour ce login"));
	}
	
	public AdmMember getByMail(String mail) {
		if(mail==null) {
			throw new RuntimeException("mail null");
		}
		return daoAdmMember.findByMail(mail).orElseThrow(()->new RuntimeException("Aucun membre n'existe pour ce mail"));
	}
	
	public List<AdmMember> getByRole(Role role){
		if(role==null) {
			throw new RuntimeException("role null");
		}
		return daoAdmMember.findByRole(role);
	}
	
	public AdmMember insert(AdmMember member) {
		if(member == null) {
			throw new RuntimeException("membre null");
		}
		
		member.setPassword(bcryptEncoder.encode(member.getPassword()));
		return daoAdmMember.save(member);
	}
	
	public AdmMember update(AdmMember member) {
		if(member==null) {
			throw new RuntimeException("membre null");
		}
		if(member.getId()==null) {
			throw new RuntimeException("id du membre null");
		}
		return daoAdmMember.save(member);
	}
	
	public void delete(AdmMember member) {
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return daoAdmMember.findByLogin(username).orElseThrow(()->{
			throw new UsernameNotFoundException("Utilisateur inconnu");});
	}
	
}
