package com.yangc.waterdrop;

import com.yangc.waterdrop.ui.CenterPane;
import com.yangc.waterdrop.ui.LeftPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	
	// 程序根面板, 有Main来维护该面板, 也是程序的整体布局
	private BorderPane root = new BorderPane();
	// 程序左面板, 需要从ui包中获取并创建
	private VBox leftPane;
	// 程序中间面板, 需要从ui包中获取并创建
	private Pane centerPane;
		
	@Override
	public void start(Stage primaryStage) {
		
		// 将图表面本和表格面本放置到中间面板上
		// 初始化root面板, 程序运行至此已经完成界面加载		
		centerPane = CenterPane.getInstance();
		centerPane.setStyle("-fx-background-color: blue");
//		leftPane = LeftPane.leftPane;
//		root.setLeft(leftPane);
		root.setCenter(centerPane);

		// 设置一个宽900, 高600的场景
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("水滴统计");
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	/**
	 *  程序入口, 启动主界面
	 * @param args
	 */
	public static void main(String[] args) {				
		launch(args);
	}
}