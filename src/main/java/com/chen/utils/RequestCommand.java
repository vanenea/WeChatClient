package com.chen.utils;
/**
 * ���������ֵ�
 * @author Xiloer
 *
 */
public class RequestCommand {
	//�����½
	public static final byte LOGIN = 1;
	//����ע��
	public static final byte REG = LOGIN + 1;
	//����㲥�ı���Ϣ
	public static final byte SEND_TEXT = REG + 1;
	//������ָ���û������ı���Ϣ
	public static final byte SEND_TEXT_TO_ONE = SEND_TEXT + 1;
	//����㲥�ļ�
	public static final byte SEND_FILE = SEND_TEXT_TO_ONE + 1;
	//������ָ���û������ļ�
	public static final byte SEND_FILE_TO_ONE = SEND_FILE + 1;
	//�������ļ���ִ
	public static final byte SEND_FILE_TO_ONE_1 = SEND_FILE_TO_ONE + 1;
	//����ʼ�����ļ�
	public static final byte SEND_FILE_TO_ONE_2 = SEND_FILE_TO_ONE_1 + 1;
}
