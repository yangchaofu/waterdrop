package com.yangc.waterdrop.entity;

public class CountResult {
	private Integer id;
	private String tickDate;
	private Integer count;
	private Double speed;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTickDate() {
		return tickDate;
	}
	public void setTickDate(String tickDate) {
		this.tickDate = tickDate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

}
