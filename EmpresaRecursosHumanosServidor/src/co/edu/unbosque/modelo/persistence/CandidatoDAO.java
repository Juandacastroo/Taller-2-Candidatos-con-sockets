package co.edu.unbosque.modelo.persistence;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.unbosque.modelo.CandidatoDTO;

/**
 * Cumpliendo con la función de "Data Access object (DAO)" contiene el CRUD de
 * candidato
 * 
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class CandidatoDAO {
	
	/**
	 * Atributo de tipo "Archivo" que nos conectara con dicha clase
	 */
	private Archivo archivo;
	
	/**
	 * Método constructor de la clase "CandidatoDAO"
	 * 
	 * @param archivo de tipo Archivo con el que inicializará el archivo
	 */
	public CandidatoDAO(Archivo archivo) {
		
		this.archivo = archivo;
	}
	
	/**
	 * Metodo que elimina un candidato de la lista de candidatos segun su cedula
	 * <b> pre </b> Existe la lista de tipo CandidatoDTO <br>
	 * <b> pre </b> El file existe en el sistema <br>
	 * <b> post </b> El candidato se eliminó de la lista de candidatos y del mismo
	 * modo, se elimina del sistema <br>
	 * 
	 * @param cedula de tipo String referente a la cedula del candidato el cual se eliminará
	 * @param candidatos de tipo ArrayList<CandidatoDTO> referente a la lista que contiene
	 * al candidato a eliminar
	 * @return Verdadero si el metodo logra eliminar el contacto de la lista y falso
	 *         si existe una excepcion que no lo permita
	 */
	public boolean eliminar(String cedula, ArrayList<CandidatoDTO> candidatos, File file) {
		
		try {
			for(int i = 0; i <candidatos.size(); i++) {
				if(candidatos.get(i).getCedula().equals(cedula)) {
					candidatos.remove(i);
					file.delete();
					file.createNewFile();
					archivo.escribirArchivo(candidatos, file);
					return true;
				}
			}
				
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo el cual crea un candidato nuevo y lo agrega a la lista de candidatos y al sistema.
	 * <b> pre </b> El file existe en el sistema <br>
	 * <b> pre </b> Todos los datos fueron revisados por sus respectivas excepciones <br>
	 * <b> post </b> Se ha agregado el candidato al archivo y a la lista de candidatos <br>
	 * 
	 * @param nombre de tipo String del candidato
	 * @param apellido de tipo String del candidato
	 * @param cedula de tipo String del candidato
	 * @param cargo de tipo String del candidato
	 * @param edad de tipo String del candidato
	 * @param candidatos de tipo ArrayList<CandidatoDTO> referente a la lista a la cual
	 * se le agregará el nuevo candidato
	 * @param file de tipo File referente al archivo donde se guardará la información.
	 * @return Verdadero si se creo y agrego correctamente al sistema, de lo constrario
	 * lanzará un valor falso
	 */
	public boolean crear(String nombre, String apellido, String cedula, String cargo, String edad, ArrayList<CandidatoDTO> candidatos, File file) {

		boolean encontrado = false;
		
			if(buscar(cedula, candidatos).size()< 1) {
				CandidatoDTO nuevo = new CandidatoDTO(nombre, apellido, cedula, cargo, edad);
				candidatos.add(nuevo);
				archivo.escribirArchivo(candidatos, file );
				encontrado = true;
			}
			return encontrado;
	}
	
	/**
	 * Metodo que odifica dentro del ArrayList datos específicos de un candidato y 
	 * modifica el archivo para guardarlo
	 * <b> pre </b> Existe el candidato en la lista de candidatos y el file en el sistema <br>
	 * <b> pre </b> El dato a modificar se revisó y verificó por sus respectivas excepciones <br>
	 * <b> post </b> Se han modificado los datos de un candidato en específico <br>
	 * 
	 * @param dato de tipo String referente al dato nuevo para actualizar
	 * @param candidato de tipo CandidatoDTO referente al candidato del cual se
	 * modificara la información
	 * @param file de tipo File donde se guardará la informacion modificada
	 * @param lista de tipo ArrayList<CandidatoDTO> que almacena todos los candidatos
	 * @param opcion de tipo String referente al dato que el usuario decida cambiar
	 * @return Verdadero si se logro actualizar los datos deseados, de lo contrario
	 * lanzará un valor falso
	 */
	public boolean actualizar(String dato, CandidatoDTO candidato, File file, String opcion, ArrayList<CandidatoDTO> lista) {
		
		boolean modificado = false;
		try{
			switch (opcion) {
			case "1":
				
				for(CandidatoDTO c : lista) {
					if(c == candidato) {
						c.setNombre(dato);
					}
				}
				file.delete();
				file.createNewFile();
				archivo.escribirArchivo(lista, file);
				modificado = true;
				break;
				
			case "2":
				
				for(CandidatoDTO c : lista) {
					if(c == candidato) {
						c.setApellido(dato);
					}
				}
				file.delete();
				file.createNewFile();
				archivo.escribirArchivo(lista, file);
				modificado = true;
				break;
			case "3":
				
				for(CandidatoDTO c : lista) {
					if(c == candidato) {
						c.setCedula(dato);
					}
				}
				file.delete();
				file.createNewFile();
				archivo.escribirArchivo(lista, file);
				modificado = true;
				break;
			case "5":
				
				for(CandidatoDTO c : lista) {
					if(c == candidato) {
						c.setEdad(dato);
					}
				}
				file.delete();
				file.createNewFile();
				archivo.escribirArchivo(lista, file);
				modificado = true;
				break;
			case "4":
				
				for(CandidatoDTO c : lista) {
					if(c == candidato) {
						c.setCargo(dato);
					}
				}
				file.delete();
				file.createNewFile();
				archivo.escribirArchivo(lista, file);
				modificado = true;
				break;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return modificado;
	}
	
	/**
	 * Metodo que busca candidatos dentro de una lista y los identifica dependiendo de su cedula, además,
	 * los almacena dentro de otra lista si existe alguno repetido.
	 * <b> pre </b> Existe la lista de tipo "CandidatoDTO" <br>
	 * <b> post </b> Se obtuvo una lista de tipo "CandidatoDTO" con los usuarios encontrados<br>
	 * 
	 * @param cedula de tipo String referente al dato por el cual se buscará al candidato
	 * @param candidatos de tipo ArrayList<CandidatoDTO> donde se buscaran los candidatos
	 * @return ArrayList con todos los candidatos encontrados
	 */
	public ArrayList<CandidatoDTO> buscar( String cedula, ArrayList<CandidatoDTO> candidatos) {

		ArrayList<CandidatoDTO> encontrado = new ArrayList<>();
		if(candidatos != null) {
			for(int i = 0; i < candidatos.size();i++){
				if(candidatos.get(i).getCedula().equals(cedula)) {
					encontrado.add(candidatos.get(i));
				}
			}
		}
		return encontrado;
	}
	
}
