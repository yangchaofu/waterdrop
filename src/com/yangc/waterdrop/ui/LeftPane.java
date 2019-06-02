package com.yangc.waterdrop.ui;

import java.io.File;

import com.yangc.waterdrop.util.CalacFileMd5Util;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 左边配置项的面板, 包括导入文件, 日期范围, 时间间隔等设置项
 * 
 * @author yangc
 *
 */
public class LeftPane extends VBox{
	// 该类需要维系的面板, 设置垂直间距默认为10px;
	public static VBox leftPane = new LeftPane(10);
	
	// 面板使用单例模式, 私有化构造方法
	private LeftPane(double gap) {
		Button fileBtn = new Button("导入文件");
		// 给导入文件添加处理事件
		fileBtn.setOnAction(e -> {
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
		});
		leftPane.getChildren().add(fileBtn);
		leftPane.setPrefWidth(150);
		leftPane.setPrefHeight(600);
		leftPane.setStyle("-fx-background-color: red");

	}
	
}
