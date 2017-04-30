package com.xpc.one_mixthread;

public class Mission {

	public String getName() {
		return name;
	}

	/**
	 * 任务优先级
	 */
	private int level;
	
	/**
	 * 占用CPU标志位
	 */
	private boolean turnFlag;
	
	/**
	 * 任务进度 满进度为100
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
