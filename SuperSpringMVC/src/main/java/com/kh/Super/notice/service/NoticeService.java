package com.kh.Super.notice.service;

import java.util.List;

import com.kh.Super.notice.domain.NoticeVO;
import com.kh.Super.notice.domain.PageInfo;

public interface NoticeService {

	/**
	 * 공지사항 등록 Service
	 * @param notice
	 * @return result
	 */
	int insertNotice(NoticeVO notice);

	/**
	 * 공지사항 리스트 Service
	 * @param pInfo
	 * @return nList
	 */
	List<NoticeVO> selectNoticeList(PageInfo pInfo);
	
	/**
	 * 공지사항 전체 게시물 갯수 Service
	 * @return totalCount
	 */
	int getTotalCount();

	/**
	 * 공지사항 게시물 상세 조회 Service
	 * @param noticeNo
	 * @return notice
	 */
	NoticeVO selectNoticeByNo(int noticeNo);
	
	/**
	 * 공지사항 게시물 수정 Service
	 * @param notice
	 * @return result
	 */
	int updateNotice(NoticeVO notice);

	/**
	 * 공지사항 게시물 삭제 Service
	 * @param noticeNo
	 * @return result
	 */
	int deleteNotice(int noticeNo);

}
