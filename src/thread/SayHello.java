package thread;
class SayHello implements MethodRequest
{
    public SayHello(Service s) {
        _service = s;
    }
    public void call() {
        _service.sayHello();
    }
    private Service _service;
}
