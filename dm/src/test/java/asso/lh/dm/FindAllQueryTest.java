package asso.lh.dm;



import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.AdmTheme;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import asso.lh.dm.services.AdmThemeService;
import jakarta.transaction.Transactional;

@SpringBootTest
class FindAllQueryTest {

	@Autowired
	AdmMemberService memberSrv;
	@Autowired
	AdmGameService gameSrv;
	@Autowired
	AdmTableService tableSrv;
	@Autowired
	AdmPlayerTableService playerTableSrv;
	@Autowired
	AdmThemeService themeSrv;
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void findAllMembers() {
		List<AdmMember> members = memberSrv.getAll();
		members.stream().forEach(member->{
			System.out.println("------" + member.getLogin().toUpperCase() + "------");
			System.out.println("Mail : " + member.getMail());
			System.out.println("Role : " + member.getRole().toString());
			System.out.println("------------------");
			});
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void findAllGames() {
		List<AdmGame> games = gameSrv.getAll();
		games.stream().forEach(game->{
			System.out.println("------" + game.getName() + "------");
			System.out.println("description : " + game.getDescription());
			System.out.println("------------------");
		});
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void findAllTables() {
		List<AdmTable> tables = tableSrv.getAll();
		tables.stream().forEach(table->{
			System.out.println("------" + table.getGame().getName() + "------");
			System.out.println("Date : " + table.getDate().toString());
			table.getPlayersTable().stream().forEach(playerTable->{
				System.out.println(playerTable.getMember().getLogin() + " : selected = " + playerTable.isSelected());
			});
			System.out.println("------------------");
		});
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void findAllThemes() {
		List<AdmTheme> themes = themeSrv.getAll();
		themes.stream().forEach(theme->{
			System.out.println("Nom : " + theme.getName());
		});
		System.out.println("------------------");
	}
}
