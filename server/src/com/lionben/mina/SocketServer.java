package com.lionben.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lionben.config.BaseConfig;
import com.lionben.mina.buffer.BufferedDecoder;
import com.lionben.mina.buffer.BufferedEncoder;
import com.lionben.mina.factory.MessageCodecFactory;
import com.lionben.pojo.Global;
import com.lionben.pojo.MinaSetting;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import com.lionben.utils.SettingsUtils;

public class SocketServer extends BaseConfig{
	
	private static NioSocketAcceptor acceptor ;
	
	public SocketServer(MinaSetting minaSetting) throws IOException {
		//线程池
		Executor threadPool = Executors.newFixedThreadPool(minaSetting.getThreadPoolSize());
		acceptor =new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(threadPool));
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory(
				new BufferedEncoder(),
				new BufferedDecoder()
		)));
		acceptor.setHandler(new SocketHandler());
		acceptor.getSessionConfig().setReadBufferSize(minaSetting.getReadBuffer());
		acceptor.getSessionConfig().setSendBufferSize(minaSetting.getSendBuffer());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		//最大监听数量
		acceptor.setBacklog(minaSetting.getBlackLog());
		//设置绑定端口
		acceptor.bind(new InetSocketAddress(minaSetting.getPort()));
	}
	
	public static boolean  getSocketStatus(){
		if(acceptor == null)
			return false ;
		return acceptor.isActive() ;
	}
	
	public void stop() {
		acceptor.dispose(true);
	}
	
}
