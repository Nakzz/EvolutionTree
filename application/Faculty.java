package application;

import java.util.ArrayList;

public class Faculty extends User{
	private ArrayList<String> coursesTaught;
	private ArrayList<String> officeHours;
	private ArrayList<String> officeLocation;
	
	public Faculty(ArrayList<String> coursesTaught, ArrayList<String> officeHours, ArrayList<String> officeLocation) {
		this.coursesTaught = coursesTaught;
		this.officeHours = officeHours;
		this.officeLocation = officeLocation;
	}
	
	public void setCoursesTaught(ArrayList<String> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}
	
	public void setOfficeHours(ArrayList<String> officeHours) {
		this.officeHours = officeHours;
	}
	
	public void setOfficeLocation(ArrayList<String> officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	public ArrayList<String> getCoursesTaught(){
		return coursesTaught;
	}
	
	public ArrayList<String> getOfficeHours(){
		return officeHours;
	}
	
	public ArrayList<String> getOfficeLocation(){
		return officeLocation;
	}
}
