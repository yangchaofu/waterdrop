package com.yangc.waterdrop.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

/**
 *   此处放置和顶部Chart相关的代码, 目的是将和Chart相关的方法和事件从Main中分离出来
 * @author yangc
 *
 */
public class MyChart extends BarChart<String, Number>{
	private static String ITEM_A = "A";
    private static String ITEM_B = "B";
    private static String ITEM_C = "C";
    private static NumberAxis yAxis = new NumberAxis();
    private static CategoryAxis xAxis = new CategoryAxis();
	public static BarChart<String, Number> myChart;
	
	// 私有化构造方法, 使用单例模式
	private MyChart() {
		super(xAxis, yAxis);
		xAxis.setLabel("数量");                
		yAxis.setLabel("时间");  
		yAxis.setTickLabelGap(30);
        myChart = new BarChart<String, Number>(getXAxis(), getYAxis());
        myChart.setTitle("水滴统计");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(ITEM_A, 2));
        series1.getData().add(new XYChart.Data(ITEM_B, 20));
        series1.getData().add(new XYChart.Data(ITEM_C, 10));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(ITEM_A,50));
        series2.getData().add(new XYChart.Data(ITEM_B, 41));
        series2.getData().add(new XYChart.Data(ITEM_C, 45));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(ITEM_A, 45));
        series3.getData().add(new XYChart.Data(ITEM_B, 44));
        series3.getData().add(new XYChart.Data(ITEM_C, 18));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("2006");
        series4.getData().add(new XYChart.Data(ITEM_A, 45));
        series4.getData().add(new XYChart.Data(ITEM_B, 44));
        series4.getData().add(new XYChart.Data(ITEM_C, 18));
        
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("2007");
        series5.getData().add(new XYChart.Data(ITEM_A, 45));
        series5.getData().add(new XYChart.Data(ITEM_B, 44));
        series5.getData().add(new XYChart.Data(ITEM_C, 18));
        
        XYChart.Series series6 = new XYChart.Series();
        series6.setName("2008");
        series6.getData().add(new XYChart.Data(ITEM_A, 45));
        series6.getData().add(new XYChart.Data(ITEM_B, 44));
        series6.getData().add(new XYChart.Data(ITEM_C, 18));
        
        XYChart.Series series7 = new XYChart.Series();
        series7.setName("2009");
        series7.getData().add(new XYChart.Data(ITEM_A, 45));
        series7.getData().add(new XYChart.Data(ITEM_B, 44));
        series7.getData().add(new XYChart.Data(ITEM_C, 18));
        
        XYChart.Series series8 = new XYChart.Series();
        series8.setName("2010");
        series8.getData().add(new XYChart.Data(ITEM_A, 45));
        series8.getData().add(new XYChart.Data(ITEM_B, 44));
        series8.getData().add(new XYChart.Data(ITEM_C, 18));
        
        XYChart.Series series9 = new XYChart.Series();
        series9.setName("2011");
        series9.getData().add(new XYChart.Data(ITEM_A, 45));
        series9.getData().add(new XYChart.Data(ITEM_B, 44));
        series9.getData().add(new XYChart.Data(ITEM_C, 18));

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<String, Number> series : myChart.getData()) {
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        data.setYValue(Math.random() * 50);                        
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

 
//        barChart.setPrefWidth(700);
        myChart.setBarGap(1);
        myChart.setCategoryGap(10);
        myChart.setLegendVisible(false);
        myChart.setMinWidth(700);
        myChart.setMaxWidth(2000);
        myChart.setPrefHeight(300);
        myChart.setMaxHeight(450);
        myChart.setStyle("-fx-background-color: white");
        myChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8,series9);        
        
	}
	
	   // 单例模式的获取实例的方法
    public static BarChart<String, Number> getInstance(){        
        if(null==myChart){
        	myChart = new MyChart();
        }       
        return myChart;
    }
	
}
