package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//��������
public class ProxyFactoryBean implements InvocationHandler {
	private Object target;// Ŀ�����
    public void setTarget(Object target) {
		this.target = target;
	}
    
	// ���������κη������ö��ᱻinvoke��������
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Ȩ�ޡ���־��������....");
		//method.invoke(target, args);
		System.out.println("����ִ����ɺ����־��������");
		return null;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

}
