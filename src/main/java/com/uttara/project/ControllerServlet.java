package com.uttara.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ControllerServlet() {
        super();
        System.out.println( "In CS no-arg constructor" );
    }

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		System.out.println("In CS doGet()");
		process( request , response );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		System.out.println("In CS doPost()");
		process( request , response );
	}

	protected void process( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		System.out.println( "In CS process() " );
		
		String uri = request.getRequestURI();
		System.out.println( "uri = " +uri );
		
		RequestDispatcher rd = null;
		String message;
		
		Model model = new Model();
		
		if( uri.contains("/openRegisterView.do") ) {
			
			System.out.println("in uri.contains(/openRegisterView.do)");
			//forward to Register.jsp
			
			rd = request.getRequestDispatcher( "Register.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains("/openLoginView.do") ) {
			
			System.out.println("in uri.contains(/openLoginView.do)");
			//forward to Login.jsp
			
			rd = request.getRequestDispatcher( "Login.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains("/register.do") ) {
			
			System.out.println("in uri.contains(/register.do)");
			
			/*
			 * 1) get the bean from scope
			 * 2) invoke model and pass bean to register
			 * 3) if registration succeeds, forward to Success.jsp
			 * 4) if registration fails, forward to Register.jsp with error msg
			 */
			
			RegBean bean = (RegBean)request.getAttribute("reg");
			message = model.register( bean );
			
			 if( message.equals(Constants.SUCCESS) ) {
				 
				 String successMsg = "Your registration has succeeded!";
				 request.setAttribute("sMessage", successMsg);
				 
				 rd = request.getRequestDispatcher("Success.jsp");
				 rd.forward(request, response);
				 
			 } else {
				 
				 request.setAttribute("errorMsg", message);
				 
				 rd = request.getRequestDispatcher("Register.jsp");
				 rd.forward(request, response);
			 }
			
		}
		
		if( uri.contains( "/login.do" ) ) {
			 
			 LoginBean loginBean = (LoginBean)request.getAttribute("login");
			 message = model.authenticate(loginBean);
			 
			 if( message.equals(Constants.SUCCESS) ) {
				 
				 System.out.println("Logged in successfully");
				 
				 ServletContext context = getServletContext();
				 context.setAttribute("loginBean", loginBean);
				 
				 rd = request.getRequestDispatcher("Menu.jsp");
				 rd.forward(request, response);
				 
			 } else {
				 
				 request.setAttribute( "errorMessage", message );
				 rd = request.getRequestDispatcher("Login.jsp");
				 rd.forward(request, response);
			 } 
			 
		 }
		
		if( uri.contains( "/openEditAccountView.do" ) ) {
			
			System.out.println("in uri.contains(/openEditAccountView.do)");
			//forward to EditAccount.jsp
			
			rd = request.getRequestDispatcher( "EditAccount.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains( "/editAccount.do" ) ) {
			
			System.out.println("in uri.contains(/editAccount.do)");
			
			RegBean regBean = (RegBean)request.getAttribute("editAcc");
			ServletContext context = getServletContext();
			
			LoginBean loginBean = null;
			
			if( context.getAttribute("loginBean") != null ) {
				
				 loginBean = (LoginBean)context.getAttribute("loginBean");
				
			}
			
			System.out.println("loginEmail = " +loginBean.getEmail() + " and registerEmail = " +regBean.getEmail());
			String msg = model.editAccountAuthenticate(regBean , loginBean);
			
			if( msg.equals(Constants.SUCCESS) ) {
				 
				 String successMsg = "Your account edited successfully!";
				 request.setAttribute("sMessage", successMsg);
				 
				 rd = request.getRequestDispatcher("Success.jsp");
				 rd.forward(request, response);
				 
			 } else {
				 
				 request.setAttribute("errorMsg", msg);
				 
				 rd = request.getRequestDispatcher("EditAccount.jsp");
				 rd.forward(request, response);
			 }
		}
		
		if( uri.contains( "/logout.do" ) ) {
			
			 String successMsg = "You logged out successfully!";
			 request.setAttribute("sMessage", successMsg);
			 
			 rd = request.getRequestDispatcher("Success.jsp");
			 rd.forward(request, response);
		}
		
		if( uri.contains( "/openAddContactView.do" ) ) {
			
			System.out.println("in uri.contains(/openAddContactView.do)");
			//forward to AddContact.jsp
			
			rd = request.getRequestDispatcher( "AddContact.jsp" );
			rd.forward(request, response);
		}
		
		if( uri.contains( "/addContact.do" ) ) {
			
			System.out.println("in uri.contains(/addContact.do)");
			
			ContactBean contactBean = (ContactBean)request.getAttribute("addContact");
			String message1 = model.addContact( contactBean );
			
			if( message1.equals(Constants.SUCCESS) ) {
				 
				 String successMsg = "Your contact added successfully!";
				 request.setAttribute("sMessage", successMsg);
				 
				 rd = request.getRequestDispatcher("Success.jsp");
				 rd.forward(request, response);
				 
			 } else {
				 
				 request.setAttribute("name", contactBean.getName());
				 request.setAttribute("email", contactBean.getEmail());
				 request.setAttribute("phoneNum", contactBean.getPhoneNum());
				 request.setAttribute("tags", contactBean.getTags());
				 request.setAttribute("dob", contactBean.getDob());
				 
				 if( contactBean.getGender().equals("M")) {
					 request.setAttribute("isMChecked", "checked");
				 }
				 
				 if( contactBean.getGender().equals("F")) {
					 request.setAttribute("isFChecked", "checked");
				 }
				 request.setAttribute("errorMsg", message1);
				 
				 rd = request.getRequestDispatcher("AddContact.jsp");
				 rd.forward(request, response);
			 }
			
		}
		
		if( uri.contains( "/openSearchContactView.do" ) ) {
			
			System.out.println("in uri.contains(/openSearchContactView.do)");
			//forward to SearchContact.jsp
			
			rd = request.getRequestDispatcher( "SearchContact.jsp" );
			rd.forward(request, response);
		}
		
		if( uri.contains( "/searchContact.do" ) ) {
			
			System.out.println("in uri.contains(/searchContact.do)");
			
			String searchString = request.getParameter("searchString");
			System.out.println("");
			System.out.println("search string = " +searchString);
			System.out.println("");
			List<ContactBean> beanList = model.getContacts(searchString , null);
			
			//forward to SearchResults.jsp
			request.setAttribute("beanList", beanList);
			
			rd = request.getRequestDispatcher( "SearchResults.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains( "/openListContactsView.do" ) ) {
			
			System.out.println("in uri.contains(/openListContactsView.do)");
			//forward to ListContacts.jsp
			
			rd = request.getRequestDispatcher( "ListContacts.jsp" );
			rd.forward(request, response);
		}
		
		if( uri.contains( "/listContact.do" ) ) {
			
			System.out.println("in uri.contains(/listContact.do)");
			
			String orderBy = request.getParameter("list");
			System.out.println("orderby selected option = " +orderBy);
			
			List<ContactBean> list = model.getContacts(null , orderBy);
			
			//forward to ListResults.jsp
			request.setAttribute("list", list);
			
			rd = request.getRequestDispatcher( "ListResults.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains( "/openEditContactView.do" ) ) {
			
			System.out.println("in uri.contains(/openEditContactView.do)");
			//forward to EditContact.jsp 
			
			rd = request.getRequestDispatcher( "EditContact.jsp" );
			rd.forward(request, response);
		}
		
		if( uri.contains( "/editContact.do" ) ) {
			
			System.out.println("in uri.contains(/editContact.do)");
			
			String nameToEdit = request.getParameter("nameToEdit");
			System.out.println("name to edit = " +nameToEdit);
			
			List<ContactBean> editBeanList = model.getContacts(nameToEdit , null);
			
			//forward to ListEditContacts.jsp
			request.setAttribute("editBeanList", editBeanList);
			rd = request.getRequestDispatcher( "ListEditContacts.jsp" );
			rd.forward(request, response);
			
		}
		
		if( uri.contains( "/edit.do" ) ) {
			
			System.out.println("in uri.contains(/edit.do)");
			//forward to UpdateContact.jsp 
			
			String no = request.getParameter("sl_no");
			int sl_no = Integer.parseInt(no);
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phoneNum = request.getParameter("phoneNum");
			String tags = request.getParameter("tags");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String created_date = request.getParameter("created_date");
			
			if(gender.equals("M")) {
				 request.setAttribute("isMChecked", "checked");
			 }
			 
			 if( gender.equals("F")) {
				 request.setAttribute("isFChecked", "checked");
			 }
			
			ContactBean contactBean = new ContactBean(sl_no , name , email , phoneNum , tags , gender , dob , created_date);
			
			System.out.println("sl_no = " +sl_no+ " name = " +name+ " email = " +email+ " phoneNum = " +phoneNum+" tags = " +tags+
					" gender =  "+gender+ " dob = " +dob+ " created_date = " +created_date);
			request.setAttribute("contactBean", contactBean);
			
			rd = request.getRequestDispatcher( "UpdateContact.jsp" );
			rd.forward(request, response);
		}

		
	
	if( uri.contains( "/updateContact.do" ) ) {
		
		System.out.println("in uri.contains(/updateContact.do)");
		
		ContactBean contactBean = (ContactBean)request.getAttribute("updateContact");
		String message2 = model.updateContact(contactBean);
		
		if( message2.equals(Constants.SUCCESS) ) {
			 
			 String successMsg = "Your contact updated successfully!";
			 request.setAttribute("sMessage", successMsg);
			 
			 rd = request.getRequestDispatcher("Success.jsp");
			 rd.forward(request, response);
			 
		 } else {
			 request.setAttribute("errorMsg", message2);
			 
			 rd = request.getRequestDispatcher("UpdateContact.jsp");
			 rd.forward(request, response);
			 
		 }
	}
	
	if( uri.contains( "/deleteContact.do" ) ) {
		
		System.out.println("in uri.contains(/updateContact.do)");
	}
	System.out.println("");
	System.out.println("");
	}
	
}









