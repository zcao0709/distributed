package reflect;

import java.lang.reflect.Array;

public class ArrayDemo {
	public static void main(String[] args) {
	   int[][] a ={{1,2,3},{4,5,6,7}};
	   Class c = a.getClass();
	   System.out.println(c==int[].class);
	   printArray(a);
	
	}
	/**
	 * ���obj��һ����ͨ����ֱ�Ӵ�ӡtoString
	 * ���obj��һ���������ӡ�����ÿ��Ԫ��
	 * @param obj
	 */
	public static void printArray(Object obj){
		Class c = obj.getClass();
		if(c.isArray()){
			int length = Array.getLength(obj);
			for(int i = 0; i < length;i++){
				Object o = Array.get(obj, i);
				printArray(o);
			}
		}else{
			System.out.println(obj);
		}
	}

}
