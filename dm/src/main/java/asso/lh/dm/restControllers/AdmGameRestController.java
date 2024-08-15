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

import asso.lh.dm.dto.requests.AdmGameRequest;
import asso.lh.dm.dto.responses.AdmGameResponse;
import asso.lh.dm.dto.responses.CustomJsonViews;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.AdmTheme;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmTableService;
import asso.lh.dm.services.AdmThemeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/game")
@CrossOrigin("*")
public class AdmGameRestController {

	@Autowired
	private AdmGameService gameSrv;
	@Autowired
	private AdmTableService tableSrv;
	@Autowired
	private AdmThemeService themeSrv;
	
	
	@GetMapping("")
	@JsonView(CustomJsonViews.GameWithAttributes.class)
	public List<AdmGameResponse> getAll(){
		return gameSrv.getAll().stream().map(game->{
			return new AdmGameResponse(game);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.GameWithAttributes.class)
	public AdmGameResponse getById(@PathVariable("id") Integer id) {
		return new AdmGameResponse(gameSrv.getById(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.GameWithAttributes.class)
	public AdmGameResponse create(@Valid @RequestBody AdmGameRequest gameRequest,BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		AdmGame game = new AdmGame();
		BeanUtils.copyProperties(gameRequest, game);
		
		if(gameRequest.getTablesId()!=null) {
			List<AdmTable> tables = new ArrayList<>();
			tables = gameRequest.getTablesId().stream().map(id->{
				return tableSrv.getById(id);
			}).collect(Collectors.toList());
			
			game.setTables(tables);
		}
		
		if(gameRequest.getThemesId()!=null) {
			List<AdmTheme> themes = new ArrayList<>();
			themes = gameRequest.getThemesId().stream().map(id->{
				return themeSrv.getById(id);
			}).collect(Collectors.toList());
			
			game.setThemes(themes);
		}
		
		return new AdmGameResponse(gameSrv.insert(game));
	}
	
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.GameWithAttributes.class)
	public AdmGameResponse update(@Valid @RequestBody AdmGameRequest gameRequest, BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmGame game = new AdmGame();
		BeanUtils.copyProperties(gameRequest, game);
		
		if(gameRequest.getTablesId()!=null) {
			List<AdmTable> tables = new ArrayList<>();
			tables = gameRequest.getTablesId().stream().map(tableId->{
				return tableSrv.getById(tableId);
			}).collect(Collectors.toList());
			
			game.setTables(tables);
		}
		
		if(gameRequest.getThemesId()!=null) {
			List<AdmTheme> themes = new ArrayList<>();
			themes = gameRequest.getThemesId().stream().map(themeId->{
				return themeSrv.getById(themeId);
			}).collect(Collectors.toList());
			
			game.setThemes(themes);
		}
		
		game.setId(id);

		return new AdmGameResponse(gameSrv.update(game));
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		gameSrv.delete(gameSrv.getById(id));
	}
}
