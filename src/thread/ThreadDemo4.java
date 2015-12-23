package thread;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadDemo4 {
	public static void main(String[] args) {
		final Output output = new Output();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
			   while(true){
				   output.print("helloworld");
			   }
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
			   while(true){
				   output.print1("BYE-BYE");
			   }
			}
		}).start();
	}
}
class Output{
	private Lock lock = new ReentrantLock();
	public synchronized static void print(String name){
		
			for(int i = 0; i <name.length();i++){
				System.out.print(name.charAt(i));
			}
		   System.out.println();	
		
		
	}
	public  void print1(String name){
		synchronized (Output.class) {
			for(int i = 0; i <name.length();i++){
				System.out.print(name.charAt(i));
			}
		   System.out.println();	
		}
		
	
	
}
}