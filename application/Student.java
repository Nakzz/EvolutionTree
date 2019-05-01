package application;

import java.util.ArrayList;

public class Student{
	private int yearOfGrad;
	private ArrayList<String> major;
	private ArrayList<String> certificate;
	private ArrayList<String> clubs;
	private ArrayList<String> scholership; 
	private ArrayList<String> courses;
	private ArrayList<String> workExperience;
	
	public Student(int yearOfGrad, ArrayList<String> major, ArrayList<String> certificate, ArrayList<String> clubs,
			ArrayList<String> scholership, ArrayList<String> courses, ArrayList<String> workExperience){
		this.yearOfGrad = yearOfGrad;
		this.major = major;
		this.certificate = certificate;
		this.clubs = clubs;
		this.scholership = scholership;
		this.courses = courses;
		this.workExperience = workExperience;	
	}
	
	public int getYearOfGrad() {
		return yearOfGrad;
	}
	
	public ArrayList<String> getMajor(){
		return major;
	}
	
	public ArrayList<String> getCertificate(){
		return certificate;
	}
	
	public ArrayList<String> getClubs(){
		return clubs;
	}
	
	public ArrayList<String> getScholership(){
		return scholership;
	}
	
	public ArrayList<String> getCourses(){
		return courses;
	}
	
	public ArrayList<String> getWorkExperience(){
		return workExperience;
	}
	
	public void setMajor(ArrayList<String> major) {
		this.major = major;
	}
	
	public void setCertificate(ArrayList<String> certificate) {
		this.certificate = certificate;
	}
	
	public void setClubs(ArrayList<String> clubs) {
		this.clubs = clubs;
	}
	
	public void setScholership(ArrayList<String> scholership) {
		this.scholership = scholership;
	}
	
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	public void setWorkExperience(ArrayList<String> workExperience) {
		this.workExperience = workExperience;
	}
	
}

