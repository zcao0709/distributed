package mina;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;



public class Demo2Client {

	private Logger logger = Logger.getLogger(Demo2Client.class);
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3005;

	public static void main(String[] args) {
		// ����һ���������Ŀͻ��˳���
		IoConnector connector = new NioSocketConnector();
		// �������ӳ�ʱʱ�� ��λ����
		connector.setConnectTimeout(30000);
		// ��ӹ�����
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new MyTextLineCodecFactory()));
		// ���ҵ���߼�������
		connector.setHandler(new Demo2ClientHandler());
		// ��������
		IoSession session = null;
		try {
			ConnectFuture connect = connector.connect(new InetSocketAddress(
					HOST, PORT));
			// �ȴ����Ӵ������
			connect.awaitUninterruptibly();
			// ��ȡsession
			session = connect.getSession();
			session.write("�Ұ��㣬mina��������");
		} catch (Exception e) {
			System.out.println("�ͻ��������쳣");
		}
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
