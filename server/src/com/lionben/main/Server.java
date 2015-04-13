package com.lionben.main;

import com.lionben.config.BaseConfig;
import com.lionben.mina.SocketServer;
import com.lionben.pojo.Global;
import com.lionben.pojo.MinaSetting;
import com.lionben.utils.SettingsUtils;

/**
 * @author lionben
 */
public class Server extends BaseConfig {

    public static void main(String[] args){
        System.out.println("start !");
        try {
            MinaSetting setting = getSetting();
            SocketServer server = new SocketServer(setting);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 得到setting对象
     *
     * @return
     * @throws Exception
     */
    public static MinaSetting getSetting() throws Exception {
        int readBuffer = Integer.parseInt(SettingsUtils.getSettings(Global.settings, "socket.readbuffer", "1024"));
        int sendBuffer = Integer.parseInt(SettingsUtils.getSettings(Global.settings, "socket.sendBuffer", "1024"));
        int port = Integer.parseInt(SettingsUtils.getSettings(Global.settings, "socket.port", "1024"));
        int threadPoolSize = Integer.parseInt(SettingsUtils.getSettings(Global.settings, "socket.threadPoolSize", "1024"));
        int blackLog = Integer.parseInt(SettingsUtils.getSettings(Global.settings, "socket.blackLog", "1024"));
        return new MinaSetting(readBuffer, sendBuffer, port, threadPoolSize, blackLog);
    }

}
