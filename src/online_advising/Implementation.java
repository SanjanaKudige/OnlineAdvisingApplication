package online_advising;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("serial")

public class Implementation extends UnicastRemoteObject implements Message_Interface{
	Queue<String> q = new LinkedList<String>();
	ConcurrentHashMap<String,String> hm=new ConcurrentHashMap<String,String>();  
	
	protected Implementation() throws RemoteException {
		super();
	}
	@Override
	public void studentRequest(String name, String course, String time) throws RemoteException 
	{
		//WE SAVE THE COURSE IN QUEUE
		q.add(course);
	}

	public Queue<String> advisorRequest() throws RemoteException 
	{
		return q;
		
	}

	@Override
	public void advisorResponse(String course, String response) throws RemoteException {
		hm.put(course, response);
		q.remove(course);
		
	}

	@Override
	public ConcurrentHashMap<String, String> getAdvisorResponse() throws RemoteException {
		
		ConcurrentHashMap<String, String> h2=new ConcurrentHashMap<String, String>();
		h2.putAll(hm);
		Iterator<String> it1 = hm.keySet().iterator();
		while(it1.hasNext()){
			String key = it1.next();
			hm.remove(key);
		}
		return h2;
		
	}

	

}
