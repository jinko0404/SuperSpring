package com.kh.Super.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.Super.board.domain.ReplyVO;
import com.kh.Super.board.store.ReplyStore;


@Repository
public class ReplyStoreImpl implements ReplyStore{

	@Override
	public int insertReply(SqlSession session, ReplyVO replyVO) {
		int result = session.insert("ReplyMapper.insertReply", replyVO);
		return result;
	}

	@Override
	public List<ReplyVO> selectReplyList(SqlSession session, Integer refBoardNo) {
		List<ReplyVO> rList = session.selectList("ReplyMapper.selectReplyList", refBoardNo);
		return rList;
	}

	@Override
	public int deleteReply(SqlSession session, Integer replyNo) {
		int result = session.delete("ReplyMapper.deleteReply", replyNo);
		return result;
	}

	@Override
	public int updateReply(SqlSession session, ReplyVO reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}

}