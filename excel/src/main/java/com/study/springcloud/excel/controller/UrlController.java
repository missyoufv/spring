package com.study.springcloud.excel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 读取jar包中文件 路径问题
 * @author admin
 *
 */
@RestController
public class UrlController {

	
	/**
	 * 下面是错误的读取方式，因为JAR中的文件无法以File方式返回，而只有在文件系统中的类资源才可以以File的形式返回。
	 * @return
	 */
//	@RequestMapping("/readSource")
//	public String readSource(){
//		
//		
//		ClassLoader classLoader = this.getClass().getClassLoader();
//		String filepath = classLoader.getResource("spring/urlTest.xml").getPath();
//		
//		System.out.println("配置文件路径为:"+filepath);
//		BufferedReader bf  = null;
//		try {
//			bf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
//			String content = null;
//			StringBuffer sb = new StringBuffer();
//			while((content = bf.readLine())!= null){
//				sb.append(content);
//			}
//			System.out.println("配置文件内容为:"+sb.toString());
//			return sb.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			try {
//				bf.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
//	}
	
	/**
	 * 正确的方式如下 ，如果生产环境不是以jar包启动运行的 ，亦可以上面那种方式
	 * 不管是文件系统中的类资源，还是JAR中的类资源文件，都可以以流的形式读取，因此，如上的代码调整一下就正常了： 
	 * @return
	 */
	
	@RequestMapping("/readSource")
	public String readSource(){
		
		
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("spring/urlTest.xml");
		
		BufferedReader bf  = null;
		try {
			bf = new BufferedReader(new InputStreamReader(inputStream));
			String content = null;
			StringBuffer sb = new StringBuffer();
			while((content = bf.readLine())!= null){
				sb.append(content);
			}
			System.out.println("配置文件内容为:"+sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
