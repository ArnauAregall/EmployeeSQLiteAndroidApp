package models;

public class Empleado {
	
	private int _id;
	private String _nombre;
	private String _cargo;
	private String _departamento;
	private String _telf;
	private String _email;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public void setNombre(String nombre) {
		this._nombre = nombre;
	}
	
	public String getCargo() {
		return _cargo;
	}
	
	public void setCargo(String cargo) {
		this._cargo = cargo;
	}
	
	public String getDepartamento() {
		return _departamento;
	}
	
	public void setDepartamento(String departamento) {
		this._departamento = departamento;
	}
	
	public String getTelf() {
		return _telf;
	}
	
	public void setTelf(String telf) {
		this._telf = telf;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		this._email = email;
	}
	
}
