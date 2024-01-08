package com.djava.board.command;

import java.util.ArrayList;

import com.djava.board.dao.FreeboardDao;
import com.djava.board.dto.FboardDto;

public class FbListCommand {
	
	public ArrayList<FboardDto> execute() {
		FreeboardDao freeboardDao = new FreeboardDao();
		ArrayList<FboardDto> dtos = freeboardDao.list();
		
		return dtos; 
		
	}
	
	
	
}
