package com.example.javaidm;

import com.example.javaidm.config.AppConfig;
import com.example.javaidm.models.FileInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.text.DecimalFormat;


public class DownloadManager {

    @FXML
    private TableView<FileInfo> tableView;

    @FXML
    private TextField urlTextField;

    public int index = 0;

    @FXML
    void downloadButtonClicked(ActionEvent event) {
        String url = this.urlTextField.getText().trim();
        String fileName = url.substring(url.lastIndexOf("/")+1);
        String status = "starting";
        String action = "open";
        String path = AppConfig.DOWNLOAD_PATH + File.separator + fileName;

        FileInfo fileInfo = new FileInfo(index+1, fileName, url, status, action, path, "0");
        this.index = this.index+1;
        DownloadThread downloadThread = new DownloadThread(fileInfo, this);
        this.tableView.getItems().add(fileInfo.getIndex()-1, fileInfo);
        downloadThread.start();
        this.urlTextField.setText("");
    }

    public void updateUI(FileInfo fileInfo) {
        System.out.println(fileInfo);
        FileInfo metaFile = this.tableView.getItems().get(fileInfo.getIndex()-1);
        metaFile.setStatus(fileInfo.getStatus());
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        metaFile.setPercentage(decimalFormat.format(Double.parseDouble(fileInfo.getPercentage())));
        this.tableView.refresh();
    }

    @FXML
    public void initialize() {
        TableColumn<FileInfo, String> sn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(0);
        sn.setCellValueFactory(p -> {
            return (p.getValue().indexProperty()).asString();
        });

        TableColumn<FileInfo, String> name = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(1);
        name.setCellValueFactory(p -> {
            return (p.getValue().fileNameProperty());
        });

        TableColumn<FileInfo, String> url = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(2);
        url.setCellValueFactory(p -> {
            return (p.getValue().urlProperty());
        });

        TableColumn<FileInfo, String> status = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(3);
        status.setCellValueFactory(p -> {
            return (p.getValue().statusProperty());
        });

        TableColumn<FileInfo, String> percentage = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(4);
        percentage.setCellValueFactory(p -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.set(p.getValue().getPercentage()+"%");
            return simpleStringProperty;
        });

        TableColumn<FileInfo, String> action = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(5);
        action.setCellValueFactory(p -> {
            return (p.getValue().actionProperty());
        });
    }
}
