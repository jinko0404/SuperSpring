package com.kh.Super.member.store;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.Super.member.domain.MemberVO;

@Repository
public class MemberStoreImpl implements MemberStore{

	@Override
	public int insertMember(SqlSession session, MemberVO member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public MemberVO checkMemberLogin(SqlSession session, MemberVO member) {
		MemberVO mOne = session.selectOne("MemberMapper.checkMemberLogin", member);
		return mOne;
	}

	@Override
	public MemberVO selectOneById(SqlSession session, String memberId) {
		MemberVO mOne = session.selectOne("MemberMapper.selectOneById", memberId);
		return mOne;
	}

	@Override
	public int modifyMember(SqlSession session, MemberVO member) {
		int result = session.insert("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember", memberId);
		return result;
	}

}
