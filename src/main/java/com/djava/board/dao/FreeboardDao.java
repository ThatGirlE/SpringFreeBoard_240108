package com.djava.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.djava.board.dto.FboardDto;

public class FreeboardDao {
	
	DataSource dataSource;

	public FreeboardDao() { //객체가 만들어지면 자동으로 실행되는 생성자
		super();
		// TODO Auto-generated constructor stub
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracledb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void write(String fbtitle, String fbname, String fbcontent) {  // 매개변수 설정
		Connection conn = null;
		//Statement stmt = null; //sql만들어주는 객체
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO freeboard(fbnum, fbtitle, fbname, fbcontent, fbhit) VALUES(freeboard_seq.nextval,?,?,?,0)"; // nextval 다음값 들어가라, preparedstatement의 장점 ?< 들어올값
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fbtitle);  //?자리에 들어가는 세팅
			pstmt.setString(2, fbname);
			pstmt.setString(3, fbcontent);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();  // 커넥션이 생기면 닫아줘라
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	// collection 객체들을 묶어 놓은것.  3가지 list, set, map
	public ArrayList<FboardDto> list() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null; // 빈그릇
		
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM FREEBOARD ORDER BY fbnum DESC"; // 내림차순으로 정렬된 모든 데이터 요청
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			dtos = new ArrayList<FboardDto>();
			
			while(rs.next()) { // 다음 레코드가 있으면 참 아니면 거짓
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
					
				FboardDto fboardDto = new FboardDto();
				
				fboardDto.setFbnum(fbnum);
				fboardDto.setFbname(fbname);
				fboardDto.setFbtitle(fbtitle);
				fboardDto.setFbcontent(fbcontent);
				fboardDto.setFbhit(fbhit);
				fboardDto.setFbdate(fbdate);
				
				dtos.add(fboardDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
public FboardDto content_view(String clickNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null; // 빈그릇
		
		FboardDto fboardDto = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM FREEBOARD WHERE fbnum=?"; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, clickNum);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) { // 다음 레코드가 있으면 참 아니면 거짓
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
					
				fboardDto = new FboardDto(fbnum, fbname, fbtitle, fbcontent, fbhit, fbdate);
				// 밑에 set이랑 같음
//				fboardDto.setFbnum(fbnum);
//				fboardDto.setFbname(fbname);
//				fboardDto.setFbtitle(fbtitle);
//				fboardDto.setFbcontent(fbcontent);
//				fboardDto.setFbhit(fbhit);
//				fboardDto.setFbdate(fbdate);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return fboardDto;
	}
	
}
