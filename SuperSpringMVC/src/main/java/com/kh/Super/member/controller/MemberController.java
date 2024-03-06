package com.kh.Super.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.Super.member.domain.MemberVO;
import com.kh.Super.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;

	@RequestMapping(value="/member/register.kh", method=RequestMethod.GET)
	public String showInterform() {
		return "member/insert";
	}
	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public String insertMember(
			@ModelAttribute MemberVO member,
//			@RequestParam("memberId") String memberId,
//			@RequestParam("memberPw") String memberPw,
//			@RequestParam("memberName") String memberName,
//			@RequestParam("memberAge") int memberAge,
//			@RequestParam("memberGender") String memberGender,
//			@RequestParam("memberEmail") String memberEmail,
//			@RequestParam("memberPhone") String memberPhone,
//			@RequestParam("memberAddress") String memberAddress,
//			@RequestParam("memberHobby") String memberHobby,
			Model model) {
		try{
//			MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender
//			, memberEmail, memberPhone, memberAddress, memberHobby);
			
			int result = mService.insertMember(member);
			if(result > 0) {
				//로그인 페이지
				return "redirect:/index.jsp";
			}else {
				//실패하면 에러페이지
				//request.setAttribute("msg","service Failed");
				//request/getRequestDispatcher("").forward(request,response)
				model.addAttribute("msg","Service Failed!");
				return "common/errorPage";
			}
		}catch (Exception e) {
			//예외 발생시 에러페이지
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
			}
	}
	
	@RequestMapping(value = "/member/login.kh", method = RequestMethod.POST)
	public String memberLogin(
			@ModelAttribute MemberVO member
//			String memberId
//			,@RequestParam("memberPw") String memberPw
			,Model model
			,HttpSession session) {
		try {
//			MemberVO member = new MemberVO();
//			member.setMemberId(memberId);
//			member.setMemberPw(memberPw);
			member = mService.checkMemberLogin(member);
			if(member != null) {
				//로그인 성공
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				return "redirect:/index.jsp";
			}
			else {
				model.addAttribute("msg", "No Data Found!");
				return "common/errorPage";
			}	
		} catch(Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/member/logout.kh", method = RequestMethod.GET)
	public String memberLogout(HttpSession session, Model model) {
		try{
			if(session != null) {
				session.invalidate();
				return "redirect:/index.jsp";
			}
			else {
				model.addAttribute("msg", "로그아웃을 완료하지 못했습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "로그아웃을 완료하지 못했습니다.");
			return "common/errorPage";
		}
			
	}
	
	@RequestMapping(value = "/member/mypage.kh", method = RequestMethod.GET)
	public String showMyPage(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = null;
			if(memberId != null) {
				member = mService.getOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				session.setAttribute("memberAge", member.getMemberAge());
				session.setAttribute("memberGender", member.getMemberGender());
				session.setAttribute("memberEmail", member.getMemberEmail());
				session.setAttribute("memberPhone", member.getMemberPhone());
				session.setAttribute("memberAddress", member.getMemberAddress());
				session.setAttribute("memberHobby", member.getMemberHobby());
				return "member/mypage";
			}
			else {
				model.addAttribute("msg", "회원 정보 조회를 완료하지 못했습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	//수정페이지 이동
	@RequestMapping(value = "/member/update.kh", method = RequestMethod.GET)
	public String showModifyForm(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = null;
			if(memberId != null) {
				member = mService.getOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberPw", member.getMemberPw());
				session.setAttribute("memberName", member.getMemberName());
				session.setAttribute("memberAge", member.getMemberAge());
				session.setAttribute("memberGender", member.getMemberGender());
				session.setAttribute("memberEmail", member.getMemberEmail());
				session.setAttribute("memberPhone", member.getMemberPhone());
				session.setAttribute("memberAddress", member.getMemberAddress());
				session.setAttribute("memberHobby", member.getMemberHobby());
				return "member/modify";
			}
			else {
				model.addAttribute("msg", "회원 정보 조회를 완료하지 못했습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	//회원 정보 수정
	@RequestMapping(value = "/member/update.kh", method = RequestMethod.POST)
	public String memberModify(
			@ModelAttribute MemberVO member,
//			@RequestParam("memberId") String memberId,
//			@RequestParam("memberPw") String memberPw,
//			@RequestParam("memberEmail") String memberEmail,
//			@RequestParam("memberPhone") String memberPhone,
//			@RequestParam("memberAddress") String memberAddress,
//			@RequestParam("memberHobby") String memberHobby,
			Model model) {
		try {
//			MemberVO member = new MemberVO(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
			int result = mService.modifyMember(member);
			if(result > 0) {
				return "redirect:/member/mypage.kh";
			}
			else {
				model.addAttribute("msg", "정보를 수정하지 못했습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/member/delete.kh", method = RequestMethod.GET)
	public String deleteMember(String memberId,
			HttpSession session,
			Model model) {
		try {
			int result = 0;
			String sessionId = (String)session.getAttribute("memberId");
			//세션에 있는 아이디와 넘어오는 아이디가 같은지 확인
			if(sessionId != null && sessionId.equals(memberId)) {
				result = mService.deleteMember(memberId);				
			}
			else {
				model.addAttribute("msg", "로그인을 해주세요.");
				return "common/errorPage";
			}
			//탈퇴 성공을 하였는지 실패하였는지 확인
			if(result > 0) {
				return "redirect:/member/logout.kh";
			}
			else {
				model.addAttribute("msg", "탈퇴에 실패했습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
		
		
	}
}














