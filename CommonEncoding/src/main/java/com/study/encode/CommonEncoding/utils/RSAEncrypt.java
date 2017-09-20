package com.study.encode.CommonEncoding.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 公钥加密私钥解密是密送，保证消息即使公开也只有私钥持有者能读懂。
 * 私钥加密公钥解密是签名，保证消息来源是私钥持有者。
 * RSA生成公私钥，对报文进行加密
 * 
 * @author admin
 *
 */
public class RSAEncrypt {

	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 随机生成密钥对
	 */
	public static void genKeyPair(String filePath) {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 初始化密钥生成器，密钥大小为94-1024
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 获得私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 获得公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		try {
			// 得到公钥字符串
			String publicKeyString = new String(
					encryptBASE64(publicKey.getEncoded()));
			// 得到私钥字符串
			String privateKeyString = new String(
					encryptBASE64(privateKey.getEncoded()));
			//判断path文件夹是否存在 ，不存在就创建
			FileUtils.smartCreateDirectory(filePath);
			// 将密钥对写到文件中
			FileWriter pubfw = new FileWriter(filePath + "/publicKey.cer");
			FileWriter prifw = new FileWriter(filePath + "/privateKey.pfx");
			BufferedWriter pubbw = new BufferedWriter(pubfw);
			BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
	
   
    
	/** 
     * 从文件中中加载公钥 
     *  
     * @param in 
     *            公钥输入流 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */   
    public static String loadPublicKeyByFile(String path) throws Exception {   
        try {   
            BufferedReader br = new BufferedReader(new FileReader(path));   
            String readLine = null;   
            StringBuilder sb = new StringBuilder();   
            while ((readLine = br.readLine()) != null) {   
                sb.append(readLine);   
            }   
            br.close();   
            return sb.toString();   
        } catch (IOException e) {   
            throw new Exception("公钥数据流读取错误");   
        } catch (NullPointerException e) {   
            throw new Exception("公钥输入流为空");   
        }   
    }
    
    /** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */   
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)   
            throws Exception {   
        try {   
            byte[] buffer = decryptBASE64(publicKeyStr);   
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");   
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);   
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此算法");   
        } catch (InvalidKeySpecException e) {   
            throw new Exception("公钥非法");   
        } catch (NullPointerException e) {   
            throw new Exception("公钥数据为空");   
        }   
    }   
    
	
	
	  /** 
     * 从文件中获取私钥 
     *  
     * @param keyFileName 
     *            私钥文件名 
     * @return 是否成功 
     * @throws Exception 
     */   
    public static String loadPrivateKeyByFile(String path) throws Exception {   
        try {   
            BufferedReader br = new BufferedReader(new FileReader(path));   
            String readLine = null;   
            StringBuilder sb = new StringBuilder();   
            while ((readLine = br.readLine()) != null) {   
                sb.append(readLine);   
            }   
            br.close();   
            return sb.toString();   
        } catch (IOException e) {   
            throw new Exception("私钥数据读取错误");   
        } catch (NullPointerException e) {   
            throw new Exception("私钥输入流为空");   
        }   
    }   
   
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)   
            throws Exception {   
        try {   
            byte[] buffer = decryptBASE64(privateKeyStr);   
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);   
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");   
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此算法");   
        } catch (InvalidKeySpecException e) {   
            throw new Exception("私钥非法");   
        } catch (NullPointerException e) {   
            throw new Exception("私钥数据为空");   
        }   
    }
    
    /** 
     * 公钥加密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */   
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)   
            throws Exception {   
        if (publicKey == null) {   
            throw new Exception("加密公钥为空, 请设置");   
        }   
        Cipher cipher = null;   
        try {   
            // 使用默认RSA   
            cipher = Cipher.getInstance("RSA");   
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());   
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);   
            byte[] output = cipher.doFinal(plainTextData);   
            return output;   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此加密算法");   
        } catch (NoSuchPaddingException e) {   
            e.printStackTrace();   
            return null;   
        } catch (InvalidKeyException e) {   
            throw new Exception("加密公钥非法,请检查");   
        } catch (IllegalBlockSizeException e) {   
            throw new Exception("明文长度非法");   
        } catch (BadPaddingException e) {   
            throw new Exception("明文数据已损坏");   
        }   
    }   
   
    /** 
     * 私钥加密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */   
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)   
            throws Exception {   
        if (privateKey == null) {   
            throw new Exception("加密私钥为空, 请设置");   
        }   
        Cipher cipher = null;   
        try {   
            // 使用默认RSA   
            cipher = Cipher.getInstance("RSA");   
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);   
            byte[] output = cipher.doFinal(plainTextData);   
            return output;   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此加密算法");   
        } catch (NoSuchPaddingException e) {   
            e.printStackTrace();   
            return null;   
        } catch (InvalidKeyException e) {   
            throw new Exception("加密私钥非法,请检查");   
        } catch (IllegalBlockSizeException e) {   
            throw new Exception("明文长度非法");   
        } catch (BadPaddingException e) {   
            throw new Exception("明文数据已损坏");   
        }   
    }   
   
    /** 
     * 私钥解密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */   
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)   
            throws Exception {   
        if (privateKey == null) {   
            throw new Exception("解密私钥为空, 请设置");   
        }   
        Cipher cipher = null;   
        try {   
            // 使用默认RSA   
            cipher = Cipher.getInstance("RSA");   
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());   
            cipher.init(Cipher.DECRYPT_MODE, privateKey);   
            byte[] output = cipher.doFinal(cipherData);   
            return output;   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此解密算法");   
        } catch (NoSuchPaddingException e) {   
            e.printStackTrace();   
            return null;   
        } catch (InvalidKeyException e) {   
            throw new Exception("解密私钥非法,请检查");   
        } catch (IllegalBlockSizeException e) {   
            throw new Exception("密文长度非法");   
        } catch (BadPaddingException e) {   
            throw new Exception("密文数据已损坏");   
        }   
    }   
   
    /** 
     * 公钥解密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */   
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)   
            throws Exception {   
        if (publicKey == null) {   
            throw new Exception("解密公钥为空, 请设置");   
        }   
        Cipher cipher = null;   
        try {   
            // 使用默认RSA   
            cipher = Cipher.getInstance("RSA");   
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());   
            cipher.init(Cipher.DECRYPT_MODE, publicKey);   
            byte[] output = cipher.doFinal(cipherData);   
            return output;   
        } catch (NoSuchAlgorithmException e) {   
            throw new Exception("无此解密算法");   
        } catch (NoSuchPaddingException e) {   
            e.printStackTrace();   
            return null;   
        } catch (InvalidKeyException e) {   
            throw new Exception("解密公钥非法,请检查");   
        } catch (IllegalBlockSizeException e) {   
            throw new Exception("密文长度非法");   
        } catch (BadPaddingException e) {   
            throw new Exception("密文数据已损坏");   
        }   
    }   
   
    /** 
     * 字节数据转十六进制字符串 
     *  
     * @param data 
     *            输入数据 
     * @return 十六进制内容 
     */   
    public static String byteArrayToString(byte[] data) {   
        StringBuilder stringBuilder = new StringBuilder();   
        for (int i = 0; i < data.length; i++) {   
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移   
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);   
            // 取出字节的低四位 作为索引得到相应的十六进制标识符   
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);   
            if (i < data.length - 1) {   
                stringBuilder.append(' ');   
            }   
        }   
        return stringBuilder.toString();   
    }   
     
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

}
