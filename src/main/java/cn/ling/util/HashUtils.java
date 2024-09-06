package cn.ling.util;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.MurmurHash3;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Hash工具类
 */
public class HashUtils {

	private HashUtils(){}

	private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();


	public static long getMurmurHash32(String str) {
		int i = MurmurHash3.hash32x86(StringUtils.getBytesUtf8(str));
		return i < 0 ? Integer.MAX_VALUE - (long) i : i;
	}

	public static boolean matchBc(CharSequence rawPassword, String encodedPassword) {
		return B_CRYPT_PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
	}

	/**
	 * 用于生成密码
	 * @param rawPassword 明文密码
	 * @return String
	 */
	public static String getBc(CharSequence rawPassword) {
		return B_CRYPT_PASSWORD_ENCODER.encode(rawPassword);
	}

	public static void main(String[] args) {
		String s = "$2a$10$FtXrq62IN1ijFpEOc6pl.uWYTj21AsfrNA57fWcigEyMQ9F4wrCLu";
		String s1="$2a$10$yG1u09xgfHsAb8W23IfFw.DidBI5AcPMoaczvfwNcXe3g6wmcqTQ6";
		System.out.println(matchBc("1451726907",s1));
		System.out.println(matchBc("123456", s));
		System.out.println(getBc("123456"));

	}

}
