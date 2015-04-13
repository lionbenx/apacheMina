package com.lionben.mina;

import com.lionben.pojo.Package;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;


public class SocketHandler extends IoHandlerAdapter {


    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session create " + session.getId());
        super.sessionCreated(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("close session count:" + session.getId());
        super.sessionClosed(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("package caught:" + session.getId());
        super.exceptionCaught(session, cause);
    }

    /**
     * 进行信息接收的包装处理
     *
     * @param session
     * @param message
     */
    @Override
    public void messageReceived(IoSession session, Object message) {
        System.out.println("get message") ;
        try {
            if (session == null || session.isClosing())
                return;
            IoBuffer buffer = (IoBuffer) message;
            Package header = new Package();
            header.flag = buffer.getShort();
            header.options = buffer.get();
            header.size = buffer.getShort();
            header.data = new byte[header.size];
            buffer.get(header.data);
            header.session = session;
            preProcessPackage(header);
            packageDispatch(header);
            postProcessPackage(header);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 接收消息的预处理
     *
     * @param header
     */
    public void preProcessPackage(Package header) {

        if ((header.options & (byte) 0x10) == 0x10)
            //TODO:解密

            ;
    }

    /**
     * 对接收消息进行业务处理
     *
     * @param header
     * @throws Exception
     * @throws RuntimeException
     */
    public void packageDispatch(Package header) throws Exception, RuntimeException {
        //根据各种标志执行对应的业务逻辑
        switch (header.flag) {
            case 0x0108:
                break;
            case 0x0109:
                break;
            default://其他标记
                ;
        }
    }

    /**
     * 对消息的后续处理
     *
     * @param header
     */
    public void postProcessPackage(Package header) {
        String clientInfo = new String(header.data,0,header.size) ;
        System.out.println("flag : "+header.flag+ " size : "+header.size+"  client Info : "+clientInfo);
    }


}
