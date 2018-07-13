package com.fei.common.util.encode;

public interface CoderNeedPassword extends Coder {

	public String encode(String content, String password);

	public String decode(String content, String password);

}
