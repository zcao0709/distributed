package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",9000);
			//�ӿͻ��˼��̶�����д���ͻ���  ---->������ģʽ/װ��ģʽ
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out =  new PrintWriter(socket.getOutputStream(),true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
		    	String str = br.readLine();
		    	if("exit".equals(str))break;
				out.println(str);
				String str1 = in.readLine();
				System.out.println("������˵:"+str1);
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
