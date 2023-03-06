package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame{
	
	private JFrame ventana1;
	private JLabel titulo;
	private JLabel buscar;
	private JLabel nombre;
	private JLabel apellido;
	private JLabel cedula;
	private JLabel edad;
	private JTextField nombre1;
	private JTextField apellido1;
	private JTextField cedula1;
	private JTextField edad1;
	private JButton agregar;
	private JButton mostrar_todo;
	private JButton buscar_por_cedula;
	private JComboBox<String> candidatos;

	 
	public VentanaPrincipal() {
		init();
		
	}
	
	public void init() {
		setSize(550,510);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("Recursos Humanos");
		JLabel fondo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/flores2RRH.jpg")).getImage().getScaledInstance(570, 546, Image.SCALE_SMOOTH)));
	    setContentPane(fondo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		titulo= new JLabel("Empresa");
		titulo.setBounds(160,-137,400,400);
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Bookman Old Style", Font.ITALIC, 48));
		
		JLabel titulo2= new JLabel("Recursos Humanos");
		titulo2.setBounds(130,-97,400,400);
		titulo2.setForeground(Color.black);
		titulo2.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 36));
		
		Font fuente = new Font("Bookman Old Style", Font.ITALIC, 20);
		Font fuente2= new Font("Bradley Hand ITC", 1, 15);
		
		JLabel info = new JLabel("En la Empresa RRH buscamos talento humano como el tuyo");
		info.setBounds(75, 170, 420, 20);
		info.setForeground(Color.black);
		info.setFont(fuente2);
		JLabel info2 = new JLabel("Porfavor indique la opcion que necesite");
		info2.setBounds(130, 190, 420, 20);
		info2.setForeground(Color.black);
		info2.setFont(fuente2);
		
		
		
		agregar= new JButton("Agregar");
		agregar.setBounds(70, 250, 150, 50);
		agregar.setFocusable(false);
		agregar.setBackground(Color.gray);
		agregar.setForeground(Color.white);
		agregar.setFont(fuente);
		
		mostrar_todo= new JButton("Mostrar todo");
		mostrar_todo.setBounds(150, 330, 220, 50);
		mostrar_todo.setFocusable(false);
		mostrar_todo.setBackground(Color.gray);
		mostrar_todo.setForeground(Color.white);
		mostrar_todo.setFont(fuente);
		
		
		buscar_por_cedula= new JButton("Buscar");
		buscar_por_cedula.setBounds(307, 250, 150, 50);
		buscar_por_cedula.setFocusable(false);
		buscar_por_cedula.setBackground(Color.gray);
		buscar_por_cedula.setForeground(Color.white);
		buscar_por_cedula.setFont(fuente);
	
		add(titulo);
		add(titulo2);
		add(info);
		add(info2);
		add(mostrar_todo);
		add(agregar);
		add(buscar_por_cedula);
	}
	
	public String leerDato(String mensaje, String titulo) {
		String datos= JOptionPane.showInputDialog(null,mensaje, titulo, 3);
		return datos;
	}
	
	public void mostrarInfo(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, 1);
	}

	public JFrame getVentana1() {
		return ventana1;
	}

	public void setVentana1(JFrame ventana1) {
		this.ventana1 = ventana1;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}

	public JLabel getBuscar() {
		return buscar;
	}

	public void setBuscar(JLabel buscar) {
		this.buscar = buscar;
	}

	public JLabel getNombre() {
		return nombre;
	}

	public void setNombre(JLabel nombre) {
		this.nombre = nombre;
	}

	public JLabel getApellido() {
		return apellido;
	}

	public void setApellido(JLabel apellido) {
		this.apellido = apellido;
	}

	public JLabel getCedula() {
		return cedula;
	}

	public void setCedula(JLabel cedula) {
		this.cedula = cedula;
	}

	public JLabel getEdad() {
		return edad;
	}

	public void setEdad(JLabel edad) {
		this.edad = edad;
	}

	public JTextField getNombre1() {
		return nombre1;
	}

	public void setNombre1(JTextField nombre1) {
		this.nombre1 = nombre1;
	}

	public JTextField getApellido1() {
		return apellido1;
	}

	public void setApellido1(JTextField apellido1) {
		this.apellido1 = apellido1;
	}

	public JTextField getCedula1() {
		return cedula1;
	}

	public void setCedula1(JTextField cedula1) {
		this.cedula1 = cedula1;
	}

	public JTextField getEdad1() {
		return edad1;
	}

	public void setEdad1(JTextField edad1) {
		this.edad1 = edad1;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public void setAgregar(JButton agregar) {
		this.agregar = agregar;
	}

	public JButton getMostrar_todo() {
		return mostrar_todo;
	}

	public void setMostrar_todo(JButton mostrar_todo) {
		this.mostrar_todo = mostrar_todo;
	}

	public JButton getBuscar_por_cedula() {
		return buscar_por_cedula;
	}

	public void setBuscar_por_cedula(JButton buscar_por_cedula) {
		this.buscar_por_cedula = buscar_por_cedula;
	}

	public JComboBox<String> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(JComboBox<String> candidatos) {
		this.candidatos = candidatos;
	}
	
	

}
