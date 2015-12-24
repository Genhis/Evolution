package sk.genhis.evolution;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public final class OrganismInfo extends JFrame {
	private static final long serialVersionUID = 0L;
	
	public JLabel lblInfo = new JLabel();
	
	public OrganismInfo() {
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(230, 230);
		this.setLayout(new FlowLayout());
		
		//this.lblInfo.setSize(200, 220);
		this.getContentPane().add(this.lblInfo);
	}
	
	public void showInfo(Organism o) {
		this.setTitle("Organizmus (ID " + o.id + ")");
		String s = "ID: " + o.id;
		s += "<br>Pohlavie: " + (o.man ? "Mu�" : "�ena");
		s += "<br>Vek: " + o.age;
		s += "<br>Ve�kos�: " + o.size;
		s += "<br>R�chlos�: " + o.speed;
		s += "<br>Sila: " + o.strength;
		s += "<br>Odolnos�: " + o.resistance;
		s += "<br>Rozmno�ovanie: " + o.reproduction;
		s += "<br>�anca sa mut�ciu: " + o.mutationChance;
		s += "<hr><br>Sk�re: " + o.getScore();
		this.lblInfo.setText("<html>" + s + "</html>");
		this.setVisible(true);
	}
}
