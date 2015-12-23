package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sk.genhis.evolution.Evolution;

public final class BtnMoreListener implements ActionListener, Runnable {
	private boolean active = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		String s = null;
		if((s = JOptionPane.showInputDialog("Zadajte poèet generácií")) != null) {
			this.active = true;
			Evolution.get().btnMore.setText("Generuje sa ...");
			Evolution.get().btn_setEnabled(false);
			
			int to = Integer.parseInt(s);
			if(Evolution.get().btnBestL.isActive())
				for(int i = 0; i < to; i++) {
					if(!Evolution.get().pop.nextGeneration())
						break;
					Evolution.get().btnBestL.select();
				}
			else
				for(int i = 0; i < to; i++)
					if(!Evolution.get().pop.nextGeneration())
						break;
			
			Evolution.get().dPop.repaintContent();
			
			this.active = false;
			Evolution.get().btnMore.setText("Nieko¾ko generácií");
			Evolution.get().toggleBtn();
		}
	}
	
	public boolean isActive() {
		return this.active;
	}
}
