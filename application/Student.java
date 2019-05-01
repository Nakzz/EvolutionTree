package application;

import java.util.ArrayList;
/**
 * Student Class
 * 
 * @author erica, ben, aj, callan
 *
 */

<<<<<<< HEAD
//This class represents a Student. This class extends User
public class Student extends User{
=======
public class Student{
>>>>>>> d64deabc8ed757bd36a2c60e9a501f6306f21069
	private int yearOfGrad;
	private ArrayList<String> major;
	private ArrayList<String> certificate;
	private ArrayList<String> clubs;
	private ArrayList<String> scholership; 
	private ArrayList<String> courses;
	private ArrayList<String> workExperience;
	
	
	//Constructor
	//@param int yearOfGrad
	//@param ArrayList<String> major, certificate, clubs, scholership,courses,workExperience
	//@param name, email, password
	public Student(int yearOfGrad, ArrayList<String> major, ArrayList<String> certificate, ArrayList<String> clubs,
<<<<<<< HEAD
			ArrayList<String> scholership, ArrayList<String> courses, ArrayList<String> workExperience, String name, String email, String password){
		super(name, email, password); //calls User constructor with name, email, password
=======
			ArrayList<String> scholership, ArrayList<String> courses, ArrayList<String> workExperience){
>>>>>>> d64deabc8ed757bd36a2c60e9a501f6306f21069
		this.yearOfGrad = yearOfGrad;
		this.major = major;
		this.certificate = certificate;
		this.clubs = clubs;
		this.scholership = scholership;
		this.courses = courses;
		this.workExperience = workExperience;	
	}
	
	//@return int yearOfGrad
	public int getYearOfGrad() {
		return yearOfGrad;
	}
	
	//@return ArrayList<String> major
	public ArrayList<String> getMajor(){
		return major;
	}
	
	//@return ArrayList<String> certificate
	public ArrayList<String> getCertificate(){
		return certificate;
	}
	 //@return ArrayList<String> clubs
	public ArrayList<String> getClubs(){
		return clubs;
	}
	
	//@return ArrayList<String> scholership
	public ArrayList<String> getScholership(){
		return scholership;
	}
	 
	//@return ArrayList<String> courses
	public ArrayList<String> getCourses(){
		return courses;
	}
	 
	//@return ArrayList<String> workExperience
	public ArrayList<String> getWorkExperience(){
		return workExperience;
	}
	
	//@param ArrayList<String> major
	public void setMajor(ArrayList<String> major) {
		this.major = major;
	}
	
	//@param ArrayList<String> certificate
	public void setCertificate(ArrayList<String> certificate) {
		this.certificate = certificate;
	}
	
	//@param ArrayList<Sting> clubs
	public void setClubs(ArrayList<String> clubs) {
		this.clubs = clubs;
	}
	
	//@param ArrayList<String> scholership
	public void setScholership(ArrayList<String> scholership) {
		this.scholership = scholership;
	}
	
	//@param ArrayList<String> courses
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	//@param ArrayList<String> workExperience
	public void setWorkExperience(ArrayList<String> workExperience) {
		this.workExperience = workExperience;
	}
	
}

