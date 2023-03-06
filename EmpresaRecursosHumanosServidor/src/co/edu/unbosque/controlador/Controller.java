package co.edu.unbosque.controlador;

import java.io.File;

import java.util.ArrayList;

import co.edu.unbosque.modelo.CandidatoDTO;
import co.edu.unbosque.modelo.persistence.Archivo;
import co.edu.unbosque.modelo.persistence.CandidatoDAO;

public class Controller {

	private HiloServidor hiloServer;

	public Controller() {

		HiloServidor hiloServer = new HiloServidor(9300);
		hiloServer.start();
	}

}
