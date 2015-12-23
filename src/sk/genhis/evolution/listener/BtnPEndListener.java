package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;

public final class BtnPEndListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Evolution.get().rec.playEnd();
	}
}
