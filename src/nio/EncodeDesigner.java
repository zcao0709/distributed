package nio;

public class EncodeDesigner {
	public static void dosomething(String name,CodeFactory cf){
		//1��name���б���
		//2.�洢
		//3.��ȡ
		//4.����
		//other
	}
}
interface Decode{
	void decode(String name);
}
interface Encode{
	void encode(String name);
}
abstract class CodeFactory{
	public abstract Decode getDecode();
	public abstract Encode getEncode();
}
//���󹤳����ģʽ---->��������ļ���
/*class A{
	public void f(){
		EncodeDesigner.dosomething("zhangsan", new CodeFactory() {
			
			@Override
			public Encode getEncode() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Decode getDecode() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}*/