package co.edu.unbosque.controlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HiloCliente extends Thread{
	
	private Socket socket_enviar;
	private ServerSocket servidor;
	private Socket socket_recibir;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private int puerto;
	private String direccion;
	private String info;
	private String datosEnviados;
	private String datosRecibidos;
	private ArrayList<String> lista;
	
	
	public HiloCliente(String direccion, int puerto) {
		socket_enviar= null;
        socket_recibir=null;
        servidor = null;
        entrada= null;
        salida= null;
        datosRecibidos= "";
        datosEnviados = "";
        this.puerto= puerto;
        info = "";
        this.direccion=direccion;
	}
	
	@Override
		public void run() {
			
		System.out.println(getDatosEnviados()+ " holiwis ");
			while(!(getDatosEnviados().isBlank())) {
				info = getDatosEnviados();
				try {
					
					socket_enviar = new Socket(direccion, puerto);
					System.out.println("Conectado");
					salida = new DataOutputStream(new BufferedOutputStream(socket_enviar.getOutputStream()));
					System.out.println(info + " fueron enviados");
					salida.writeUTF(info);
					salida.close();
					socket_enviar.close();
	
					servidor = new ServerSocket(puerto+1);
					socket_recibir = servidor.accept();
					entrada = new DataInputStream(new BufferedInputStream(socket_recibir.getInputStream()));
					setDatosRecibidos(entrada.readUTF());
					System.out.println(datosRecibidos);
					entrada.close();
					socket_recibir.close();
					servidor.close();
					
					datosEnviados= "";
					
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
 }

	public String getDatosEnviados() {
		return datosEnviados;
	}

	public void setDatosEnviados(String datos) {
		this.datosEnviados = datos;
	}

	public String getDatosRecibidos() {
		return datosRecibidos;
	}

	public void setDatosRecibidos(String datosRecibidos) {
		this.datosRecibidos = datosRecibidos;
	}
	
}
