package com.lionben.main;

import com.lionben.hanlder.MinaClientHandler;
import com.lionben.mina.buffer.BufferedDecoder;
import com.lionben.mina.buffer.BufferedEncoder;
import com.lionben.mina.factory.MessageCodecFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import java.net.InetSocketAddress;

/**
 * Created by lionbenx
 */
public class MinaClient{
    private static final int bindPort=8800;

    public static void main(String[] args)throws Exception{
        // ����һ��socket����
        NioSocketConnector connector=new NioSocketConnector();
        // ��ȡ��������
        DefaultIoFilterChainBuilder chain=connector.getFilterChain();

        ProtocolCodecFilter filter= new ProtocolCodecFilter(new MessageCodecFactory(
                new BufferedEncoder(),
                new BufferedDecoder()
        ));
        // ��ӱ�������� �������롢��������
        chain.addLast("objectFilter",filter);
        // ��Ϣ���Ĵ�����
        connector.setHandler(new MinaClientHandler());
        // �������ӳ�ʱʱ��
        connector.setConnectTimeoutCheckInterval(30);
        // ���ӷ�������֪���˿ڡ���ַ
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",bindPort));
        // �ȴ����Ӵ������
        cf.awaitUninterruptibly();
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        connector.dispose();

    }

}
