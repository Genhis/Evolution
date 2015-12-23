package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public final class BtnPSpeedListener implements ActionListener {
	private int speed = 100;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s;
		if((s = JOptionPane.showInputDialog("Zadajte r˝chlosù prehr·vania v milisekund·ch/gener·cia")) != null)
			this.speed = Integer.parseInt(s);
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
