package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

@Mapper
public interface FoodMapper {
	
	List<Map<String, Object>> findBoardList(Map<String, Object> param);
	
	// 게시판 메뉴 상세조회
	List<Map<String, Object>> findBoardDtlList(Map<String, Object> param);
	
	// 음식 게시판 조회
	List<Map<String, Object>> getBoardList(Map<String, Object> param);
	
	// 게시판 상세 조회
	Map<String, Object> getBoardDetail(Map<String, Object> param);
	
	// 댓글 조회
	List<Map<String, Object>> getCommentList(Map<String, Object> param);
	
	// 게시판 등록
	void insertFoodBoard(Map<String, Object> param);
	
	// 게시판 상세 삭제
	void deleteFoodBoard(Map<String, Object> param);
	
	// 게시판 상세 수정
	void updateFoodBoard(Map<String, Object> param);
	
	// 메뉴 전체 조회
	List<Map<String, Object>> findMenuAllList(Map<String, Object> param);
	
	void insertComment(Map<String, Object> param);
}
