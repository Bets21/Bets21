package gui;

import java.awt.Color;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.User;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher { 
	
	private static BLFacade appFacadeInterface;
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		MainOutGUI ma= new MainOutGUI();
		ma.setBussinessLogic(appFacadeInterface);
		ma.setVisible(true);
		
		try {
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
				//In this option the DataAccess is created by FacadeImplementationWS
				//appFacadeInterface=new BLFacadeImplementation();

				//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				appFacadeInterface=new BLFacadeImplementation(da);
				
			}
			
			else { //If remote
				
				 String serviceName= "http://localhost:"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 System.out.println(serviceName);
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		        System.out.println("vjvboiqbieq");
		         appFacadeInterface = service.getPort(BLFacade.class);
		        
				  
				  }
			
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();*/
				
			ma.setBussinessLogic(appFacadeInterface);
	
		}catch (Exception e) {
			
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();

	}

}
