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
					 System.out.println("向队列取中插入一个元素，队列剩余空间："+ (queuesize - queue.size()));
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
					System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
