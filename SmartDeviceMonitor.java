import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract class SmartDevice{
		protected String name;
		protected boolean isOn;
		
		public SmartDevice(String name){
			this.name = name;
			this.isOn = false;
		}
		
		public abstract void operate();
		
		public void showTime(){
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss ddMMyyyy");
			System.out.println("Logged time is: " + now.format(formatter));
		}
		
		public void rename(String newName){
			this.name = newName;
		}
		
		@Override
		public String toString(){
			return  name + " is now " + (isOn ? "ON" : "OFF");
		}
	}
	
interface Switchable{
	void turnOn();
	void turnOff();
}

interface StatusCheckable{
	boolean isActive();
	String getStatusMessage();
}

class SmartBulb extends SmartDevice implements Switchable, StatusCheckable{
	private int brightness;
	
	public SmartBulb(String name){
		super(name);
		this.brightness = 0;
	}
	public void setBrightness( int level){
		this.brightness = level;
	}
	
	@Override
	public void turnOn(){
		this.isOn = true;
		System.out.println(name + " is now ON");
	}
	
	@Override
	public void turnOff(){
		this.isOn = false;
		System.out.println(name + " is now Off");
	}
	
	@Override
	public void operate(){
		System.out.println("Light at " + brightness + "% brightness");
	}
	@Override
	public boolean isActive(){
		return isOn && brightness > 0;
	}
	 @Override
	 public String getStatusMessage(){
		 return "Status: " + (isActive() ? "ACTIVE" : "INACTIVE") + " - Brightness: " + brightness + "%";
	 }
}

public class SmartDeviceMonitor{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter device name: ");
		String deviceName = scan.nextLine();
		
		SmartBulb bulb = new SmartBulb(deviceName);
		
		int brightness;
		while(true){
			System.out.println("Set brightness (0-100): ");
			brightness = scan.nextInt();
			if(brightness >= 0 && brightness <= 100){
				break;
			}
			System.out.println("invalid brightness. Please enter a value between 0 and 100.");
		}
		bulb.setBrightness(brightness);
		bulb.turnOn();
		bulb.operate();
		
		StatusCheckable status = bulb;
		System.out.println(status.getStatusMessage());
		
		bulb.showTime();
		scan.close();
	}
}