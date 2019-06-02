package com.yangc.waterdrop.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CenterPane extends Pane {
	public static Pane centerPane = new CenterPane();
	// 程序中间面板的图表区域, 滚动面板, 会左右延伸, 该面板中是MyChart的实例,  由该面板维护
	private static ScrollPane scrollPaneChart;
	// 程序中间面板的表格区域, 滚动面板, 会上下延伸, 该面板中是一个Table类, 由 该面板维护
	private static ScrollPane scrollPaneTable;

	// 单例模式, 私有化构造方法
	private CenterPane() {
		centerPane = new VBox();
		setScrollPaneChart(ScrollPaneChart.getInstance());
		setScrollPaneTable(ScrollPaneTable.getInstance());
		scrollPaneChart.setStyle("-fx-background-color: white");
		centerPane.getChildren().addAll(scrollPaneChart, scrollPaneTable);
	}
	
   public static ScrollPane getScrollPaneTable() {
		return scrollPaneTable;
	}

	public static void setScrollPaneTable(ScrollPane scrollPaneTable) {
		CenterPane.scrollPaneTable = scrollPaneTable;
	}

	// 单例模式的获取实例的方法
    public static Pane getInstance(){        
        if(null==centerPane){
        	centerPane = new CenterPane();
        }       
        return centerPane;
    }

	public static ScrollPane getScrollPaneChart() {
		return scrollPaneChart;
	}

	public static void setScrollPaneChart(ScrollPane scrollPaneChart) {
		CenterPane.scrollPaneChart = scrollPaneChart;
	}
}
