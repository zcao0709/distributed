package thread;




class ServiceProxy implements Service {
	public ServiceProxy() {
		_service = new ServiceImp();
		_active_object = new ActiveObject();
		
	}

	public void sayHello() {
		//��װ�ɶ����������������� ---->����������
		MethodRequest mr = new SayHello(_service);
		//�ӵ�������ȥ
		_active_object.enqueue(mr);
		
	}
	
	

	private Service _service;
	private ActiveObject _active_object;
}
