package asso.lh.dm;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.MemberTableKey;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.transaction.Transactional;

@SpringBootTest
class DeleteQueryTest {
	
	@Autowired
	private AdmMemberService memberSrv;
	@Autowired
	private AdmTableService tableSrv;
	@Autowired
	private AdmGameService gameSrv;
	@Autowired
	private AdmPlayerTableService playerTableSrv;

	@Test
	@Commit
	@Transactional
	@Disabled
	void deleteMemberTest() {		
		AdmMember member = memberSrv.getByLogin("Beurrarghasst");
		memberSrv.delete(member);
	}
	
	@Test
	@Commit
	@Transactional
	@Disabled
	void deleteGameTest() {
		AdmGame game = gameSrv.getByName("Ravnica");
		gameSrv.delete(game);
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void deleteTableTest() {
		AdmTable table= tableSrv.getById(2);
		tableSrv.delete(table);
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void deletePlayerTableTest() {
		MemberTableKey key = new MemberTableKey();
		AdmMember member = memberSrv.getByLogin("Brice");
		AdmTable table = tableSrv.getById(1);
		key.setMemberId(member.getId());
		key.setTableId(table.getId());
		AdmPlayerTable playerTable = playerTableSrv.getById(key);
		playerTableSrv.delete(playerTable);
	}
	

}
