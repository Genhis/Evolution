package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;

public final class BtnInstantListener implements ActionListener, Runnable {
	private boolean started = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.started = !this.started)
			new Thread(this).start();
	}
	
	@Override
	public void run() {
		Evolution.get().toggleBtn();
		Evolution.get().btnInstant.setText("Zastaviť generovanie");
		
		while(this.started) {
			if(!Evolution.get().pop.nextGeneration())
				break;
			if(Evolution.get().btnBestL.isActive())
				Evolution.get().btnBestL.select();
			Evolution.get().dPop.repaintContent();
			
			try {
				Thread.sleep(Evolution.get().btnPSpeedL.getSpeed());
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		Evolution.get().btnInstant.setText("Do nekonečna");
		Evolution.get().toggleBtn();
	}
}
