package Project_UI.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Project_UI.model.Member;

@Mapper
public interface MemberMapper {
	public void join(Member member);
	public Member findMemberById(long member_idn);
	public Member login(Member member);
	public Member findId(Member member);
	public Member findPw(Member member);
	public void updateMember(Member member);
	public void withdraw(long member_idn);
	public ArrayList<Member> findAllMember();
	public Member securityId(String member_id);
}
