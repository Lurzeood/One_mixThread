package com.xpc.one_mixthread;

public class TestDemo {

	
	
	public static void main(String[] args) {
		CheapCPU CPU=new CheapCPU();		
		CPU.init();
		while (!CPU.getList().isEmpty()) {
			Mission temp = CPU.getMissionFromStack();
			temp.setTurnFlag(true);
			CPU.work(temp);
		}
		
	}

}
