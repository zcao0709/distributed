package mina;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Demo2ClientHandler extends IoHandlerAdapter{

	private Logger logger = Logger.getLogger(Demo2ClientHandler.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.info("�ͻ��˷�����Ϣ�쳣....");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		System.out.println("�ͻ��˽��յ�����ϢΪ��" + msg);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		String msg = message.toString();
		System.out.println("�ͻ��˷��͵���ϢΪ��" + msg);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("�ͻ��������˶Ͽ�����.....");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("�ͻ��������˴�������.....");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("�ͻ��˽������״̬....");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("�ͻ������������Ӵ�.....");
	}
	
}
