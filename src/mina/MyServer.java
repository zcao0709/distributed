package mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {
	public static void main(String[] args) {
		try {
			/*
			 * ��һ������дIoService ��ʼ���˷���˵�TCP/IP �Ļ���NIO ���׽��֣�
			 * Ȼ�����IoSessionConfig���ö�ȡ���ݵĻ��� ����С����дͨ������10 �������κβ����ͽ��� ����״̬��
			 */
			IoAcceptor acceptor = new NioSocketAcceptor();
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			

			/*
			 * �ڶ�������д������
			 */
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));

			// ����������дIoHandler  MyIoHandler
			
			//ע��
			acceptor.setHandler(new MyIoHandler());

			
			acceptor.bind(new InetSocketAddress(8000));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

class MyIoHandler extends IoHandlerAdapter {
	// ��������ʹ�õ�SLF4J��Ϊ��־���棬����Ϊʲô�ں���˵����
	private final static Logger log = LoggerFactory
			.getLogger(MyIoHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String str = message.toString();
		System.out.println(str);
		log.info("The message received is [" + str + "]");
		if (str.endsWith("quit")) {
			session.close(true);
			return;
		}
	}
	
	
}