package asso.lh.dm.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

import asso.lh.dm.dto.requests.AdmMemberRequest;
import asso.lh.dm.dto.requests.CustomValidation;
import asso.lh.dm.dto.responses.AdmMemberResponse;
import asso.lh.dm.dto.responses.CustomJsonViews;
import asso.lh.dm.model.AdmMember;
import asso.lh.dm.model.AdmPlayerTable;
import asso.lh.dm.model.AdmTable;
import asso.lh.dm.model.Role;
import asso.lh.dm.services.AdmMemberService;
import asso.lh.dm.services.AdmPlayerTableService;
import asso.lh.dm.services.AdmTableService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/member")
@CrossOrigin("*")
public class AdmMemberRestController {
	
	@Autowired
	private AdmMemberService memberSrv;

	@Autowired
	private AdmTableService tableSrv;
	
	@Autowired
	private AdmPlayerTableService playerTableSrv;
	
	@GetMapping("")
	@JsonView(CustomJsonViews.MemberWithAttributes.class)
	public List<AdmMemberResponse> getAll(){
		return memberSrv.getAll().stream().map(member->{
			return new AdmMemberResponse(member);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.MemberWithAttributes.class)
	public AdmMemberResponse getById(@PathVariable("id") Integer id) {
		return new AdmMemberResponse(memberSrv.getById(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.MemberWithAttributes.class)
	public AdmMemberResponse create(@Validated(CustomValidation.OnCreation.class) @RequestBody AdmMemberRequest memberRequest, BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			
		}
		AdmMember member = new AdmMember();
		BeanUtils.copyProperties(memberRequest, member);
		member.setRole(Role.valueOf(memberRequest.getRole()));
		
		if(memberRequest.getTablesId()!=null) {
			List<AdmTable> tables =  new ArrayList<>();
			tables = memberRequest.getTablesId().stream().map(tableId->{
				return tableSrv.getById(tableId);
			}).collect(Collectors.toList());
			member.setTables(tables);
		}
		
		if(memberRequest.getPlayersTableId()!=null) {
			List<AdmPlayerTable> pts = new ArrayList<>();
			pts = memberRequest.getPlayersTableId().stream().map(ptId->{
				return playerTableSrv.getById(ptId);
			}).collect(Collectors.toList());
			member.setPlayersTable(pts);
		}
		
		return new AdmMemberResponse(memberSrv.insert(member));
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.MemberWithAttributes.class)
	public AdmMemberResponse update(@Validated(CustomValidation.OnGeneralUpdate.class) @RequestBody AdmMemberRequest memberRequest, BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		AdmMember member = new AdmMember();
		BeanUtils.copyProperties(memberRequest, member,"password");
		member.setRole(Role.valueOf(memberRequest.getRole()));
		
		if(memberRequest.getTablesId()!=null) {
			List<AdmTable> tables =  new ArrayList<>();
			tables = memberRequest.getTablesId().stream().map(tableId->{
				return tableSrv.getById(tableId);
			}).collect(Collectors.toList());
			member.setTables(tables);
		}
		
		if(memberRequest.getPlayersTableId()!=null) {
			List<AdmPlayerTable> pts = new ArrayList<>();
			pts = memberRequest.getPlayersTableId().stream().map(ptId->{
				return playerTableSrv.getById(ptId);
			}).collect(Collectors.toList());
			member.setPlayersTable(pts);
		}
		member.setPassword(memberSrv.getById(id).getPassword());
		
		member.setId(id);
		
		return new AdmMemberResponse(memberSrv.update(member));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		memberSrv.delete(memberSrv.getById(id));
	}
}
