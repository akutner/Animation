package linked;


import acm.program.*;
public class SignalTower{
	private String towerName;
	private SignalTower nextTower;
	
	public SignalTower(String name, SignalTower link){
		towerName=name;
		nextTower=link;
	}
	public void signal(){
		lightCurrentTower();
		//pause(50);
		if(nextTower!=null){
			nextTower.signal();
		}
	}
	
	public void lightCurrentTower(){
		System.out.println("lighting " + towerName);
		
	}
	public void setSignal(SignalTower t){
		nextTower=t;
	}

}
