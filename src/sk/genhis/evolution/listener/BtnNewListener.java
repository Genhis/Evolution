package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;
import sk.genhis.evolution.Population;

public final class BtnNewListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Evolution.get().pop = new Population();
		Evolution.get().dPop.repaintContent();
		Evolution.get().toggleBtn();
	}
}
