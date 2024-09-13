package practice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import practice.StudentUser.student;

	public interface AdminUser{
		abstract void viewall(CourseDirectory dir);
		abstract void displayinfo(String id, CourseDirectory dir);
		abstract void editcourse(String id, CourseDirectory dir);
		abstract void createcourse(course c, CourseDirectory dir);
		abstract void deletecourse(CourseDirectory dir, course c);
		abstract void registerstudent(String user, String pass, String first, String lastc);
		abstract void viewallfull(CourseDirectory dir);
		abstract void fulllist(CourseDirectory dir);
		abstract void viewstudents(CourseDirectory dir, course c);
		abstract void viewstudents(student s);
		abstract void sort(CourseDirectory dir);
	public class admin extends User implements AdminUser {
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
		admin(String un, String pass, String first, String last){
			super(un, pass, first, last);
			
			try(BufferedWriter writer=new BufferedWriter(new FileWriter("Users.txt",true))){
				List<String> lines=(List<String>) Files.readAllLines(Paths.get("Users.txt"));			
				if(!lines.contains(un+","+pass+","+first+","+last+","+"admin" )) {
						writer.write(un+","+pass+","+first+","+last+","+"admin"+"\n");}
				
			}catch(IOException e) {
				e.getMessage();
			}
			}

			public void viewall(CourseDirectory dir) {
				for (course entry:dir.getDirectory()) {
					entry.print();
				}
			}
			public void editcourse(String id,CourseDirectory dir) {
				boolean exists=false;
				course c=null;
				for (course entry:dir.getDirectory()) {
					if (entry.getCourse_id().equalsIgnoreCase(id)) {
						exists=true;
						c=entry;
					}
					}
				if (exists) {
							Scanner yea=new Scanner(System.in);
							int option=0;
							do {
								System.out.println("What would you like to edit?");
								System.out.println("1.Maximum number of students");
								System.out.println("2.Current number of students");
								System.out.println("3.List of students");
								System.out.println("4.Course Instructor");
								System.out.println("5.Section number");
								System.out.println("6.quit");
							option=yea.nextInt();
							switch(option) {
							
							case 1:
								System.out.println("What is the new maximum number of students");
								int n=yea.nextInt();
								if(n<0){
									System.out.println("Error choose a positive number");
								}else {
									c.setMaximum_students(n);	
								}
								break;
							case 2:
								System.out.println("What is the new number of students?");
								int x=yea.nextInt();
								if (x==c.getCurrent_students()) {
									System.out.println("There are already "+x+" students in the class");
								}else if(x<0){
									System.out.println("Error choose a positive number");
								
								}
								else {
									c.setCurrent_students(x);
									
								}
							break;
							case 3:
								System.out.println("Would you like to 1.remove or 2.add a name?");
								int o=yea.nextInt();
								if(o==1) {
								System.out.println("Enter first and last name of person you want to remove separated by comma");
								String name=yea.next();
								for(String e: c.getList_of_names()){
									if (e.equals(name)) {
										c.getList_of_names().remove(name);
										System.out.println("name removed");
										c.printList();
									}else {
										System.out.println("Student is not in the course");
									}
									}
								}else if(o==2) {
									System.out.println("Enter first and last name of person you want to add separated by comma");
									String name=yea.next();
									c.getList_of_names().add(name);
									System.out.println("name added");
									c.printList();
										
									
										
										
								}
								break;
							case 4:
								System.out.println("Enter the name of the new course instructor");
								String p=yea.next();
								if(c.getCourse_instructor().equals(p)) {
									System.out.println("This is already the instructor for "+c.getCourse_name());
									
								}else {
									c.setCourse_instructor(p);
									System.out.println("The new instructor for "+c.getCourse_name()+" is "+c.getCourse_instructor());
									
								}
							break;
							case 5:
								System.out.println("Enter new section number");
								int s=yea.nextInt();
								if (s==c.getSection_number()) {
									System.out.println("Already set");
									
								}else if(s<0){
									System.out.println("Error choose a positive number");
								
								}
								else {
									c.setSection_number(s);
									System.out.println("Section number set to "+c.getSection_number());
									
								}
								break;
							case 6:
								System.out.println("bye");
								break;
							
							}
						
						}while(option!=6);
							
				}else {
					System.out.println("course does not exist, create it before you edit");
				}
				
			}
			public void createcourse(course c, CourseDirectory dir) {
				boolean exists=false;
				for (course entry:dir.getDirectory()) {
					if (entry.getCourse_id().equalsIgnoreCase(c.getCourse_id())) {
						exists=true;
						
					}
				}
				if (exists){
					System.out.println("this course already exists");
				}else {
					dir.getDirectory().add(c);
					
					
				}
			}
			public void deletecourse(CourseDirectory dir, course c) {
				
					dir.getDirectory().remove(c);
					
				
			}

			@Override
			public void displayinfo(String id, CourseDirectory dir) {
				boolean exists=false;
				for (course entry:dir.getDirectory()) {
					if (entry.getCourse_id().equalsIgnoreCase(id)) {
						exists=true;
						entry.print();
					}
				}
				if (exists==false){
					System.out.println("course doesnt exist");
				
			}
				
			}

			@Override
			public void registerstudent(String user, String pass, String first, String last) {
				
					StudentUser.student newstudent=new StudentUser.student(user,pass,first,last);
					
				}
				

			@Override
			public void viewallfull(CourseDirectory dir) {
				for(course entry:dir.getDirectory()) {
					if (entry.getCurrent_students()==entry.getMaximum_students()) {
						entry.print();
					}
				}
				
			}

			@Override
			public void fulllist(CourseDirectory dir) {
				for(course entry:dir.getDirectory()) {
					if (entry.getCurrent_students()==entry.getMaximum_students()) {
						String file="full.csv";
						try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
							writer.write(entry.getCourse_name()+","+entry.getCourse_id()+","+entry.getMaximum_students()+","+entry.getCurrent_students()+","+entry.getList_of_names()+","+entry.getCourse_instructor()+","+entry.getSection_number()+","+entry.getLocation());
							
							
						}catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}

			@Override
			public void viewstudents(CourseDirectory dir, course c) {
				
				for (course entry:dir.getDirectory()) {
					if (entry.getCourse_id().equalsIgnoreCase(c.getCourse_id())) {
						entry.printList();
					}
				}
				
				
			}

			@Override
			public void viewstudents(student s) {
				
						s.viewallreg();
					
				
				
				
			}

			@Override
			public void sort(CourseDirectory dir) {
				ArrayList<course> sort=dir.getDirectory();
				for(int i=0;i<sort.size();i++) {
					course min=sort.get(i);
					int minindex=i;
					int newind=i;
					for(int j=i;j<sort.size();j++) {
						if (min.getCurrent_students()>sort.get(j).getCurrent_students()) {
							min=sort.get(j);
							newind=j;
						}
					}
				sort.set(minindex, min);
				sort.set(newind,sort.get(i));
				}
				for(course c:dir.getDirectory()) {
					c.print();
				}
			}
			}
			}
			
			




	
	
	