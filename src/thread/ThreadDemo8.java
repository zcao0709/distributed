package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadDemo8 {
	public static void main(String[] args) {
		// ExecutorService threadPool = Executors.newFixedThreadPool(3);
		/*
		 * ExecutorService threadPool = Executors.newSingleThreadExecutor(); for
		 * (int i = 1; i <= 10; i++) { final int task = i;
		 * threadPool.execute(new Runnable() {
		 * 
		 * @Override public void run() { for (int j = 1; j <= 10; j++) {
		 * System.out.println(Thread.currentThread().getName() + "ִ�е�" + task+
		 * "������ĵ�" + j + "��ѭ��"); } } }); }
		 */
		ScheduledExecutorService threadPool = Executors
				.newScheduledThreadPool(1);
		threadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("��ը��...");
			}
		}, 0, 2, TimeUnit.SECONDS);

	}

}
