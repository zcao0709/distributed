package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(9000);
			System.out.println("�����Ѿ�����...");
			Socket socket = s.accept();//����
			System.out.println("���յ��ͻ��ķ���");
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			while(true){
				String str = br.readLine();
				System.out.println("�ͻ���˵:"+str);
				String str1 = in.readLine();
				if("end".equals(str1))break;
				out.println(str1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
