package com.study.encode.CommonEncoding.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;

/**
 * 1、客户端：首先进行DES加密，再将加密后的结果进行BASE64加密。
 * 2、服务端：将得到的流转化为字符串，在进行BASE64解密， 将解密之后的结果再进行DES解密。
 *	  DES加解密的KEY必须一致！
 * @author admin
 *
 */
public class EncryptUtil {  
     
    public static final String DES_KEY="@Wx^t)V#";  
 
    /** 
    * 
    * 解密 
    * 
    * @param src 
    *            数据源 
    * @param key 
    *            密钥，长度必须是8的倍数 
    * @return 返回解密后的原始数据 
    * @throws Exception 
    */  
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {  
         // DES算法要求有一个可信任的随机数源  
         SecureRandom sr = new SecureRandom();  
         // 从原始密匙数据创建一个DESKeySpec对象  
         DESKeySpec dks = new DESKeySpec(key);  
         // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成  
         // 一个SecretKey对象  
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
         SecretKey securekey = keyFactory.generateSecret(dks);  
         // Cipher对象实际完成解密操作  
         Cipher cipher = Cipher.getInstance("DES");  
         // 用密匙初始化Cipher对象  
         cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
         // 现在，获取数据并解密  
         // 正式执行解密操作  
         return cipher.doFinal(src);  
    }  
 
    /** 
    * 
    * 加密 
    * 
    * @param src 
    *            数据源 
    * 
    * @param key 
    *            密钥，长度必须是8的倍数 
    * 
    * @return 返回加密后的数据 
    * 
    * @throws Exception 
    * 
    */  
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {  
         // DES算法要求有一个可信任的随机数源  
         SecureRandom sr = new SecureRandom();  
         // 从原始密匙数据创建DESKeySpec对象  
         DESKeySpec dks = new DESKeySpec(key);  
         // 创建一个密匙工厂，然后用它把DESKeySpec转换成  
         // 一个SecretKey对象  
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
         SecretKey securekey = keyFactory.generateSecret(dks);  
         // Cipher对象实际完成加密操作  
         Cipher cipher = Cipher.getInstance("DES");  
         // 用密匙初始化Cipher对象  
         cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
         // 现在，获取数据并加密  
         // 正式执行加密操作  
         return cipher.doFinal(src);  
    }  
    public static String encryptBASE64(byte[] key) throws Exception {  
         return (new BASE64Encoder()).encodeBuffer(key);  
    }  
    public static byte[] decryptBASE64(String key) throws Exception {  
         return (new BASE64Decoder()).decodeBuffer(key);  
    }  
     
    @SuppressWarnings("deprecation")  
    public static void main(String[] args) throws Exception {  
         JSONObject obj= new JSONObject();  
         obj.put("name", "张三");  
         System.out.println("加密前的数据："+obj.toString());  
         System.out.println("DES开始加密......");  
          
         String enData=encryptBASE64(encrypt(obj.toString().getBytes(), DES_KEY.getBytes()));  
         System.out.println("加密后的数据"+enData);  
          
         System.out.println("DES开始解密.....");  
         String deData=new String(decrypt(decryptBASE64(enData), DES_KEY.getBytes()));  
         System.out.println("DES解密后的数据:"+deData);  
    }  
}  
