package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;

public final class BtnPStartListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Evolution.get().rec.playStart();
	}
}
