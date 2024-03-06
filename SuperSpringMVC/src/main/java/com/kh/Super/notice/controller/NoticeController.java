package com.kh.Super.notice.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.Super.notice.domain.NoticeVO;
import com.kh.Super.notice.domain.PageInfo;
import com.kh.Super.notice.service.NoticeService;

@Controller//컨트롤러 빈 등록 해야된다.
public class NoticeController {
	@Autowired
	private NoticeService nService;
	
//	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.POST)
//	public String showInsertForm() {
//		return "notice/register";
//	}모델과 뷰가 따로 되어있어서 커스터마이징하기 쉽다.
	
	@RequestMapping(value = "/notice/detail.kh", method = RequestMethod.GET)
	public ModelAndView showNoticeDetail(ModelAndView mv, int noticeNo) {
		try {
			NoticeVO notice = nService.selectNoticeByNo(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice).setViewName("notice/detail");
			}
			else {
				mv.addObject("msg", "데이터가 존재하지 않습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/notice/list.kh", method = RequestMethod.GET)
	public ModelAndView showNoticeList(ModelAndView mv
			, @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try {
			int totalCount = nService.getTotalCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<NoticeVO> nList = nService.selectNoticeList(pInfo);
			mv.addObject("nList", nList);
			mv.addObject("pInfo", pInfo);
			mv.setViewName("notice/list");			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	private PageInfo getPageInfo(Integer currentPage, int totalCount) {
		PageInfo pi = null;
		int recordCountPerPage = 10;	//한 페이지당 보여줄 게시물 갯수
		int naviCountPerPage = 5;		//한 페이지당 보여줄 페이지 범위의 갯수
		int naviTotalCount;				//범위의 총갯수
		int startNavi;
		int endNavi;
		
		naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		startNavi = (((int)((double)currentPage / naviCountPerPage + 0.9)) - 1) * naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pi = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pi;
	}

	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.GET)
	public ModelAndView showInsertForm(ModelAndView mv) {
		mv.setViewName("notice/register");
		return mv;
	}
	
	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.POST)
	public ModelAndView insertNotice(ModelAndView mv
			,@ModelAttribute NoticeVO notice
			,@RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
			, HttpServletRequest request) {
		try {
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				Map<String, Object> infoMap = this.saveFile(uploadFile, request);
				String fileName = (String)infoMap.get("fileName");
				String fileReName = (String)infoMap.get("fileRename");
				String filePath = (String)infoMap.get("filePath");
				long fileSize = (long)infoMap.get("fileSize");
				
				notice.setNoticeFileName(fileName);
				notice.setNoticeFileRename(fileReName);
				notice.setNoticeFilePath(filePath);
				notice.setNoticeFileLength(fileSize);
			}
			int result = nService.insertNotice(notice);
			if(result > 0) {
				mv.setViewName("redirect:/notice/list.kh");
			}
			else {
				mv.addObject("msg", "공지사항 등록을 실패했습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/notice/modify.kh", method = RequestMethod.GET)
	public ModelAndView showModifyForm(ModelAndView mv, int noticeNo) {
		try {
			NoticeVO notice = nService.selectNoticeByNo(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice).setViewName("notice/modify");
			}
			else {
				mv.addObject("msg", "데이터가 존재하지 않습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/notice/modify.kh", method = RequestMethod.POST)
	public ModelAndView updateNotice(ModelAndView mv
			,@ModelAttribute NoticeVO notice
			,@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile
			,HttpServletRequest request) {
		try {
			//수정기능 1. 대체한다.
			//2. 삭제 후 등록
			if(reloadFile != null && !reloadFile.isEmpty()) {
				String fileName = notice.getNoticeFileRename();
				if(fileName != null) {
					//기존 파일 있을 시 기존 파일 삭제
					this.deleteFile(request, fileName);
				}
				//새로 업로드 하려는 파일 저장
				Map<String, Object> infoMap = this.saveFile(reloadFile, request);
				String noticeFileName = (String)infoMap.get("fileName");
				String noticeFileRename = (String)infoMap.get("fileRename");
				String noticeFilePath = (String)infoMap.get("filePath");
				long noticeFileLength = (long)infoMap.get("fileSize");
				notice.setNoticeFileName(noticeFileName);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilePath(noticeFilePath);
				notice.setNoticeFileLength(noticeFileLength);
			}
			int result = nService.updateNotice(notice);
			if(result > 0) {
				mv.setViewName("redirect:/notice/detail.kh?noticeNo=" + notice.getNoticeNo());
			}
			else {
				mv.addObject("msg", "데이터가 존재하지 않습니다");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}

	@RequestMapping(value = "/notice/delete.kh", method = RequestMethod.GET)
	public ModelAndView deleteNotice(ModelAndView mv, int noticeNo) {
		try {
			int result = nService.deleteNotice(noticeNo);
			if(result > 0) {
				mv.setViewName("redirect:/notice/list.kh");
				
			}
			else {
				mv.addObject("msg", "삭제할 데이터가 없습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	private void deleteFile(HttpServletRequest request, String fileName) {
		String rPath = request.getSession().getServletContext().getRealPath("resources");
		String delFilePath = rPath + "\\nuploadFiles\\" + fileName;
		File delFile = new File(delFilePath);
		if(delFile.exists()) {
			delFile.delete();
		}
	}

	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws IllegalStateException, IOException {
		//파일이름
		String fileName = uploadFile.getOriginalFilename();
		//저장 경로
		String projectPath = request.getSession().getServletContext().getRealPath("resources");
		String saveDirectory = projectPath + "\\nuploadFiles";
		File sDir = new File(saveDirectory);
		if(sDir.exists()) {//nuploadFiles 폴더가 없으면
			sDir.mkdir();//nuploadFiles폴더를 자동으로 생성
		}
		//파일 리네임의 필요성
		//A : 1.png  B: 1.png
		//내용은 다르지만 이름이 같은 파일을 구별하기 위해서
		//꼭 파일 리네임을 해줘야한다.
		//리네임을 할 때에는 올린 시각을 기준으로 파일이름을 만들어서 따로 저장한다.(NOTICE_FILERENAME 컬럼에 저장)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		String fileRename = strResult + "." + ext;
		
		String savePath = saveDirectory + "\\" + fileRename;
		
		File file = new File(savePath);
		//파일저장
		uploadFile.transferTo(file);
		//디비에 저장할 파일 정보 셋팅
		//1. 파일 이름
		//2. 파일 리네임
		//3. 파일 경로
		//4. 파일 크기
		long fileLength = uploadFile.getSize();
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		infoMap.put("filePath", savePath);
		infoMap.put("fileSize", fileLength);
		return infoMap;
	}
}












