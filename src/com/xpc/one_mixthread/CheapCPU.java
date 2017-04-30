package com.xpc.one_mixthread;

import java.util.ArrayList;
import java.util.List;

public class CheapCPU {

	public List<Mission> getList() {
		return list;
	}

	public void setList(List<Mission> list) {
		this.list = list;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	/**
	 * 判断CPU是否繁忙
	 */
	private boolean isBusy;
	
	/**
	 * 时间片计时器
	 */
	private int timer;
	
	private List<Mission> list = new ArrayList<Mission>();
	
	private static int i=0;
	
		
	
	
	/**
	 * CPU工作,判断工作是否完成或时间片是否用尽，并将对应任务的优先级减低重新压栈
	 * @param mission
	 */
	public void work(Mission mission) {
		if (!this.isBusy&&mission.isTurnFlag()) {
			this.isBusy=true; //判断是否该该任务工作，设置CPU标志位
			System.out.println(mission.getName()+"开始工作");
			for (timer = 0; timer < 100; timer=timer+20) {
				if (mission.getProcess()<100) {
					mission.setProcess(mission.getProcess()+(int)(Math.random()*16));
					System.out.println(mission.getName()+"进度为："+mission.getProcess());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println(mission.getName()+"任务完成");
					mission.setTurnFlag(false);
					this.isBusy=false;
					impLevel();
					if (Math.random()>0.5) {
						addMission();
						System.out.println("添加新任务#"+i);
					}
					return;
				}
			}
			impLevel();
			mission.setLevel(mission.getLevel()+1);
			mission.setTurnFlag(false);
			stack(mission);
			System.out.println(mission.getName()+"时间到，优先级提升为"+mission.getLevel());
			this.isBusy=false;				
		}
	}
	
	/**
	 * 会将任务压栈，并按照优先级从高到低排序
	 * @param mission
	 */
	public void stack(Mission mission) {
		if (list.isEmpty()) {
			list.add(mission);
		}else {
			for (int i = 0; i < list.size(); i++) {
				Mission temp=list.get(i);
				if (mission.getLevel()<temp.getLevel()) {
					list.add(i,mission);
					return;
				}
			}
			list.add(mission);
		}
	}
	
	/**
	 * 创建新任务，并向栈中添加任务
	 */
	public void addMission(){
		Mission mission = new Mission("任务#"+i);
		mission.setLevel((int)(Math.random()*10)+5);
		mission.setProcess(0);
		mission.setTurnFlag(false);
		stack(mission);
		i++;
	}
	
	/**
	 * 栈中所有任务优先级提高
	 */
	public void impLevel() {
		for (int i=0;i<list.size();i++){
			int temp = list.get(i).getLevel();
			list.get(i).setLevel(temp-1);
		}
	}
	
	/**
	 * 从栈中取出优先级最高的任务，并从栈中移除
	 * @return
	 */
	public Mission getMissionFromStack() {
		Mission temp = list.get(0);
		list.remove(0);
		return temp;
	}
	
	public void init() {
		while (list.size()<3) {
			addMission();
		}
	}
	
	
}
