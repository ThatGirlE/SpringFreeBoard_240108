package com.djava.board.command;

import com.djava.board.dao.FreeboardDao;
import com.djava.board.dto.FboardDto;

public class FbContentCommand {
	
	public FboardDto execute(String fbnum) {
	
		FreeboardDao freeboardDao = new FreeboardDao();
		FboardDto fboardDto = freeboardDao.content_view(fbnum);
		
		return fboardDto;
	}
}
