package asso.lh.dm;



import java.time.LocalDate;
import java.util.ArrayList;
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
import asso.lh.dm.model.Role;
import asso.lh.dm.model.Theme;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.transaction.Transactional;

@SpringBootTest
class InsertQueryTest {

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
	void insertAdmin() {
		AdmMember member = new AdmMember();
		member.setLogin("admin");
		member.setMail("admin@gmail.com");
		member.setPassword("admin");
		member.setRole(Role.ROLE_ADMIN);
		
		memberSrv.insert(member);
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void insertGame() {
		AdmGame game = new AdmGame();
		game.setName("Delta Green");
		List<Theme> themes = new ArrayList<>();
		themes.add(Theme.Enquête);
		themes.add(Theme.Conspiration);
		themes.add(Theme.Horreur);
		game.setThemes(themes);
		game.setDescription("Ne peuvent jouer que les personnes qui ont lu ce message et respecté la demande. (les personnes n'ayant pas accès au Discord auront un joker sous la forme d'un pré-tiré simple)\r\n"
				+ "__\r\n"
				+ "\r\n"
				+ "Je précise juste : je ne compte pas faire une partie dans le pur style d'origine du jeu qui implique de mourir ou devenir fou en moyenne 3 fois par partie\r\n"
				+ "\r\n"
				+ "On sera proche du rythme d'une série comme X-files, Dark Skies, Twin Peaks ou The Lost Room avec un soupçon de Supernatural, le tout plus ou moins prononcé selon les personnages (pour résumer, des complots, des secrets et du fantastique mystérieux avec un peu d'action)\r\n"
				+ "\r\n"
				+ "Le doute et/ou la folie arrivant sur le long terme et faisant partie du concept.\r\n"
				+ "\r\n"
				+ "- Pour ceux qui n'en ont pas encore je m'occupe de préparer vos personnages, vous avez juste à choisir un événement particulier qui vous démarque du lot et qui donne à votre personnage une ouverture d'esprit (même légère) sur l'anormal\r\n"
				+ "On considérera que vous avez été contacté / recruté par un office semi officiel d'enquête validé par l'état mais sans une complète liberté d'action. (des détectives consultants en para-normal pour résumer) \r\n"
				+ "par exemple\r\n"
				+ "Un scientifique / météorologue ayant capté une trace sonore/biologique qui ne semble pas tout à fait normale, il se peut que son échantillon soit juste endommagé/falsifié, peut être depuis a t-il constaté des \"coïncidences\" autour de lui (accidents, disparitions...)\r\n"
				+ "\r\n"
				+ "Une personne ayant vécu l’expérience de se réveiller dans un endroit insolite, peut être est ce seulement un blackout ou autre chose\r\n"
				+ "\r\n"
				+ "Un ancien membre d'un service de sécurité ou d'une agence gouvernementale ayant participé de loin à une mission qui éveille en lui des doutes\r\n"
				+ "\r\n"
				+ "Un citoyen lambda ayant été recruté dans une secte (scientologie, Rael, communisme...) et s'en étant sorti.\r\n"
				+ "\r\n"
				+ "Une personne sans histoire qui un jour a vécu une expérience qu'elle pourrait qualifier d'extraterrestre.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Autre point important : on tire à balles réelles.\r\n"
				+ "je m'explique, votre personnage sera le seul que vous jouerez lors de cette campagne.\r\n"
				+ "Pas de sacrifice héroïque chez moi, seules les abysses vous attendent après.\r\n"
				+ "\r\n"
				+ "La demande : Pour pouvoir jouer il suffit de trouver une idée de perso, et d'écrire quelques lignes dessus son état psychologique, sa famille, sa vision du monde, son métier... et un évènement anormal qu'il a vécu, ou croit avoir vécu.");
		gameSrv.insert(game);
	
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void insertMembers() {
		AdmMember brice = new AdmMember();
		brice.setLogin("Brice");
		brice.setMail("brice@gmail.com");
		brice.setPassword("brice");
		brice.setRole(Role.ROLE_MEMBRE);
		
		AdmMember theo = new AdmMember();
		theo.setLogin("Azimute");
		theo.setMail("theo@gmail.com");
		theo.setPassword("theo");
		theo.setRole(Role.ROLE_MEMBRE);
		
		AdmMember damien = new AdmMember();
		damien.setLogin("Fireloup");
		damien.setMail("damien@gmail.com");
		damien.setPassword("damien");
		damien.setRole(Role.ROLE_MEMBRE);
		
		AdmMember tibo = new AdmMember();
		tibo.setLogin("Tibo");
		tibo.setMail("tibo@gmail.com");
		tibo.setPassword("tibo");
		tibo.setRole(Role.ROLE_MEMBRE);
		
		AdmMember lenan = new AdmMember();
		lenan.setLogin("Beurrarghasst");
		lenan.setMail("lenan@gmail.com");
		lenan.setPassword("lenan");
		lenan.setRole(Role.ROLE_MEMBRE);
		
		AdmMember william = new AdmMember();
		william.setLogin("ashaba.cheshire");
		william.setMail("william@gmail.com");
		william.setPassword("william");
		william.setRole(Role.ROLE_MEMBRE);
		
		AdmMember poupoule = new AdmMember();
		poupoule.setLogin("Poupoulee");
		poupoule.setMail("poupoule@gmail.com");
		poupoule.setPassword("poupoule");
		poupoule.setRole(Role.ROLE_BUREAU);
		
		memberSrv.insert(brice);
		memberSrv.insert(theo);
		memberSrv.insert(damien);
		memberSrv.insert(tibo);
		memberSrv.insert(lenan);
		memberSrv.insert(william);
		memberSrv.insert(poupoule);
		
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void insertTable() {
		AdmTable table = new AdmTable();
		table.setDate(LocalDate.of(2024, 8, 17));
		table.setEstimatedDuration("14h-00h");
		AdmGame game = gameSrv.getByName("Delta Green");
		AdmMember gameMaster = memberSrv.getByLogin("Beurrarghasst");
		table.setGame(game);
		table.setGameMaster(gameMaster);
		table.setOpenToNewPlayer(false);
		table.setRequirement("respecter la demande dans la description");
		
		tableSrv.insert(table);
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void insertMembersToTable() {
		AdmTable table = tableSrv.getById(1);
		
		AdmPlayerTable pt1 = new AdmPlayerTable();		
		pt1.setMember(memberSrv.getByLogin("Fireloup"));
		pt1.setTable(table);
		pt1.setSelected(true);
		
		AdmPlayerTable pt2 = new AdmPlayerTable();		
		pt2.setMember(memberSrv.getByLogin("Brice"));
		pt2.setTable(table);
		pt2.setSelected(true);
		
		AdmPlayerTable pt3 = new AdmPlayerTable();		
		pt3.setMember(memberSrv.getByLogin("Azimute"));
		pt3.setTable(table);
		pt3.setSelected(true);
		
		AdmPlayerTable pt4 = new AdmPlayerTable();		
		pt4.setMember(memberSrv.getByLogin("Tibo"));
		pt4.setTable(table);
		pt4.setSelected(true);
		
		AdmPlayerTable pt5 = new AdmPlayerTable();		
		pt5.setMember(memberSrv.getByLogin("ashaba.cheshire"));
		pt5.setTable(table);
		pt5.setSelected(true);
		
		AdmPlayerTable pt6 = new AdmPlayerTable();		
		pt6.setMember(memberSrv.getByLogin("Poupoulee"));
		pt6.setTable(table);
		pt6.setSelected(false);
		
		playerTableSrv.insert(pt1);
		playerTableSrv.insert(pt2);
		playerTableSrv.insert(pt3);
		playerTableSrv.insert(pt4);
		playerTableSrv.insert(pt5);
		playerTableSrv.insert(pt6);
	
	}
}
