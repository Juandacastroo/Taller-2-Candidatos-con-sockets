package co.edu.unbosque.modelo;

/**
 * Esta clase hace la funcion de Creador siguiendo los principios "GRASP",
 * será la excepción de una cadena de caracteres con solo numeros mal ingresado
 * 
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class CedulaException extends Exception{
	
	/**
     * Constructor de la clase "CedulaException"
     */
	 public CedulaException() {
		super();
	}
	
	/**
	* Constructor de la clase "CedulaException" con mensaje para la excepción
	* @param exce de tipo String que digitaremos al momento de usa la excepción
    */
	public CedulaException(String exce){
		super(exce);	
	}
}
