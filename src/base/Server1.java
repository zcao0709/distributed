package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class Server1 {
	public static void main(String[] args) {
		new Server1().startServer();
	}

	private static HashMap<String, Socket> users = new HashMap<String, Socket>();

	public static void addClient(String addr, Socket socket) {
		users.put(addr, socket);
	}

	public static Socket findClient(String addr) {
		if (users.containsKey(addr))
			return users.get(addr);
		return null;
	}

	public static HashMap<String, Socket> getUsers() {
		return users;
	}

	public void startServer() {
		try {
			ServerSocket s = new ServerSocket(9000);
			System.out.println("�������Ѿ�����");
			while (true) {
				Socket socket = s.accept();
				String uuid = UUID.randomUUID().toString();
				String addr = socket.getInetAddress().getHostAddress() + uuid;
				System.out.println("���յ��ͻ���:" + addr + "�ķ���!");
				Server1.addClient(addr, socket);
				new Service(socket,addr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Service extends Thread {
		private Socket socket;
		private PrintStream ps;// ��������ǰ�ͻ��˷�������
		private PrintStream clientToClient;// ˽��
		private PrintStream clientToAll;// Ⱥ��
		private BufferedReader br;
		private String addr;

		public Service(Socket socket,String addr) {
			this.socket = socket;
			try {
				ps = new PrintStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.addr = addr;
			this.start();
		}

		public void run() {
			try {
				while(true){
					String str = br.readLine();
					if("".equals(str)||str==null)
						continue;
					if("exit".equals(str)){
						System.out.println("�пͻ����˳�...");
					}
					int index = str.indexOf("/");
					if(index==-1){//Ⱥ��
						for(String key:Server1.getUsers().keySet()){
							Socket clientSocket = Server1.getUsers().get(key);
							clientToAll = new PrintStream(clientSocket.getOutputStream());
							clientToAll.println(str);
						}
					}else{//˽��
						String ip = str.split("/")[0];
						String content = str.split("/")[1];
						Socket clientSocket = Server1.findClient(ip);
						if(clientSocket==null){
							ps.println("��Ҫ���͵Ŀͻ�������!");
						}else{
							clientToClient = new PrintStream(clientSocket.getOutputStream());
							String message = this.addr+"/"+content;
							clientToClient.println(message);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
