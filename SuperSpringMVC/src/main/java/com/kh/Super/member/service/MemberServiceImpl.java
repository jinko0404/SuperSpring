package com.kh.Super.member.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.Super.member.domain.MemberVO;
import com.kh.Super.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(MemberVO member) {
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public MemberVO checkMemberLogin(MemberVO member) {
		MemberVO mOne = mStore.checkMemberLogin(session, member);
		return mOne;
	}

	@Override
	public MemberVO getOneById(String memberId) {
		MemberVO mOne = mStore.selectOneById(session, memberId);
		return mOne;
	}

	@Override
	public int modifyMember(MemberVO member) {
		int result = mStore.modifyMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

}
