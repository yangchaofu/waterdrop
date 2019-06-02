package com.yangc.waterdrop.ui;

import javafx.scene.control.ScrollPane;

public class ScrollPaneChart extends ScrollPane{
	public static ScrollPane scrollPaneChart;
	
	//使用单例模式来构建UI, 私有化构造方法
	private ScrollPaneChart() {
		scrollPaneChart = new ScrollPane();
		
		scrollPaneChart.setPrefHeight(350);
		scrollPaneChart.setStyle("-fx-background-color: white");
		scrollPaneChart.setContent(MyChart.getInstance());
	}
	
	   // 单例模式的获取实例的方法
    public static ScrollPane getInstance(){        
        if(null==scrollPaneChart){
        	scrollPaneChart = new ScrollPaneChart();
        }       
        return scrollPaneChart;
    }
	
}
