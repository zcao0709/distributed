package thread;

public class Test {
	public static void main(String[] args) {
		C c = new C();
	}
}
/*
 * ʲôthis:��������������Ǹ����󣨵�����)
 * ����this:һ���Ǿ�̬�ĳ�Ա������������һ���Ǿ�̬�ĳ�Ա��ʡ����this
 */
class B{
	B(){this.test();}
	public void test(){
		System.out.println("hello");
	}
}
class C extends B{
//	private int i = 11;
	private int i ;
	{i = 11;}
	public void test() {
		System.out.println(i);
	}
}
