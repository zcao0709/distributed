package reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

/*
 *  ��������Զ������ǲ��Ƕ����أ���Ȼ��
 *  ����˭�Ķ����أ�java.lang.Class
 *  ���������α�ʾ��?3�з�ʽ
 */
public class ClassDemo {
	public static void main(String[] args) {
		
		
		Class c = A.class;
		A a = new A();
		Class c2 = a.getClass();
		try {
			Class c3 = Class.forName("reflect.A");//Ҳ�����˶�̬������
			System.out.println(c==c2);
			System.out.println(c2==c3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("====================");
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
	    Class cc1 = list.getClass();
	    Class cc2 = list1.getClass();
	    System.out.println(cc1==cc2);
	    /*��һ�д�ӡtrue��˵��java�������ȥ���ͻ���
	     * java�ķ���ֻ�ڱ���׶���Ч����Ϊ�˷�ֹ����
	     * ���룬����ƹ����룬�Ϳ����ƹ�����
	     * ����Ĳ��������ƹ�����ġ�
	      */
	    list.add("hello");
	    try {
			Method m = cc1.getMethod("add", Object.class);
			m.invoke(list, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println(list.size());
	  /*  for(String string :list){
	    	System.out.println(string);
	    }*/
	    
	    Class cc3 = int.class;
	    Class cc5 = Integer.class;
	    Class cc4 = void.class;
	}
}
class A{
	
}
