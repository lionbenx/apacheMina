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
        // 创建一个socket连接
        NioSocketConnector connector=new NioSocketConnector();
        // 获取过滤器链
        DefaultIoFilterChainBuilder chain=connector.getFilterChain();

        ProtocolCodecFilter filter= new ProtocolCodecFilter(new MessageCodecFactory(
                new BufferedEncoder(),
                new BufferedDecoder()
        ));
        // 添加编码过滤器 处理乱码、编码问题
        chain.addLast("objectFilter",filter);
        // 消息核心处理器
        connector.setHandler(new MinaClientHandler());
        // 设置链接超时时间
        connector.setConnectTimeoutCheckInterval(30);
        // 连接服务器，知道端口、地址
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",bindPort));
        // 等待连接创建完成
        cf.awaitUninterruptibly();
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        connector.dispose();

    }

}
