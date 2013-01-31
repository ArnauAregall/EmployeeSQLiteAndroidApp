package models;

/**
 * Employee model, default constructor.
 * @author ArnauAregall
 *
 */
public class Employee {
	
	/**
	 * Private class attributes
	 */
	private int _id;
	private String _name;
	private String _charge;
	private String _department;
	private String _phone;
	private String _email;
	
	/**
	 * Getters and setters for private class attributes.
	 * @return
	 */
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		this._name = name;
	}
	
	public String getCharge() {
		return _charge;
	}
	
	public void setCharge(String charge) {
		this._charge = charge;
	}
	
	public String getDepartament() {
		return _department;
	}
	
	public void setDepartament(String department) {
		this._department = department;
	}
	
	public String getPhone() {
		return _phone;
	}
	
	public void setPhone(String phone) {
		this._phone = phone;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		this._email = email;
	}
	
}
