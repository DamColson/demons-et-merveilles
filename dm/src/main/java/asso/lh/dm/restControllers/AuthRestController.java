package asso.lh.dm.restControllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


import asso.lh.dm.dto.responses.AdmMemberResponse;
import asso.lh.dm.model.AdmMember;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthRestController {

	@JsonView()
	@GetMapping("")
	public AdmMemberResponse auth(@AuthenticationPrincipal AdmMember member) {
		return new AdmMemberResponse(member);
	}
}
