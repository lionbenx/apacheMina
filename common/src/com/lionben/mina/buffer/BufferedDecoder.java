package com.lionben.mina.buffer;

import com.lionben.utils.ByteUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import java.nio.ByteOrder;

public class BufferedDecoder implements MessageDecoder {

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

	/**
	 * 重新分配缓冲区的大小，对接收信息进行处理
	 * @param session
	 * @param in
	 * @param out
	 * @return
	 * @throws Exception
	 */
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