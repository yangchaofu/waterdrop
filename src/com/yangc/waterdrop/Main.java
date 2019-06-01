package com.yangc.waterdrop;

import java.io.File;

import com.yangc.waterdrop.util.CalacFileMd5Util;
import com.yangc.waterdrop.util.PathUtil;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 程序UI说明
 * root.Left-程序左边布局为垂直的一些配置想, 使用VBox作为布局面板, 放在root的Left上
 * root.Center程序中间主面板为一个垂直布局的VBox, 分为上下两个部分
 *	root.Center(上) 中间面板上半部分为一个滚动面板, 放置直方图,可横向滚动
 *	root.Center(下) 中间面板下半部分也是一个滚动面本, 放置表格数据, 可竖向滚动  
 * @author yangc
 *
 */
public class Main extends Application {
	
	// 程序根面板
	private BorderPane root = new BorderPane();
	// 程序左面板
	private VBox leftPane = new VBox();
	// 程序中间面板
	private Pane centerPane = new VBox();
	// 程序中间面板的图表区域, 滚动面板, 会左右延伸
	private ScrollPane scrollPaneChart = new ScrollPane();
	// 程序中间面板的表格区域, 滚动面板, 会上下延伸
	private ScrollPane scrollPaneTable = new ScrollPane();
		
	@Override
	public void start(Stage primaryStage) {
				

		scrollPaneChart.setPrefHeight(350);
		scrollPaneChart.setContent(getBarChart());		
		scrollPaneTable.prefHeight(300);		
		
		// 将图表面本和表格面本放置到中间面板上
		// 初始化root面板, 程序运行至此已经完成界面加载		
		centerPane.getChildren().addAll(scrollPaneChart, scrollPaneTable);
		leftPane = getLeftPane();
		root.setLeft(leftPane);
		root.setCenter(centerPane);
		
		Button btn = new Button("添加分类");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		        XYChart.Series series = new XYChart.Series();
		        series.setName("2003");
		        series.getData().add(new XYChart.Data("A", 2));
		        series.getData().add(new XYChart.Data("B", 20));
		        series.getData().add(new XYChart.Data("C", 10));
		        
				BarChart bc = (BarChart)scrollPaneChart.getContent();
				bc.setPrefWidth(bc.getPrefWidth()+100);
				bc.getData().add(series);
			}
		});
		scrollPaneTable.setContent(btn);
		
		// 设置一个宽900, 高600的场景
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("水滴统计");
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	// 获取初始的柱形图
	public BarChart<String, Number> getBarChart() {
	    String itemA = "A";
	    String itemB = "B";
	    String itemC = "C";
	    final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<String, Number> barChart = new BarChart<>(yAxis, xAxis);
        barChart.setTitle("水滴统计");
        xAxis.setLabel("数量");                
        yAxis.setLabel("时间");  
        yAxis.setTickLabelGap(30);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(itemA, 2));
        series1.getData().add(new XYChart.Data(itemB, 20));
        series1.getData().add(new XYChart.Data(itemC, 10));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(itemA,50));
        series2.getData().add(new XYChart.Data(itemB, 41));
        series2.getData().add(new XYChart.Data(itemC, 45));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(itemA, 45));
        series3.getData().add(new XYChart.Data(itemB, 44));
        series3.getData().add(new XYChart.Data(itemC, 18));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("2006");
        series4.getData().add(new XYChart.Data(itemA, 45));
        series4.getData().add(new XYChart.Data(itemB, 44));
        series4.getData().add(new XYChart.Data(itemC, 18));
        
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("2007");
        series5.getData().add(new XYChart.Data(itemA, 45));
        series5.getData().add(new XYChart.Data(itemB, 44));
        series5.getData().add(new XYChart.Data(itemC, 18));
        
        XYChart.Series series6 = new XYChart.Series();
        series6.setName("2008");
        series6.getData().add(new XYChart.Data(itemA, 45));
        series6.getData().add(new XYChart.Data(itemB, 44));
        series6.getData().add(new XYChart.Data(itemC, 18));
        
        XYChart.Series series7 = new XYChart.Series();
        series7.setName("2009");
        series7.getData().add(new XYChart.Data(itemA, 45));
        series7.getData().add(new XYChart.Data(itemB, 44));
        series7.getData().add(new XYChart.Data(itemC, 18));
        
        XYChart.Series series8 = new XYChart.Series();
        series8.setName("2010");
        series8.getData().add(new XYChart.Data(itemA, 45));
        series8.getData().add(new XYChart.Data(itemB, 44));
        series8.getData().add(new XYChart.Data(itemC, 18));
        
        XYChart.Series series9 = new XYChart.Series();
        series9.setName("2011");
        series9.getData().add(new XYChart.Data(itemA, 45));
        series9.getData().add(new XYChart.Data(itemB, 44));
        series9.getData().add(new XYChart.Data(itemC, 18));

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<String, Number> series : barChart.getData()) {
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        data.setYValue(Math.random() * 50);                        
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

 
//        barChart.setPrefWidth(700);
        barChart.setBarGap(1);
        barChart.setCategoryGap(10);
        barChart.setLegendVisible(false);
        barChart.setMinWidth(700);
        barChart.setMaxWidth(2000);
        barChart.setPrefHeight(300);
        barChart.setMaxHeight(450);
        barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8,series9);        
        
        return barChart;
	}
	
	public static VBox getLeftPane() {
		VBox left = new VBox(10);
		Button importFilebt = new Button("导入文件");
		// 给导入文件添加处理事件
		importFilebt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage openFile = new Stage();
				System.out.println("导入文件按钮被点击");
				FileChooser fc = new FileChooser();
				fc.getExtensionFilters().add(new ExtensionFilter("文本文件", "*.txt"));
				fc.getExtensionFilters().add(new ExtensionFilter("所有文件", "*.*"));
				
				
				File f = fc.showOpenDialog(openFile);
				if(f == null) {
					return;
				} else {					
					System.out.println("文件选择器获取的文件为: "+f.getPath());
					//计算文件的md5, 查看文件是否已经被导入过
					CalacFileMd5Util.getFileMD5(f);
				}
			}
		});
		left.getChildren().add(importFilebt);
		left.setPrefWidth(150);
		left.setPrefHeight(600);
		left.setStyle("-fx-background-color: red");
		return left;
	}

	/**
	 * 程序入口, 启动主界面
	 * @param args
	 */
	public static void main(String[] args) {		
		
		launch(args);
	}
}