package practice;
import java.io.Serializable;
import java.util.ArrayList;
public class course implements Serializable {
	private static final long serialVersionUID = 1L;
	public String course_name;
	public String course_id;
	public int maximum_students;
	public int current_students;
	public ArrayList<String>list_of_names;
	public String course_instructor;
	public int section_number;
	public String location;
	course(String name, String id, int max, int current,ArrayList<String> list, String teach, int num, String loc){
	this.course_name=name;
	this.course_id=id;
	this.maximum_students=max;
	this.current_students=current;
	this.list_of_names=list;
	this.course_instructor=teach;
	this.section_number=num;
	this.location=loc;
	}
	
	public int getCurrent_students() {
		return current_students;
	}

	public void setCurrent_students(int current_students) {
		this.current_students = current_students;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getMaximum_students() {
		return maximum_students;
	}

	public void setMaximum_students(int maximum_students) {
		this.maximum_students = maximum_students;
	}

	public ArrayList<String> getList_of_names() {
		return list_of_names;
	}

	public void setList_of_names(ArrayList<String>list_of_names) {
		this.list_of_names = list_of_names;
	}
	

	public String getCourse_instructor() {
		return course_instructor;
	}

	public void setCourse_instructor(String course_instructor) {
		this.course_instructor = course_instructor;
	}

	public int getSection_number() {
		return section_number;
	}

	public void setSection_number(int section_number) {
		this.section_number = section_number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public void printList(){
	for (String entry:this.getList_of_names()) {
		System.out.println(entry);
	}
	}

	public void print() {
		System.out.println("Course Name:"+"\t"+this.getCourse_name());
		System.out.println("Course  ID:"+"\t"+this.getCourse_id());
		System.out.println("Maximum Students:"+"\t"+"\t"+this.getMaximum_students());
		System.out.println("Current number of students:"+"\t"+this.getCurrent_students());
		System.out.println("List of names"+"\t");
		printList();
		System.out.println("Course instructor"+"\t"+this.getCourse_instructor());
		System.out.println("Section number:"+"\t"+"\t"+this.getSection_number());
		System.out.println("Location"+"\t"+this.getLocation());
	}
	
}



