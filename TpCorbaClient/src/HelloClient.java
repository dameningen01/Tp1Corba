

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//Java  Client.java

 public class HelloClient
 {
 public static void main(String args[])
 {
 
 java.util.Properties props = System.getProperties();

 int status = 0;
 org.omg.CORBA.ORB orb = null;

 try
 {
 orb = org.omg.CORBA.ORB.init(args, props);
 status = run(orb);
 }
 catch(Exception ex)
 {
 ex.printStackTrace();
 status = 1;
 }

 if(orb != null)
 {
 try
 {
 orb.destroy();
 }
 catch(Exception ex)
 {
 ex.printStackTrace();
 status = 1;
 }
 }

 System.exit(status);
 }

  static int run(org.omg.CORBA.ORB orb) throws NamingException
 {
 org.omg.CORBA.Object obj = null;
 
 try {
	 Context ctx = new InitialContext();
	 Object ref = ctx.lookup("HELLO");
	 System.out.println(ref);
	 
	 Hello stub =  HelloHelper.narrow((org.omg.CORBA.Object)ref);
	 
	 
	 System.out.println("Connected to CORBA SERVER");
	 System.out.println(stub.sayHello());
	 
	 //String refFile = "Hello.ref";
	 //java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(refFile));
	 //String ref = in.readLine();
	 //obj = orb.string_to_object(ref); 
 }catch(NamingException ex){
	 ex.printStackTrace();
 }
 //Hello hello = HelloHelper.narrow(obj);
 //System.out.println(hello.sayHello());
 return 0;


}
}