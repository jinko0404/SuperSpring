package com.kh.Super.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.Super.board.domain.BoardVO;
import com.kh.Super.board.service.BoardService;
import com.kh.Super.board.domain.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	public ModelAndView showRegisterForm(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping(value = "/board/register.kh", method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "board/register";
	}
	
	@RequestMapping(value = "/board/register.kh", method = RequestMethod.POST)
	public String insertBoard(@ModelAttribute BoardVO board, Model model
			,HttpSession session
			,@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile
			,HttpServletRequest request) {
		try {
			String writer = (String)session.getAttribute("memberId");
			if(session != null && writer != null) {
				board.setBoardWriter(writer);
				if(uploadFile != null && !uploadFile.isEmpty()) {
					Map<String,Object> infoMap = this.saveFile(uploadFile, request);
					board.setBoardFileName((String)infoMap.get("fileName"));
					board.setBoardFileRename((String)infoMap.get("fileRename"));
					board.setBoardFilePath((String)infoMap.get("filePath"));
					board.setBoardFileLength((long)infoMap.get("fileLength"));
				}
			} else {
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "common/errorPage";
			}
			int result = bService.insertBoard(board);
			if(result > 0) {
				return "redirect:/board/list.kh";
			}
			else {
				model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
				return "common/errorPage";
			}			
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/board/list.kh", method = RequestMethod.GET)
	public String showNoticeList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try{
			int totalCount = bService.getTotalCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<BoardVO> bList = bService.selectNoticeList(pInfo);
			if(!bList.isEmpty()) {
				model.addAttribute("pInfo",pInfo);
				model.addAttribute("bList",bList);
			}
			else {
				model.addAttribute("bList",null);
			}
			return "board/list";						
		} catch (Exception e){
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/board/detail.kh", method = RequestMethod.GET)
	public String showNoticeDetail(Model model
			, Integer boardNo ) {
		try {
			BoardVO boardVO = bService.selectOneByNo(boardNo);
			if(boardVO != null) {
				model.addAttribute("board", boardVO);
				return "board/detail";
			}
			else {
				model.addAttribute("msg","게시물 데이터가 없습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		}
	}
	
	private PageInfo getPageInfo(Integer currentPage, int totalCount) {
		PageInfo pi = null;
		int recordCountPerPage = 10;	//한 페이지당 보여줄 게시물 갯수
		int naviCountPerPage = 10;		//한 페이지당 보여줄 페이지 범위의 갯수
		int naviTotalCount;				//범위의 총갯수
		int startNavi;
		int endNavi;
		
		naviTotalCount = (int)(Math.ceil((double)totalCount / recordCountPerPage));
		startNavi = ((int)(Math.ceil((double)currentPage / naviCountPerPage)) - 1) * naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pi = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pi;
	}

	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		String boardFolder = request.getSession().getServletContext().getRealPath("resources");
		String savePath = boardFolder + "\\buploadFiles";
		File saveFolder = new File(savePath);
		if(saveFolder.exists()) {
			saveFolder.mkdir();
		}
		String fileName = uploadFile.getOriginalFilename();
		
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String FileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + ext;
		
		File saveFile = new File(savePath + "\\" + FileRename);
		uploadFile.transferTo(saveFile);
		
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", FileRename);
		infoMap.put("filePath", "../resouces/buploadFike/" + FileRename);
		infoMap.put("fileLength", uploadFile.getSize());
		return infoMap;
	}
	
	
}
