package com.project.food.foodKind;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Transactional
@Service
public class FoodService {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.project.dao.FoodMapper.";
	
	// 게시판 조회
	public List<Map<String, Object>> findBoardList(Map param) {
		List<Map<String, Object>> resultList = sqlSession.selectList(NAMESPACE + "findBoardList", param);
		
		for(Map<String , Object> row : resultList) {
			List<Map<String, Object>> dtlList = sqlSession.selectList(NAMESPACE + "findBoardDtlList", row);
			
			row.put("cate_menu", dtlList);
		}
		
		return resultList;
	}
	
	public List<Map<String, Object>> getBoardList(Map param) {
		List<Map<String, Object>> resultList = sqlSession.selectList(NAMESPACE + "getBoardList", param);
		
		return resultList;
	}
	
	public Map<String, Object> getBoardDetail(Map param) {
		Map<String, Object> resultMap = Maps.newHashMap(); 
				
		Map<String,Object> boardDetail = sqlSession.selectOne(NAMESPACE + "getBoardDetail", param);
		List<Map<String, Object>> commentList = sqlSession.selectList(NAMESPACE + "getCommentList", param);
		
		resultMap.put("article", boardDetail);
		resultMap.put("comments", commentList);
		
		return resultMap;
	}
	
	// 게시판 신규등록
	public Map<String, Object> insertFoodBoard(Map<String, Object> param) {
		Map<String, Object> resultMap = Maps.newHashMap();
		
		Map<String, Object> boardParam = (Map) param.get("foodInfo");
		Map<String, Object> menuParam = (Map) param.get("foodMenu");
		
		boardParam.put("board_key", UUID.randomUUID().toString());
		boardParam.put("bd_id", menuParam.get("bd_id"));
		
		sqlSession.insert(NAMESPACE + "insertFoodBoard", boardParam);
		
		resultMap.put("result", "S");
		
		return resultMap;
	}
	
	// 게시판 삭제
	public Map<String, Object> deleteFoodBoard(Map<String, Object> param) {
		Map<String, Object> resultMap = Maps.newHashMap();
		
		sqlSession.delete(NAMESPACE + "deleteFoodBoard", param);
		
		resultMap.put("result", "S");
		return resultMap;
	}
	
	// 게시판 수정
	public Map<String, Object> updateFoodBoard(Map<String, Object> param) {
		Map<String, Object> resultMap = Maps.newHashMap();
		sqlSession.update(NAMESPACE + "updateFoodBoard", param);
		
		resultMap.put("result", "S");
		return resultMap;
	}
	
	// 게시판 전체 조회
	public List<Map<String, Object>> findMenuAllList(Map param) {
		return sqlSession.selectList(NAMESPACE + "findMenuAllList", param);
	}
	
	// 댓글 저장
	public Map<String, Object> insertComment(Map<String, Object> param) {
		Map<String, Object> resultMap = Maps.newHashMap();
		
		System.out.println(param);
		
		Map<String, Object> commentInfo = (Map) param.get("commentInfo");
		Map<String, Object> article = (Map) param.get("article");
		
		commentInfo.put("board_key", article.get("board_key"));
		commentInfo.put("reply_key", UUID.randomUUID().toString());
		
		sqlSession.insert(NAMESPACE + "insertComment", commentInfo);
		
		resultMap.put("result", "S");
		resultMap.put("commentInfo", commentInfo);
		
		return resultMap;
	}
}
