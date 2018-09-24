package com.chen.utils;
/**
 * ��Ӧ�����ֵ�
 * @author Xiloer
 *
 */
public class ResponseCommand {
	/**
	 * ע����Ӧ
	 */
	public static final byte REG_RESPONSE = 1;
	/**
	 * ��½��Ӧ
	 */
	public static final byte LOGIN_RESPONSE = REG_RESPONSE + 1;
	/**
	 * ˢ���û��б�
	 */
	public static final byte USER_LIST_RESPONSE = LOGIN_RESPONSE + 1;
	/**
	 * ˽����Ӧ
	 */
	public static final byte MESSAGE_TO_ONE_RESPONSE = USER_LIST_RESPONSE + 1;
	/**
	 * Ⱥ����Ӧ
	 */
	public static final byte MESSAGE_TO_ALL_RESPONSE = MESSAGE_TO_ONE_RESPONSE + 1;
	/**
	 * ˽�˷����ļ�������Ӧ
	 */
	public static final byte FILE_TO_ONE_RESPONSE = MESSAGE_TO_ALL_RESPONSE + 1;
	/**
	 * �ռ��˻�ִ��Ӧ
	 */
	public static final byte FILE_TO_ONE_1_RESPONSE = FILE_TO_ONE_RESPONSE + 1;
	/**
	 * ִ��˽�˷����ļ���Ӧ
	 */
	public static final byte FILE_TO_ONE_2_RESPONSE = FILE_TO_ONE_1_RESPONSE + 1;
}
