package base;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] rrgs) throws IOException {
		DatagramSocket socket = new DatagramSocket(14567);
		while (true) {
			
			byte data[] = new byte[1024];
			// ����һ���յ�DatagramPacket����
			DatagramPacket packet = new DatagramPacket(data, data.length);
			// ʹ��receive�������տͻ��������͵����ݣ�
			// ����ͻ���û�з������ݣ��ý��̾�ͣ��������
			socket.receive(packet);
			String result = new String(packet.getData(), packet.getOffset(),
					packet.getLength());
			System.out.println("result--->" + result);
		}

	}

}