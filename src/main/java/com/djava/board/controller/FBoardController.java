package com.djava.board.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djava.board.command.FbContentCommand;
import com.djava.board.command.FbListCommand;
import com.djava.board.command.FbWriteCommand;
import com.djava.board.dao.FreeboardDao;
import com.djava.board.dto.FboardDto;

@Controller
public class FBoardController {
	
	// dao로 이동 주석처리
	
	//DataSource dataSource;
	
	@RequestMapping(value="/write_form")
	public String write_form() {
		return "write_form";
	
	}

	@RequestMapping(value="/write")
	public String write(HttpServletRequest request){
		
	
		
		String fbtitle = request.getParameter("fbtitle");
		String fbname = request.getParameter("fbname");
		String fbcontent = request.getParameter("fbcontent");
		
//		Context context;
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracledb");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Connection conn = null;
//		//Statement stmt = null; //sql만들어주는 객체
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO freeboard(fbnum, fbtitle, fbname, fbcontent, fbhit) VALUES(freeboard_seq.nextval,?,?,?,0)"; // nextval 다음값 들어가라, preparedstatement의 장점 ?< 들어올값
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, fbtitle);  //?자리에 들어가는 세팅
//			pstmt.setString(2, fbname);
//			pstmt.setString(3, fbcontent);
//			
//			pstmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();  // 커넥션이 생기면 닫아줘라
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		
		
		// fbwritecommand로 이동
//		FreeboardDao freeBoardDao = new FreeboardDao();
//		
//		freeBoardDao.write(fbtitle, fbname, fbcontent);
		
		FbWriteCommand command = new FbWriteCommand();
		command.execute(fbtitle, fbname, fbcontent);
		
				
		return "redirect:list"; // list 다시 받음
		
	}
	@RequestMapping(value = "/list")
	public String list(Model model) {
		FbListCommand command = new FbListCommand();
		ArrayList<FboardDto> dtos = command.execute();
		
		model.addAttribute("fboardDtos", dtos);
		
		return "list";
		
	}
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		String fbnum= request.getParameter("fbnum"); // 클릭한 글번호
		
		FbContentCommand command = new FbContentCommand();
		FboardDto fboardDto = command.execute(fbnum);
		
		model.addAttribute("fboardDto", fboardDto);
		
		return "content_view";
	}
}
