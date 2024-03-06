package com.kh.Super.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.Super.board.domain.BoardVO;
import com.kh.Super.board.service.BoardService;
import com.kh.Super.board.store.BoardStore;
import com.kh.Super.board.domain.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private SqlSession session;
	@Autowired
	private BoardStore bStore;
	
	@Override
	public int insertBoard(BoardVO board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	@Override
	public List<BoardVO> selectNoticeList(PageInfo pInfo) {
		List<BoardVO> bList = bStore.selectNoticeList(session, pInfo);
		return bList;
	}

	@Override
	public int getTotalCount() {
		int totalCount = bStore.getTotalCount(session);
		return totalCount;
	}

	@Override
	public BoardVO selectOneByNo(Integer boardNo) {
		BoardVO boardVO = bStore.selectOneByNo(session, boardNo);
		return boardVO;
	}
}
