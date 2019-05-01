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
import javax.swing.*;
import java.util.*;//imports all important classes like Linkedlist and queue// 
//import java.util.Queue;
/*
 * defining classes and variable initialization
 */
public class Student_advisor {
	public static void main(String[] args) {
		try{
			Registry reg= LocateRegistry.getRegistry("127.0.0.1",1009);
			Message_Interface im=(Message_Interface)reg.lookup("vkbind");
/*
 * defining new linked list			
 */
			Queue<String> q=new LinkedList<String>();
			int i=0;
			int count=0;
			JFrame jframe = new JFrame();
			JPanel jp=new JPanel();
			JTextArea jta = new JTextArea(60,50);
			jframe.setSize(600, 600);
			jframe.setLocationRelativeTo(null);
			jframe.setTitle("ADVISOR");			
			JScrollPane scroll = new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    jframe.add(scroll);
			jta.setEditable(false);
			jp.add(jta);
			jframe.add(jp);
			jframe.setVisible(true);

			//System.out.println("---------INSIDE ADVISOR-------");
			while(i==0){
				q=im.advisorRequest();
				if(q.isEmpty())//if there's no request from student,print message//
				{
					//System.out.print("NO DECISIONS TO BE MADE..");
					long start = System.currentTimeMillis();
					long end = start + 3*1000;// 3 * 1000 ms/sec
					//JOptionPane.showMessageDialog(null, "Waiting for student's request","Notification Message",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Waiting for student's request");
					while (System.currentTimeMillis() < end)
					{}
					if(count==1)
					{
						jta.setText("Waiting for response \n");
					}
					else
					{
						jta.append("Waiting for response \n");
					}
				

				}
				else
				{
					//i=1;
					for(String str:q)
					{
						//String student=(String) l.get(0);
						//String course=(String) l.get(1);
					//	String time=(String) l.get(2);
				/*
				 * random operation used here to make a decision randomly		
				 */
						Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
						long time = cal.getTimeInMillis();
						
						int random = (int )(Math.random() * 50000 + 1);
						time = time + random;
						if(time % 3==0)// decision made on this randomability which decides whether the variable is divisible by 3//
						{
							count++;
							if(count==1)
							{
								jta.setText("advisors decision for course '"+str+"' is 'NO' \n");
								count++;
							}
							else
							{
								jta.append("advisors decision for course '"+str+"' is 'NO' \n");
							}

							//JOptionPane.showMessageDialog(null, "Advisor decision for course "+str+" is 'NO'", "Notification Message",JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Advisor decision for course "+str+" is 'NO'");//for each decision made the response is sent to the queue
							im.advisorResponse(str, "no");
						}
						else
						{
							count++;
							if(count==1)
							{
								jta.setText("advisors decision for course '"+str+"' is 'YES' \n");
								count++;
							}
							else
							{
								jta.append("advisors decision for course '"+str+"' is 'YES' \n");
							}

							//JOptionPane.showMessageDialog(null, "Advisor decision for course "+str+" is 'YES'", "Notification Message",JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Advisor decision for course "+str+" is 'YES'");	
							im.advisorResponse(str, "yes");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}


}
