package asso.lh.dm.restControllers;

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

import asso.lh.dm.dto.requests.AdmPlayerTableRequest;
import asso.lh.dm.dto.responses.AdmPlayerTableResponse;
import asso.lh.dm.dto.responses.CustomJsonViews;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.MemberTableKey;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/player-table")
@CrossOrigin("*")
public class AdmPlayerTableRestController {

	@Autowired
	private AdmPlayerTableService playerTableSrv;
	@Autowired
	private AdmTableService tableSrv;
	@Autowired
	private AdmMemberService memberSrv;
	
	@GetMapping("")
	@JsonView(CustomJsonViews.PlayerTableWithAttributes.class)
	public List<AdmPlayerTableResponse> getAll(){
		return playerTableSrv.getAll().stream().map(pt->{
			return new AdmPlayerTableResponse(pt);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{tableId}/{memberId}")
	@JsonView(CustomJsonViews.PlayerTableWithAttributes.class)
	public AdmPlayerTableResponse getById(@PathVariable("tableId") Integer tableId,@PathVariable("memberId") Integer memberId) {
		
		MemberTableKey id = new MemberTableKey();
		id.setMemberId(memberId);
		id.setTableId(tableId);
		
		return new AdmPlayerTableResponse(playerTableSrv.getById(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.PlayerTableWithAttributes.class)
	public AdmPlayerTableResponse create(@Valid @RequestBody AdmPlayerTableRequest ptRequest,BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmPlayerTable pt = new AdmPlayerTable();
		BeanUtils.copyProperties(ptRequest, pt);
		
		if(ptRequest.getMemberId()!=null){
			AdmMember member = memberSrv.getById(ptRequest.getMemberId());
			pt.setMember(member);
		}
		
		if(ptRequest.getTableId()!=null) {
			AdmTable table = tableSrv.getById(ptRequest.getTableId());
			pt.setTable(table);
		}
		
		return new AdmPlayerTableResponse(playerTableSrv.insert(pt));
		
	}
	
	@PutMapping("/{tableId}/{memberId}")
	@JsonView(CustomJsonViews.PlayerTableWithAttributes.class)
	public AdmPlayerTableResponse update(@Valid @RequestBody AdmPlayerTableRequest ptRequest,BindingResult br,@PathVariable("tableId") Integer tableId,
			@PathVariable("memberId") Integer memberId) {
		
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		AdmPlayerTable pt = new AdmPlayerTable();
		BeanUtils.copyProperties(ptRequest, pt);
		
		MemberTableKey key = new MemberTableKey();
		key.setMemberId(memberId);
		key.setTableId(tableId);
		
		pt.setId(key);
		
		if(ptRequest.getMemberId()!=null){
			AdmMember member = memberSrv.getById(ptRequest.getMemberId());
			pt.setMember(member);
		}
		
		if(ptRequest.getTableId()!=null) {
			AdmTable table = tableSrv.getById(ptRequest.getTableId());
			pt.setTable(table);
		}
		
		return new AdmPlayerTableResponse(playerTableSrv.update(pt));
		
	}
	
	@DeleteMapping("/{tableId}/{memberId}")
	public void delete(@PathVariable("tableId") Integer tableId,@PathVariable("memberId") Integer memberId) {
		MemberTableKey key = new MemberTableKey();
		key.setMemberId(memberId);
		key.setTableId(tableId);
		
		playerTableSrv.delete(playerTableSrv.getById(key));
	}
	
	
}
