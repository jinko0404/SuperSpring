package com.kh.Super.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.kh.Super.board.domain.BoardVO;
import com.kh.Super.board.domain.PageInfo;

public interface BoardStore {

	/**
	 * 게시물 작성 Store
	 * @param session
	 * @param board
	 * @return result
	 */
	int insertBoard(SqlSession session, BoardVO board);

	/**
	 * 게시물 목록 조회 Store
	 * @param session
	 * @param pInfo 
	 * @return bList
	 */
	List<BoardVO> selectNoticeList(SqlSession session, PageInfo pInfo);

	/**
	 * 게시물 전체 갯수 Store 
	 * @param session 
	 * @return totalCount;
	 */
	int getTotalCount(SqlSession session);

	/**
	 * 게시물 상세조회
	 * @param session
	 * @param boardNo
	 * @return BoardVO
	 */
	BoardVO selectOneByNo(SqlSession session, Integer boardNo);

}
