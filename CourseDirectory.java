package practice;
import java.util.ArrayList;
import java.io.*;
public class CourseDirectory implements Serializable {
	private static final long serialVersionUID = 1L;
	protected ArrayList<course>Directory;
	
	CourseDirectory(){
		Directory=new ArrayList<course>();
		String file="/Users/marieqi/eclipse-workspace/practice/src/practice/MyUniversityCourses.csv";
		String newfile="CourseDirectory.ser";
		try(BufferedReader reader=new BufferedReader(new FileReader(file))){
			String line;
			boolean firstline=true;
			while ((line=reader.readLine())!=null){
				if(firstline==true) {
					firstline=false;
					continue;
				}
				ArrayList<String>students=new ArrayList<>();
				String[]data=line.split(",");
				if (data[4]!=null) {
					String[]studentarray=data[4].trim().split(",");
					for(String s:studentarray) {
						students.add(s);
						
					}
					
				}else {
					students=null;
				}
				course courseobj=new course(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),students,data[5],Integer.parseInt(data[6]),data[7]);
				Directory.add(courseobj);
				
				
				
				
				
		}
			
		}catch(IOException e){
			e.printStackTrace();
			}
		try(ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(newfile))){
			writer.writeObject(this);
		}catch(IOException e) {
		e.printStackTrace();
	}
		
		}
	

public ArrayList<course> getDirectory() {
		return Directory;
	}


	public void setDirectory(ArrayList<course> directory) {
		Directory = directory;
	}

}