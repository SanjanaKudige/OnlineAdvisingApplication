package online_advising;
/*
 * imports all the classes required that enables your java program to use those classes and their methods to achieve some task.
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.util.concurrent.ConcurrentHashMap;
/*
 * defining classes and variable initialization
 */
public class Notification_process {
	
	public static void main(String[] args) {
		try
		{	
			Registry reg= LocateRegistry.getRegistry("127.0.0.1",1009);
			Message_Interface mi=(Message_Interface)reg.lookup("vkbind");
              int i=0,z=0;
			
			JFrame jframe = new JFrame();
			JPanel jp=new JPanel();
			JTextArea jta = new JTextArea(50,50);
			jframe.setSize(1000, 600);
			jframe.setLocationRelativeTo(null);
			jframe.setTitle("NOTIFICATION_PROCESS");
			
			JScrollPane scroll = new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jp.add(scroll);
			jta.setEditable(false);
			jp.add(jta);
			jframe.add(jp);
			jframe.setVisible(true);
			int count=0;

			
			while(z==0)//notification process checks for requests from message queue server//
			{
				long start = System.currentTimeMillis();
				long end = start + 7*1000; // 7 seconds 
				while (System.currentTimeMillis() < end)
				{
				}
				ConcurrentHashMap<String, String>  h=mi.getAdvisorResponse();
				/*
				 * Displaying output when student enters details 
				 */
				
				if(h.size()!=0)
				{
					//JOptionPane.showMessageDialog(null, "Student Details", "Notification Message",JOptionPane.INFORMATION_MESSAGE);
					
					System.out.println("------------NEW NOTIFICATION---------");
						for ( String key : h.keySet() ) {
							if(count==0)
							{
								jta.setText("COURSE NAME: '"+key+"' ADVISOR DECISION: '"+h.get(key)+"' \n");
								
								System.out.println("COURSE NAME: 	  "+key);
								System.out.println("ADVISOR DECISION: "+h.get(key));
								count++;
							}
							else
							{
								jta.append("COURSE NAME: '"+key+"' ADVISOR DECISION: '"+h.get(key)+"' \n");
							}
						}
						i=0;
				}	
				else
				{
					//if there are no responses the control goes in to this loop 
					if(count==0)
					{
							count++;
							jta.setText("WAITING FOR THE RESPONSE \n");
							i++;
							System.out.println("WAITING FOR THE RESPONSE ");
					}
					else
					{
						jta.append("No requests found \n");
					}
					

							//JOptionPane.showMessageDialog(null, "COURSE NAME: 	  "+key+ "   Advisor Decision: 	  "+h.get(key)+"","Notification Message",JOptionPane.INFORMATION_MESSAGE);
							//System.out.println("COURSE NAME: 	  "+key);
							//System.out.println("ADVISOR DECISION: "+h.get(key));
						
				//else {//Message displayed when no requests from queuing server//
					//JOptionPane.showMessageDialog(null, "No requests from Queuing server","Notification Message",JOptionPane.INFORMATION_MESSAGE);
							//System.out.println("No requests");
				}		
			}
		}
		catch(Exception e)
		{
			
		}
}

}
