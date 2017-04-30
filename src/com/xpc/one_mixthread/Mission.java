package com.xpc.one_mixthread;

public class Mission {

	public String getName() {
		return name;
	}

	/**
	 * �������ȼ�
	 */
	private int level;
	
	/**
	 * ռ��CPU��־λ
	 */
	private boolean turnFlag;
	
	/**
	 * ������� ������Ϊ100
	 */
	private int process;
	
	private String name;
	
	public Mission(String name){
		this.name=name;
	}
	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isTurnFlag() {
		return turnFlag;
	}

	public void setTurnFlag(boolean turnFlag) {
		this.turnFlag = turnFlag;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		if (process<=100) {
			this.process = process;
		}else {
			this.process=100;
		}
	}
}
