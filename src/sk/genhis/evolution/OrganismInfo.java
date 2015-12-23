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
		s += "<br>Pohlavie: " + (o.man ? "Muû" : "éena");
		s += "<br>Vek: " + o.age;
		s += "<br>Veækosù: " + o.size;
		s += "<br>R˝chlosù: " + o.speed;
		s += "<br>Sila: " + o.strength;
		s += "<br>Odolnosù: " + o.resistance;
		s += "<br>Rozmnoûovanie: " + o.reproduction;
		s += "<br>äanca sa mut·ciu: " + o.mutationChance;
		s += "<hr><br>SkÛre: " + o.getScore();
		this.lblInfo.setText("<html>" + s + "</html>");
		this.setVisible(true);
	}
}
