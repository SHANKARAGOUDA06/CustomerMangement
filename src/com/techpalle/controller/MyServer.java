package com.techpalle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.DataAccess;
import com.techpalle.model.Customer;
import com.techpalle.model.Success;


@WebServlet("/")
public class MyServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path=request.getServletPath();
		switch(path)
		{
			case "/LogCustomer":
			validateCustomer(request,response); 	
			break;
			
			case "/regCustomer":
			insertCustomer(request,response);
			break;
				
			case "/log":
			getLoginPage(request,response);
			break;
			
			case "/reg":
			getRegistrationPage(request,response);
			break;
				
			default:		
			getStartUpPage(request, response);
			break;
		}
		
	}

	
	private void validateCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		
		//read the email and password from jsp page
		String e=request.getParameter("tbEmailLog");
		String p=request.getParameter("tbPassLog");
		
		//call the method present in DAO
		
		boolean res=DataAccess.validCustomer(e, p);
		
		//condition and Redirect user to destination page (success)
		
		if(res)
		{
			try 
			{
				RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
				Success sp=new Success();
				request.setAttribute("succesDetails",sp);
				rd.forward(request, response);
			} 
			catch (ServletException | IOException e1)
			{
				e1.printStackTrace();
			}
			
		}
		else
		{
			getLoginPage(request, response);
		}
		
		
	}


	private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		//Read the data form jsp page
		
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		long m=Long.parseLong(request.getParameter("tbMobile"));
		String p=request.getParameter("tbPass");
		String s=request.getParameter("ddlStates");
		
		
		//store that data in customer object
		Customer c=new Customer(n, e, m, p, s);
		
		//call insertCustomer method present in dao by passing above object
		
		DataAccess.insertCustomer(c);
		
		//redirect user to login page
		
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e1)
		{
			e1.printStackTrace();
		}
		
		
	}


	private void getRegistrationPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("Customer_registration.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private void getLoginPage(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{	
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_mangement.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
