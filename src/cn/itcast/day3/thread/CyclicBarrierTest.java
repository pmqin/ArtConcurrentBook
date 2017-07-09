package cn.itcast.day3.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CyclicBarrierTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await();
						// 重置计数
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有"
								+ cb.getNumberWaiting() + "个已经到达，正在等候");
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
			
			

		}
		service.shutdown();//异步的
		
		//Java 如何判断线程池所有任务是否执行完毕
		//如果关闭后所有任务都已完成，则返回 true。注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。
		System.out.println("shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接受新任务。");
		while (true) {
			if (service.isTerminated()) {
				System.out.println("所有的子线程都结束了！");
				break;
			}
			Thread.sleep(100);
		}
		System.out.println("主线程结束");
	}

}
