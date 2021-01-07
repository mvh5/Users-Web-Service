package com.tecgurus.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.tecgurus.ws.bean.Usuario;

@WebService
public interface IServicioWeb {
	
	//contiene todos los servicios y metodos que seran publicados
	//los metodos son aquellos que estaran disenados por CRUD
	//Los metodos son aquellos con los que el cliente puede interactuar con su servicio
	
	//Particularidades de una interfaz:
	//Todos los metodos por default son publicos
	//Puede o no tener public en los metodos
	//solo contienen la definicion del metodo
	//no puede contener variables
	
	@WebMethod
	public String crearUsuario(Usuario usuario);
	
	@WebMethod
	public Usuario[] buscarUsuario(@WebParam(name = "var") String var,
									@WebParam(name = "var2") int var2);
	
	@WebMethod
	public String actualizarUsuario(Usuario usuario);
	
	@WebMethod
	public boolean borrarUsuario(@WebParam(name = "id") int id);

}
