package com.tecgurus.ws.soap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tecgurus.ws.bean.Usuario;
import com.tecgurus.ws.db.ConectorBD;

public class ImplServiceWeb implements IServicioWeb {

	@Override
	public String crearUsuario(Usuario usuario) {
		
		Connection conn = ConectorBD.getConexionBD();
		String sql = "insert into usuarios(nombre, apellido, usuario, contrasenia)"
				+ " values(?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getUsuario());
			ps.setString(4, usuario.getContrasenia());
			int resultado = ps.executeUpdate();
			
			if (resultado == 0) {
				return "ERROR";
			} else {
				return "Actualizado correctamente";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ocurrio Error!";
		} finally {
			ConectorBD.close(conn, ps);
		}
		
		
		
	}

	@Override
	public Usuario[] buscarUsuario(String var, int var2) {
		
		Connection conn = ConectorBD.getConexionBD();
		
		String sql = "select * from usuarios where usuario = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, var);
			
			ResultSet rs = ps.executeQuery();
			
			List<Usuario> lista = new ArrayList<Usuario>();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String usuario = rs.getString("usuario");
				String contrasenia = rs.getString("contrasenia");
				
				String datos = "id: "+id+" Nombre: "+nombre+" Apellido: "+apellido+" Usuario: "+
				            usuario+" Contrasenia: "+contrasenia;
				System.out.println(datos);
				u.setId(id);
				u.setNombre(nombre);
				u.setApellido(apellido);
				u.setUsuario(usuario);
				u.setContrasenia(contrasenia);
				lista.add(u);
			}
			
			ConectorBD.close(conn, ps);
			
			Usuario[] usuarioArray = new Usuario[lista.size()];
			usuarioArray = lista.toArray(usuarioArray);
			return usuarioArray;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public String actualizarUsuario(Usuario usuario) {
		Connection conn = ConectorBD.getConexionBD();
		
		String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, usuario = ?, contrasenia = ?"
				+ " where id = ? ";
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getUsuario());
			ps.setString(4, usuario.getContrasenia());
			ps.setInt(5, usuario.getId());
			int resultado = ps.executeUpdate();
			
			if (resultado == 0) {
				return "ERROR";
			} else {
				return "Actualizado correctamente";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception al actualizar";
		} finally {
			ConectorBD.close(conn, ps);
		}
		
	}

	@Override
	public boolean borrarUsuario(int id) {
		
		Connection conn = ConectorBD.getConexionBD();
		
		String sql = "delete from usuarios where id = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int resultado = ps.executeUpdate();
			
			if (resultado == 0) {
				return false;
			} else {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConectorBD.close(conn, ps);
		}
		
		
	}

}
