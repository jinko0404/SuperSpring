package com.kh.Super.member.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.Super.member.domain.MemberVO;

public interface MemberStore {

	/**
	 * 회원 정보 등록 Store
	 * @param session
	 * @param member
	 * @return result
	 */
	int insertMember(SqlSession session, MemberVO member);

	/**
	 * 회원 로그인 Store
	 * @param session
	 * @param member
	 * @return mOne
	 */
	MemberVO checkMemberLogin(SqlSession session, MemberVO member);
	
	/**
	 * 회원 아이디 검색 Store
	 * @param session 
	 * @param memberId
	 * @return mOne;
	*/
	MemberVO selectOneById(SqlSession session, String memberId);

	/**
	 * 회원 정보 수정 Store
	 * @param session 
	 * @param member
	 * @return result;
	*/
	int modifyMember(SqlSession session, MemberVO member);

	/**
	 * 회원 탈퇴 Store
	 * @param session 
	 * @param memberId
	 * @return result;
	*/
	int deleteMember(SqlSession session, String memberId);

}
