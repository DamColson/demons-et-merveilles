package asso.lh.dm.dto.responses;

public class CustomJsonViews {

	public interface Common{
		
	}
	
	//Member Custom Views
	
	public interface MemberWithTables extends Common{
		
	}
	
	public interface MemberWithPlayersTable extends Common{
		
	}
	
	public class MemberWithAttributes implements MemberWithPlayersTable,MemberWithTables{
		
	}
	
	//Game Custom Views
	
	public interface GameWithThemes extends Common{
		
	}
	
	public interface GameWithTables extends Common{
		
	}
	
	public class GameWithAttributes implements GameWithThemes,GameWithTables{
		
	}
	
	//Theme Custom Views
	
	public interface ThemeWithGames extends Common{
		
	}
	
	//Table Custom Views
	
	public interface TableWithGame extends Common{
		
	}
	
	public interface TableWithGameMaster extends Common{
		
	}
	
	public interface TableWithPlayerTable extends Common{
		
	}
	
	public class TableWithAttributes implements TableWithGame,TableWithGameMaster{
		
	}
}
