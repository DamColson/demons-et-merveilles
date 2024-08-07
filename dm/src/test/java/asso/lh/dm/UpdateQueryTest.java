package asso.lh.dm;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.Theme;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.transaction.Transactional;

@SpringBootTest
class UpdateQueryTest {

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
	void updateMemberTest() {
		AdmMember member = memberSrv.getByLogin("Fireloup");
		member.setMail("damienc@gmail.com");
		memberSrv.update(member);
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void updateGameTest() {
		AdmGame game = gameSrv.getByName("Delta Green");
		List<Theme> themes = game.getThemes();
		themes.add(Theme.Moderne);
		game.setThemes(themes);
		gameSrv.update(game);
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void updateTableTest() {
		AdmTable table = tableSrv.getById(1);
		List<AdmPlayerTable> playersTable = table.getPlayersTable();
		playersTable.stream().forEach(entity->{
			entity.setSelected(false);
			playerTableSrv.update(entity);
		});
	}
}
