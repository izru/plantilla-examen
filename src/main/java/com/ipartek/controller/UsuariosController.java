package com.ipartek.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.model.UsuarioDAO;
import com.ipartek.pojo.Usuario;
import com.ipartek.pojo.Alert;
import com.mysql.jdbc.MysqlDataTruncation;

/**
 * Servlet implementation class RolesController
 */
@WebServlet("/usuarios")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_FORM = "/usuarios/form.jsp";
	private static final String VIEW_INDEX = "/usuarios/index.jsp";

	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;

	private RequestDispatcher dispatcher;
	private Alert alert;
	private UsuarioDAO dao;

	private int idUsuario;
	private String nombre;
	private String apellido;

	private String search;
	private int op;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = UsuarioDAO.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			alert = null;

			recogerParametros(request);

			switch (op) {
			case OP_MOSTRAR_FORMULARIO:
				mostrarFormulario(request);
				break;

			case OP_BUSQUEDA:
				buscar(request);
				break;

			case OP_ELIMINAR:
				eliminar(request);
				break;

			case OP_GUARDAR:
				guardar(request);
				break;

			default:
				listar(request);
				break;
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher(VIEW_INDEX);
			listar(request);
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			dispatcher.forward(request, response);
		}
	}

	private void buscar(HttpServletRequest request) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = dao.getByName(search);
		request.setAttribute("usuarios", usuarios);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	private void eliminar(HttpServletRequest request) {
		if (dao.delete(idUsuario)) {
			alert = new Alert("Se ha eliminado el registro: " + idUsuario, Alert.TIPO_DANGER);
		} else {
			alert = new Alert("Ha habido un error eliminando", Alert.TIPO_WARNING);
		}
		listar(request);
	}

	private void guardar(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);

		if ("".equals(nombre)) {
			alert = new Alert("El nombre no puede estar vacio. No se ha guardado el registro", Alert.TIPO_WARNING);
		} else {
			try {
				if (dao.save(usuario)) {
					alert = new Alert("Se ha guardado el registro", Alert.TIPO_PRIMARY);
				} else {
					alert = new Alert("Ha habido un error al guardar", Alert.TIPO_DANGER);
				}
			} catch (MysqlDataTruncation e) {
				alert = new Alert("Rol duplicado. No se ha podido guardar", Alert.TIPO_WARNING);
				request.setAttribute("usuario", usuario);

			} catch (SQLException e) {
				dispatcher = request.getRequestDispatcher(VIEW_FORM);
				alert = new Alert("El nombre solo puede contener 45 caracteres. No se ha guardado el registro",
						Alert.TIPO_WARNING);
			}
		}

		request.setAttribute("usuario", usuario);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void mostrarFormulario(HttpServletRequest request) {
		Usuario usuario = new Usuario();

		if (idUsuario != -1) {
			usuario = dao.getById(idUsuario);
		}
		request.setAttribute("usuario", usuario);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void recogerParametros(HttpServletRequest request) {
		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		} else {
			op = 0;
		}

		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		if (request.getParameter("idUsuario") != null) {
			idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		}

		nombre = (request.getParameter("nombre") != null) ? request.getParameter("nombre").trim() : "";
		apellido = (request.getParameter("apellido") != null) ? request.getParameter("apellido").trim() : "";
	}

	private void listar(HttpServletRequest request) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = dao.getAll();
System.out.println(usuarios);
		request.setAttribute("usuarios", usuarios);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

}