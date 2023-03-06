package co.edu.unbosque.modelo;

/**
 * Esta clase hace la funcion de Creador siguiendo los principios "GRASP",
 * será la excepción de una cadena de caracteres con solo letras mal ingresado
 * 
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class CadenasException extends Exception{
	
	 /**
     * Constructor de la clase "CadenasException"
     */
	 public CadenasException() {
		super();
	}
	
	/**
	* Constructor de la clase "CadenasException" con mensaje para la excepción
    * @param exce de tipo String que digitaremos al momento de usa la excepción
    */
	public CadenasException(String exce){
		super(exce);	
	}

}
