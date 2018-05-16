package com.ipartek.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.pojo.Usuario;
import com.ipartek.utilidades.Utilidades;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UsuarioDAO implements Persistible<Usuario> {

	private static UsuarioDAO INSTANCE = null;

	private UsuarioDAO() {
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
	}

	public static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT `id`, descripcion FROM prueba-examen.usuario ORDER BY nombre ASC LIMIT 500";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				lista.add(mapper(rs));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Usuario getById(int id) {
		Usuario usuario = new Usuario();
		String sql = "SELECT `id`, descripcion FROM prueba-examen.usuario WHERE `id` = ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuario = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public ArrayList<Usuario> getByName(String search) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT id, descripcion FROM prueba-examen.usuario WHERE nombre LIKE ? ORDER BY id DESC LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, "%" + search + "%");
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					lista.add(mapper(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public boolean save(Usuario pojo) throws SQLException {
		boolean resultado = false;

		if (pojo != null) {
			// Sanitize nombre
			pojo.setNombre(Utilidades.limpiarEspacios(pojo.getNombre()));

			if (pojo.getId() == -1) {
				resultado = crear(pojo);
			} else {
				resultado = modificar(pojo);
			}
		}

		return resultado;
	}

	private boolean modificar(Usuario pojo) throws MysqlDataTruncation, MySQLIntegrityConstraintViolationException {
		boolean resultado = false;

		String sql = "UPDATE prueba-examen.usuario SET nombre= ? WHERE  id= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getId());

			resultado = doSave(pst, pojo);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Usuario duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private boolean crear(Usuario pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;
		String sql = "INSERT INTO prueba-examen.usuario (nombre) VALUES (?);";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());

			resultado = doSave(pst, pojo);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Usuario duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	private boolean doSave(PreparedStatement pst, Usuario pojo)
			throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;

		try {
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						pojo.setId(generatedKeys.getInt(1));
					}
				}
				resultado = true;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Usuario duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;
		String sql = "DELETE FROM prueba-examen.usuario WHERE  `id`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);

			int affetedRows = pst.executeUpdate();
			if (affetedRows == 1) {
				resultado = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		return usuario;
	}

}