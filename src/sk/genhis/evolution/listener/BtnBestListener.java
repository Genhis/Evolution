package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sk.genhis.evolution.Evolution;

public final class BtnBestListener implements ActionListener {
	private int max;
	private boolean active = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.toggle();
	}
	
	public void toggle() {
		if(this.active) {
			this.max = 0;
			this.active = false;
			Evolution.get().btnBest.setText("Vybra� najlep��ch");
		}
		else {
			String s = null;
			if((s = JOptionPane.showInputDialog("Zadajte po�et jedincov")) != null) {
				this.max = Integer.parseInt(s);
				Evolution.get().btnBest.setText("Neh�ada� najlep��ch");
				
				this.select();
				this.active = true;
				Evolution.get().dPop.repaintContent();
			}
		}
	}
	
	public void reset() {
		if(this.active)
			this.toggle();
	}
	
	public void select() {
		Evolution.get().pop.selectTheBest(this.max);
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public int getMaxOrganism() {
		return this.max;
	}
}
