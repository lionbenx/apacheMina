package com.lionben.pojo;

import org.apache.mina.core.session.IoSession;

public class Package {
	public short flag;	//消息的类型
	public byte options;	//消息的选项
	public short size;		//消息的长度
	public byte[] data;		//实际接收的数据
	public IoSession session;
}
