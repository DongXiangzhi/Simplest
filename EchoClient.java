package cn.edu.ldu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoClient {

    public static void main(String[] args) {
        Socket clientSocket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            //1.创建客户机套接字
            clientSocket = new Socket();
            SocketAddress remoteAddr=new InetSocketAddress("localhost",5000); 
            System.out.println("1.创建客户机套接字成功！");
            //2.连接服务器
            clientSocket.connect(remoteAddr);
            System.out.println("2.客户机连接服务器localhost端口5000成功！");
            System.out.println("客户机使用的地址和端口："+clientSocket.getLocalSocketAddress());
            //与服务器会话           
            in = new BufferedReader(
                 new InputStreamReader(
                 clientSocket.getInputStream()));           
            out = new BufferedWriter(
                  new OutputStreamWriter(            
                  clientSocket.getOutputStream()));
            String sendStr="有朋自远方来";
            out.write(sendStr);  //向服务器发送字符串
            out.newLine();
            out.flush();
            System.out.println("3.1向服务器发送字符串成功!"+sendStr);
            String recvStr=in.readLine();  //从服务器接收字符串 
            System.out.println("3.2从服务器接收回送字符串成功！"+recvStr);
        } catch (IOException ex) {
            System.out.println("异常信息："+ex.getMessage());
        }
        //关闭套接字和流
        try {
            if (in != null) in.close();
            if (out != null)  out.close();
            if (clientSocket != null)   clientSocket.close(); 
            System.out.println("4.关闭套接字和流成功！");
        } catch (IOException ex) {
             System.out.println("异常信息："+ex.getMessage());
        } 
    }
}