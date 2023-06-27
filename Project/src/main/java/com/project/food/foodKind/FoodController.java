package com.project.food.foodKind;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping(value="foodkind")
public class FoodController {
	
	@Autowired
	FoodService foodService;

	@RequestMapping(value="/findBoardList")
	public @ResponseBody List<Map<String, Object>> findBoardList(Map<String, Object> param) {
		List<Map<String, Object>> resultList = foodService.findBoardList(param);
		
		return resultList;
	}
	
	@RequestMapping(value="/getBoardList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getBoardList(@RequestBody Map<String, Object> param) {
		List<Map<String, Object>> resultList = foodService.getBoardList(param);
		
		return resultList;
	}
	
	// 게시판 상세조회
	@RequestMapping(value="/getBoardDetailInfo", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBoardDetail(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultList = foodService.getBoardDetail(param);
		
		return resultList;
	}
	
	// 게시판 신규 등록
	@RequestMapping(value="/insertFoodBoard", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertFoodBoard(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = foodService.insertFoodBoard(param);
		
		return resultMap;
	}
	
	// 게시판 삭제
	@RequestMapping(value="/deleteFoodBoard", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteFoodBoard(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = foodService.deleteFoodBoard(param);
		
		return resultMap;
	}
	
	// 게시판 수정
	
	@RequestMapping(value="/updateFoodBoard", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateFoodBoard(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = foodService.updateFoodBoard(param);
		
		return resultMap;
	}
	
	// 메뉴 전체조회
	@RequestMapping(value="/findMenuAllList")
	public @ResponseBody List<Map<String, Object>> findMenuAllList(Map<String, Object> param) {
		List<Map<String, Object>> resultList = foodService.findMenuAllList(param);
		
		return resultList;
	}

	// 댓글 저장
	@RequestMapping(value="/insertComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertComment(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = foodService.insertComment(param);
		
		return resultMap;
	}
}
