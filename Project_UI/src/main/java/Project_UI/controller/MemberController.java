package Project_UI.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Project_UI.model.Item;
import Project_UI.model.Member;
import Project_UI.security.UserInfo;
import Project_UI.service.ItemService;
import Project_UI.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
	private final MemberService memberService;
	private final ItemService itemService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원가입 이동
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("join", new Member());
		return "/joinForm";
	}
	
	// 회원가입
	@PostMapping("/joinForm")
	public String join(@Validated @ModelAttribute("join") Member member) {
		member.setMember_pw(bCryptPasswordEncoder.encode(member.getMember_pw()));
		memberService.join(member);
		return "redirect:/";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Member());
		return "/login";
	}
	
	// 로그인 성공
    @GetMapping(value = "/login-success")
    public String login_success(@AuthenticationPrincipal UserInfo userInfo) {
    	 log.info("userInfo : {}", userInfo);
    	return "redirect:/";
    }

    // 로그인 실패
    @GetMapping(value = "/login-fail")
    public String login_fail() {
    	log.info("login-fail");
        return "redirect:/login";
    }

    // 로그아웃
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
    
    // 마이페이지 이동
    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal UserInfo user) {
    	Member member = memberService.securityId(user.getUsername());
    	List<Item> sellList = itemService.sellList(user.getUsername());
    	List<Item> buyList = itemService.buyList(user.getUsername());
    	List<Item> rating = itemService.rating(user.getUsername());
    	model.addAttribute("mypage", member);
    	model.addAttribute("sellList", sellList);
    	model.addAttribute("buyList", buyList);
    	model.addAttribute("rating", rating);
    	return "/mypage";
    }
    
    // 회원정보 수정이동
    @GetMapping("/updateMember/{member_idn}")
    public String member_updateForm(Model model, @AuthenticationPrincipal UserInfo user, @PathVariable long member_idn) {
    	Member member = new Member();
    	model.addAttribute("updateMember", member);
    	return "/updateMember";
    }
    
    // 회원정보 수정
    @PostMapping("/updateMember")
    public String member_update(@ModelAttribute ("updateMember") Member member, @AuthenticationPrincipal UserInfo user) {
    	Member securityId = memberService.securityId(user.getUsername());
    	securityId.setMember_pw(member.getMember_pw());
    	securityId.setMember_nick(member.getMember_nick());
    	securityId.setMember_pw(bCryptPasswordEncoder.encode(securityId.getMember_pw()));
    	memberService.updateMember(securityId);
    	return "redirect:/mypage";
    }
    
    // 회원탈퇴
    @GetMapping("/withdraw/{member_idn}")
    public String withdraw(Model model, @AuthenticationPrincipal UserInfo user, @PathVariable long member_idn, HttpServletRequest request) {
    	Member securityId = memberService.securityId(user.getUsername());
    	memberService.withdraw(securityId.getMember_idn());
    	HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    	return "redirect:/";
    }
    
}
