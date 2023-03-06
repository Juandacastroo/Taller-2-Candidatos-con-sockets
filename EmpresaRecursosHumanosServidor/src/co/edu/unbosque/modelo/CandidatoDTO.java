package co.edu.unbosque.modelo;

import java.io.Serializable;

/**
 * Esta clase hace la funcion de Creador siguiendo los principios "GRASP", será
 * la super clase del programa y servirá como base para todas sus derivadas
 * 
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class CandidatoDTO implements Serializable{
	
	/**
	 * Atributo que permite serializar la clase asignandole una version unica
	 */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Atributo de tipo String refente al nombre del candidato 
	 */
	private String nombre;
	
	/**
	 * Atributo de tipo String refente al apellido del candidato 
	 */
	private String apellido;
	
	/**
	 * Atributo de tipo String refente a la cedula del candidato 
	 */
	private String cedula;
	
	/**
	 * Atributo de tipo String refente al cargo del candidato 
	 */
	private String cargo;
	
	/**
	 * Atributo de tipo String refente a la edad del candidato 
	 */
	private String edad;
	
	/**
	 * Metodo constructor de la clase "CandidatoDTO"
	 * @param nombre String referente al nombre que digite el usuario
	 * @param apellido String referente al apellido que digite el usuario
	 * @param cedula String referente a la cedula que digite el usuario
	 * @param cargo String referente al cargo que digite el usuario
	 * @param edad String referente a la edad que digite el usuario
	 */
	public CandidatoDTO(String nombre, String apellido, String cedula, String cargo, String edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.cargo = cargo;
		this.edad = edad;
	}

	 /**
     * Este metodo nos permite obtener la variable "nombre"
     * 
     * @return String
     */
	public String getNombre() {
		return nombre;
	}
	
	  /**
     * Este metodo nos permite asignar la variable "nombre"
     * 
     * @param nombre
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 /**
     * Este metodo nos permite obtener la variable "apellido"
     * 
     * @return String
     */
	public String getApellido() {
		return apellido;
	}

	  /**
     * Este metodo nos permite asignar la variable "apellido"
     * 
     * @param apellido
     */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	 /**
     * Este metodo nos permite obtener la variable "cedula"
     * 
     * @return String
     */
	public String getCedula() {
		return cedula;
	}

	  /**
     * Este metodo nos permite asignar la variable "cedula"
     * 
     * @param cedula
     */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	 /**
     * Este metodo nos permite obtener la variable "cargo"
     * 
     * @return String
     */
	public String getCargo() {
		return cargo;
	}

	  /**
     * Este metodo nos permite asignar la variable "cargo"
     * 
     * @param cargo
     */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	 /**
     * Este metodo nos permite obtener la variable "edad"
     * 
     * @return String
     */
	public String getEdad() {
		return edad;
	}
	
	  /**
     * Este metodo nos permite asignar la variable "edad"
     * 
     * @param edad
     */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * Metodo sobreescrito que permite organizar la informacion y mostrarla
	 * a nuestro gusto
	 */
	@Override
	public String toString() {
		return "Nombre = " + nombre + " \n Apellido = " + apellido+ " \n Cedula = " + cedula + "\n Cargo = " + cargo
				+ "\n Edad = " + edad ;
	}
	
	
	
	
	
}
