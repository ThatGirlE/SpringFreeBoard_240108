package com.djava.board.command;

import com.djava.board.dao.FreeboardDao;

public class FbWriteCommand {

	public void execute(String fbtitle, String fbname, String fbcontent) {
		
		FreeboardDao freeBoardDao = new FreeboardDao();
		
		freeBoardDao.write(fbtitle, fbname, fbcontent);
		
	}
	
}
