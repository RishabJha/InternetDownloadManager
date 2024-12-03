package com.example.javaidm.models;

import com.example.javaidm.DownloadManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileInfo {

    private final SimpleIntegerProperty index = new SimpleIntegerProperty();
    private final SimpleStringProperty fileName = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    private final SimpleStringProperty status = new SimpleStringProperty();
    private final SimpleStringProperty action = new SimpleStringProperty();
    private final SimpleStringProperty path = new SimpleStringProperty();
    private final SimpleStringProperty percentage = new SimpleStringProperty();

    public FileInfo(int index, String fileName, String url, String status, String action, String path, String percentage) {
        this.index.set(index);
        this.fileName.set(fileName);
        this.url.set(url);
        this.status.set(status);
        this.action.set(action);
        this.path.set(path);
        this.percentage.set(percentage);
    }

    public String getAction() {
        return action.get();
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "index=" + index +
                ", fileName=" + fileName +
                ", url=" + url +
                ", status=" + status +
                ", action=" + action +
                ", path=" + path +
                ", percentage=" + percentage +
                '}';
    }

    public String getPercentage() {
        return percentage.get();
    }

    public SimpleStringProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }
}
