package nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo03 {
	public static void main(String[] args) throws Exception {
		File file = new File("d:" + File.separator + "d:\\user.txt");
		FileInputStream input = null;			// �ļ�������
		input = new FileInputStream(file);	// ʵ����������
		FileChannel fin = null ;		// ���������ͨ������
		fin = input.getChannel() ;		// �õ������ļ�ͨ��
		MappedByteBuffer mbb = null ;	// �����ļ����ڴ�ӳ��
		mbb = fin.map(FileChannel.MapMode.READ_ONLY, 
				0, file.length()) ;	// ���ļ�ӳ�䵽�ڴ���
		byte data[] = new byte[(int) file.length()];	// �����ֽ����飬��������
		int foot = 0 ;				// �����±�
		while(mbb.hasRemaining()){		// �ж��Ƿ�������
			data[foot++] = mbb.get() ;		// ȡ������
		}
		System.out.println(new String(data)) ;	// ��ʾ���������
		fin.close() ;			// �ر�����ͨ��
		input.close() ;				// �ر�������
	}
}
