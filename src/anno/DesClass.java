package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*����һ��ע���ʵ����
 * ע���ʵ������ʵ����@ע��(������1=����ֵ,������2=����ֵ... ...)
 * ��ע��ʵ����ʱ���������ֻҪ��һ��������Ϊvalue,��ôvalue=����ʡ��
 */
@Target(ElementType.TYPE)//дһ��ע��������Ҫ����ע����������˭?
@Retention(RetentionPolicy.RUNTIME)//ע��ʲôʱ����Ч
public @interface DesClass {
	String testClass() default "";
	int[]  getArray();
}
