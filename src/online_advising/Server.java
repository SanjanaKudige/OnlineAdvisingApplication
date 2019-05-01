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
/*
 * defining classes and variable initialization
 */
public class Server {
	
	public static void main(String args[]){
		try{
			/*The following code in the server obtains a stub for a registry 
			 * on the local host and default registry port and then uses the registry stub to 
			 * bind the name "vkbind" to the remote object's stub in that registry:
			*/
			Registry reg= LocateRegistry.createRegistry(1009);
			Implementation e=new Implementation();
			reg.rebind("vkbind", e);
			System.out.println("Server is started and ready!!!");//Indicates that the server is ready to accept requests//
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
