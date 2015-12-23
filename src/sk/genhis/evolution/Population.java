package sk.genhis.evolution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import sk.genhis.evolution.util.AbilityComparator;

public final class Population implements Serializable {
	private static final long serialVersionUID = 0L;
	
	public int num = 0;
	
	public final LinkedHashMap<Integer, Organism> org = new LinkedHashMap<Integer, Organism>();
	
	public final ArrayList<Organism> man = new ArrayList<Organism>();
	public final ArrayList<Organism> woman = new ArrayList<Organism>();
	
	private boolean msg = false;
	
	public Population() {
		this(20);
	}
	
	public Population(int startOrganism) {
		for(int i = 0; i < startOrganism; i++)
			this.addOrganism(new Organism());
		
		if(Evolution.get().btnRecordL.isActive())
			Evolution.get().rec.add(this);
	}
	
	public Population(Population p) {
		this.num = p.num;
		this.org.putAll(p.org);
		this.msg = p.msg;
	}
	
	public boolean nextGeneration() {
		this.num++;
		
		for(Organism o : this.org.values())
			if(o.man)
				this.man.add(o);
			else
				this.woman.add(o);
		
		if((this.man.size() == 0 || this.woman.size() == 0) && !this.msg) {
			JOptionPane.showMessageDialog(null, "Tvoja populácia sa blíži k vyhynutiu!");
			this.msg = true;
		}
		
		while(this.man.size() > 0 && this.woman.size() > 0) {
			Organism a = null, b = null;
			
			a = this.man.get(0);
			b = this.woman.get(0);
			
			this.man.remove(a);
			this.man.remove(b);
			
			for(int i = 0; i < Math.min(a.reproduction, b.reproduction); i++) {
				Organism n = new Organism(a, b);
				this.org.put(n.id, n);
			}
			
			a.age++;
			if(a.die())
				this.org.remove(a.id);
			
			b.age++;
			if(b.die())
				this.org.remove(b.id);
		}
		
		while(this.man.size() > 0) {
			Organism a = this.man.get(0);
			this.man.remove(a);
			a.age++;
			if(a.die())
				this.org.remove(a.id);
		}
		while(this.woman.size() > 0) {
			Organism b = this.woman.get(0);
			this.woman.remove(b);
			b.age++;
			if(b.die())
				this.org.remove(b.id);
		}
		
		if(!Evolution.get().btnBestL.isActive() && Evolution.get().btnRecordL.isActive())
			Evolution.get().rec.add(this);
		
		if(this.org.size() == 0) {
			JOptionPane.showMessageDialog(null, "Tvoja populácia vyhynula!");
			Evolution.get().btnRecordL.toggle();
			Evolution.get().toggleBtn();
			return false;
		}
		return true;
	}
	
	public void selectTheBest(int count) {
		if(count < this.org.size()) {
			TreeMap<Integer, Organism> map = new TreeMap<Integer, Organism>();
			LinkedList<Organism> list = new LinkedList<Organism>(this.org.values());
			Collections.sort(list, new AbilityComparator(Evolution.get().slcDisplay.getSelectedIndex()));
			
			int i = 0;
			
			for(Organism o : list) {
				if(++i > count)
					break;
				map.put(o.id, o);
			}
			
			this.org.clear();
			this.org.putAll(map);
		}
		
		if(Evolution.get().btnBestL.isActive() && Evolution.get().btnRecordL.isActive())
			Evolution.get().rec.add(this);
	}
	
	private void addOrganism(Organism o) {
		this.org.put(o.id, o);
	}
}
