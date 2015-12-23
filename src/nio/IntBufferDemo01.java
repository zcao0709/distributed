package nio;

import java.nio.IntBuffer;
public class IntBufferDemo01 {
	public static void main(String[] args) {
		IntBuffer buf = IntBuffer.allocate(10);// ����10����С�Ļ�����
		System.out.print("1��д������֮ǰ��position��limit��capacity��") ;
		System.out.println("position = " + buf.position() + "��limit = "
				+ buf.limit() + "��capacity = " + buf.capacity());
		int temp[] = { 5, 7, 9 };	// ������������
		buf.put(3);		// �򻺳���д������
		buf.put(temp);		// �򻺳���д��һ������
		System.out.print("2��д������֮���position��limit��capacity��") ;
		System.out.println("position = " + buf.position() + "��limit = "
				+ buf.limit() + "��capacity = " + buf.capacity());
		buf.flip();				// ���軺����
		System.out.print("3��׼���������ʱ��position��limit��capacity��") ;
		System.out.println("position = " + buf.position() + "��limit = "
				+ buf.limit() + "��capacity = " + buf.capacity());
		System.out.print("�������е����ݣ�") ;
		while (buf.hasRemaining()) {	// ֻҪ�����������������
			int x = buf.get();		// ȡ����ǰ����
			System.out.print(x + "��");		// �������
		}
	}
}
