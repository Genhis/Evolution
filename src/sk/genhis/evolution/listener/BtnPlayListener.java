package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sk.genhis.evolution.Evolution;

public final class BtnPlayListener implements ActionListener {
	private boolean active = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.toggle();
	}
	
	public void toggle() {
		if(this.active = !this.active) {
			Evolution.get().btnPlay.setText("STOP");
			Evolution.get().rec.play();
		}
		else
			Evolution.get().btnPlay.setText("PLAY");
	}
	
	public boolean isActive() {
		return this.active;
	}
}
