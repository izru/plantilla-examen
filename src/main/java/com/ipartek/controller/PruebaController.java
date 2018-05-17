package com.ipartek.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ipartek.pojo.Alert;
import com.ipartek.model.UsuarioDAO;
import com.ipartek.pojo.Usuario;

/**
 * Servlet implementation class PruebaController
 */
@WebServlet("/prueba")
public class PruebaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		Alert alert = null;

		try {

			UsuarioDAO dao = UsuarioDAO.getInstance();
			usuarios = dao.getAll();
System.out.println(usuarios);
		} catch (Exception e) {
			alert = new Alert();
			e.printStackTrace();

		} finally {
			// enviar atributos a la JSP
			request.setAttribute("alert", alert);
			request.setAttribute("usuarios", usuarios);
			// ir a la JSP
			request.getRequestDispatcher("usuario.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
