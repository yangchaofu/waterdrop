package com.yangc.waterdrop.service;

import com.yangc.waterdrop.dao.FileRecordDAO;

public class ImportFileService {
	private FileRecordDAO fileRecordDAO = new FileRecordDAO();
	private String filePath = null;
	
	public ImportFileService(String path) {
		this.filePath = path;
	}
	
	
	public boolean insertFileToData() {
		
		
		return true;
	}
}
