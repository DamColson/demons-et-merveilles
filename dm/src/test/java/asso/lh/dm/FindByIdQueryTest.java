package asso.lh.dm;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.transaction.Transactional;

@SpringBootTest
class FindByIdQueryTest {

	@Autowired
	AdmMemberService memberSrv;
	@Autowired
	AdmGameService gameSrv;
	@Autowired
	AdmTableService tableSrv;
	@Autowired
	AdmPlayerTableService playerTableSrv;
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void findMemberById() {
		AdmMember member = memberSrv.getById(6);
		System.out.println("------" + member.getLogin() + "------");
		System.out.println("Mail : " + member.getMail());
		System.out.println("id : " + member.getId());
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void findGameById() {
		AdmGame game = gameSrv.getById(1);
		System.out.println("------" + game.getName() + "------");
		System.out.println(game.getDescription());
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void findTableById() {
		AdmTable table = tableSrv.getById(1);
		System.out.println("------" + table.getGame().getName() + "------");
		System.out.println("MJ : " + table.getGameMaster().getLogin());
		System.out.println("Date : " + table.getDate().toString());
		table.getPlayersTable().stream().forEach(playerTable->{
			System.out.println(playerTable.getMember().getLogin() + " -> choisi : " + playerTable.isSelected());
		});
	}

}
