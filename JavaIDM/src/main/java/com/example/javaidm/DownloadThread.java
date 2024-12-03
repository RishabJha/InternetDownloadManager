package com.example.javaidm;

import com.example.javaidm.models.FileInfo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadThread extends Thread{

    private FileInfo fileInfo;
    DownloadManager downloadManager;

    public DownloadThread(FileInfo fileInfo, DownloadManager downloadManager) {
        this.fileInfo = fileInfo;
        this.downloadManager = downloadManager;
    }

    @Override
    public void run() {
        this.fileInfo.setStatus("downloading");
        this.downloadManager.updateUI(fileInfo);
        try {
            URL url = new URL(this.fileInfo.getUrl());
            URLConnection urlConnection = url.openConnection();
            int fileSize = urlConnection.getContentLength();

            int downloadedData = 0;
            double percentage = 0.0;
            double byteSum = 0.0;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fos = new FileOutputStream(this.fileInfo.getPath());
            byte data[] = new byte[1024];
            while(true) {
                downloadedData = bufferedInputStream.read(data, 0, 1024);
                if (downloadedData == -1) {
                    break;
                }
                fos.write(data, 0, downloadedData);
                byteSum += downloadedData;

                if (fileSize > -1) {
                    percentage = (byteSum / fileSize) * 100;
                    this.fileInfo.setPercentage(percentage+"");
                    this.downloadManager.updateUI(fileInfo);
                }
            }
            bufferedInputStream.close();
            fos.close();
            this.fileInfo.setStatus("completed");
        } catch (IOException e) {
            this.fileInfo.setStatus("failed");
            System.out.println("Downloading error");
            e.printStackTrace();
        }

        this.downloadManager.updateUI(fileInfo);
    }
}
