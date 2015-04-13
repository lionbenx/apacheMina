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
    public MinaClientHandler() {
        super();
    }

    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
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
        Package packageInfo = new Package() ;
        final String sendInfo = "���ǿͻ���" ;
        packageInfo.flag = 0x0101 ;
        packageInfo.options = 0x01 ;
        packageInfo.size = (short)sendInfo.getBytes().length ;
        packageInfo.data = sendInfo.getBytes() ;
        //������Ϣ���ͻ���
        session.write(IoBuffer.wrap(PackageUtil.packageToBytes(packageInfo))) ;
    }
}
