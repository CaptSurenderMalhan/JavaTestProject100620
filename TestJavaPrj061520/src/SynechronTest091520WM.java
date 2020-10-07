import java.util.concurrent.atomic.AtomicBoolean;
public class SynechronTest091520WM { // Walter Marin
	public static void main(String[] args) {
		int[] numArr1 = {1,4,7,10};
		int[] numArr2 = {2,5,8,11};
		int[] numArr3 = {3,6,9,12};
		AtomicBoolean task1Completed = new AtomicBoolean(false);
		AtomicBoolean task2Completed = new AtomicBoolean(false);
		AtomicBoolean task3Completed = new AtomicBoolean(true);
		Thread[] threads = new Thread[3];
		threads[0] = new Thread(new PrintNumTask(numArr1, task3Completed, task1Completed));
		threads[1] = new Thread(new PrintNumTask(numArr2, task1Completed, task2Completed));
		threads[2] = new Thread(new PrintNumTask(numArr3, task2Completed, task3Completed));
		for(Thread t: threads) {
			t.start();
		}
		for(Thread t: threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static class PrintNumTask implements Runnable {
		int[] numArray;
		private AtomicBoolean dependentTaskCompleted;
		private AtomicBoolean currentTaskCompleted;
		private int currIndex;
		public PrintNumTask(int[] numArray, AtomicBoolean dependentTaskCompleted, AtomicBoolean currentTaskCompleted) {
			this.numArray = numArray;
			this.dependentTaskCompleted = dependentTaskCompleted;
			this.currentTaskCompleted = currentTaskCompleted;
			this.currIndex = 0;
		}
		@Override
		public void run() {
			while(currIndex < numArray.length) {
				if(this.dependentTaskCompleted.get()) {
					this.dependentTaskCompleted.set(false);
					System.out.println(numArray[currIndex++]);
					this.currentTaskCompleted.set(true);
				} else {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException("Thread sleep interrupted", e);
					}
				}
			}
			this.currentTaskCompleted.set(true);
		}
	}
}
class ThreeThreadPrintNumsInSeq09162020WM { // 09/16/20
	public static void main1(String[] args) {
		int[] numArr1 = {1,4,7,10};
		int[] numArr2 = {2,5,8,11};
		int[] numArr3 = {3,6,9,12};
		Thread[] threads = new Thread[3];
		int taskId1 = PrintNumTask.getActiveTaskId();
		int taskId2 = taskId1 + 1;
		int taskId3 = taskId2 + 1;
		threads[0] = new Thread(new PrintNumTask(numArr1, taskId1, taskId2));
		threads[1] = new Thread(new PrintNumTask(numArr2, taskId2, taskId3));
		threads[2] = new Thread(new PrintNumTask(numArr3, taskId3, taskId1));
		for(Thread t: threads) {
			t.start();
		}
		for(Thread t: threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static class PrintNumTask implements Runnable {
		private static volatile int activeTaskId = 1;
		private int[] numArray;
		private int currIndex;
		private int taskId;
		private int nextTaskId;
		public PrintNumTask(int[] numArray, int taskId, int nextTaskId) {
			this.numArray = numArray;
			this.currIndex = 0;
			this.taskId = taskId;
			this.nextTaskId = nextTaskId;
		}
		@Override
		public void run() {
			while(currIndex < numArray.length) {
				if(this.taskId == activeTaskId) {
					System.out.println(numArray[currIndex++]);
					activeTaskId = this.nextTaskId;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException("Task interrupted", e);
				}
			}
		}
		public static int getActiveTaskId() {
			return activeTaskId;
		}
		
	}
}
