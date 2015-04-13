package com.lionben.mina.buffer;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * 译码器，不做任何事情
 */
public class BufferedEncoder implements MessageEncoder<Object> {

    /* (non-Javadoc)
     * @see org.apache.mina.filter.codec.ProtocolEncoder#encode(org.apache.mina.core.session.IoSession, java.lang.Object, org.apache.mina.filter.codec.ProtocolEncoderOutput)
     */
    @Override
    public void encode(IoSession session, Object message,
            ProtocolEncoderOutput out) throws Exception {
        //Do nothing
    }

}
