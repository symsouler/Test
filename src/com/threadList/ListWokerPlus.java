package com.threadList;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ListWokerPlus extends Thread{
	private List<String> workList;
	private AtomicInteger  index;
	private int executenum=0;//���̴߳���������
	private int  localIndex;
	public ListWokerPlus(List<String>  worklIist,AtomicInteger  index){
		this.workList=worklIist;
		this.index=index;
	}
	@Override
	public void run() {
		/*��ռʽ*/
		while(true){
			synchronized (workList) {
				localIndex=index.intValue();
				index.incrementAndGet();
			}
			if(localIndex>=workList.size()){
				System.out.println("----���������----�߳�"+Thread.currentThread().getName()+"---��������------���δ���������:"+executenum);
				break;
			}
			else{
				String msg=workList.get(localIndex);
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("�߳�"+Thread.currentThread().getName()+"����������"+localIndex+",���ؽ��:"+msg);
					executenum++;
				} catch (InterruptedException e) {
					e.printStackTrace();
			}
			}
		
			}
	}
}
