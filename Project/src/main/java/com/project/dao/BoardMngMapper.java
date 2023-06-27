package com.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

@Mapper
public interface BoardMngMapper {
	
	// 게시판 관리 수정
	void updateBoardMng(Map<String, Object> param);
	
	// 게시판 등록
	void insertBoardMng(Map<String, Object> param);
	
	// type 명 가져오기
	String getBdTypeNm(Map<String, Object> param);
	
	// bd_id 가져오기
	String getBdId(Map<String, Object> param);
	
}
