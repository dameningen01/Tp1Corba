
 import java.io.PrintWriter;

import org.omg.CORBA.ORB; 
 import org.omg.CORBA.SystemException; 

public class HelloServer
{
  public static void main(String args[])
   {
      java.util.Properties props = System.getProperties();
      
      int status = 0 ;
      org.omg.CORBA.ORB orb = null ;

      try
       {
         orb = org.omg.CORBA.ORB.init ( args, props) ;
         status = run (orb) ;
       }
      catch (Exception ex)
       {
         ex.printStackTrace ();
         status = 1;
       } 

      if (orb != null)
       {
         try
         {
           orb.destroy() ;
         }
         catch (Exception ex)
          {
            ex.printStackTrace ();
            status = 1;
          } 
   }
  System.exit(status) ;
}

static int run(org.omg.CORBA.ORB orb)
 throws org.omg.CORBA.UserException
 {
 org.omg.PortableServer.POA rootPOA =
     org.omg.PortableServer.POAHelper.narrow(
        orb.resolve_initial_references("RootPOA"));

 org.omg.PortableServer.POAManager manager =
 rootPOA.the_POAManager();
 HelloImp hi = new HelloImp();
 Hello hello = hi._this(orb);
 try {
	 String ref = orb.object_to_string(hello);
	 String refFile = "Hello.ref";
	 java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileOutputStream(refFile));
	 out.println(ref);
	 out.close();
 }
 catch(java.io.IOException ex)
 {
 ex.printStackTrace();
 return 1;
 }

 manager.activate();
 orb.run();

 return 0;
 }
}
 