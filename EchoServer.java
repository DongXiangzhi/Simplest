package cn.edu.ldu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoServer {

    public static void main(String[] args) {
        //1.启动服务器
        ServerSocket listenSocket = null; 
        Socket clientSocket = null; 
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            listenSocket = new ServerSocket();
            SocketAddress serverAddr=new InetSocketAddress("localhost",5000);
            listenSocket.bind(serverAddr); 
            System.out.println("1.服务器启动成功！开始在localhost的5000端口侦听连接...");
            //2.处理连接                      
            clientSocket = listenSocket.accept();
            System.out.println("2.客户机连接成功！客户机地址和端口："+clientSocket.getRemoteSocketAddress());
            //3.与客户机会话           
            in = new BufferedReader(
                 new InputStreamReader(
                 clientSocket.getInputStream()));           
            out = new BufferedWriter(
                  new OutputStreamWriter(            
                  clientSocket.getOutputStream()));
            String recvStr=in.readLine();  //从客户机接收字符串
            System.out.println("3.1 服务器收到字符串："+recvStr);
            out.write(recvStr);  //向客户机回送字符串           
            out.newLine();
            out.flush(); 
            System.out.println("3.2 服务器回送字符串成功："+recvStr);
        } catch (IOException ex) {
            System.out.println("异常信息："+ex.getMessage());
        }
        //4.关闭套接字和流
        try {  
            if (in != null)in.close();
            if (out != null)  out.close();
            if (listenSocket != null)   listenSocket.close();
            if (clientSocket != null)   clientSocket.close(); 
            System.out.println("4.关闭套接字和流成功！");
        } catch (IOException ex) {
           System.out.println("异常信息"+ex.getMessage());
        }
    }
}