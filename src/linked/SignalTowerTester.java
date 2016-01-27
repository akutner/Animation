package linked;
import acm.program.ConsoleProgram;
public class SignalTowerTester extends ConsoleProgram{
	private SignalTower rohan, rogo, nacho, gibbons, israel, alex;//signal towers
	public void run(){
		createSignalTowers();
		alex.signal();//signal first tower
	}
	
	private void createSignalTowers(){//creates towers
		
		rohan= new SignalTower("Rohan",null);
		rogo= new SignalTower("Rogo",rohan);
		nacho=new SignalTower("Nacho",rogo);
		gibbons= new SignalTower("Gibbons",nacho);
		israel= new SignalTower("Israel",gibbons);
		alex=new SignalTower("Alex",israel);
		rohan.setSignal(alex);
	}
}
