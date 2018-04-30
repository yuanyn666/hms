package com.hms.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

public class MD5Utils {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static Secret getSecret(String password) {
		String md5Hex = DigestUtils.md5Hex(password);
		String salt = getSalt(md5Hex);
		String shaHex = DigestUtils.shaHex(md5Hex + salt);
		return new Secret(password, salt, shaHex);
	}

	private static String getSalt(String str) {
		char[] cs = new char[12];
		for (int i = 0; i < cs.length; i++) {
			int random = new Random().nextInt(str.length());
			cs[i] = str.charAt(random);
		}
		return new String(cs);
	}

	public static boolean isPassword(String password, String salt,
			String encryptionData) {
		String md5Hex = DigestUtils.md5Hex(password);
		String shaHex = DigestUtils.shaHex(md5Hex + salt);
		// Secret secret = getSecret(password);
		return shaHex.equals(encryptionData);
	}

	/**
	 * 内部类Secret
	 * <p>
	 * 用于存储加密后的密码和盐
	 * 
	 * @author 晴
	 *
	 */
	public static class Secret {
		private String originalData;// 原始数据
		private String salt;
		private String encryptionData;// 加密数据

		/**
		 * 获得原始的密码
		 */
		public String getOriginalData() {
			return originalData;
		}

		public void setOriginalData(String originalData) {
			this.originalData = originalData;
		}

		/**
		 * 获得12位加密的盐
		 * @return
		 */
		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		/**
		 * 获得加密之后的密码
		 * @return
		 */
		public String getEncryptionData() {
			return encryptionData;
		}

		public void setEncryptionData(String encryptionData) {
			this.encryptionData = encryptionData;
		}

		public Secret(String originalData, String salt, String encryptionData) {
			super();
			this.originalData = originalData;
			this.salt = salt;
			this.encryptionData = encryptionData;
		}

	}

}
