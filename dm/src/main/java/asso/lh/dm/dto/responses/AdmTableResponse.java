package asso.lh.dm.dto.responses;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.AdmTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmTableResponse {

	@JsonView(CustomJsonViews.Common.class)
	private LocalDate date;
	
	@JsonView(CustomJsonViews.Common.class)
	private String requirement;
	
	@JsonView(CustomJsonViews.Common.class)
	private String estimatedDuration;
	
	@JsonView(CustomJsonViews.Common.class)
	private boolean openToNewPlayer;
	
	@JsonView(CustomJsonViews.TableWithGame.class)
	private AdmGameResponse gameResponse;
	
	@JsonView(CustomJsonViews.TableWithGameMaster.class)
	private AdmMemberResponse gameMasterResponse;
	
	@JsonView(CustomJsonViews.TableWithPlayerTable.class)
	private List<AdmPlayerTableResponse> playersTableResponse;
	
	public AdmTableResponse(AdmTable table) {
		this(table,true);
	}
	
	public AdmTableResponse(AdmTable table,boolean bool) {
		BeanUtils.copyProperties(table, this);
		if(bool) {
			if(table.getGame()!=null) {
				this.gameResponse = new AdmGameResponse(table.getGame(),false);
			}
			if(table.getGameMaster()!=null) {
				this.gameMasterResponse = new AdmMemberResponse(table.getGameMaster(),false);
			}
			if(table.getPlayersTable()!=null) {
				this.playersTableResponse = table.getPlayersTable().stream().map(pt->{
					return new AdmPlayerTableResponse(pt,false);
				}).collect(Collectors.toList());
			}
		}
	}
}
