package com.project.food.foodKind;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Transactional
@Service
public class BoardMngService {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.project.dao.BoardMngMapper.";
	
	// 게시판 수정
	public Map<String, Object> saveBoardMng(Map<String, Object> param) {
		Map<String, Object> resultMap = Maps.newHashMap();
	
		List<Map<String, Object>> insertList = (List<Map<String, Object>>)((param.get("insertMng") == null) ? Lists.newArrayList() : param.get("insertMng"));
		List<Map<String, Object>> updateList = (List<Map<String, Object>>)((param.get("updateMng") == null) ? Lists.newArrayList() : param.get("updateMng"));
		
		for(Map<String, Object> row: insertList ) {
			String bdTypeNm = sqlSession.selectOne(NAMESPACE + "getBdTypeNm", row);
			String bdId = sqlSession.selectOne(NAMESPACE + "getBdId", row);
			
			row.put("bd_type_nm", bdTypeNm);
			row.put("bd_id", bdId);
			
			sqlSession.insert(NAMESPACE + "insertBoardMng", row);
		}
		
		for(Map<String, Object> row: updateList) {
			String bdTypeNm = sqlSession.selectOne(NAMESPACE + "getBdTypeNm", row);
			
			row.put("chg_bd_type_nm", bdTypeNm);
			sqlSession.update(NAMESPACE + "updateBoardMng", row); 
		}
		
		resultMap.put("result", "S");
		return resultMap;
	}
	

}
