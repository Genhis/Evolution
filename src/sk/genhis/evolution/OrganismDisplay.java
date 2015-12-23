package sk.genhis.evolution;

import javax.swing.JPanel;

public class OrganismDisplay extends JPanel {
	private static final long serialVersionUID = 0L;
	
	private final Organism o;
	
	public OrganismDisplay(Organism o) {
		this.o = o;
	}
	
	public Organism getOrganism() {
		return this.o;
	}
}
