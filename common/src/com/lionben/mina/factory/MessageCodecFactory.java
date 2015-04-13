package com.lionben.mina.factory;



import com.lionben.mina.buffer.BufferedDecoder;
import com.lionben.mina.buffer.BufferedEncoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class MessageCodecFactory extends DemuxingProtocolCodecFactory {
	public MessageCodecFactory(BufferedEncoder encoder, BufferedDecoder decoder) {
		addMessageDecoder(decoder);
		addMessageEncoder(Object.class, encoder);
	}

}

