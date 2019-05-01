package online_advising;
import java.rmi.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public interface Message_Interface extends Remote{

	public void studentRequest(String name, String course, String time) throws RemoteException;
	
	public Queue<String> advisorRequest() throws RemoteException;
	
	public void advisorResponse( String course, String response) throws RemoteException ;
	
	public ConcurrentHashMap<String, String>  getAdvisorResponse() throws RemoteException;


}
