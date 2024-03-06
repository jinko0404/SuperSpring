package com.kh.Super.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.Super.board.domain.ReplyVO;
import com.kh.Super.board.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	ReplyService rService;
	
	@ResponseBody
	@RequestMapping(value = "/reply/add.kh", method = RequestMethod.POST)
	public String insertReplyAjax(@ModelAttribute ReplyVO reply
			,HttpSession session) {
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			int result = 0;
			if(replyWriter != null && !replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				result = rService.insertReply(reply);
			}
			else {
				return "Login needed";
			}
			if(result > 0) {
				return "Success";
			}
			else {
				return "fail";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/reply/list.kh",
	produces = "application/json;charset=utf-8",
	method = RequestMethod.GET)
	public String showReplyList(@RequestParam("refBoardNo") Integer refBoardNo) {
		List<ReplyVO> rList= rService.selectReplyList(refBoardNo);
		//ReplyVO -> JSON 변환 시 json-simple 라이브러리 필요하다.
		//List -> JSON Array로 만들어서 리턴해줘야한다.
//		JSONObject json = new JSONObject();
//		JSONArray jsonArr = new JSONArray();
//		for(ReplyVO reply : rList) {
//			json.put("replyNo"		, reply.getReplyNo());
//			json.put("refBoardNo"	, reply.getRefBoardNo());
//			json.put("replyContent"	, reply.getReplyContent());
//			json.put("replyWriter"	, reply.getReplyWriter());
//			json.put("rCreateDate"	, reply.getrCreateDate());
//			json.put("rUpdateDate"	, reply.getrUpdateDate());
//			json.put("updateYn"		, reply.getUpdateYn());
//			json.put("rStatus"		, reply.getrStatus());
//			
//			jsonArr.add(json);
//		}
		Gson gson = new Gson();
		return gson.toJson(rList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/reply/delete.kh"
	, method = RequestMethod.POST)
	public String removeReplyAjax(@RequestParam("replyNo") Integer replyNo) {
		try {
			int result = rService.deleteReply(replyNo);
			if(result > 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/reply/update.kh"
			, method = RequestMethod.POST)
	public String modifyReply(@ModelAttribute ReplyVO reply) {
		try {
			int result = rService.updateReply(reply);
			if(result > 0) {
				return "success";
			}else {
				return "fail";							
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
