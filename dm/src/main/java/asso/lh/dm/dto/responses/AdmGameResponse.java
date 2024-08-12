package asso.lh.dm.dto.responses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.model.AdmGame;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmGameResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	
	@JsonView(CustomJsonViews.Common.class)
	private String name;
	
	@JsonView(CustomJsonViews.Common.class)
	private String description;
	
	@JsonView(CustomJsonViews.GameWithThemes.class)
	private List<AdmThemeResponse> themesResponse;
	
	@JsonView(CustomJsonViews.GameWithTables.class)
	private List<AdmTableResponse> tablesResponse;
	
	public AdmGameResponse(AdmGame game) {
		this(game,true);
	}
	
	public AdmGameResponse(AdmGame game,boolean bool) {
		BeanUtils.copyProperties(game, this);
		if(bool) {
			if(game.getThemes()!=null) {
				this.themesResponse = game.getThemes().stream().map(theme->{
					return new AdmThemeResponse(theme,false);
				}).collect(Collectors.toList());
			}
			
			if(game.getTables()!=null) {
				this.tablesResponse = game.getTables().stream().map(table->{
					return new AdmTableResponse(table,false);
				}).collect(Collectors.toList());
			}
		}
	}
}
