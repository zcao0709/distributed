package proxy;

public class PersonServiceProxy implements PersonService {
	private PersonServiceImpl ps ;
	public void setPs(PersonServiceImpl ps) {
		this.ps = ps;
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("����ִ��ǰ����־������Ȩ�޵ȴ���...");
		ps.delete();
		System.out.println("����ִ�к����־������Ȩ�޵ȴ���...");
	}

	@Override
	public void update() {
		System.out.println("����ִ��ǰ����־������Ȩ�޵ȴ���...");
		ps.update();
		System.out.println("����ִ�к����־������Ȩ�޵ȴ���...");
		
	}

}
