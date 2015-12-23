package anno;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) {
		//��λ�ȡע��   ���ע����Class��������ע��Method,��Ա������ע��Field
		Class c = A.class;
		//��ȡע���ʵ��
		DesClass dc = (DesClass)c.getAnnotation(DesClass.class);
		System.out.println(dc.testClass());
		System.out.println(Arrays.toString(dc.getArray()));
		
		Field[] fs = c.getDeclaredFields();
		for (Field field : fs) {
			DesField df = field.getAnnotation(DesField.class);
			if(df!=null){
				System.out.println(df.getName());
			}
		}
		
	}
}
@DesClass(testClass="������A",getArray={1,2,3})
class A{
	@DesField(getName="����")
	private String name;
	@DesField(getName="����")
	private int age;
	

}
