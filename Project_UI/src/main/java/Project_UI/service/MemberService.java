package Project_UI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Project_UI.model.Member;
import Project_UI.repository.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberMapper memberMapper;
	
	public void join(Member member) {
		memberMapper.join(member);
	}
	
	public Member findMemberById(long member_idn) {
		return memberMapper.findMemberById(member_idn);
	}
	
	public Member login(Member member) {
		return memberMapper.login(member);
	}
	
	public Member findId(Member member) {
		return memberMapper.findId(member);
	}
	
	public Member findPw(Member member) {
		return memberMapper.findPw(member);
	}
	
	public void updateMember(Member member) {
		memberMapper.updateMember(member);
	}
	
	public void withdraw(long member_idn) {
		memberMapper.withdraw(member_idn);
	}
	
	public ArrayList<Member> findAllMember(){
		return memberMapper.findAllMember();
	}
	
	public Member securityId(String member_id) {
		return memberMapper.securityId(member_id);
	}
}
