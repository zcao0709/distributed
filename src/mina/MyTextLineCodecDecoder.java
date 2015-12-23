package mina;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


/**
 * @author qh
 * �Զ����ı������� 
 */
public class MyTextLineCodecDecoder implements ProtocolDecoder{
   
	private Logger logger = Logger.getLogger(MyTextLineCodecDecoder.class);
	//�ַ���������
	private Charset charset = Charset.forName("UTF-8");
	//�������ݴ�ŵ�IoBuffer��С
	private IoBuffer ioBuffer = IoBuffer.allocate(1024).setAutoExpand(true);
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		logger.info("��ʼ����...........");
		while(in.hasRemaining()){
			byte by = in.get();
			//�����ݴ�ŵ�IoBuffer��
			ioBuffer.put(by);
			//���ݽ���������
			if(by == '\n'){
				ioBuffer.flip();
				byte[] b = new byte[ioBuffer.limit()];
				ioBuffer.get(b);
				String msg = new String(b,charset);
				//����ioBuffer
				ioBuffer = IoBuffer.allocate(100).setAutoExpand(true);
				out.write(msg);
			}
		}
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {		
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		logger.info("���������......");
	}

}
