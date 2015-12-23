package mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ��һ������дIoService��ע�������
			IoConnector connector = new NioSocketConnector();
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));
			// ��дIoHandler
	           connector.setHandler(new ClientHandler("��ã�\r\n��Һ�"));
			connector.connect(new InetSocketAddress("localhost", 8000));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ClientHandler extends IoHandlerAdapter {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ClientHandler.class);
	private final String values;

	public ClientHandler(String values) {
		this.values = values;
	}

	@Override
	public void sessionOpened(IoSession session) {
		session.write(values);
	}
	
}
