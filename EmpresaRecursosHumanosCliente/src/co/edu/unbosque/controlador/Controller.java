package co.edu.unbosque.controlador;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import co.edu.unbosque.modelo.CadenasException;
import co.edu.unbosque.modelo.CedulaException;
import co.edu.unbosque.vista.PersonasRegistradas;
import co.edu.unbosque.vista.VentanaPrincipal;

/**
 * Esta clase hace la funcion de Controlador siguiendo los principios "GRASP"
 * 
 * @author Juan David Castro García
 * @author Ariadna Sophia Cabrera Carrera
 */
public class Controller implements ActionListener {

	/**
	 * Declaración del frame de la ventana principal del programa
	 */
	private VentanaPrincipal vp;
	private PersonasRegistradas pr;
	private String datos;
	private HiloCliente hilo;
	private Runnable runear;
	/**
	 * Constructor de la clase "Controller"
	 */
	public Controller() {

		vp = new VentanaPrincipal();
		datos = "";
		hilo = new HiloCliente("192.168.0.40", 9300);
		runear = new Runnable() {
			@Override
			public void run() {
				hilo.run();
			}
		};
		iniciar();
	}

	/**
	 * Metodo que asigna los ActionCommand y los ActionListener de los botones de
	 * cada panel <b> pre </b> Los botones fueron previamente creados <br>
	 * <b> post </b> los ActionListener fueron correctamente asignados <br>
	 */
	public void iniciar() {
		vp.getAgregar().addActionListener(this);
		vp.getAgregar().setActionCommand("Agregar");

		vp.getMostrar_todo().addActionListener(this);
		vp.getMostrar_todo().setActionCommand("Mostrar");

		vp.getBuscar_por_cedula().addActionListener(this);
		vp.getBuscar_por_cedula().setActionCommand("Buscar");

		vp.setVisible(true);
	}

	/**
	 * Metodo que verifica si una cadena de caracteres ya sea el nombre o el cargo,
	 * cuenta con las condiciones para ser agregada, de lo contrario lanzará una
	 * excepción <b> pre </b> Se creó previamente su respectiva excepción <br>
	 * <b> post </b> Se revisó la cadena ingresada y se verificó si cumple con las
	 * condiciones establecidas. <br>
	 * 
	 * @param cadena de tipo String referente a lo que el usuario digite en pantalla
	 * @return La cadena ingresado por el usuario ya procesado
	 * @throws CadenasException En caso de que la cadena no cumpla con las
	 *                          condiciones
	 */
	public String cadenas(String cadena) throws CadenasException {

		String cadenaCorrecta = "";
		if (cadena == null) {
			return cadena;
		} else {
			if (!cadena.isBlank()) {
				for (int i = 0; i < cadena.length(); i++) {
					if (Character.isDigit(cadena.charAt(i))) {
						throw new CadenasException("El campo no puede contener numeros.");
					} else {
						cadenaCorrecta = cadena;
					}
				}
			} else {
				throw new CadenasException("El campo no puede quedar en blanco");
			}
		}

		return cadenaCorrecta;
	}

	/**
	 * Metodo que verifica si una cadena de caracteres ya sea la cedula o la edad,
	 * cuenta con las condiciones para ser agregada, de lo contrario lanzará una
	 * excepción <b> pre </b> Se creó previamente su respectiva excepción <br>
	 * <b> post </b> Se revisó la cadena ingresada y se verificó si cumple con las
	 * condiciones establecidas. <br>
	 * 
	 * @param cadena de tipo String referente a lo que el usuario digite en pantalla
	 * @return La cadena ingresado por el usuario ya procesado
	 * @throws CedulaException En caso de que la cadena no cumpla con las
	 *                         condiciones
	 */
	public String cedula(String cadena) throws CedulaException {

		String cedulaCorrecta = "";
		if (cadena == null) {
			return cadena;
		} else {
			if (!cadena.isBlank()) {
				for (int i = 0; i < cadena.length(); i++) {
					if (!(Character.isDigit(cadena.charAt(i)))) {
						throw new CedulaException("El campo no puede contener letras.");
					} else {
						cedulaCorrecta = cadena;
					}
				}
			} else {
				throw new CedulaException("El campo no puede quedar vacio");
			}
		}
		return cedulaCorrecta;
	}

	/**
	 * Metodo que leerá los comandos de los botones para darles acciones a los
	 * mismos <b> pre </b> Se asignó los actionlistener de cada boton <br>
	 * <b> post </b> Se ejecutó un algoritmo luego de haber sido accionado un boton
	 * <br>
	 * 
	 * @param e de tipo ActionCommand referente a los comando asignados a cada boton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {

		case "Agregar":

			int cancelar = 0;
			String nombre = "";
			String cedula = "";
			String edad = "";
			String apellido = "";
			String cargo = "";
			boolean edadIncorrecta = true;
			boolean nombreIncorrecto = true;
			boolean cedulaIncorrecta = true;
			boolean cargoIncorrecto = true;

			while (nombreIncorrecto) {
				try {
					nombre = vp.leerDato("Ingrese su nombre y apellido", "NUEVO CANDIDATO");

					if (cadenas(nombre) == null) {
						cancelar++;
						nombreIncorrecto = false;
					} else if (!cadenas(nombre).isEmpty()) {
						nombreIncorrecto = false;
						for (int i = 0; i < cadenas(nombre).length(); i++) {
							if (cadenas(nombre).charAt(i) == ' ') {
								nombre = cadenas(nombre).substring(0, i);
							}
							for (int j = cadenas(nombre).length() - 1; j >= 0; j--) {
								if (cadenas(nombre).charAt(j) == ' ') {
									apellido = cadenas(nombre).substring(j + 1, cadenas(nombre).length());
								}
							}
						}
					}
				} catch (CadenasException exce) {
					vp.mostrarInfo(exce.getMessage(), "ERROR");
				}
			}

			while (cedulaIncorrecta) {

				try {
					cedula = vp.leerDato("Ingrese su cedula", "NUEVO CANDIDATO");

					if (cedula(cedula) == null) {
						cancelar++;
						cedulaIncorrecta = false;
					} else if (!cedula(cedula).isEmpty()) {
						cedulaIncorrecta = false;
					}
				} catch (CedulaException exce) {
					vp.mostrarInfo(exce.getMessage(), "ERROR");
				}
			}

			while (edadIncorrecta) {

				try {
					edad = vp.leerDato("Ingrese su edad", "NUEVO CANDIDATO");
					if (cedula(edad) == null) {
						cancelar++;
						edadIncorrecta = false;
					} else if (cedula(edad).length() > 2) {
						vp.mostrarInfo("La edad debe encontrarse en un rango menor a 100", "ERROR");
					} else if (!cedula(edad).isEmpty()) {
						edadIncorrecta = false;
					}
				} catch (CedulaException exce) {
					vp.mostrarInfo(exce.getMessage(), "ERROR");
				}
			}

			while (cargoIncorrecto) {

				try {
					cargo = vp.leerDato("Ingrese su profesion", "NUEVO CANDIDATO");
					if (cadenas(cargo) == null) {
						cancelar++;
						cargoIncorrecto = false;
					} else if (!cadenas(cargo).isEmpty()) {
						cargoIncorrecto = false;
					}
				} catch (CadenasException exce) {
					vp.mostrarInfo(exce.getMessage(), "ERROR");
				}
			}

			if (cancelar == 0) {
				datos = nombre + " " + apellido + " "+ cedula + " " + cargo + " " + edad +" " + "agregar";
				hilo.setDatosEnviados(datos);
				runear.run();
				if(hilo.getDatosRecibidos().equals("Correcto")) {
					vp.mostrarInfo("Se agrego correctamente", "AGREGADO");
				}
			} else {
				vp.mostrarInfo("No se agrego porque cancelo algun campo", "TODO MAL");

			}
			break;

		case "Buscar":

			String cedulaBuscar = vp.leerDato("Ingrese la cedula del candidato a buscar", "BUSCAR CANDIDATO");
			int seleccion = -1;
			String opcion = "";
			if (cedulaBuscar != null) {
				hilo.setDatosEnviados(cedulaBuscar + " buscar");
				runear.run();
				if (cedulaBuscar.isEmpty()) {
					vp.mostrarInfo("No se encuentra un usuario sin cedula", "NO HAY CEDULA");
				} else if (hilo.getDatosRecibidos().equals("No se encontro el candidato")) {
					vp.mostrarInfo("No se encontro usuario con cedula " + cedulaBuscar, "LO SIENTO");
				} else {
					String[] metodos = { "Modificar", "Eliminar" };
					seleccion = JOptionPane.showOptionDialog(null,
							"Esta es la informacion del usuario con cedula " + cedulaBuscar + "\n \n"
									+ hilo.getDatosRecibidos() + "\n\n"
									+ "Desea eliminar o modificar la informacion",
							"INFORMACION CANDIDATO", JOptionPane.YES_NO_CANCEL_OPTION, 1, null, metodos, null);
				}
			}
			if (seleccion == 0) {
				String[] datos = { "Nombre", "Apellido", "Cedula", "Edad", "Cargo" };
				opcion = (String) JOptionPane.showInputDialog(null,
						"Seleccione el dato que desea \n modificiar del candidato de cedula " + cedulaBuscar,
						"DATOS PARA MODIFICAR", JOptionPane.DEFAULT_OPTION, null, datos, null);

				String nuevoDato = vp.leerDato("Ingrese un/una " + opcion + " nuevo/a", "MODIFICAR INFORMACION");
				if (nuevoDato != null) {
					if (opcion.equals("Cedula") || opcion.equals("Edad")) {
						while (true) {
							try {
								if (!cedula(nuevoDato).isEmpty()) {
									if(opcion.equals("Cedula")) {
										hilo.setDatosEnviados(nuevoDato + "3 actualizar");
										runear.run();
									}else if(opcion.equals("Edad")) {
										hilo.setDatosEnviados(nuevoDato + "5 actualizar");
										runear.run();
									}
									if (hilo.getDatosRecibidos().equals("Correcto")) {
										vp.mostrarInfo("Se actualizo correctamente los datos", "ACTUALIZADO");
									}
									break;
								}
							} catch (CedulaException exce) {
								vp.mostrarInfo(exce.getMessage(), "ERROR");
							}
						}

					} else if (opcion.equals("Nombre") || opcion.equals("Apellido") || opcion.equals("Cargo")) {

						while (true) {
							try {
								if (!cadenas(nuevoDato).isEmpty()) {
									if(opcion.equals("Nombre")) {
										hilo.setDatosEnviados(nuevoDato + "1 actualizar");
										runear.run();
									}else if(opcion.equals("Apellido")) {
										hilo.setDatosEnviados(nuevoDato + "2 actualizar");
										runear.run();
									}else if(opcion.equals("Cargo")) {
										hilo.setDatosEnviados(nuevoDato + "4 actualizar");
										runear.run();
									}
									if (hilo.getDatosRecibidos().equals("Correcto")) {
										vp.mostrarInfo("Se actualizo correctamente los datos", "ACTUALIZADO");
									}
									break;
								}
							} catch (CadenasException exce) {
								vp.mostrarInfo(exce.getMessage(), "ERROR");
							}
						}
					}
				}
			} else if (seleccion == 1) {
				hilo.setDatosEnviados(cedulaBuscar + " eliminar");
				runear.run();
				if (hilo.getDatosRecibidos().equals("Correcto")) {
					vp.mostrarInfo("Candidato eliminado con exito!", "CANDIDATO ELIMINADO");
				}
			}
			break;

		case "Mostrar":
			hilo.setDatosEnviados(" mostrar");
			runear.run();
			ArrayList<String> listaString = new ArrayList<String>();
			if(hilo.getDatosRecibidos()!= null) {
				int inicial = 0;
				for(int i = 0; i < hilo.getDatosRecibidos().length(); i++) {
					if(hilo.getDatosRecibidos().charAt(i) == '\n') {
						String m = hilo.getDatosRecibidos().substring(inicial, i);
						hilo.setDatosRecibidos(hilo.getDatosRecibidos().substring(i+1, hilo.getDatosRecibidos().length()-1));
						i = inicial;
						listaString.add(m);
					} else if(i == hilo.getDatosRecibidos().length()-1) {
						String m = hilo.getDatosRecibidos().substring(inicial, hilo.getDatosRecibidos().length());
						listaString.add(m);
					}
				}
				
				pr = new PersonasRegistradas(listaString);
				pr.setVisible(true);
			}
		}
		

	}

	public String getDatos() {
		return datos; 
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}
	

}
