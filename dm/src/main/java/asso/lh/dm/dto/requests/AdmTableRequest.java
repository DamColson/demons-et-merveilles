package asso.lh.dm.dto.requests;

import java.time.LocalDate;
import java.util.List;

import asso.lh.dm.model.MemberTableKey;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmTableRequest {

	@Future
	@NotNull
	private LocalDate date;
	private String requirement;
	@NotBlank
	private String estimatedDuration;
	private boolean openToNewPlayer;
	@NotNull
	private Integer gameId;
	@NotNull
	private Integer gameMasterId;
	private List<MemberTableKey> PlayersTableId;
}
