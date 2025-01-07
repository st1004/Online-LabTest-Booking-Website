package com.LabTest.LabTest.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.thymeleaf.expression.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LabTest.LabTest.entity.EmailSender;
import com.LabTest.LabTest.entity.ListItem;
//import java.util.Arrays;
import com.LabTest.LabTest.entity.User;
import com.LabTest.LabTest.entity.UserSelection;
import com.LabTest.LabTest.entity.ViewResult1;
import com.LabTest.LabTest.entity.ViewResults;
import com.LabTest.LabTest.service.Test1Service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Controller
public class LabController {
	String labName="";
	
	@Value("${spring.datasource.url}")
    private String ds;
	@Value("${spring.datasource.username}")
    private String un;
	@Value("${spring.datasource.password}")
    private String pw;
	String g_username="";
	String g_labname="";
	String g_email="";
	String g_message="";
	List<String> g_selectedTest;
	String g_selectedHour="";
	int g_totalPrice=0;
	 UserSelection userData = new UserSelection();
	
	@Autowired
	private Test1Service service;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/user_register")
	public String userRegister() {
		return "userRegister";
	}
	@GetMapping("/about_us")
	public String aboutUs() {
		return "aboutUs";
	}
	@GetMapping("/privacy_policy")
	public String getPrivacyPolicy() {
		return "privacyPolicy";
	}
	@GetMapping("/faq")
	public String getFaq() {
		return "faq";
	}
	@GetMapping("/contact_us")
	public String contactUs() {
		return "contactUs";
	}
	@GetMapping("/sign_in")
	public String signIn() {
		return "signIn";
	}
	
	@PostMapping("/save")
	public String addUser(@ModelAttribute User u) {
		try {
		service.save(u);
		return "/home";
		}
		catch(Exception e) {
			return "/userRegisterError";
		}
	}
	
	  @PostMapping("/signin")
	    public String handleSubmitForm(@RequestParam("Username1") String name, @RequestParam("psw1") String pname) {
	        // Process the submitted form data
		  g_username=name;
	        System.out.println("Received name: " + name);
	        System.out.println("Received name: " + pname);
	        System.out.println(ds);
	        String q1="select count(1) c from user where username=\""+name+"\" and password=\""+pname+"\""; 
	        
	        try {
	            // 1. Get a connection to the database
	            Connection connection = DriverManager.getConnection(ds, un, pw);

	            // 2. Create a statement object to execute the query
	            Statement statement = connection.createStatement();

	            // 3. Execute the SQL query and get the result set
	            ResultSet resultSet = statement.executeQuery(q1);

	            // 4. Iterate through the result set and print the data
	            
				  int result=0;
				  while (resultSet.next()) { 
					  result = resultSet.getInt("c"); 
				  }
	            // 5. Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
	            if(result==1) {
	            	return "/staticInterface";
	            }
	            else {
	            	return "signInError";
	            }

	        } catch (SQLException e) {
	        	return "/signInError";
	        }
	    }
	  @PostMapping("/scheduleForm")
	    public String submitForm(String action,Model model) {
	        if ("schedule".equals(action)) {
	        	
	        	String q1="select name from test1.laboratory"; 
	        	List<String> buttonOptions = new ArrayList<>();
		        try {

		            Connection connection = DriverManager.getConnection(ds, un, pw);
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery(q1);
		            String labName;
					  while (resultSet.next()) { 
						 // labName = resultSet.getString("name"); 
						  buttonOptions.add(resultSet.getString("name"));
					  }
		            // 5. Close the resources
		            resultSet.close();
		            statement.close();
		            connection.close();
		            
		        } catch (SQLException e) {
		        	return "/signInError";
		        }
		        model.addAttribute("buttonOptions",buttonOptions);
                return "/scheduleForm1";
		    }
	        	
	         else if ("view_scheduled".equals(action)) {
                System.out.println("Action 2 performed!");
                    // Create a list to store data objects
                    List<ViewResults> dataList = new ArrayList<>();
                    
                    String q1="select labname,testname,date_format(scheduleDate,'%Y-%m-%d %H:%i')as sDate from test1.testSchedule where scheduleDate>=sysdate() and username='"+g_username+"'"; 
    		        try {

    		            Connection connection = DriverManager.getConnection(ds, un, pw);
    		            Statement statement = connection.createStatement();
    		            ResultSet resultSet = statement.executeQuery(q1);
    		           // String labName;
    					  while (resultSet.next()) { 
    						  dataList.add(new ViewResults(resultSet.getString("labname"),resultSet.getString("testname"),resultSet.getString("sDate")));
    					  }
    		            // 5. Close the resources
    		            resultSet.close();
    		            statement.close();
    		            connection.close();
    		            
    		        } catch (SQLException e) {
    		        	return "/staticInterfaceE2";
    		        }

                    model.addAttribute("dataList", dataList);

                    return "viewSchedule1"; // Return the name of the HTML template
                
                
             
	        } 
	        else if ("view_history".equals(action)) {
	            System.out.println("Action 3 performed!");
	            List<ViewResult1> dataList1 = new ArrayList<>();
	            String q1="select labname,testname,date_format(scheduleDate,'%Y-%m-%d %H:%i')as sDate,result from test1.testSchedule where username='"+g_username+"'"; 
	            try {

		            Connection connection = DriverManager.getConnection(ds, un, pw);
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery(q1);
		           // String labName;
					  while (resultSet.next()) { 
						  dataList1.add(new ViewResult1(resultSet.getString("labname"),resultSet.getString("testname"),resultSet.getString("sDate"),resultSet.getString("result")));
					  }
		            // 5. Close the resources
		            resultSet.close();
		            statement.close();
		            connection.close();
		            
		        } catch (SQLException e) {
		        	return "/staticInterfaceE3";
		        }

                model.addAttribute("dataList1", dataList1);

                return "viewResult";
 }
	        else {
	            // Handle unknown action (optional)
	            System.out.println("Unknown action!");
	        }
	 
	        // Redirect back to the custom form page after processing
	        return "/staticInterface";
	    }
	  
	  
	  @PostMapping("/handleLabSelection")
	  public String handleLabSelection(@RequestParam("buttonLabel")String buttonLabel, Model model) {
		  // Handle the button click based on the button label        
		  g_labname=buttonLabel;
		  System.out.println("Button Clicked: " + buttonLabel);
		  labName=buttonLabel;
		
		  List<String> final_slots=new ArrayList<>(); 
		  String q1="SELECT schedule_days,time_slots FROM test1.laboratory WHERE name = \"" + buttonLabel + "\"";
		  String slot="";
          int days=0;
          String[] strArray;
          String formattedDate="";
          int[] intArray;
		  try {
			  Connection connection = DriverManager.getConnection(ds, un, pw);
	          Statement statement = connection.createStatement();
	          ResultSet resultSet = statement.executeQuery(q1);
	          while (resultSet.next()) { 
					  slot=resultSet.getString("time_slots");
					  days=resultSet.getInt("schedule_days");
					  System.out.println(slot+"\t"+days);
				  }
	           strArray = slot.split(",");
	           

		  }
		  catch(Exception e) {
			  return "/home";
		  }
		  String formattedDate2="";
		  for(int i=1;i<=days;i++) {
			  LocalDate currentDate = LocalDate.now();

		        LocalDate newDate = currentDate.plusDays(i);

		        // Display the new date
		        System.out.println("Date after adding " + i + " days: " + newDate);

		        // Optionally, if you want to format the date
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        formattedDate = newDate.format(formatter);
		        System.out.println("Formatted date after adding " + i + " days: " + formattedDate);
		        
		        intArray = new int[strArray.length];
		        for (int j = 0; j < strArray.length; j++) {
		            intArray[j] = Integer.parseInt(strArray[j]);
		            formattedDate2=formattedDate+"  "+intArray[j]+":00";
		            final_slots.add(formattedDate2);
		        }
		        
		  }
		  
		  String q2 = "SELECT testname, price " +"FROM test1.labtest " + "WHERE labid IN (SELECT id FROM test1.laboratory WHERE name = \"" + buttonLabel + "\")";	
		  try {
		  Connection connection = DriverManager.getConnection(ds, un, pw);
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(q2);
          String name;
          int price;
          List<ListItem> listItems = new ArrayList<>();
          while (resultSet.next()) { 
				 // labName = resultSet.getString("name"); 
				  name=resultSet.getString("testname");
				  price=resultSet.getInt("price");
				  System.out.println(name+"\t"+price);
				  listItems.add(new ListItem(name,price));
			  }
          Map<String, Integer> mapItems = new HashMap<>();
          for (ListItem item : listItems) {
              mapItems.put(item.getColumn1(), item.getColumn2());
          }
        //  model.addAttribute("listItems", listItems);
          model.addAttribute("validHours", final_slots);
	      model.addAttribute("testNamesAndPrices", mapItems);
		  return "/appointment";   
		  }
		  catch(Exception e) {
			  return "";
		  }
	  
	  }	
	  
	    @PostMapping("/appointmentSelection")
	  public String processAppointmentForm(@RequestParam String selectedHour,@RequestParam(required = false) List<String> selectedTests, Model model) {
	    
	    	g_selectedHour=selectedHour; 
	    	g_selectedTest=selectedTests;
	    	String inclause="";
	    	for(int i=0;i<selectedTests.size();i++) {
	    		System.out.println(i+"\t"+selectedTests.get(i));
	    		if(i==selectedTests.size()-1) {
	    			inclause+="'"+selectedTests.get(i)+"'";
	    		}
	    	     else if(i>=0) {
	    			inclause+="'"+selectedTests.get(i)+"'"+",";
	    		}
	    	}
	    	
	    	
	    	String q1="select sum(price) tp from test1.labtest where labid=(select id from test1.laboratory where name="+"'"+g_labname+"'"+") and testname in ("+inclause+")";

	          
			  try {
				  Connection connection = DriverManager.getConnection(ds, un, pw);
		          Statement statement = connection.createStatement();
		          ResultSet resultSet = statement.executeQuery(q1);
		          while (resultSet.next()) { 
						  g_totalPrice=resultSet.getInt("tp");
					  }
			  }
			  catch(Exception e) {
				  return "/home";
			  }
			  System.out.println(g_totalPrice);
            System.out.println(inclause);
	    	System.out.println(g_username+" "+g_labname+" "+String.join(", ", g_selectedTest)+" "+g_selectedHour);
	       g_message="Hi "+g_username+"! This email is to confirm your appointment for the following tests: "+String.join(", ", g_selectedTest)+". The appointment is scheduled for "+g_selectedHour+" has been confirmed.";
	    	// UserSelection userData = new UserSelection();
	        userData.setUs_username(g_username);
	        userData.setUs_labname(g_labname);
	        userData.setUs_selectedHour(g_selectedHour);
	        userData.setUs_selectedTest(g_selectedTest);
	        userData.setUs_totalPrice(g_totalPrice);

	        model.addAttribute("myData", userData);
	        return "/confirmation"; // This corresponds to myPage.html


	    }

	    	
	   @PostMapping("/confirmAndPay")
	    public String processPayment(Model model) {
	 model.addAttribute("g_totalPrice", g_totalPrice);
       return "/payment";
	   }
	   
	   @PostMapping("/afterPayment")
	    public String afterPayment() {

		   System.out.println(userData.getUs_username()+userData.getUs_labname()+userData.getUs_selectedHour()+String.join(", ", userData.getUs_selectedTest())+userData.getUs_totalPrice());	   
	  
		   for(int i=0;i<userData.getUs_selectedTest().size();i++) {
			   
		    //STR_TO_DATE('2024-05-09 12:30:45', '%Y-%m-%d %H:%i:%s')
		   String q1="insert into test1.testSchedule values("+"'"+userData.getUs_username()+"','"+userData.getUs_labname()+"','"+userData.getUs_selectedTest().get(i)+"',"+"STR_TO_DATE('"+userData.getUs_selectedHour()+"', '%d/%m/%Y %H:%i')"+",'NO'"+",'Results_Awaited')";
		    System.out.println(q1);

			  try {
				  Connection connection = DriverManager.getConnection(ds, un, pw);
		         // Statement statement = connection.createStatement();
				  PreparedStatement statement=connection.prepareStatement(q1);
		          int no_rows = statement.executeUpdate(q1);
		          System.out.println("Rows inseted:"+no_rows);
			  }
			  catch(Exception e) {
				  System.out.println(e);
			
				  return "/staticInterfaceE1";
			  }
		   }
		   System.out.println(g_username);
		   String q1="select email e from test1.user where username='"+g_username+"'";
		   //String q1 = "select email e from user where username='" + g_username + "'";
	       
	        try {
	            // 1. Get a connection to the database
	            Connection connection = DriverManager.getConnection(ds, un, pw);

	            // 2. Create a statement object to execute the query
	            Statement statement = connection.createStatement();

	            // 3. Execute the SQL query and get the result set
	            ResultSet resultSet = statement.executeQuery(q1);

	            // 4. Iterate through the result set and print the data
	            
	            while (resultSet.next()) { 
					  g_email = resultSet.getString("e"); 
	            }
				  System.out.print(g_email);
	            // 5. Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
				/*
				 * if(result==1) { return "/staticInterface"; } else { return "signInError"; }
				 */

	        } catch (SQLException e) {
	        	System.out.println(e);
	        	return "/home";
	        }
		   EmailSender.sendEmail(g_email, "SSG Lab Test Booking Confirmation", g_message);
		   
		   
		   return "/staticInterface1";
}
	   
	   @GetMapping("/chart")
	    public String chartPage() {
	        return "graph";
	    }

	    @GetMapping("/chart/bar")
	    public ResponseEntity<byte[]> generateLineChart() throws IOException {
	        // Example list data
	        List<DataPoint> dataPoints =new ArrayList<>();
			/*
			 * new DataPoint("Category1", 1), new DataPoint("Category2", 4), new
			 * DataPoint("Category3", 3)
			 */
	        //);
	        String q1="select sdate,n_result from test1.graph_data where username='"+g_username+"'"; 
	        try {

	            Connection connection = DriverManager.getConnection(ds, un, pw);
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(q1);
				  while (resultSet.next()) { 
					  dataPoints.add(new DataPoint(resultSet.getString("sDate"),resultSet.getInt("n_result")));
				  }
	            // 5. Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
	            
	        } catch (SQLException e) {
	        	return null ;
	        }
	        
	        

	        // Generate the dataset
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (DataPoint dp : dataPoints) {
	            dataset.addValue(dp.getValue(), "HBA1C", dp.getTest_Date());
	        }

	        // Create the chart
	        JFreeChart barChart = ChartFactory.createLineChart(
	                "HBA1C Trend",
	                "Date",
	                "Result",
	                dataset,
	                PlotOrientation.VERTICAL,
	                true, true, false);

	        // Write the chart to a byte array
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ChartUtils.writeChartAsPNG(byteArrayOutputStream, barChart, 800, 600);
	        byte[] chartBytes = byteArrayOutputStream.toByteArray();

	        // Set headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        headers.setContentLength(chartBytes.length);

	        return new ResponseEntity<>(chartBytes, headers, HttpStatus.OK);
	    }

	    // Inner class to represent data points
		
		  static class DataPoint { private String Test_Date; private double value;
		  
		  public DataPoint(String Test_Date, double value) { this.Test_Date =
		  Test_Date; this.value = value; }
		  
		  public String getTest_Date() { return Test_Date; }
		  
		  public double getValue() { return value; } }
		 

}
