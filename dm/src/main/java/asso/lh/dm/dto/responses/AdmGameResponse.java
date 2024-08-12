package asso.lh.dm.dto.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

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
	
	//private List<AdmThemeResponse> themesResponse;
	//private List<AdmTableResponse> tablesResponse;
}
