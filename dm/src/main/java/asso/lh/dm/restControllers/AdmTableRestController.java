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

import asso.lh.dm.dto.requests.AdmTableRequest;
import asso.lh.dm.dto.responses.AdmTableResponse;
import asso.lh.dm.dto.responses.CustomJsonViews;
import asso.lh.dm.model.AdmGame;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.services.AdmGameService;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/table")
@CrossOrigin("*")
public class AdmTableRestController {

	@Autowired
	private AdmTableService tableSrv;
	@Autowired
	private AdmMemberService memberSrv;
	@Autowired
	private AdmGameService gameSrv;
	@Autowired
	private AdmPlayerTableService playerTableSrv;
	
	
	@GetMapping("")
	@JsonView(CustomJsonViews.TableWithAttributes.class)
	public List<AdmTableResponse> getAll(){
		return tableSrv.getAll().stream().map(table->{
			return new AdmTableResponse(table);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.TableWithAttributes.class)
	public AdmTableResponse getById(@PathVariable("id") Integer id) {
		return new AdmTableResponse(tableSrv.getById(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.TableWithAttributes.class)
	public AdmTableResponse create(@Valid @RequestBody AdmTableRequest tableRequest,BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmTable table = new AdmTable();
		BeanUtils.copyProperties(tableRequest, table);
		
		if(tableRequest.getGameMasterId()!=null) {
			AdmMember member = memberSrv.getById(tableRequest.getGameMasterId());
			table.setGameMaster(member);
		}
		
		if(tableRequest.getGameId()!=null) {
			AdmGame game = gameSrv.getById(tableRequest.getGameId());
			table.setGame(game);
		}
		
		if(tableRequest.getPlayersTableId()!=null) {
			List<AdmPlayerTable> pts =  new ArrayList<AdmPlayerTable>();
			pts = tableRequest.getPlayersTableId().stream().map(ptId->{
				return playerTableSrv.getById(ptId);
			}).collect(Collectors.toList());
			
			table.setPlayersTable(pts);
		}
		
		return new AdmTableResponse(tableSrv.insert(table));
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.TableWithAttributes.class)
	public AdmTableResponse update(@Valid @RequestBody AdmTableRequest tableRequest, BindingResult br,@PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmTable table = new AdmTable();
		BeanUtils.copyProperties(tableRequest, table);
		
		if(tableRequest.getGameMasterId()!=null) {
			AdmMember member = memberSrv.getById(tableRequest.getGameMasterId());
			table.setGameMaster(member);
		}
		
		if(tableRequest.getGameId()!=null) {
			AdmGame game = gameSrv.getById(tableRequest.getGameId());
			table.setGame(game);
		}
		
		if(tableRequest.getPlayersTableId()!=null) {
			List<AdmPlayerTable> pts =  new ArrayList<AdmPlayerTable>();
			pts = tableRequest.getPlayersTableId().stream().map(ptId->{
				return playerTableSrv.getById(ptId);
			}).collect(Collectors.toList());
			
			table.setPlayersTable(pts);
		}
		
		table.setId(id);
		
		return new AdmTableResponse(tableSrv.update(table));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		tableSrv.delete(tableSrv.getById(id));
	}
}
