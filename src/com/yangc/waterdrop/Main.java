package com.yangc.waterdrop;

import java.io.File;
import java.util.List;

import com.yangc.waterdrop.entity.CountResult;
import com.yangc.waterdrop.service.CountResultService;
import com.yangc.waterdrop.util.CalacFileMd5Util;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 程序UI说明 root.Left-程序左边布局为垂直的一些配置想, 使用VBox作为布局面板, 放在root的Left上
 * root.Center程序中间主面板为一个垂直布局的VBox, 分为上下两个部分 root.Center(上) 中间面板上半部分为一个滚动面板,
 * 放置直方图,可横向滚动 root.Center(下) 中间面板下半部分也是一个滚动面本, 放置表格数据, 可竖向滚动
 * 
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
		scrollPaneChart.setContent(new MyChart().getMyChart(new CountResultService().getDefaultList()));
//		scrollPaneTable.prefHeight(300);

		// 将图表面本和表格面本放置到中间面板上
		// 初始化root面板, 程序运行至此已经完成界面加载
		centerPane.getChildren().addAll(scrollPaneChart, scrollPaneTable);
		leftPane = getLeftPane();
		root.setLeft(leftPane);
		root.setCenter(centerPane);

//		Button btn = new Button("添加分类");
//		btn.setOnAction(e-> {
//				XYChart.Series series = new XYChart.Series();
//				series.setName("2003");
//				series.getData().add(new XYChart.Data("A", 2));
//				series.getData().add(new XYChart.Data("B", 20));
//				series.getData().add(new XYChart.Data("C", 10));
//
//				BarChart bc = (BarChart) scrollPaneChart.getContent();
//				LineChart lc = (LineChart) scrollPaneChart.getContent();
//				bc.setPrefWidth(bc.getPrefWidth() + 100);
//				bc.getData().add(series);			
//		});
//		scrollPaneTable.setContent(btn);

		// 设置一个宽900, 高600的场景
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("水滴统计");
		primaryStage.show();
		primaryStage.setResizable(false);
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
				if (f == null) {
					return;
				} else {
					System.out.println("文件选择器获取的文件为: " + f.getPath());
					// 计算文件的md5, 查看文件是否已经被导入过
					CalacFileMd5Util.getFileMD5(f);
				}
			}
		});
		left.getChildren().add(importFilebt);
		left.setPrefWidth(150);
		left.setPrefHeight(600);
		left.setStyle("-fx-background-color: #1E90FF");
		return left;
	}

	class MyChart {

		public StackPane getMyChart(List<CountResult> countResultList) {

			// x-axis and y-axis for both charts:
			final CategoryAxis xAxis = new CategoryAxis();
			//设置数据标题,表示当天的数据, 只要日期数据
			String lable = countResultList.get(0).getTickDate().substring(0, 10);
			xAxis.setLabel(lable);
			xAxis.setTickLabelRotation(30);
			final NumberAxis yAxis1 = new NumberAxis();
			yAxis1.setLabel("水滴数");
			final NumberAxis yAxis2 = new NumberAxis();
			yAxis2.setLabel("滴速");

			// 条形图
			final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis1);			
			barChart.setLegendVisible(false);
			barChart.setAnimated(true);
			barChart.setBarGap(3);
			barChart.setTitle("水滴统计");	
			barChart.setPadding(new Insets(0, 50, 0, 16));
			
			XYChart.Series<String, Number> series1 = new XYChart.Series<>();
			for (CountResult countResult : countResultList) {
				String tickTime = countResult.getTickDate().substring(11, 16);
				series1.getData().add(new XYChart.Data<>(tickTime, countResult.getCount()));
			}			

			barChart.getData().add(series1);

			// 折线图
			final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis2);			
			yAxis2.setSide(Side.RIGHT);
			lineChart.setPadding(new Insets(0, 15, 0, 60));
			lineChart.setLegendVisible(false);
			lineChart.setAnimated(true);
			lineChart.setCreateSymbols(true);
			lineChart.setAlternativeRowFillVisible(false);
			lineChart.setAlternativeColumnFillVisible(false);
			lineChart.setHorizontalGridLinesVisible(false);
			lineChart.setVerticalGridLinesVisible(false);
			lineChart.getXAxis().setVisible(false);
			lineChart.getYAxis().setVisible(false);
			lineChart.getStylesheets().addAll(getClass().getResource("Exemple158.css").toExternalForm());
						
			XYChart.Series<String, Number> series2 = new XYChart.Series<>();
			for (CountResult countResult : countResultList) {
				String tickTime = countResult.getTickDate().substring(11, 16);
				series2.getData().add(new XYChart.Data<>(tickTime, countResult.getSpeed()));
			}
			lineChart.getData().add(series2);

			StackPane root = new StackPane();
			root.setPrefHeight(320);
			root.setPrefWidth(series1.getData().size() * 45);
			root.getChildren().addAll(barChart, lineChart);

			return root;
		}
	}

	/**
	 * 程序入口, 启动主界面
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);
	}
}