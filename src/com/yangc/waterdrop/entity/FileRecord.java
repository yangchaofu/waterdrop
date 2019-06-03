package com.yangc.waterdrop.entity;

/**
*  file_record表的实体类
* @author yangc 2019-06-03
*/
public class FileRecord {   

    /**
    * md5
    */
    private String md5;

    /**
    * file_name
    */
    private String fileName;

    public FileRecord() {
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}