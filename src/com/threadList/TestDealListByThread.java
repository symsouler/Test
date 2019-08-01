package com.threadList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestDealListByThread {

	public static void main(String[] args) {
		ExecutorService service= Executors.newCachedThreadPool();
		int threadNum=20;//线程数数量
		List<String> taskList=new ArrayList<String>();
		for(int i=0;i<345;i++){
			taskList.add("任务执行结果为:"+i+"-----");
		}
		int dealNum=taskList.size()/threadNum;//平均每个线程处理的任务量
		int remainNum=taskList.size()%threadNum;//余数任务量
		
		if(threadNum>taskList.size()){
			threadNum=taskList.size();
		}
		int end=0;
		Date startDate=new Date();
		for(int i=0;i<threadNum;i++){
			int start=end;
			end=start+dealNum;
			if(i==threadNum-1){
				end=taskList.size();
			}
			else if(i<remainNum){
				end=end+1;
			}
			Thread thread=new ListWoker(taskList, start, end);
			thread.setName("线程"+i);
			service.execute(thread);
		}
		
		service.shutdown();
		try {
			service.awaitTermination(1, TimeUnit.HOURS);
			Date endDate=new Date();
			System.out.println("本次处理耗时:"+String.valueOf(endDate.getTime()-startDate.getTime())+"ms");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
