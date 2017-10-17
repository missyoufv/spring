package com.study.framework.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket 服务端 监听指定端口号
 * @author admin
 *
 */
public class Server {
	
	public static final int SOCKET_PORT = 10010;
	
	
	public static void main(String[] args) {
		
		Socket socket = null;
		ServerSocket server = null;
		
		try{
			server = new ServerSocket(SOCKET_PORT);
			while(true){
				socket = server.accept();//获取客户端socket
				//从客户端socket获取输入流
				InputStream inputStream = socket.getInputStream();
				byte [] buff = new byte[1024];
				int lenth = inputStream.read(buff);
				if(lenth>0){
					String message = new String(buff,0,lenth);
					System.out.println("客户端请求信息为"+message);
				}else{
					System.out.println("bad request--");
				}
				String respStr = "<html><head><title>my frame</title></head><body>hello world</body></html>";
			    PrintStream writer = new PrintStream(socket.getOutputStream());
			    
			    /**
			     * 以下是http协议响应报文的格式
			     */
			  
                writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
                //响应头参数
                writer.println("Content-Type:text/html;charset=GB2312");
                writer.println("Content-Length:" + respStr.length());// 返回内容字节数
                //响应头和响应体之间隔着一行
                writer.println();
                //响应体
				writer.print(respStr);
				writer.flush();
				writer.flush();
				socket.close();
				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	
	

}
