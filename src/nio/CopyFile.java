package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("d:\\users.txt");
			FileOutputStream fout = new FileOutputStream("d:\\excel.txt");
			//��ȡ�������ͨ��
			FileChannel fcin = fin.getChannel();
			FileChannel fcout = fout.getChannel();
			//����������
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(true){
				// clear�������軺������ʹ�����Խ��ܶ�������� 
				buffer.clear();
				int r = fcin.read(buffer);
				if(r==-1){
					break;
				}
				// flip�����û��������Խ��¶��������д����һ��ͨ��   
				buffer.flip();
				fcout.write(buffer);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
