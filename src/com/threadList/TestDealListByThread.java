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
		int threadNum=20;//�߳�������
		List<String> taskList=new ArrayList<String>();
		for(int i=0;i<345;i++){
			taskList.add("����ִ�н��Ϊ:"+i+"-----");
		}
		int dealNum=taskList.size()/threadNum;//ƽ��ÿ���̴߳����������
		int remainNum=taskList.size()%threadNum;//����������
		
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
			thread.setName("�߳�"+i);
			service.execute(thread);
		}
		
		service.shutdown();
		try {
			service.awaitTermination(1, TimeUnit.HOURS);
			Date endDate=new Date();
			System.out.println("���δ����ʱ:"+String.valueOf(endDate.getTime()-startDate.getTime())+"ms");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
