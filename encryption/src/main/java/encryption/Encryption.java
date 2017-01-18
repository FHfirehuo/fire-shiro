package encryption;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;

public class Encryption {

	/***
	 * base64编码/解码操作
	 */
	private void Base64Util() {
		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes());
		String str2 = Base64.decodeToString(base64Encoded);
		Assert.assertEquals(str, str2);

	}

	/***
	 * 16进制字符串编码/解码操作
	 */
	private void HexUtil() {
		String str = "hello";
		String base64Encoded = Hex.encodeToString(str.getBytes());
		String str2 = new String(Hex.decode(base64Encoded.getBytes()));
		Assert.assertEquals(str, str2);

	}

	/***
	 * byte数组/String之间转换
	 */
	public void aa() {
		String str = "hello";
		byte[] bytes = CodecSupport.toBytes(str, "utf-8");
		String str1 = CodecSupport.toString(bytes, "utf-8");
	}

}
