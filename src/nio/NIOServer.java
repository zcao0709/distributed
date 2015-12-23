package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class NIOServer {
	//��ʶ����
	private int flag = 0;
	private int BLOCK = 4096;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	private Selector selector ;
	public NIOServer(int port)throws IOException{
		//�򿪷������׽���ͨ��
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//����Ϊ������ģʽ
		serverSocketChannel.configureBlocking(false);
		//�������ͨ�������ķ����׽���
		ServerSocket serverSocket = serverSocketChannel.socket();
		//���з����
		serverSocket.bind(new InetSocketAddress(port));
		//ͨ��open�����ҵ�selector
		selector = Selector.open();
		//��Selectorע��Channel����������Ȥ���¼�
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server Start......");
	}
	public void listener()throws IOException{
		 //���ϵ���ѯ
		while(true){
			//ѡ��һ�����������Ӧ��ͨ���Ѿ���   ��
			//Selectorͨ��select����֪ͨ�������Ǹ���Ȥ���¼������ˡ�
			selector.select();
			//���ش�ѡ��������ѡ�����
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while(iterator.hasNext()){
				//���Ǵ���Щkey�е�channel()������ȡ�����Ǹո�ע���channel��
				SelectionKey selectionKey = iterator.next();
				//һ��key��������ɺ󣬾Ͷ����Ӿ����ؼ��֣�ready keys���б��г�ȥ
				iterator.remove();
				handleKey(selectionKey);
			}
		}
	}
	public void handleKey(SelectionKey selectionKey)throws IOException{
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String receiveText;
		String sendText;
		int count = 0;
		// ���Դ˼���ͨ���Ƿ���׼���ý����µ��׽������ӡ�
		if(selectionKey.isAcceptable()){
			//����Ϊ֮�����μ�ֵͨ��
			server = (ServerSocketChannel)selectionKey.channel();
			//���ܵ���ͨ���׽��ֵ�����
			//Ĭ������
			client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}else if(selectionKey.isReadable()){
			client = (SocketChannel)selectionKey.channel();
			receiveBuffer.clear();
			count = client.read(receiveBuffer);
			if(count > 0){
				receiveText = new String(receiveBuffer.array(),0,count);
				System.out.println("�������˽��տͻ�������:"+receiveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		}else if(selectionKey.isWritable()){
			sendBuffer.clear();
			client = (SocketChannel)selectionKey.channel();
			sendText = "message from server:" + flag++;
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();
			client.write(sendBuffer);
			System.out.println("�������ͻ��˷�������:"+sendText);
			client.register(selector, SelectionKey.OP_READ);
		}

		
	}
	public static void main(String[] args)throws IOException {
		int port = 8888;
		NIOServer server = new NIOServer(port);
		server.listener();
	}
}
