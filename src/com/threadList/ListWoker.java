package com.threadList;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ListWoker extends Thread{
	public List<String> workList;
	public int start;
	public int end;
	public ListWoker(List<String>  worklIist,int start,int end) {
		this.workList=worklIist;
		this.start=start;
		this.end=end;
	}
	@Override
	public void run() {
		/*�ֶ�ʽ*/
		for(int i=start;i<end;i++){
			String msg=workList.get(i);
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("�߳�"+Thread.currentThread().getName()+"����������"+i+",���ؽ��:"+msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
