package application;

import java.util.ArrayList;
/**
 * Faculty Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//This class represents a Faculty user. This class extends User
public class Faculty extends User{
	private ArrayList<String> coursesTaught;
	private ArrayList<String> officeHours;
	private ArrayList<String> officeLocation;
	
	//Constructor
	//@param ArrayList<String> coursesTaught, officeHours, officeLocation
	//@param String name, email, password
	public Faculty(ArrayList<String> coursesTaught, ArrayList<String> officeHours, ArrayList<String> officeLocation,
			String name, String email, String password) {
		super(name, email, password); //calls the User constructor with name, email, and password
		this.coursesTaught = coursesTaught;
		this.officeHours = officeHours;
		this.officeLocation = officeLocation;
	}
	
	//@param ArrayList<String> coursesTaught
	public void setCoursesTaught(ArrayList<String> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}
	
	//@param ArrayList<String> officeHours
	public void setOfficeHours(ArrayList<String> officeHours) {
		this.officeHours = officeHours;
	}
	
	//@param ArrayList<String> officeLocation
	public void setOfficeLocation(ArrayList<String> officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	//@return ArrayList<String> coursesTaught
	public ArrayList<String> getCoursesTaught(){
		return coursesTaught;
	}
	
	//@return Array<String> officeHours
	public ArrayList<String> getOfficeHours(){
		return officeHours;
	}
	
	//@return ArrayList<String> officeLocation
	public ArrayList<String> getOfficeLocation(){
		return officeLocation;
	}
}
