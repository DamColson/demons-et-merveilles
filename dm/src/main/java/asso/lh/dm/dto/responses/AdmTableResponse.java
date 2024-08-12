package asso.lh.dm.dto.responses;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

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
	
	//private List<AdmPlayerTableResponse> playersTableResponse;
}
