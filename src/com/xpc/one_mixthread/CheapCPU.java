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
	 * �ж�CPU�Ƿ�æ
	 */
	private boolean isBusy;
	
	/**
	 * ʱ��Ƭ��ʱ��
	 */
	private int timer;
	
	private List<Mission> list = new ArrayList<Mission>();
	
	private static int i=0;
	
		
	
	
	/**
	 * CPU����,�жϹ����Ƿ���ɻ�ʱ��Ƭ�Ƿ��þ���������Ӧ��������ȼ���������ѹջ
	 * @param mission
	 */
	public void work(Mission mission) {
		if (!this.isBusy&&mission.isTurnFlag()) {
			this.isBusy=true; //�ж��Ƿ�ø�������������CPU��־λ
			System.out.println(mission.getName()+"��ʼ����");
			for (timer = 0; timer < 100; timer=timer+20) {
				if (mission.getProcess()<100) {
					mission.setProcess(mission.getProcess()+(int)(Math.random()*16));
					System.out.println(mission.getName()+"����Ϊ��"+mission.getProcess());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println(mission.getName()+"�������");
					mission.setTurnFlag(false);
					this.isBusy=false;
					impLevel();
					if (Math.random()>0.5) {
						addMission();
						System.out.println("���������#"+i);
					}
					return;
				}
			}
			impLevel();
			mission.setLevel(mission.getLevel()+1);
			mission.setTurnFlag(false);
			stack(mission);
			System.out.println(mission.getName()+"ʱ�䵽�����ȼ�����Ϊ"+mission.getLevel());
			this.isBusy=false;				
		}
	}
	
	/**
	 * �Ὣ����ѹջ�����������ȼ��Ӹߵ�������
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
	 * ���������񣬲���ջ���������
	 */
	public void addMission(){
		Mission mission = new Mission("����#"+i);
		mission.setLevel((int)(Math.random()*10)+5);
		mission.setProcess(0);
		mission.setTurnFlag(false);
		stack(mission);
		i++;
	}
	
	/**
	 * ջ�������������ȼ����
	 */
	public void impLevel() {
		for (int i=0;i<list.size();i++){
			int temp = list.get(i).getLevel();
			list.get(i).setLevel(temp-1);
		}
	}
	
	/**
	 * ��ջ��ȡ�����ȼ���ߵ����񣬲���ջ���Ƴ�
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
