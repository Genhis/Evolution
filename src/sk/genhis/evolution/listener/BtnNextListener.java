package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;

public final class BtnNextListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Evolution.get().pop.nextGeneration();
		if(Evolution.get().btnBestL.isActive())
			Evolution.get().btnBestL.select();
		Evolution.get().dPop.repaintContent();
	}
}
