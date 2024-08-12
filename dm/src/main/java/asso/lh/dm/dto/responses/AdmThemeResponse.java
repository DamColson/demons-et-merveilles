package asso.lh.dm.dto.responses;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.AdmTheme;
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
	
	public AdmThemeResponse(AdmTheme theme) {
		this(theme,true);
	}
	
	public AdmThemeResponse(AdmTheme theme,boolean bool) {
		BeanUtils.copyProperties(theme, this);
		if(bool) {
			if(theme.getGames()!=null) {
				this.gamesResponse = theme.getGames().stream().map(game->{
					return new AdmGameResponse(game,false);
				}).collect(Collectors.toList());
			}
		}
	}
}
