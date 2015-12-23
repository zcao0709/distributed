package mina;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class Demo1Server {

	private static Logger logger = Logger.getLogger(Demo1Server.class);
	private static final int PORT = 3005;

	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.addListener(new ServerListener());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new MyTextLineCodecFactory()));
		// ���ö�ȡ���ݵĻ�������С
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// ��дͨ��10�����޲����������״̬
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		// ���߼���������
		acceptor.setHandler(new Demo1ServerHandler());
		// �󶨶˿�
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			logger.info("�����������ɹ����������˿�Ϊ��" + PORT);
		} catch (IOException e) {
			logger.error("�����������쳣��������", e);
			e.printStackTrace();
		}
	}
}
