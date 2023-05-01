package FactorySAP;


import entity.IronRoll;
import entity.ListOfIronRolls;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

 
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;

 

 
  
 
public class SapConnector {
	
	 // static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";
	    static String DESTINATION_NAME2 = "ABAP_AS_WITH_POOL";
	    String Balance="";String Quantity="";Float Quantity_temp=0.0f;
	    
	    public String getQuantity() {
	    	
	    	
			return Quantity;
		}

	 

		public String getBalance() {
			return Balance;
		}

		public void setBalance(String balance) {
			Balance="";
			Balance = balance;
		}


		static    JCoDestination destination;
	   static
	    {
	        
	        Properties connectProperties = new Properties();
	        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "172.16.0.86");
	        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "0");
	        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "300");
	        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "mhalawany");
	        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "allahrahim");
	        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "en");
	        createDataFile("ABAP_AS_WITH_POOL", "jcoDestination", connectProperties);
		    
	     //   createDestinationDataFile(DESTINATION_NAME1, connectProperties);
	        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "1");
	        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,    "20");
	        //createDestinationDataFile(DESTINATION_NAME2, connectProperties);
	        
	        
	      
	    }
	   
	   static void createDataFile(String name, String suffix, Properties properties)

	   {

	   File cfg = new File(name+"."+suffix);

	   if(!cfg.exists())

	   {

	   try

	   {

	   FileOutputStream fos = new FileOutputStream(cfg, false);

	   properties.store(fos, "for tests only !");

	   fos.close();

	   }

	   catch (Exception e)

	   {

	   throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);

	   }

	   }

	   }

	   static SapConnector instance = null;
	   
	   static public SapConnector getInstance()
	    {

	        if (instance == null)
	            instance = new SapConnector();

	        return instance;
	    }
	   
	   
	   public  void Connect() throws JCoException
	    {
		   if(destination==null) {
	         destination = JCoDestinationManager.getDestination(DESTINATION_NAME2);
	         
		   }
	    }
	   
 
	     
	         
	        public static String today() {
	        	
	        	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	          	  
	               GregorianCalendar call = (GregorianCalendar) GregorianCalendar.getInstance();


	              String  currentDate =dateFormat.format( call.getTime()  );
	              
	             return currentDate;
	        }
	        
	        public static String tendaysback() {
	        	
	        	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	          	  
	        	  
	          	  
	               GregorianCalendar call = (GregorianCalendar) GregorianCalendar.getInstance();
	               
	               
	               call.roll(GregorianCalendar.MONTH,  -1);
	              
	               
	               int month = call.get(GregorianCalendar.MONTH) + 1; // beware of month indexing from zero
	               if (month==12)
	            	   call.roll(GregorianCalendar.YEAR,  -1);
	               
	               
	               int year  = call.get(GregorianCalendar.YEAR);
	               
	               System.out.println(year+"	 	final year" );
	               
	               String  currentDate =dateFormat.format( call.getTime()  );
	            
	             return currentDate;
	        }
 
	        
	        
	        public static String tendays_back() {
	        	
	        	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	          	  
	               GregorianCalendar call = (GregorianCalendar) GregorianCalendar.getInstance();
	               
	               /*
	               call.set(GregorianCalendar.YEAR, 2020);
	               call.set(GregorianCalendar.MONTH, 1);
	               call.set(GregorianCalendar.DATE, 5);
	               */
	            //   call.set(2020,11,1);
	               int Day=call.get(GregorianCalendar.DAY_OF_MONTH);
	               call.roll(GregorianCalendar.DAY_OF_MONTH,  -10);
	              
	               if (Day<=10)
	            	   call.roll(GregorianCalendar.MONTH,  -1);
	               
	               int month = call.get(GregorianCalendar.MONTH) + 1; // beware of month indexing from zero
	               
	               if (month==1) {
	            	   call.roll(GregorianCalendar.YEAR,  -1);
	               call.set(GregorianCalendar.MONTH, 11);
	               }
	               int year  = call.get(GregorianCalendar.YEAR);
	               
	               System.out.println(year+"	 	final year" );
	               
	               String  currentDate =dateFormat.format( call.getTime()  );
	            System.out.print(currentDate+"Current date is ");
	             return currentDate;
	        }
	        
	  public boolean changePin(ListOfIronRolls ironRolls,String target) {
		  
			 JCoDestination Destination ;
			 JCoContext.begin(destination);
			 //List<String>  target_distenation_data=Arrays.asList(target.split("-"));
			 String target_distenation_data = target.substring(0, 4);
			 String target_distenation_data_pin = target.substring(4, target.length());
			 
			  System.out.println(target);
			  /*target_distenation_data.forEach((element)->{
				  System.out.println(element);
			  });*/
		//must be done on mobile if(target_distenation_data.size()==2)
			 
			  
			  
			 
		  for (IronRoll ironRoll:ironRolls.ironRolls) {
			  
			  System.out.println(ironRoll.BATCH+"change bATCH Pin");
		 
 	        try
	        {
 	       
	    	      	 
	    	      	ZABAPI_BIN(ironRoll.BATCH,ironRoll.MATERIAL,target_distenation_data_pin);
	          	 
	 	    	      	 
	 	    	      
	        }catch(Exception e)
		     {
	        	
	        	System.out.println("exception fROM hASSAN	");
		        System.out.println("exception from SAP	"+e.toString());
		        return false;
		     }

	  	}
		  
		  System.out.println("true");
		  return true;
	  }
	        

		  
 	         public   static void main(String arg[]) throws ParseException, JCoException {
 	         
 	        	 
 	        	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
 		       	String yyyyMMdd = dateFormat.format(new Date());
 		       	System.out.println(yyyyMMdd);
	        SapConnector xx=new SapConnector();
	        xx.Connect();

	        try
	        {
	        	 System.out.println("before ");
	        ZABAPI_BIN("376326ABRA","FBNS","55555");
	        System.out.println("after ");
	        }catch(Exception e)
		     {
		        System.out.println("exception from SAP	"+e.toString());
		     }
	        
	        /*try
	        {
	        	  
	          
	        //	
	         
	        	 JCoDestination Destination ;
	 			 JCoContext.begin(destination);
	 			JCoFunction function=destination.getRepository().getFunction("BAPI_GOODSMVT_CREATE");
	 			
	       	 JCoStructure importStructure2=function.getImportParameterList().getStructure("GOODSMVT_HEADER");
        	 // TO DATE	20180524 valid date format for SAP
        	 importStructure2.setValue("PSTNG_DATE","20230326"); 
        	 importStructure2.setValue("DOC_DATE", "20230326");
        	 importStructure2.setValue("PR_UNAME", "AFATMAHMOUD");
        	 
        	 
        	 JCoStructure importStructure3=function.getImportParameterList().getStructure("GOODSMVT_CODE");
        	 importStructure3.setValue("GM_CODE","04"); 
        	 
         
        	 JCoTable importStructure4 = function.getTableParameterList().getTable("GOODSMVT_ITEM");
        	
   
        	 
        	 
        importStructure4.appendRow();
        	 importStructure4.setValue("MATERIAL","FBNS"); 
        	 importStructure4.setValue("PLANT","1100"); 
        	 importStructure4.setValue("STGE_LOC", "CR2A");
        	 importStructure4.setValue("BATCH", "376326ABRA");// scan
        	 importStructure4.setValue("MOVE_TYPE","311"); // fixed
        	 importStructure4.setValue("SPEC_STOCK","E");  // fixed
        	 importStructure4.setValue("SALES_ORD", "0035004549"); //SD_DOC
        	 importStructure4.setValue("S_ORD_ITEM", "000010"); // ITM_NUMBER
        	 importStructure4.setValue("ENTRY_QNT","8.770"); // QTY
        	 importStructure4.setValue("ENTRY_UOM","TO");  // BASE_UOM
        	
        	 
        	 
        	 importStructure4.setValue("MOVE_MAT", "FBNS");
        	 importStructure4.setValue("MOVE_PLANT", "1100");
        	 			importStructure4.setValue("MOVE_STLOC","CG2B"); // Second scan
        	 importStructure4.setValue("MOVE_BATCH","376326ABRA"); 
        	 importStructure4.setValue("VAL_SALES_ORD", "0035004549");
        	 importStructure4.setValue("VAL_S_ORD_ITEM", "000010");
        	 
         	 
        	
 	    	 if(function == null)
 		           throw new RuntimeException("BAPI_GOODSMVT_CREATE FAILD");
 		 
 	    	   function.execute(destination);
 	     	     JCoStructure ldata = function.getExportParameterList().getStructure("GOODSMVT_HEADRET");
 	   		 
 			 
 			    System.out.println( ldata.getValue("MAT_DOC").toString()+"******************MAT_DOC********************");
 			   	
 			    
 			   function.getTableParameterList().getTable("RETURN").firstRow();

 			  importStructure4.firstRow();
 			  
 			 String result="";
 			 
 			 
 			   JCoFunction function1=destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
               	 
 				function1.getImportParameterList().setValue("WAIT", "X");
 				
	    	       
	    	      	 
	    	       	 function1.execute(destination);
	    	       	 System.out.println( function1.getExportParameterList().getStructure("RETURN"));	
 
	    	 
	    	      // Check for errors
	    	            JCoTable returnTable = function1.getTableParameterList().getTable("RETURN");
	    	            for (int i = 0; i < returnTable.getNumRows(); i++) {
	    	                returnTable.setRow(i);
	    	                if (returnTable.getString("TYPE").equals("E")) {
	    	                    // Handle error
	    	                    System.out.println("Error: " + returnTable.getString("MESSAGE"));
	    	                }
	    	            }      	 
	    	       	 
	    	       	 
	    	       	 
 			  if(!function1.getExportParameterList().getStructure("RETURN").getString("TYPE").equalsIgnoreCase("E")){
 			  
 			  }
 	
 		//	 JCoTable materialList = function.getTableParameterList().getTable("RETURN");
 			 
 			    //System.out.println(function1.getExportParameterList().getStructure("RETURN").getString("TYPE")+"HASSAN");
 			   //System.out.println( function1.getExportParameterList().getStructure("RETURN").getString("MESSAGE"));
 			 //  System.out.println( function.getTableParameterList().getTable("RETURN"));
 		//	  System.out.println(function.getExportParameterList().getString("MATERIALDOCUMENT"));
 			   
 //			  JCoContext.end(destination);
 	 	    	      	 
 	 	    	      	 
 	 	    	      
	        }catch(Exception e)
		     {
		        System.out.println("exception from SAP	"+e.toString());
		     }
	        */
	      
	        }
  	    	  
 	     static void ZABAPI_BIN(String batch,String matrial,String bin) {
 	    	 
 	    	  try
 		        {
 		        	    
 		        	JCoDestination Destination ;
 		 			//JCoContext.begin(destination);
 		 			JCoFunction function=destination.getRepository().getFunction("ZBAPI_BIN");
 		 			 if(function == null)
 		  		           throw new RuntimeException("STFC_CONNECTION not found in SAP.");
 		  		 
 		           
 		 		       /* function.getImportParameterList().setValue("BATCH", "385728");
 		 		        function.getImportParameterList().setValue("MATERIAL", "HPCSS");*/
 		 		        
 		  	    	function.getImportParameterList().setValue("BATCH",batch);
 	 		        function.getImportParameterList().setValue("MATERIAL", matrial);
 	 		        function.getImportParameterList().setValue("BIN", bin);
 		            function.execute(destination);
 		        	//JCoTable table_ITEMS = function.getTableParameterList().getTable("ZABAPI_BIN"); 	
 		        	JCoTable table_ITEMS = function.getTableParameterList().getTable("GET_DATA");
 		        	
 		        	System.out.println(" we are in	   ");
 		 			String BIN="",MATERIAL_="",ATWRT="";
 		        	do {
 		        		 
 		        		MATERIAL_=table_ITEMS.getValue("MATERIAL").toString();
 		        		BIN=table_ITEMS.getValue("BATCH").toString();
 		        		ATWRT=table_ITEMS.getValue("ATWRT").toString();  
 		        		System.out.print(	"MATERIAL_ "+MATERIAL_+"	   ");
		        		 System.out.print(	"BIN "+BIN+"	   ");
		        		 System.out.print(	"ATWRT "+ATWRT+"	   ");
		        		 
 		        		
 		        	}while(table_ITEMS.nextRow()) ;
 		 			
 		 			
 		        }catch(Exception e) {
 		        	System.out.println("exception from SAP	"+e.toString());
 			        	
 		        }
 	     }
	     
	     
	     
	     
	     
 
	}
 
