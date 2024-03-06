package com.kh.Super.board.service;

import java.util.List;

import com.kh.Super.board.domain.BoardVO;
import com.kh.Super.board.domain.PageInfo;

public interface BoardService {

	/**
	 * 게시물 등록 Service
	 * @param board
	 * @return result
	 */
	int insertBoard(BoardVO board);

	/**
	 * 게시물 목록 조회 Service
	 * @param pInfo 
	 * @return bList
	 */
	List<BoardVO> selectNoticeList(PageInfo pInfo);

	/**
	 * 게시물 전체 갯수 Service 
	 * @return totalCount;
	 */
	int getTotalCount();
	
	/**
	 * 게시물 상세조회 Service
	 * @param boardNo
	 * @return BoardVO
	 */
	BoardVO selectOneByNo(Integer boardNo);

}
