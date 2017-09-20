package com.study.encode.CommonEncoding.test;

import org.junit.Test;

import com.study.encode.CommonEncoding.utils.RSAEncrypt;
import com.study.encode.CommonEncoding.utils.RSASignature;

public class RsaTest {
	
	@Test
	public void testGenKey(){
		RSAEncrypt.genKeyPair("e:/data/certs");
	}
	
	/**
	 * 下面用 私钥加密（数字签名），用公钥进行验证
	 * @throws Exception
	 */
	@Test
	public void testEncrypt() throws Exception{
	
		String privateKeyPath ="e:/data/certs/privateKey.pfx";
		String publicKeyPath = "e:/data/certs/publicKey.cer";
		System. out.println( "---------------私钥签名过程------------------" );   
        String content= "ihep_这是用于签名的原始数据" ;   
        String signstr=RSASignature.encryptBASE64(RSASignature.sign(content,RSAEncrypt.loadPrivateKeyByFile(privateKeyPath)));   
        System. out.println( "签名原串：" +content);   
        System. out.println( "签名串："+signstr);   
        System. out.println();   
//	           
        System. out.println( "---------------公钥校验签名------------------" );   
        System. out.println( "签名原串：" +content);   
        System. out.println( "签名串："+signstr);   
           
        System. out. println("验签结果："+RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(publicKeyPath)));
	}


}
