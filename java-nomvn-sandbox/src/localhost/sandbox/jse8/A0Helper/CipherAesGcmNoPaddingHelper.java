package localhost.sandbox.jse8.A0Helper;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>Cipher "AES/GCM/NoPadding" example.
 *  
 * <p>This cipher does use intialization vector (iv), generated with each encrypted.
 * 
 * <p>Convention regarding encoding:
 * <ul>
 * <li>{@link SecretKeyH#keyBytesHex}, B16/Hex (preferred).
 * <li>iv, B64 (fixed).
 * <li>encrypted, B64 (fixed).
 * </ul>
 * 
 * <p>Pending:
 * <ul>
 * <li>Add attribute <i>SecreKeyH.encoding</i>.
 * <li>Finer control of attribute {@link SecretKeyH#keyBytesHex}, along 
 * with mentioned created attribute.
 * </ul>
 * 
 * @author Alberto Romero
 * @since 2025-09-29
 */
public class CipherAesGcmNoPaddingHelper {

	private static enum Encoding { BASE_16, BASE_64 };

	private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
	private static final int AUTHENTICATION_TAG_BITS = 128;
	private static boolean initialized = false;

	private static class SecretKeyH {
		private static final String ALGORITHM = "AES";
		private static final int KEY_BYTES_SIZE = 16; // 16, 32
		private static final Encoding DEFAULT_ENCODING = Encoding.BASE_16;
		private static String keyBytesHex = null;
	}



	public static void main() {
		System.out.println("\n-*-*-*-*- -*-*-*-*- -*-*-*-*-\n");
		System.out.println("Hello from Cipher AesGcmNoPadding Helper!");

		// input message
		String unencrypted = "Hello World";
		System.out.println("--> unencrypted: " + unencrypted);

		// initialize -- generate and/or assign byte array for symmetric key
		// keyBytesHex example, 128 bit (16 bytes): "983791D130AE8AC39E6BF5FD31C2768A"
		String initialKeyBytesHex = generateSecretKeyBytesHelper(SecretKeyH.KEY_BYTES_SIZE, SecretKeyH.DEFAULT_ENCODING); // method for example and key generation
		initialize(initialKeyBytesHex); // argument must be defined as constant in production environment
		System.out.println("--> secretKey.keyBytesHex: " + SecretKeyH.keyBytesHex  + " (do not print this on production!!!)");

		// generate iv and encrypted text
		// String ivHexDotEncryptedHex = encrypt(inputStr);
		String ivB64DotEncryptedB64 = encrypt(unencrypted);
		System.out.println("--> ivB64.encryptedB64: " + ivB64DotEncryptedB64);

		// generate decrypted text
		String decrypted = decrypt(ivB64DotEncryptedB64);
		System.out.println("--> decrypted: " + decrypted);

	}



	public static String generateSecretKeyBytesHelper(int keyBytesSize, Encoding outputEncoding) {
		// keyBytesSize must be 16 or 32 for AES symmetric encryption
		Random rd = new Random();
		byte[] secretKeyBytes = new byte[keyBytesSize];
		rd.nextBytes(secretKeyBytes);
		String secretKeyEncodedStr = null;
		switch (outputEncoding) {
		case BASE_16:
			secretKeyEncodedStr = ByteHexHelper.byteArrayToHexString(secretKeyBytes);
			break;
		case BASE_64:
			secretKeyEncodedStr = Base64.getEncoder().encodeToString(secretKeyBytes);
			break;
		}
		// System.out.println("secretKeyEncodedStr: " + secretKeyEncodedStr);
		return secretKeyEncodedStr;
	}



	public static String generateIvBytes() {
		try {
			SecureRandom secureRandom = SecureRandom.getInstanceStrong();
			int blockSize = Cipher.getInstance(CIPHER_ALGORITHM).getBlockSize();
			byte[] iv = new byte[blockSize];
			secureRandom.nextBytes(iv);
			String ivB64 = Base64.getEncoder().encodeToString(iv);
			return ivB64;
		} catch (Exception e) {
			System.err.println("e.getMessage(): " + e.getMessage() +  " -- e.getClass(): " + e.getClass());
			return null;
		}
	}



	public static String encrypt(String unencrypted) {
		try {
			// input, unencrypted
			// System.out.println("unencrypted: " + unencrypted);
			byte [] inputBytes = unencrypted.getBytes();

			// secret key
			byte[] keyBytes = ByteHexHelper.hexStringToByteArray(SecretKeyH.keyBytesHex);
			SecretKey secretKey = new SecretKeySpec(keyBytes, SecretKeyH.ALGORITHM);

			// initialization vector
			String ivB64 = generateIvBytes();
			byte[] ivBytes = Base64.getDecoder().decode(ivB64);
			GCMParameterSpec gcmParamSpec = new GCMParameterSpec(AUTHENTICATION_TAG_BITS, ivBytes);

			// cipher instance, initialization
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParamSpec);

			// do encrypt
			byte[] encryptedBytes = cipher.doFinal(inputBytes);
			String encryptedB64 = Base64.getEncoder().encodeToString(encryptedBytes);

			// output
			return ivB64 + "." + encryptedB64;

		} catch (Exception e) {
			System.err.println("e.getMessage(): " + e.getMessage() +  " -- e.getClass(): " + e.getClass());
			return null;
		}
	}



	public static String decrypt(String ivB64, String encryptedB64) {
		try {
			// System.out.println("ivB64: " + ivB64 + ", encryptedB64: " + encryptedB64);

			// input, initialization vector
			byte[] ivBytes = Base64.getDecoder().decode(ivB64);
			GCMParameterSpec gcmParamSpec = new GCMParameterSpec(AUTHENTICATION_TAG_BITS, ivBytes);

			// input, encrypted
			byte [] encryptedBytes = Base64.getDecoder().decode(encryptedB64);

			// secret key
			byte[] keyBytes = ByteHexHelper.hexStringToByteArray(SecretKeyH.keyBytesHex);
			SecretKey secretKey = new SecretKeySpec(keyBytes, SecretKeyH.ALGORITHM);

			// cipher instance, initialization
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParamSpec);

			// do decrypt
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			String decryptedStr = new String(decryptedBytes);

			// output
			// System.out.println("decryptedStr: " + decryptedStr);
			return decryptedStr;

		} catch (Exception e) {
			System.err.println("e.getMessage(): " + e.getMessage() +  " -- e.getClass(): " + e.getClass());
			return null;
		}
	}



	public static String decrypt(String ivB64DotEncryptedB64) {
		try {
			String[] auxStrArr = ivB64DotEncryptedB64.split("\\.");
			String ivB64 = auxStrArr[0];
			String encryptedB64 = auxStrArr[1];
			String decrypted = decrypt(ivB64, encryptedB64);
			return decrypted;
		} catch (Exception e) {
			System.err.println("e.getMessage(): " + e.getMessage() +  " -- e.getClass(): " + e.getClass());
			return null;
		}
	}



	public static void initialize(String keyBytesHex) {
		SecretKeyH.keyBytesHex = keyBytesHex;
		try {
			// secret key
			byte[] secretKeyBytes = ByteHexHelper.hexStringToByteArray(keyBytesHex);
			SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SecretKeyH.ALGORITHM);
			initialized = true;
		} catch (Exception e) {
			System.err.println("e.getMessage(): " + e.getMessage() +  " -- e.getClass(): " + e.getClass());
			initialized = false;
			return;
		}
	}



	public static boolean isInitialized() {
		return initialized;
	}

}

