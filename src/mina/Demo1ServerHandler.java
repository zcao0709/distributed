package mina;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Demo1ServerHandler extends IoHandlerAdapter{
    Logger logger = Logger.getLogger(Demo1ServerHandler.class);
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.info("������������Ϣ�쳣....");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg =message.toString();
		System.out.println(msg);
		logger.info("����˽��ܵ�������Ϊ��" + message);
		if("bye".equals(msg)){
			session.close();
		}
		Date date = new Date();
		session.write(date);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
        //������Ϣ�ɹ�֮�󣬹ر�session�����ر���ͻ��˵����ӣ������ӷ�ʽ
		//session.close();
		logger.info("������������Ϣ�ɹ�....");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("�������ͻ��˴�������.....");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("�������������״̬....");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("�������ͻ������Ӵ�.....");
	}

}
