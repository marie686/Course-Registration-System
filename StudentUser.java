package practice;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import practice.StudentUser.student;

public interface StudentUser {
	abstract void viewall(CourseDirectory dir);
	abstract void viewnotfull(CourseDirectory dir);
	abstract void register(course c);
	abstract void withdraw(course c);
	abstract void viewallreg();
public class student implements StudentUser,Serializable{
		private static final long serialVersionUID = 1L;
		
		
		private String username;
		private String password;
		private String firstname;
		private String lastname;
		
	
		public String getUsername() {
			return username;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getFirstname() {
			return firstname;
		}
		
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		
		public String getLastname() {
			return lastname;
		}
		
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		

		private ArrayList<course>courses;
	public ArrayList<course> getCourses() {
			return courses;
		}
		public void setCourses(ArrayList<course> courses) {
			this.courses = courses;
		}
	student(String un, String pass, String first, String last){
		this.firstname=first;
		this.username=un;
		this.lastname=last;
		this.password=pass;
		this.courses=new ArrayList<>();
		try(BufferedWriter writer=new BufferedWriter(new FileWriter("Users.txt",true))){
			List<String> lines=(List<String>) Files.readAllLines(Paths.get("Users.txt"));			
			if(!lines.contains(un+","+pass+","+first+","+last+","+"student" )) {
			writer.write(un+","+pass+","+first+","+last+","+"student"+"\n");}
		}catch(IOException e) {
			e.getMessage();
		}
		
		
		}
		
		
		@Override
		public void viewall(CourseDirectory dir) {
			for (course entry:dir.getDirectory()) {
				entry.print();
			}
			
		}

		@Override
		public void viewnotfull(CourseDirectory dir) {
			for (course entry:dir.getDirectory()) {
				if (entry.getCurrent_students()<entry.getMaximum_students()) {
					entry.print();
				}
			}
			
		
			
		}

		@Override
		public void register(course c) {
		if(c.getMaximum_students()>c.getCurrent_students()) {
			if (c.getList_of_names().get(0).equals("NULL")){
				c.getList_of_names().remove("NULL");
			}
				c.getList_of_names().add(this.firstname+","+this.lastname);
				c.setCurrent_students(c.getCurrent_students()+1);
				this.getCourses().add(c);
				System.out.println("Registered Sucessfully");
				
		}
					else {
						System.out.println("Course full");
					}
				}
			

		@Override
		public void withdraw(course c) {
			boolean in=false;

	for(String name:c.getList_of_names()) {
						if (name.equals(this.firstname+","+this.lastname)){
							in=true;
							}
	}
						if(in==true) {
							
							c.getList_of_names().remove(this.firstname+","+this.lastname);
							int newnum=c.getCurrent_students()-1;
							c.setCurrent_students(newnum);
							this.getCourses().remove(c);
							System.out.println("Withdrawn Sucessfully");
							
						
					}
						else if (in==false) {
		System.out.println("You are already not in this course");
	}
						
						
			
		}

		@Override
		public void viewallreg() {
			for(course c: this.courses) {
				c.print();
			}	
			if(this.getCourses().size()<1) {
				System.out.println("this student has no courses");
			}
		
	}

		}
		}
