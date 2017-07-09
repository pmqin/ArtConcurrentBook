package cn.itcast.heima2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueuePConsumer {

	private static  int queuesize = 10;
	public static void main(String[] args) {
		
		
		BlockingQueuePConsumer test = new BlockingQueuePConsumer();	
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(queuesize);

		Producer producer = test.new Producer(queue);
		new Thread(producer).start();
		
		ExecutorService executorService=Executors.newFixedThreadPool(3);		
		for (int i = 0; i < 3; i++) {
			Consumer consumer = test.new Consumer(queue);
			executorService.execute(consumer);
		}
	
		
		
	}

	class Producer implements Runnable {
		BlockingQueue<String> queue;

		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}

		public void run() {
			while (true) {
				try {
					queue.put("1");
					 System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"+ (queuesize - queue.size()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer implements Runnable {
		BlockingQueue<String> queue;

		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		public void run() {

			while(true){
				try {
					queue.take();
					System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��" + queue.size() + "��Ԫ��");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
