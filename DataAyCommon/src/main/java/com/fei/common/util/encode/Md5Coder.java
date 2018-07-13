package com.fei.common.util.encode;

import java.security.MessageDigest;

public class Md5Coder extends AbstractCoder {

	@Override
	public String encode(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return parseByte2HexStr(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String decode(String value) {
		throw new UnsupportedOperationException("md5不支持解密");
	}

}
