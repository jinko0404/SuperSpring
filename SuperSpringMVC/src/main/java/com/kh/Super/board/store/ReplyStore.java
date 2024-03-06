package com.kh.Super.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.Super.board.domain.ReplyVO;


public interface ReplyStore {

	/**
	 * 댓글 등록 Store
	 * @param replyVO
	 * @return int
	 */
	int insertReply(SqlSession session, ReplyVO replyVO);

	/**
	 * 댓글 목록 조회 Store
	 * @param session
	 * @return List
	 */
	List<ReplyVO> selectReplyList(SqlSession session, Integer refBoardNo);

	/**
	 * 댓글 삭제 Store
	 * @param session
	 * @param replyNo
	 * @return int
	 */
	int deleteReply(SqlSession session, Integer replyNo);

	/**
	 * 댓글 수정 Store
	 * @param session
	 * @param reply
	 * @return int
	 */
	int updateReply(SqlSession session, ReplyVO reply);

}