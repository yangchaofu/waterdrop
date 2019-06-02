package com.yangc.waterdrop.ui;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class ScrollPaneTable extends ScrollPane{
	public static ScrollPane scrollPaneTable;

	// 单例模式, 私有化构造方法
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ScrollPaneTable() {
		scrollPaneTable = new ScrollPane();
		Button btn = new Button("添加分类");
		btn.setOnAction(e -> {
			XYChart.Series series = new XYChart.Series();
			series.setName("2003");
			series.getData().add(new XYChart.Data("A", 2));
			series.getData().add(new XYChart.Data("B", 20));
			series.getData().add(new XYChart.Data("C", 10));

			BarChart bc = (BarChart) ScrollPaneChart.scrollPaneChart.getContent();
			bc.setPrefWidth(bc.getPrefWidth() + 100);
			bc.getData().add(series);
		});
		scrollPaneTable.setContent(btn);
	}
	
	   // 单例模式的获取实例的方法
    public static ScrollPane getInstance(){        
        if(null==scrollPaneTable){
        	scrollPaneTable = new ScrollPaneTable();
        }       
        return scrollPaneTable;
    }
}
