package sk.genhis.evolution.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sk.genhis.evolution.Evolution;
import sk.genhis.evolution.Organism;
import sk.genhis.evolution.OrganismDisplay;

public final class OrganismMouseListener implements MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {
		Evolution.showOrganismInfo(((OrganismDisplay)e.getComponent()).getOrganism());
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		Organism o = ((OrganismDisplay)e.getComponent()).getOrganism();
		Evolution.get().lblInfo.setText("ID: " + o.id + " | " + Evolution.get().slcDisplay.getSelectedItem() + ": " + o.getAbilityById(Evolution.get().slcDisplay.getSelectedIndex()) + " | Sk�re: " + o.getScore());
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		Evolution.get().lblInfo.setText("Prejdi myšou po organizme pre dodatočné informácie");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
}
