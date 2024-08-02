package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	/*
	 *  [1] Conenction 객체 생성
	 *  	- jdbc driver 등록
	 *  	- Connection 객체 생성
	 *  [2] DML문 실행 시 트랜잭션 처리
	 *  	- commit
	 *  	- rollback
	 *  [3] Connection 객체 반납
	 *  	- close
	 */
	public int insertMember(Member m) {
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO에게 전달받은 데이터와 Connection 객체를 전달하여
		// 		DB 처리 결과를 받기
		int result = new MemberDao().insertMember(conn, m);
		
		// 3) DML(INSERT) 실행 후 트랜잭션 처리
		if(result >0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public ArrayList<Member> selectList() {
		
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		
		return list;
	}

	public int updateById(String id, String column, String data) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateById(conn, id, column, data); 
		
		if(result >0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int delete(String id) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteById(conn, id);
		
		if(result >0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member search(String id) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().searchById(conn, id);
		
		if(m != null) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	public ArrayList<Member> selectByUserName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	
}
