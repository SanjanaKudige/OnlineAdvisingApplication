package online_advising;
/*
 * LocateRegistry is used to obtain a reference to a bootstrap remote object registry on a particular host (including the local host), or to create a remote object registry that accepts calls on a specific port.
 Registry is a remote interface to a simple remote object registry that provides methods for storing and retrieving remote object references bound with arbitrary string names.
 Hence,importing the classes for the same
 */
/*
 * imports all the classes required that enables your java program to use those classes and their methods to achieve some task.
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;//The java.text.SimpleDateFormat class provides methods to format and parse date and time in java.//
import java.util.*;//imports all important classes like Arraylist,Date,list and scanner//
/*
 * defining classes and variable initialization
 */
public class Student_process {
	/*
	 * @SuppressWarnings annotation is one of the three built-in annotations available in JDK
	 */
	@SuppressWarnings("rawtypes")
	static List al=new ArrayList();

		@SuppressWarnings("resource")
		public static void main(String[] args) {
			
			try{
				Registry reg= LocateRegistry.getRegistry("127.0.0.1",1009);
				Message_Interface mi=(Message_Interface)reg.lookup("vkbind");
				int i=0;
				/*
				 * input name of the student from the console
				 */
				System.out.println("Enter your name: ");
				Scanner scanner = new Scanner(System.in);
				String name = scanner.nextLine();
				while(i==0)
				{
					/*
					 * input name of the course to be waived from the console
					 */
					System.out.println("Enter the course name to be waived: ");
					scanner = new Scanner(System.in);
					String course=scanner.nextLine();
					mi.studentRequest(name, course, new SimpleDateFormat("HH.mm.ss").format(new Date()));
					
					/*
					 * input names of other courses to be waived
					 */
					System.out.println("Do you want to enter other courses(y/n): ");
					
									while(true) {
										scanner = new Scanner(System.in);
										String choice=scanner.nextLine();
					if(choice.equals("n")|| choice.equals("N") )//the loop ends when the user enters "n"
					{	System.exit(0);
					break;
						
					}
					else if(choice.equals("y")||choice.equals("Y"))//the loop continues for other subjects when the user enters "y"
					{
					//System.out.println("invalid choice.Please try again later");
					//System.exit(0);
						break;
					}
					else//Prints invalid choice message and asks the user whether to continue the online advising process 
					{
						System.out.println("invalid choice.Press y to continue and n to exit the online advising process");
						//System.exit(0);
						
					}
					
									}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}	
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static List getNotification(String name,String course,String response)
		{
			al.add(name);
			al.add(course);
			al.add(response);
			return al;
		}

}
