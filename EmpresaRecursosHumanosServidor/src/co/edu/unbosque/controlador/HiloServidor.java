package co.edu.unbosque.controlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.unbosque.modelo.CandidatoDTO;
import co.edu.unbosque.modelo.persistence.Archivo;
import co.edu.unbosque.modelo.persistence.CandidatoDAO;

public class HiloServidor extends Thread {

	private ServerSocket servidor;
	private Socket socket_enviar;
	private Socket socket_recibir;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private int puerto;
	private String metodo;
	private String mensajeRecibido;
	private String mensajeEnviado;
	private CandidatoDTO candidato_dto;
	private CandidatoDAO candidato_dao;
	private File file;
	private Archivo archivo;
	private ArrayList<CandidatoDTO> candidatos;

	public HiloServidor(int puerto) {
		socket_enviar = null;
		socket_recibir = null;
		entrada = null;
		salida = null;
		servidor = null;
		mensajeEnviado = "";
		this.puerto = puerto;
		metodo = "";
		file = new File("datos/datos.dat");
		archivo = new Archivo(file);
		candidato_dao = new CandidatoDAO(archivo);
		candidatos = archivo.leerArchivo(file);
		mensajeRecibido = "";
		candidato_dto = new CandidatoDTO(null, null, null, null, null);
	}

	@Override
	public void run() {

		String datos = "";

		while (!(datos.equals("Terminar"))) {

			try {
				servidor = new ServerSocket(puerto);
				System.out.println("Servidor disponible...");
				socket_recibir = servidor.accept();
				System.out.println("Objeto recibido");
				entrada = new DataInputStream(new BufferedInputStream(socket_recibir.getInputStream()));
				datos = entrada.readUTF();
				System.out.println(datos);
				entrada.close();
				socket_recibir.close();
				servidor.close();
				if (!datos.isBlank()) {
					for (int i = datos.length() - 1; i >= 0; i--) {
						if (datos.charAt(i) == ' ') {
							metodo = datos.substring(i + 1, datos.length());
							mensajeRecibido = datos.substring(0, i);
							break;
						}
					}
					System.out.println(getMetodo() + " metodo");
					if (!(metodo.isBlank())) {

						if (metodo.equals("agregar")) {
							candidato_dto = agregar(mensajeRecibido);
							if (funcionar(metodo).size() == 1) {
								mensajeEnviado = funcionar(metodo).get(0);
								mandarInfo(mensajeEnviado);
							}
						} else if (metodo.equals("buscar")) {
							candidato_dto.setCedula(mensajeRecibido);
							if (funcionar(metodo).size() == 1) {
								mensajeEnviado = funcionar(metodo).get(0);
								mandarInfo(mensajeEnviado);
							}
						}else if (metodo.equals("actualizar")) {
							if (funcionar(metodo).size() == 1) {
								mensajeEnviado = funcionar(metodo).get(0);
								mandarInfo(mensajeEnviado);
							}
						}else if (metodo.equals("eliminar")) {
							if (funcionar(metodo).size() == 1) {
								mensajeEnviado = funcionar(metodo).get(0);
								mandarInfo(mensajeEnviado);
							}
						}else if (metodo.equals("mostrar")) {
							if (funcionar(metodo).size() == 1) {
								mensajeEnviado = funcionar(metodo).get(0);
								mandarInfo(mensajeEnviado);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public CandidatoDTO agregar(String atributos) {

		int espacios = 0;
		int pos_anterior = 0;
		String nombre = "";
		String apellido = "";
		String cedula = "";
		String edad = "";
		String cargo = "";
		for (int i = 0; i < atributos.length(); i++) {
			if (atributos.charAt(i) == ' ') {
				espacios++;
				if (espacios == 1) {
					nombre = atributos.substring(0, i);
					pos_anterior = i;
				} else if (espacios == 2) {
					apellido = atributos.substring(pos_anterior + 1, i);
					pos_anterior = i;

				} else if (espacios == 3) {
					cedula = atributos.substring(pos_anterior + 1, i);
					pos_anterior = i;

				} else if (espacios == 4) {
					cargo = atributos.substring(pos_anterior + 1, i);
					edad = atributos.substring(i + 1, atributos.length());
				}
			}
		}
		CandidatoDTO candiNuevo = new CandidatoDTO(nombre, apellido, cedula, cargo, edad);
		return candiNuevo;
	}

	public void mandarInfo(String mensaje) {

		try {
			socket_enviar = new Socket(servidor.getInetAddress(), puerto +1);
			salida = new DataOutputStream(new BufferedOutputStream(socket_enviar.getOutputStream()));
			salida.writeUTF(mensaje);
			salida.close();
			socket_enviar.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<String> funcionar(String metodo) {

		ArrayList<String> mensaje = new ArrayList<String>();
		if (!metodo.isBlank()) {
			if (metodo.equals("agregar")) {

//				candidato_dto = getCandidato_dto();
				if (candidato_dao.crear(candidato_dto.getNombre(), candidato_dto.getApellido(),
						candidato_dto.getCedula(), candidato_dto.getCargo(), candidato_dto.getEdad(), candidatos,
						file)) {
					mensaje.add(0, "Correcto");
				} else {
					mensaje.add(0, "Incorrecto");
				}
			} else if (metodo.equals("buscar")) {
				ArrayList<CandidatoDTO> candidato = candidato_dao.buscar(getCandidato_dto().getCedula(), candidatos);
				if (candidato != null) {
					if (candidato.size() > 1 || candidato.size() == 0) {
						mensaje.add(0, "No se encontro el candidato");
					} else {
						mensaje.add(0, candidato.get(0).toString());
						candidato_dto = candidato.get(0);
					}
				}
			} else if (metodo.equals("actualizar") && candidato_dto != null) {
				if (candidato_dao.actualizar(mensajeRecibido.substring(0, mensajeRecibido.length() - 1),
						candidato_dto, file,
						String.valueOf(mensajeRecibido.charAt(mensajeRecibido.length() - 1)), candidatos)) {
					mensaje.add(0, "Correcto");
				} else {
					mensaje.add(0, "Incorrecto");
				}
			} else if (metodo.equals("eliminar")) {
				if (candidato_dao.eliminar(getMensajeRecibido(), candidatos, file)) {
					mensaje.add(0, "Correcto");
				} else {
					mensaje.add(0, "Incorrecto");
				}
			} else if (metodo.equals("mostrar")) {
				String datos = "";
				for (int i = 0; i < candidatos.size(); i++) {
					datos = datos + "Nombre: " + candidatos.get(i).getNombre() + ",   Cédula: " + candidatos.get(i).getCedula()
							+ ",   Cargo: " + candidatos.get(i).getCargo() + ",   Edad: " + candidatos.get(i).getEdad() + "\n";
				}
				mensaje.add(0,datos);
			}
		}
		return mensaje;
	}

	public CandidatoDTO getCandidato_dto() {
		return candidato_dto;
	}

	public void setCandidato_dto(CandidatoDTO candidato_dto) {
		this.candidato_dto = candidato_dto;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getMensajeRecibido() {
		return mensajeRecibido;
	}

	public void setMensajeRecibido(String mensaje) {
		this.mensajeRecibido = mensaje;
	}

	public String getMensajeEnviado() {
		return mensajeEnviado;
	}

	public void setMensajeEnviado(String mensajeEnviado) {
		this.mensajeEnviado = mensajeEnviado;
	}

}
