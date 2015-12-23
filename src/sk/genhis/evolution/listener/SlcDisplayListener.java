package sk.genhis.evolution.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import sk.genhis.evolution.Evolution;

public final class SlcDisplayListener implements ItemListener {
	@Override
	public void itemStateChanged(ItemEvent e) {
		Evolution.get().dPop.changeOutput(Evolution.get().slcDisplay.getSelectedIndex());
	}
}
