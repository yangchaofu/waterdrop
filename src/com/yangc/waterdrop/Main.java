package com.yangc.waterdrop;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import com.yangc.waterdrop.entity.CountResult;
import com.yangc.waterdrop.service.CountResultService;
import com.yangc.waterdrop.service.ImportFileService;
import com.yangc.waterdrop.util.CalacFileMd5Util;
import com.yangc.waterdrop.util.ExportToExcelUtil;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	// 主场景
	private Scene scene = new Scene(root, 900, 600);
	// 程序左面板
	private VBox leftPane = new VBox();
	// 程序中间面板
	private Pane centerPane = new VBox();
	// 程序中间面板的图表区域, 滚动面板, 会左右延伸
	private ScrollPane scrollPaneChart = new ScrollPane();
	// 程序中间面板的表格区域, 滚动面板, 会上下延伸
	private ScrollPane scrollPaneTable = new ScrollPane();

	private HBox tab = new HBox(650);

	private List<CountResult> countResultList;
	
	private CountResultService countResultService = new CountResultService();

	private String label = null;
	
	private MyChart myChart = new MyChart();
	
	private MyTable myTable = new MyTable();
	
	private int dataFlag = -1;

	@Override
	public void start(Stage primaryStage) {
		scrollPaneChart.setPrefHeight(350);		
		scrollPaneTable.setPrefHeight(250);

		reDraw(1, null);
//		countResultList = countResultService.getDefaultList();
//		scrollPaneChart.setContent(myChart.getMyChart(countResultList));
//		scrollPaneTable.setContent(myTable.getMyTable(countResultList));

		tab = getHBox();
		// 将图表面本和表格面本放置到中间面板上
		// 初始化root面板, 程序运行至此已经完成界面加载
		centerPane.getChildren().addAll(scrollPaneChart, tab, scrollPaneTable);
		
		leftPane = getLeftPane();
		root.setLeft(leftPane);
		root.setCenter(centerPane);

		// 设置场景的光标为默认光标
		scene.setCursor(Cursor.DEFAULT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("水滴统计");
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public HBox getHBox() {
		HBox hbox = new HBox(650);
		Button btnPre = new Button("前一天");
		btnPre.setOnAction(e -> {
			String beforeDay = countResultService.getTargetDay(label, -1);
			System.out.println(label);
			System.out.println("前一天:"+beforeDay);
			System.out.println("btnPre 被点击");
			label = beforeDay;
			reDraw(2, beforeDay);
			
		});
		Button btnNex = new Button("后一天");
		btnNex.setOnAction(e -> {
			String afterDay = countResultService.getTargetDay(label, 1);
			System.out.println(label);
			System.out.println("后一天:"+afterDay);
			System.out.println(label);
			System.out.println("btnNex 被点击");
			label = afterDay;
			reDraw(2, afterDay);			
		});
		hbox.getChildren().addAll(btnPre, btnNex);
		
		if(dataFlag == 0) {
			btnNex.setDisable(true);
			btnPre.setDisable(true);
		}
		return hbox;
	}

	public VBox getLeftPane() {
		VBox left = new VBox(10);
		Button btnImportFile = new Button("导入文件");
		// 给导入文件添加处理事件
		btnImportFile.setOnAction(e -> {
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
				ImportFileService importFileSevice = new ImportFileService(f.getPath());
				String insertRes = importFileSevice.insertToData();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(insertRes);
				alert.show();
				System.out.println(insertRes);
			}
		});
		Separator sep1 = new Separator();
		Label lbStartDate = new Label("开始日期");
		lbStartDate.setPadding(new Insets(0, 0, 0, 10));
		DatePicker dpStartDate = new DatePicker();
		dpStartDate.setValue(LocalDate.now());
		Button btnSetDateScope = new Button("跳转");
		Label lbTimeGap = new Label("时间间隔(单位分钟)");
		lbTimeGap.setPadding(new Insets(0, 0, 0, 10));
		Separator sep2 = new Separator();
		TextField tfTimeGap = new TextField("30");
		Button btnSetTimeGap = new Button("确定");
		Separator sep3 = new Separator();
		Button btnExportTodayToExcel = new Button("导出今日数据到Excel");
//		Button btnExportAllToExcel = new Button("导出全部数据到Excel");
		left.getChildren().addAll(btnImportFile, sep1, lbStartDate, dpStartDate, btnSetDateScope, sep2, lbTimeGap,
				tfTimeGap, btnSetTimeGap, sep3, btnExportTodayToExcel);
		left.setPrefWidth(150);
		left.setPrefHeight(600);
		left.setMargin(btnImportFile, new Insets(20, 0, 0, 10));
		left.setMargin(dpStartDate, new Insets(0, 20, 0, 10));
		left.setMargin(btnSetDateScope, new Insets(0, 0, 0, 10));
		left.setMargin(tfTimeGap, new Insets(0, 20, 0, 10));
		left.setMargin(btnSetTimeGap, new Insets(0, 0, 0, 10));
		left.setMargin(btnExportTodayToExcel, new Insets(0, 0, 0, 10));
//		left.setMargin(btnExportAllToExcel, new Insets(0, 0, 0, 10));

		// 以下代码设置事件
		// 导出全部数据到excel表的按钮事件
//		btnExportAllToExcel.setOnAction(e -> {
//			System.out.println("导出全部数据到excel被点击!");
//		});
		// 导出今日数据到excel表
		btnExportTodayToExcel.setOnAction(e -> {
			if(countResultList == null || countResultList.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(label + " 没有数据,无法导出!!!");
				alert.show();
				System.out.println(label + " 没有数据,无法导出!!!");
			} else {	
				Stage saveFile = new Stage();
				System.out.println("导出今日数据到Excel按钮被点击");
				FileChooser fc = new FileChooser();
				fc.setInitialFileName(label + "数据");
				
				fc.getExtensionFilters().add(new ExtensionFilter("Excel 2007 - Excel 2019", "*.xlsx"));
				fc.getExtensionFilters().add(new ExtensionFilter("Excel 2003", "*.xls"));

				File f = fc.showSaveDialog(saveFile);
				if (f == null) {
					return;
				} else {
					System.out.println("文件选择器获取的文件为: " + f.getPath());
					ExportToExcelUtil.exportToExcel(countResultList, f);
					System.out.println("文件保存成功-"+f.getAbsolutePath());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("文件保存成功:  " + f.getAbsolutePath());
					alert.show();
				}
				
			}			
		});
		// 设置日期范围的按钮事件
		btnSetDateScope.setOnAction(e -> {
			String inputDate = dpStartDate.getValue().toString();
			if(inputDate.equals(label)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("已经是当天的数据!!!");
				alert.show();
				System.out.println("已经是当天的数据!!!");
			}else {				
				label = inputDate;
				reDraw(2, inputDate);
				System.out.println(inputDate);
			}
		});
		// 设置时间间隔的按钮事件
		btnSetTimeGap.setOnAction(e -> {
			if(countResultList == null || countResultList.size() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("没有数据,无法设置时间间隔!!!");
				alert.show();
				System.out.println("已经是当天的数据!!!");
			}else {				
				String timeGap = tfTimeGap.getText();
				System.out.println("设置时间间隔为：" + timeGap);			
				// 设置新得时间间隔
				countResultService.setTimeGap(Integer.parseInt(timeGap));
				reDraw(2, label);
			}
		});
		
		if(dataFlag == 0) {
			btnSetDateScope.setDisable(true);
			btnSetTimeGap.setDisable(true);
			btnExportTodayToExcel.setDisable(true);
//			btnExportAllToExcel.setDisable(true);
			tfTimeGap.setDisable(true);
			dpStartDate.setDisable(true);
		}

		return left;
	}

	private void reDraw(int flag, String oneDay) {				
		if(flag == 1 && oneDay == null) {
			// 根据新的时间间隔获取新的数据
			countResultList = countResultService.getDefaultList();
			// 将新的数据绘制到面板上
			scrollPaneChart.setContent(new MyChart().getMyChart(countResultList));
			scrollPaneTable.setContent(new MyTable().getMyTable(countResultList));			
		} else if(flag == 2 && oneDay != null){
			// 根据新的时间间隔获取新的数据
			countResultList = countResultService.getOneDayList(oneDay);
			if(countResultList == null || countResultList.size() <= 0) {
				dataFlag = 0;
			}
			// 将新的数据绘制到面板上
			scrollPaneChart.setContent(new MyChart().getMyChart(countResultList));
			scrollPaneTable.setContent(new MyTable().getMyTable(countResultList));				
		}		
	}

	class MyChart {
		StackPane root = new StackPane();		
		
		public StackPane getMyChart(List<CountResult> countResultList) {			
			if(countResultList == null) {
				root.setPrefSize(700, 320);
				dataFlag = 0;
				Label emptyLabel = new Label(label+" 没有数据");
				root.setAlignment(Pos.CENTER);
				root.getChildren().add(emptyLabel);				
			} else {
				dataFlag = 1;
				// 将折线图和条形图绘制在同一个面板上
				final CategoryAxis xAxis = new CategoryAxis();
				// 设置数据标题,表示当天的数据, 只要日期数据
				label = countResultList.get(0).getTickDate().substring(0, 10);
				xAxis.setLabel(label);
				xAxis.setTickLabelRotation(30);
				final NumberAxis yAxis1 = new NumberAxis();
				yAxis1.setLabel("水滴数");
				final NumberAxis yAxis2 = new NumberAxis();
				yAxis2.setLabel("滴速");

				// 条形图
				final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis1);
				barChart.setLegendVisible(false);
				barChart.setAnimated(false);
				barChart.setBarGap(3);
				barChart.setTitle("水滴统计");
				barChart.setPadding(new Insets(0, 50, 0, 16));
				barChart.setAlternativeRowFillVisible(false);
				barChart.setAlternativeColumnFillVisible(false);
				barChart.getXAxis().setVisible(false);
				barChart.getYAxis().setVisible(false);

				XYChart.Series<String, Number> series1 = new XYChart.Series<>();
				for (CountResult countResult : countResultList) {
					String tickTime = countResult.getTickDate().substring(11, 16);
					series1.getData().add(new XYChart.Data<>(tickTime, countResult.getCount()));
				}
				series1.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {

					@Override
					public void accept(Data<String, Number> t) {
						HBox hbox = new HBox();
						hbox.setAlignment(Pos.TOP_CENTER);
						hbox.getChildren().add(new Label(String.valueOf(t.getYValue())));
						t.setNode(hbox);
						Tooltip tip = new Tooltip("水滴数 " + t.getYValue());
						Tooltip.install(t.getNode(), tip);
					}
				});
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
				lineChart.getXAxis().setVisible(true);
				lineChart.getYAxis().setVisible(false);

				lineChart.getStylesheets().addAll(getClass().getResource("Exemple158.css").toExternalForm());
				barChart.getStylesheets().addAll(getClass().getResource("Exemple159.css").toExternalForm());
				XYChart.Series<String, Number> series2 = new XYChart.Series<>();
				for (CountResult countResult : countResultList) {
					String tickTime = countResult.getTickDate().substring(11, 16);
					series2.getData().add(new XYChart.Data<>(tickTime, countResult.getSpeed()));
				}

				lineChart.getData().add(series2);

				series2.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {

					@Override
					public void accept(Data<String, Number> t) {
						Tooltip tip = new Tooltip("滴速 " + t.getYValue());
						Tooltip.install(t.getNode(), tip);
					}
				});

				root.setPrefHeight(320);
				root.setPrefWidth(series1.getData().size() * 45);
				root.getChildren().addAll(barChart, lineChart);				
			}
			
			return root;
		}
	}

	class MyTable {

		public TableView<CountResult> getMyTable(List<CountResult> countResList) {
			TableView<CountResult> tv;
			
			if(countResList == null) {
				dataFlag = 0;
				tv = new TableView<CountResult>();
				tv.setPrefWidth(745);
				tv.setPrefHeight(240);
			}else {
				dataFlag = 1;
				ObservableList<CountResult> obCountResList = FXCollections.observableArrayList();
				obCountResList.addAll(countResList);

				tv = new TableView<CountResult>(obCountResList);
				tv.setPrefWidth(745);
				TableColumn<CountResult, String> tcDate = new TableColumn<>("日期");
				tcDate.setPrefWidth(330);

				tcDate.setResizable(false);
				TableColumn<CountResult, Number> tcCount = new TableColumn<>("水滴数");
				tcCount.setPrefWidth(200);
				tcCount.setResizable(false);
				TableColumn<CountResult, Number> tcSpeed = new TableColumn<>("滴速");
				tcSpeed.setPrefWidth(200);
				tcSpeed.setResizable(false);
				tcDate.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<CountResult, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<CountResult, String> param) {
								SimpleStringProperty date = new SimpleStringProperty(param.getValue().getTickDate());
								return date;
							}
						});
				tcCount.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<CountResult, Number>, ObservableValue<Number>>() {

							@Override
							public ObservableValue<Number> call(CellDataFeatures<CountResult, Number> param) {
								SimpleIntegerProperty count = new SimpleIntegerProperty(param.getValue().getCount());
								return count;
							}
						});
				tcSpeed.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<CountResult, Number>, ObservableValue<Number>>() {

							@Override
							public ObservableValue<Number> call(CellDataFeatures<CountResult, Number> param) {
								SimpleDoubleProperty speed = new SimpleDoubleProperty(param.getValue().getSpeed());
								return speed;
							}
						});
				tv.getColumns().add(tcDate);
				tv.getColumns().add(tcCount);
				tv.getColumns().add(tcSpeed);				
			}			
			return tv;
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