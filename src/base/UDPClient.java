package base;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) {
		try {
			// ���ȴ���һ��DatagramSocket����
			DatagramSocket socket = new DatagramSocket();
			// ����һ��InetAddree
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			String str = "hello"; // ����Ҫ���������
			byte data[] = str.getBytes(); // �Ѵ������ݷֽ���ֽ�
			// ����һ��DatagramPacket���󣬲�ָ��Ҫ��������ݰ����͵����統�е��ĸ�����ַ���Լ��˿ں�
			DatagramPacket packet = new DatagramPacket(data, data.length,
					serverAddress, 14567);
			// ����socket�����send��������������
			socket.send(packet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
