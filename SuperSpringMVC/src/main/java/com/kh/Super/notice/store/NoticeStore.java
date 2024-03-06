package com.kh.Super.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.Super.notice.domain.NoticeVO;
import com.kh.Super.notice.domain.PageInfo;

public interface NoticeStore {

	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return result
	 */
	int insertNotice(SqlSession session, NoticeVO notice);

	/**
	 * 공지사항 목록 Store
	 * @param session
	 * @param pInfo
	 * @return nList
	 */
	List<NoticeVO> selectNoticeList(SqlSession session, PageInfo pInfo);

	/**
	 * 공지사항 전체 게시물 갯수 Store
	 * @param session
	 * @return totalCount
	 */
	int selectTotalCount(SqlSession session);
	/**
	 * 공지사항 게시물 상세 조회 Store
	 * @param session
	 * @param noticeNo
	 * @return notice
	 */
	NoticeVO selectNoticeByNo(SqlSession session, int noticeNo);
	
	/**
	 * 공지사항 게시글 수정 Store
	 * @param session
	 * @param notice
	 * @return result
	 */
	int updateNotice(SqlSession session, NoticeVO notice);

	/**
	 * 공지사항 게시글 삭제 Store
	 * @param session
	 * @param notice
	 * @return result
	 */
	int deleteNotice(SqlSession session, int noticeNo);

}
