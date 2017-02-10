package encryption;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/***
 * 散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如MD5、SHA等。一般进行散列时最好提供一个salt（
 * 盐），比如加密密码“admin”，产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，
 * 可以到一些md5解密网站很容易的通过散列值得到密码“admin”，即如果直接对密码进行散列相对来说破解更容易，此时我们可以加一些只有系统知道的干扰数据，
 * 如用户名和ID（即盐）；这样散列的对象是“密码+用户名+ID”，这样生成的散列值相对来说更难破解。
 * 
 * @author fire
 *
 */
public class Hashing {

	/***
	 * 通过盐“123”MD5散列“hello”
	 */
	private void MD5() {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();// 还可以转换为toBase64()/toHex()
	}

	/***
	 * 另外散列时还可以指定散列次数，如2次表示： md5(md5(str))
	 */
	private void MD5_2() {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt, 2).toString();// 还可以转换为toBase64()/toHex()
	}

	/***
	 * Shiro还提供了通用的散列支持. 通过调用SimpleHash时指定散列算法，其内部使用了Java的MessageDigest实现。
	 */
	private void General() {
		String str = "hello";
		String salt = "123";
		// 内部使用MessageDigest
		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();

	}

	/***
	 * 1、首先创建一个DefaultHashService，默认使用SHA-512算法； 2、可以通过hashAlgorithmName属性修改算法；
	 * 3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
	 * 4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
	 * 5、可以设置randomNumberGenerator用于生成公盐； 6、可以设置hashIterations属性来修改默认加密迭代次数；
	 * 7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
	 */
	private void HashService() {
		DefaultHashService hashService = new DefaultHashService(); // 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123")); // 私盐，默认无
		hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(1); // 生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2)
				.build();
		String hex = hashService.computeHash(request).toHex();

	}

	/***
	 * SecureRandomNumberGenerator用于生成一个随机数：
	 */
	private void randomNumber() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
	}
}
