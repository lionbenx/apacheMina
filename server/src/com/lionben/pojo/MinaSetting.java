package com.lionben.pojo;

import com.lionben.utils.SettingsUtils;

/**
 * Created by lionben .
 */
public class MinaSetting {

    private int readBuffer;
    private int sendBuffer;
    private int port;
    private int threadPoolSize;
    private int blackLog;

    public MinaSetting(int readBuffer, int sendBuffer, int port, int threadPoolSize, int blackLog) {
        this.readBuffer = readBuffer;
        this.sendBuffer = sendBuffer;
        this.port = port;
        this.threadPoolSize = threadPoolSize;
        this.blackLog = blackLog;
    }

    public int getReadBuffer() {
        return readBuffer;
    }

    public void setReadBuffer(int readBuffer) {
        this.readBuffer = readBuffer;
    }

    public int getSendBuffer() {
        return sendBuffer;
    }

    public void setSendBuffer(int sendBuffer) {
        this.sendBuffer = sendBuffer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getBlackLog() {
        return blackLog;
    }

    public void setBlackLog(int blackLog) {
        this.blackLog = blackLog;
    }
}
