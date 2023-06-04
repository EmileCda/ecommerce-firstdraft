package fr.ecommerce.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;

import fr.ecommerce.model.dao.implement.ParamsDao;
import fr.ecommerce.model.dao.interfaces.IParamsDao;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Code {

	private static Key key = null;

	public final static String ALGORITHM = "AES";
	public final static int FUNCTION_KEY_DB = 1705;
	public final static int DEFAULT_FUNCTION_KEY = 1000;
	public final static int KEY_LENGTH = 256;
	public final static String DEFAULT_ALGORYTHM = "DES";
	public final static int DEFAULT_FUNCTION_CODE = 0;
	public final static int BC_KEY = 0;
	public final static int PASS_KEY = 1;
	public final static String CHARSET = "UTF8";

//-------------------------------------------------------------------------------------------------	
	private static Key generateKey(int keyLength, String algorythm) throws NoSuchAlgorithmException {

		KeyGenerator keyGen = KeyGenerator.getInstance(algorythm);
//			SecureRandom secRandom = new SecureRandom();// Creating a SecureRandom object
//			keyGen.init(secRandom); // other solution : Initializing the KeyGenerator witht random length  

		keyGen.init(keyLength); // Initializing the KeyGenerator
		return keyGen.generateKey(); // Creating/Generating a key

	}
	// -------------------------------------------------------------------------------------------------

	private static Key getKeyFromDB(int functionCode) {

		Key keyReturn = null;

		IParamsDao myParamsDao = new ParamsDao();
		try {
			Params param = myParamsDao.getParamsFunctionCode(functionCode);
			if (param != null)
				keyReturn = new SecretKeySpec(param.getBlobKey(), param.getAlgorythm());
		} catch (Exception e) {
			Utils.trace("catch getKeyFromDB(functionCode) ");
			e.printStackTrace();
		}

		return keyReturn;
	}

	// -------------------------------------------------------------------------------------------------
	private static void saveKeyToDB(Key key) {

		IParamsDao myParamsDao = new ParamsDao();
		try {

			if (key != null) {
				byte[] blobKey = key.getEncoded();
				Params param = new Params(FUNCTION_KEY_DB, blobKey, KEY_LENGTH, ALGORITHM);
				myParamsDao.addParams(param);
			}

		} catch (Exception e) {
			Utils.trace("catch getKeyFromDB(functionCode) ");
			e.printStackTrace();
		}

	}
// -------------------------------------------------------------------------------------------------

	private static void initKey() {
		Code.setStaticKey(Code.getKeyFromDB(FUNCTION_KEY_DB));
		if (Code.getStaticKey() == null) {
			Utils.trace("Code.setStaticKey(Code.getKeyFromDB(FUNCTION_KEY_DB))== null");
			try {
				Code.setStaticKey(generateKey(KEY_LENGTH, ALGORITHM));
				Code.saveKeyToDB(Code.getStaticKey());
			} catch (NoSuchAlgorithmException e) {
				Utils.trace("catch initKey()");
				Utils.trace(e.toString());
				;
			}
		}

	}

	// -------------------------------------------------------------------------------------------------
	private static void setStaticKey(Key key) {
		Code.key = key;
	}

	// -------------------------------------------------------------------------------------------------
	private static Key getStaticKey() {
		return Code.key;
	}

	// -------------------------------------------------------------------------------------------------
	public static Key getKey() {
		if (Code.getStaticKey() == null) {
			Code.initKey();
		}
		return Code.getStaticKey();
	}

	// -------------------------------------------------------------------------------------------------
	public static byte[] encrypt(String toEncrypt) {

		if ((toEncrypt != null) && (toEncrypt.length() > 0)) {

			byte[] messageByte = null;
			try {
				messageByte = toEncrypt.getBytes(CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encrypt(messageByte);
		}
		return null;

	}

	// -------------------------------------------------------------------------------------------------
	public static byte[] encrypt(byte[] toEncrypt) {

		if (toEncrypt.length > 0) {

			try {
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, Code.getKey());
				return cipher.doFinal(toEncrypt);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	// -------------------------------------------------------------------------------------------------
	public static String decrypt(byte[] toDecrypte) {

		if ((toDecrypte != null) && (toDecrypte.length > 0)) {

			Cipher cipher;
			byte[] result = null;

			try {
				cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, Code.getKey());
				result = cipher.doFinal(toDecrypte);

			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result != null)
				return new String(result);

		}

		return null;
	}

	// -------------------------------------------------------------------------------------------------
	public static String decrypt2String(byte[] toDecrypte) {

		return new String(Code.decrypt(toDecrypte));

	}

	// -------------------------------------------------------------------------------------------------
}
