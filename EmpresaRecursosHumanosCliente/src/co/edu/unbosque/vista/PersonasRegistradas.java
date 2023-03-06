package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class PersonasRegistradas extends JFrame {



	private ArrayList<JLabel>registrop;
	
	public ArrayList<JLabel> getRegistrop() {
	return registrop;
	}
	
	
	
	public void setRegistrop(ArrayList<JLabel> registrop) {
	this.registrop = registrop;
	}
	
	
	
	public PersonasRegistradas(ArrayList<String> registro) {
	
		registrop = new ArrayList<>();
		for(int i= 0; i< registro.size(); i++) {
		registrop.add(new JLabel(registro.get(i)));
		}
	
	
		setSize(660, 450);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("Personas Registradas");
		JLabel fondo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/floresRRH.jpg")).getImage().getScaledInstance(590, 450, Image.SCALE_SMOOTH)));
		setContentPane(fondo);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(550,800));
		setVisible(false);
		
		JScrollBar scroll = new JScrollBar();
		scroll.setBounds(625,0,20,415);
		
		Font fuente = new Font("Bookman Old Style", 3, 25);
		Font fuente2= new Font("Bradley Hand ITC", 1, 15);
		
		JLabel titulo = new JLabel("Personas Registradas");
		titulo.setBounds(190, 67, 300, 40);
		titulo.setFont(fuente);
		titulo.setVisible(true);
		
		int alto = 115;
		
		for (JLabel jlabel : registrop) {
		jlabel.setBounds(65, alto, 500, 25);
		jlabel.setFont(fuente2);
		jlabel.setVisible(true);
		alto += 25;
		add(jlabel);
		}
		
		
		
		
		add(scroll);
		add(titulo);
		
	
	


}

}