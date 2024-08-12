package asso.lh.dm.dto.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmThemeResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String name;
	@JsonView(CustomJsonViews.ThemeWithGames.class)
	private List<AdmGameResponse> gamesResponse;
}
