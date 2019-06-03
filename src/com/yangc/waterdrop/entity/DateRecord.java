package com.yangc.waterdrop.entity;

import java.util.Date;

/**
 * date_record表的实体类
 * 
 * @author yangc 2019-06-03
 */
public class DateRecord {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * date
	 */
	private Date date;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}