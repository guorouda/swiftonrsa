package com.defonds.util.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.defonds.entity.LinkPayAnthar;

public class RsaEncryptUtil {
	private static final Log logger = LogFactory.getLog(RsaEncryptUtil.class);
	
	/**
	 * String to hold name of the encryption algorithm.
	 */
	private static final String ALGORITHM = "RSA";

	/**
	 * String to hold name of the encryption padding.
	 */
	private static final String PADDING = "RSA/NONE/NoPadding";

	/**
	 * String to hold name of the security provider.
	 */
	private static final String PROVIDER = "BC";
	
	/**
	 * apache commons codec to convert binary to String
	 */
	private static final Base64 COMMONS_CODEC_BASE_64 = new Base64();
	
	/**
	 * String to hold name of the public key file.
	 * this would be some string like:
	 * E:/javaprojects/swifton/src/main/webapp/WEB-INF/public.key
	 */
	private static final String PUBLIC_KEY_FILE_PATH = RsaEncryptUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "public.key";
	
	/**
	 * rsa public key
	 */
	private static PublicKey RSA_PUBLIC_KEY = null;
	
	/**
	 * java encrypt tool
	 */
	private static Cipher RSA_CIPHER = null;
	
	static {
		try {
			// add bc provider
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
			// load rsa public key from disk
			RSA_PUBLIC_KEY = (PublicKey) new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE_PATH)).readObject(); 
			// cipher init here
			RSA_CIPHER = Cipher.getInstance(PADDING, PROVIDER);
			RSA_CIPHER.init(Cipher.ENCRYPT_MODE, RSA_PUBLIC_KEY);
		} catch (ClassNotFoundException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (FileNotFoundException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (IOException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (NoSuchProviderException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (NoSuchPaddingException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		} catch (InvalidKeyException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.static code block error:", e);
		}
	}
	
	/**
	 * encrypt to_atm_no using rsa
	 * @param payAnthar
	 */
	public static void rsaEncryptForAtmNo(LinkPayAnthar linkPayAnthar) {
		// original text of to atm no, in String
		String toAtmNo = linkPayAnthar.getToAtmNo();
		// in fact, toAtmNo is always in range [12,24]
		if (toAtmNo.length() < 10) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.rsaEncrypt error: to atm no length is less than 10. to atm no=" + toAtmNo);
		} else if (toAtmNo.length() > 24) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.rsaEncrypt error: to atm no length is more than 24. to atm no=" + toAtmNo);
		}
		// cipher text of to atm no, in binary
		byte[] cipherText = null;
		// cipher text of to atm no, in String
		String cipherTextBase64 = null;
		try {
			// encrypt to atm no
			cipherText = RSA_CIPHER.doFinal(toAtmNo.getBytes());
			// use String to hold cipher binary data
			cipherTextBase64 = COMMONS_CODEC_BASE_64.encodeToString(cipherText);
			// SIMPLE_ACCT_NUM column init
			linkPayAnthar.setSimpleAcctNum(cipherTextBase64);
			
			// first 6 chars of toAtmNo
			String first6Chars = toAtmNo.substring(0, 6);
			// last 4 chars of toAtmNo
			String last4Chars = toAtmNo.substring(toAtmNo.length() - 4);
			// new to atm no is first6Chars + "******" + last4Chars. like this: 123456******7890
			linkPayAnthar.setToAtmNo(first6Chars + "******" + last4Chars);
		} catch (IllegalBlockSizeException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.rsaEncrypt error:", e);
		} catch (BadPaddingException e) {
			logger.error("com.cardinfolink.util.tool.RsaEncryptUtil.rsaEncrypt error:", e);
		}
	}
	
}
