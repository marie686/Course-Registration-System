package practice;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import practice.StudentUser.student;
public class RegistrationApplication {

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		StudentUser.student studentobject=new StudentUser.student("Student","Student001","Jane","Doe");
		AdminUser.admin adminobject=new AdminUser.admin("Admin","Admin001","John","Doe");
		Scanner a=new Scanner(System.in);
		System.out.println("Enter Username:");
		String user=a.next();
		System.out.println("Enter Password:");
		String pass=a.next();	
		boolean log=false;
		
		ArrayList<student>students=new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Users.txt"), "UTF-8"))){
			String line;
			String[]inf=new String[5];
			while((line=reader.readLine())!=null) {
				inf=line.split(",");
				if (inf[0].equals(user)&&inf[1].equals(pass)){
					System.out.println("Login Succesful");
					log=true;
					break;
				}else {
					continue;	
						
				}
			}
			if (log==false) {
				System.out.println("User not found try again");
			}
			CourseDirectory direct= new CourseDirectory();
				try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("CourseDirectory.ser"))){
					 direct=(CourseDirectory) ois.readObject();
						}catch(IOException|ClassNotFoundException e){
							e.printStackTrace();
						}
				try(ObjectInputStream oi=new ObjectInputStream(new FileInputStream("Students.ser"))){
					
					while(true) {
						try {
							students.add((student)oi.readObject());
						}catch(EOFException e){
					break;
						}catch(ClassNotFoundException e) {
							e.printStackTrace();
				}
				}
				}catch(IOException e) {
					e.printStackTrace();;
				}
				try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Students.ser"))){
					for (student s:students) {
						out.writeObject(s);
					}
				}catch(IOException e3){
					e3.getMessage();
					}
					
					if(log==true&&inf[4].equals("admin")) {
						int option=0;
						do {
						printadminmenu();
						
						option=a.nextInt();
						switch(option) {
						case 1:
							adminobject.viewall(direct);
							break;
						case 2:
							adminobject.viewallfull(direct);
							break;
						case 3:
							adminobject.fulllist(direct);
							break;
						case 4:
							System.out.println("Enter course id");
							String id=a.next();
							boolean e=false;
							for (course c:direct.getDirectory()) {
								if (c.getCourse_id().equals(id)) {
									e=true;
									adminobject.viewstudents(direct,c);
								}
							}
							if (e==false) {
								
								System.out.println("course not found");
							}
							
							break;
						case 5:
							System.out.println("Enter student first and last name(click return between each)");
							String first=a.next();
							String last=a.next();
							boolean p=false;
							student f=null;
							for(student s: students) {
								if (s.getFirstname().equals(first)&&s.getLastname().equals(last)) {
									f=s;
									p=true;
								}
								}
							if (p==true) {
								adminobject.viewstudents(f);
							}
							if (p==false) {
									System.out.println("student does not exist");
								}
							
							break;
						case 6:
							adminobject.sort(direct);
							break;
						case 7:
							System.out.println("Enter course information(course name, course id, maximum number of students, current students, course instructor, section number, and location(press return key each time)");
							String n=a.next();
							String i=a.next();
							int l=a.nextInt();
							int c=a.nextInt();
							String ins=a.next();
							int num=a.nextInt();
							String loc=a.next();
							ArrayList<String>studs=new ArrayList<>();
							course bruh=new course(n,i,l,c,studs,ins,num,loc);
							adminobject.createcourse(bruh,direct);
							break;
						case 8:
							System.out.println("Enter course id");
							String re=a.next();
							boolean y=false;
							course c1=null;
							for (course co:direct.getDirectory()) {
								if (co.getCourse_id().equals(re)) {
									
									y=true;
									c1=co;
								}
							}if(y==true) {
								adminobject.deletecourse(direct,c1);
							}
							if (y==false) {
				
								System.out.println("course not found");
							}
							
							break;
						case 9:
							System.out.println("Enter course id");
							String real=a.next();
							adminobject.editcourse(real,direct);
							break;
							
						case 10:
							System.out.println("Enter course id");
							String poo=a.next();
							adminobject.displayinfo(poo,direct);
							break;
						case 11:
							System.out.println("Enter student username, password, first name, lastname (press return key each time)");
							String usernam=a.next();
							String passwor=a.next();
							String fir=a.next();
							String las=a.next();
							student newstudent=new student(usernam,passwor,fir,las);
							System.out.println("New list of students");
							students.add(newstudent);
							for(student s:students) {
							System.out.println(s.getFirstname()+" "+s.getLastname());
							}
							try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Students.ser"))){
								
							for (student s:students) {
								out.writeObject(s);
							}
						}catch(IOException e3){
							e3.getMessage();
							}
							break;
						case 12:
							System.out.println("goodbye");
							break;
						}
						}while(option!=12);	
						} else if(log==true&&inf[4].equals("student")) {
							
							for(student s:students) {
								if (s.getUsername().equals(user)&&s.getPassword().equals(pass)) {
									studentobject=s;
								}
							}
							int op=0;
							do {
							printstudentmenu();
							op=a.nextInt();
								switch(op) {
								case 1: 
									studentobject.viewall(direct);
									break;
								case 2:
									studentobject.viewnotfull(direct);
									break;
								case 3:
									System.out.println("Enter course id");
									String id=a.next();
									boolean exists=false;
									course u=null;
									for (course c:direct.getDirectory()) {
										if (c.getCourse_id().equals(id)) {
											exists=true;
											u=c;
										}
									}if (exists==true) {
										studentobject.register(u);
										try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Students.ser"))){
											
											for (student s:students) {
												out.writeObject(s);
											}
										}catch(IOException e3){
											e3.getMessage();
											}
									}
									if (exists==false) {
										System.out.println("course not found");
									}
									break;
								case 4:
									System.out.println("Enter course id");
									String ide=a.next();
									boolean exist=false;
									course d=null;
									for (course c:direct.getDirectory()) {
										if (c.getCourse_id().equals(ide)) {
											d=c;
											exist=true;
										}
									}
									if (exist==true) {
										studentobject.withdraw(d);
										studentobject.getCourses().remove(d);
										try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Students.ser"))){
											
											for (student s:students) {
												out.writeObject(s);
											}
										}catch(IOException e3){
											e3.getMessage();
											}
									}
									if (exist==false) {
										
										System.out.println("course not found");
									}
									
									break;
								case 5:
									for(course j:studentobject.getCourses()) {
										j.print();
									}
									if(studentobject.getCourses().size()<1) {
										System.out.println("this student has no courses");
									}
								
									break;
								case 6:
									System.out.println("goodbye");
									break;		
								}
							}while(op!=6); 
							}
					try(ObjectOutputStream fos=new ObjectOutputStream(new FileOutputStream("CourseDirectory.ser"))){
						fos.writeObject(direct);
					}catch(IOException e) {
						e.getStackTrace();
					}
					
					
		
			
				
		}
	
	
		a.close();
	}

		
	
		


	
		
	


	public static void printadminmenu() {
		System.out.println("What would you like to do?");
		System.out.println("1.View all courses");
		System.out.println("2.View all full courses");
		System.out.println("3.Write to a file all full courses ");
		System.out.println("4.View names of students registered to a course");
		System.out.println("5.View the list of courses a given student is taking");
		System.out.println("6.Sort courses");
		System.out.println("7.Create a new course");
		System.out.println("8.Delete a course");
		System.out.println("9.Edit a course");
		System.out.println("10.Display information for a given course");
		System.out.println("11.Register a student");
		System.out.println("12.Exit");
	}
	public static void printstudentmenu() {
		System.out.println("What would you like to do?");
		System.out.println("1.View all courses");
		System.out.println("2.View all courses that are not full");
		System.out.println("3.Register for a course");
		System.out.println("4.Withdraw from a course");
		System.out.println("5.View all Courses you are taking");
		System.out.println("6.Exit");
		
	}
}
