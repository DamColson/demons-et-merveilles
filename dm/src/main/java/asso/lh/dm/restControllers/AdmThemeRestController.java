package asso.lh.dm.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import asso.lh.dm.dto.requests.AdmThemeRequest;
import asso.lh.dm.dto.responses.AdmThemeResponse;
import asso.lh.dm.dto.responses.CustomJsonViews;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmTheme;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmThemeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/theme")
@CrossOrigin("*")
public class AdmThemeRestController {

	@Autowired
	private AdmThemeService themeSrv;
	@Autowired
	private AdmGameService gameSrv;
	
	@GetMapping("")
	@JsonView(CustomJsonViews.ThemeWithGames.class)
	public List<AdmThemeResponse> getAll(){
		return themeSrv.getAll().stream().map(theme->{
			return new AdmThemeResponse(theme);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.ThemeWithGames.class)
	public AdmThemeResponse getById(@PathVariable("id") Integer id) {
		return new AdmThemeResponse(themeSrv.getById(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.ThemeWithGames.class)
	public AdmThemeResponse create(@Valid @RequestBody AdmThemeRequest themeRequest,BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmTheme theme = new AdmTheme();
		BeanUtils.copyProperties(themeRequest, theme);
		
		if(themeRequest.getGamesId()!=null) {
			List<AdmGame> games = new ArrayList<AdmGame>();
			games = themeRequest.getGamesId().stream().map(gameId->{
				return gameSrv.getById(gameId);
			}).collect(Collectors.toList());
			
			theme.setGames(games);
		}
		
		return new AdmThemeResponse(themeSrv.insert(theme));
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.ThemeWithGames.class)
	public AdmThemeResponse update(@Valid @RequestBody AdmThemeRequest themeRequest,BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmTheme theme = new AdmTheme();
		BeanUtils.copyProperties(themeRequest, theme);
		
		if(themeRequest.getGamesId()!=null) {
			List<AdmGame> games = new ArrayList<AdmGame>();
			games = themeRequest.getGamesId().stream().map(gameId->{
				return gameSrv.getById(gameId);
			}).collect(Collectors.toList());
			
			theme.setGames(games);
		}
		
		theme.setId(id);
		
		return new AdmThemeResponse(themeSrv.insert(theme));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		themeSrv.delete(themeSrv.getById(id));
	}
}
