package com.lionben.hanlder;

import com.lionben.pojo.Package;
import com.lionben.utils.PackageUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by lionbenx.
 */
public class MinaClientHandler extends IoHandlerAdapter{

    private IoSession ioSession ;
    public MinaClientHandler() {
        super();
    }

    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("client close !") ;
        super.sessionClosed(session);
    }

    public void sessionOpened(IoSession session) throws Exception {
        this.messageSent(session,null);
    }

    /**
     * 接收消息
     * @param session
     * @param message
     * @throws Exception
     */
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
    }

    /**
     * 发送消息
     * @param session
     * @param message
     * @throws Exception
     */
    public void messageSent(IoSession session, Object message) throws Exception {
        //避免线程发送信息太快
        Thread.sleep(1);
        Package packageInfo = new Package() ;
        final String sendInfo = "i am client " ;
        packageInfo.flag = 0x0101 ;
        packageInfo.options = 0x01 ;
        packageInfo.size = (short)sendInfo.getBytes().length ;
        packageInfo.data = sendInfo.getBytes() ;
        System.out.println(sendInfo) ;
        //发送消息到客户端
        session.write(IoBuffer.wrap(PackageUtil.packageToBytes(packageInfo))) ;
    }
}
