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
     * ������Ϣ
     * @param session
     * @param message
     * @throws Exception
     */
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
    }

    /**
     * ������Ϣ
     * @param session
     * @param message
     * @throws Exception
     */
    public void messageSent(IoSession session, Object message) throws Exception {
        //�����̷߳�����Ϣ̫��
        Thread.sleep(1);
        Package packageInfo = new Package() ;
        final String sendInfo = "i am client " ;
        packageInfo.flag = 0x0101 ;
        packageInfo.options = 0x01 ;
        packageInfo.size = (short)sendInfo.getBytes().length ;
        packageInfo.data = sendInfo.getBytes() ;
        System.out.println(sendInfo) ;
        //������Ϣ���ͻ���
        session.write(IoBuffer.wrap(PackageUtil.packageToBytes(packageInfo))) ;
    }
}
