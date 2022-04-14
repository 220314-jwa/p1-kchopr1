package com.revature.maincontroller;
import javax.servlet.http.HttpSession;

import com.revature.controller.DepartmentController;
import com.revature.controller.EmployeeController;
import com.revature.controller.EventController;
import com.revature.controller.ReimbursementController;
import com.revature.controller.StatusController;
import com.revature.dao.EventDaoImpli;
import com.revature.dao.DepartmentDaoImpli;
import com.revature.dao.StatusDaoImpli;
import com.revature.dao.UtliiiesDao;
import com.revature.dao.EmployeeDaoImpli;
import com.revature.dao.ReimbursementDaoImpli;
import com.revature.model.Employee;
import com.revature.model.Login;
import com.revature.services.EventServicesImplimentation;
import com.revature.services.StatusServicesImplimentation;
import com.revature.services.DepartmentServicesImplimentation;
import com.revature.services.EmployeeServicesImplimentation;
import com.revature.services.ReimbursementServicesImplimentation;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.Javalin;
import io.javalin.core.util.Util;
import io.javalin.http.staticfiles.Location;
public class AppDemo {
   public static void main(String[] args) {
	   
	   	Javalin app;
		app = Javalin.create(config->{
			config.addStaticFiles("/public", Location.CLASSPATH);
			config.enableCorsForAllOrigins();
		});
		// pass in a port to have it run on that port:
		
		app.start(8081);
		
		app.routes(()-> crud("/status/{status-id}", new StatusController(new StatusServicesImplimentation(new StatusDaoImpli()))));
		app.routes(()-> crud("/event/{event-id}", new EventController(new EventServicesImplimentation(new EventDaoImpli()))));
		app.routes(()-> crud("/department/{department-id}", new DepartmentController(new DepartmentServicesImplimentation(new DepartmentDaoImpli()))));
		app.routes(()-> crud("/employee/{employee-id}", new EmployeeController(new EmployeeServicesImplimentation(new EmployeeDaoImpli()))));
		app.routes(()-> crud("/reimbursement/{reimbursement -id}", new ReimbursementController(new ReimbursementServicesImplimentation(new ReimbursementDaoImpli()))));

	
        
        app.post("/login", ctx->{
        
        	System.out.println("LOGIN");
        	Login login=ctx.bodyAsClass(Login.class); 
        	System.out.println(login);
        	UtliiiesDao utli=new UtliiiesDao();
        	
        	Employee emp=utli.login(login);
        	System.out.println(emp);
        	if(emp!=null)
        	{
        		ctx.json(emp);
        	}
        	else
        	{
        		System.out.println("Invalid USer");
        	}
        	
        });
    }
}
