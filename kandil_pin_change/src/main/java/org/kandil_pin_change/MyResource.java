package org.kandil_pin_change;

import com.sap.conn.jco.JCoException;

import FactorySAP.SapConnector;
import entity.ListOfIronRolls;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
    
    @POST
    @Path("/ChangePin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean move_into_new_place(ListOfIronRolls ironroll) throws Exception {
    	boolean fBoolean = false;

    	SapConnector SapConnetor_instance=SapConnector.getInstance();
    	try {
			SapConnetor_instance.Connect();
			System.out.println(ironroll.distenation+"distenation");
			  fBoolean=SapConnetor_instance.changePin(ironroll,ironroll.distenation);
			
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    	if(fBoolean==false)
    		System.out.println("moveToNewDistenation false");
    	return fBoolean;
    }
}
