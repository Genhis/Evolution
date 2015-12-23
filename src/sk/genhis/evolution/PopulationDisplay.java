package sk.genhis.evolution;

import java.awt.Color;

import javax.swing.JPanel;

import sk.genhis.evolution.listener.OrganismMouseListener;

public final class PopulationDisplay extends JPanel implements Runnable {
	private static final long serialVersionUID = 0L;
	private Thread t = new Thread(this);
	
	private int index = 0;
	private Population pop;
	
	public void repaintContent() {
		this.repaintContent(Evolution.get().pop);
	}
	
	public void repaintContent(Population pop) {
		if(this.t.isAlive())
			return;
		if(pop != null)
			this.pop = new Population(pop);
		(this.t = new Thread(this)).start();
	}
	
	@Override
	public void run() {
		OrganismMouseListener l = new OrganismMouseListener();
		this.removeAll();
		for(Organism o : this.pop.org.values()) {
			OrganismDisplay p = new OrganismDisplay(o);
			
			switch(this.index) {
				case 0:
					p.setBackground(o.man ? Color.BLACK : Color.RED);
					break;
				case 1:
					p.setBackground(this.getColorByAbility(o.size));
					break;
				case 2:
					p.setBackground(this.getColorByAbility(o.speed));
					break;
				case 3:
					p.setBackground(this.getColorByAbility(o.strength));
					break;
				case 4:
					p.setBackground(this.getColorByAbility(o.resistance));
					break;
				case 5:
					p.setBackground(this.getColorByAbility(o.reproduction));
					break;
				case 6:
					p.setBackground(this.getColorByAbility(o.mutationChance));
					break;
				case 7:
					p.setBackground(this.getColorByAbility(o.getScore()));
					break;
			}
			p.addMouseListener(l);
			
			this.add(p);
		}
		this.revalidate();
		this.repaint();
		
		Evolution.get().lblGen.setText("Generácia " + this.pop.num);
	}
	
	public void changeOutput(int index) {
		this.index = index;
		this.repaintContent(null);
	}
	
	private Color getColorByAbility(int ability) {
		if(ability > 1500)
			return new Color(0x00, 0x2C, 0x99);
		if(ability > 900)
			return new Color(0x00, 0x49, 0xFF);
		if(ability > 700)
			return new Color(0x00, 0x5F, 0x99);
		if(ability > 500)
			return new Color(0x01, 0x9E, 0xFF);
		if(ability > 250)
			return new Color(0x00, 0x97, 0x99);
		if(ability > 150)
			return new Color(0x01, 0xFB, 0xFF);
		if(ability > 100)
			return new Color(0xC3, 0xFE, 0xFF);
		if(ability > 60)
			return new Color(0x0A, 0x77, 0x00);
		if(ability > 40)
			return new Color(0x06, 0xF9, 0x00);
		if(ability > 25)
			return new Color(0x6D, 0xFF, 0x6D);
		if(ability > 15)
			return new Color(0xFF, 0xC3, 0x64);
		if(ability > 10)
			return Color.ORANGE;
		if(ability > 6)
			return new Color(0xFF, 0x7F, 0x7F);
		if(ability > 3)
			return Color.RED;
		if(ability > 0)
			return new Color(0x9F, 0x00, 0x03);
		else
			return Color.BLACK;
	}
}
