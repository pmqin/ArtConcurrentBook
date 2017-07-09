package chapter03;

class VolatileExample {
	public static void main(String[] args) {
		final example1 itExample = new VolatileExample().new example1();

		new Thread(new Runnable() {
			public void run() {
				itExample.writer();
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				itExample.reader();
			}
		}).start();

	}

	class example1 {
		int a = 0;
		volatile boolean flag = false;

		public void writer() {
			a = 1; // 1
			flag = true; // 2
		}

		public void reader() {
			if (flag) { // 3
				int i = a; // 4
				System.out.println(i);
				// бнбн
			}
		}
	}
}
