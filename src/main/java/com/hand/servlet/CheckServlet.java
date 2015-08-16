package com.hand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.entity.User;
import com.hand.service.CheckUserService;


public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CheckUserService cku = new CheckUserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
       
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		//String passwd = request.getParameter("upwd");
		
		RequestDispatcher rd = null;
		String forward = null;
		
		if(uname == null ){
			request.setAttribute("msg", "用户名为空!");
			rd = request.getRequestDispatcher("/17/error.jsp");
			rd.forward(request, response);
		}else{
			User user = new User();
			user.setFirst_name(uname);;
//			user.setPassword(passwd);
			
			boolean bool = cku.check(user);
			
			if(bool){
				forward = "/17/success.jsp";
			}else{
				request.setAttribute("msg", "用户名错误，请重新输入！");
				forward = "/17/error.jsp";
			}
			
			rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
	}

}
