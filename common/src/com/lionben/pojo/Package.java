package com.lionben.pojo;

import org.apache.mina.core.session.IoSession;

public class Package {
	public short flag;	//��Ϣ������
	public byte options;	//��Ϣ��ѡ��
	public short size;		//��Ϣ�ĳ���
	public byte[] data;		//ʵ�ʽ��յ�����
	public IoSession session;
}
