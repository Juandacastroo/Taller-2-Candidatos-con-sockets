package co.edu.unbosque.modelo.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import co.edu.unbosque.modelo.CandidatoDTO;

/**
 * Esta clase sirve para administrar los archivos binarios donde se almacenarán
 * los contactos
 *
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class Archivo {
	
	/**
	 * Atributo de tipo ObjectOutputStream que nos permitirá serializar la
	 * información en un archivo binario
	 */
	private ObjectOutputStream salida;
	
	/**
	 * Atributo de tipo ObjectOInputStream que nos permitirá deserializar la
	 * información de un archivo binario
	 */
	private ObjectInputStream entrada;
	
	 /**
     * Constructor de la clase "Archivo" donde si no existe el file, lo crea
     * <b> pre </b> El parametro debe tener un archivo o la ruta correcta <br>
	 * <b> post </b> El archivo existe <br>
     * @param file de tipo File donde se serializará la información
     */
	public Archivo( File file) {
		
		if(!(file.exists())){
			try {
			file.createNewFile();
			
			}catch( IOException e) {
			System.out.println(e.getMessage());
			}
		}
	
	}
	
	 /**
	 * Metodo que leer el archivo binario y crea la lista con la informacion
	 * de los candidatos
	 * <b> pre </b> El file debe existir en el sistema  <br>
	 * <b> post </b> Se creó la lista con la informacion de los usuarios
	 * que se encontraba en el archivo <br>
	 * 
	 * @param file de tipo File donde se encuentra la informacion de los candidatos
	 * @return La lista de candidatos con su respectiva información
	 */
	public ArrayList<CandidatoDTO> leerArchivo(File file){
	
		ArrayList<CandidatoDTO> lista = new ArrayList<>();
	
		if(file.length()!= 0){
			try {
				entrada = new ObjectInputStream(new FileInputStream(file));
				lista = (ArrayList<CandidatoDTO>) entrada.readObject();
				entrada.close();
			}catch(IOException e){
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return lista;
	}
	
	 /**
     * Metodo que escribe la informacion de los candidatos en un archivo binario
     * <b> pre </b> La lista de candidatos debe existir y no estar vacia <br>
     * <b> pre </b> El file debe existir en el sistema <br>
     * <b> post </b> Se escribió la lista y la informacion de los candidatos en el archivo <br>
     * 
     * @param candidato de tipo Arraylist referente a la lista de candidatos
     * @param file de tipo File donde se encuentra su respectiva informacion.
     */
	public void escribirArchivo(ArrayList<CandidatoDTO> candidato, File file) {

		try {
			salida = new ObjectOutputStream(new FileOutputStream(file));
			salida.writeObject(candidato);
			salida.flush();
			salida.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
