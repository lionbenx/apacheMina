package com.lionben.mina.buffer;

import java.nio.ByteOrder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.lionben.utils.ByteUtil;

public class BufferedDecoder implements MessageDecoder {

	//private final int MessageMaxByte = 10240 ;
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		if(in.remaining()<5)
			return MessageDecoderResult.NEED_DATA;
		//short flag=in.getShort();
		in.getShort() ;
		in.get();
		short size=in.getShort();
		if(in.remaining()<size)
			return MessageDecoderResult.NEED_DATA;
		else
			return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		short flag=in.getShort();
		byte options=in.get();
		short size=in.getShort();
		byte[] bytes = new byte[size];
		in.get(bytes) ;
		IoBuffer buf = IoBuffer.allocate(size+5);   //ServerConfig.MessageMaxByte 最大消息字节数
        buf.order(ByteOrder.BIG_ENDIAN);
        buf.put(ByteUtil.shortTobyte(flag)) ;
        buf.put(options) ;
        buf.put(ByteUtil.shortTobyte(size)) ;
        buf.put(bytes);    
        buf.flip();
        out.write(buf);
		return MessageDecoderResult.OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		
	}

}