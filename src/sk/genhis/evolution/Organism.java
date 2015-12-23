package sk.genhis.evolution;

import java.io.Serializable;
import java.util.Random;

public final class Organism implements Serializable {
	private static final long serialVersionUID = 0L;
	
	public static int lastid = 0;
	
	public final Random r = new Random();
	public final int id;
	public int age = 0;
	
	public int mutationChance;
	
	public int size;
	public int speed;
	public int strength;
	public int resistance;
	public int reproduction;
	
	public boolean man;
	
	public Organism() {
		this.id = ++Organism.lastid;
		this.man = this.r.nextBoolean();
		
		this.mutationChance = this.r.nextInt(10) + 1;
		
		this.size = this.r.nextInt(2);
		this.speed = this.r.nextInt(2);
		this.strength = this.r.nextInt(2);
		this.resistance = this.r.nextInt(4) + 1;
		this.reproduction = this.r.nextInt(2) + 1;
	}
	
	public Organism(Organism a, Organism b) {
		this.id = ++Organism.lastid;
		this.man = this.r.nextBoolean();
		
		this.mutationChance = this.updateAbility(a.mutationChance, b.mutationChance);
		
		this.size = this.updateAbility(a.size, b.size);
		this.speed = this.updateAbility(a.speed, b.speed);
		this.strength = this.updateAbility(a.strength, b.strength);
		this.resistance = this.updateAbility(a.resistance, b.resistance);
		this.reproduction = this.updateAbility(a.reproduction, b.reproduction);
		
		this.checkVariables();
		
		int rand = this.r.nextInt(100 / this.mutationChance + 1);
		if(rand == 0 || rand == 1)
			this.mutate();
		else
			this.mutationChance += this.r.nextInt(15) - 5;
		
		this.checkVariables();
	}
	
	public boolean die() {
		int rand = this.r.nextInt(this.resistance);
		if(rand == 0 || rand == 1)
			return true;
		return false;
	}
	
	public void mutate() {
		int rand = this.r.nextInt(4) - 1;
		switch(this.r.nextInt(5)) {
			case 0:
				this.size += rand;
				break;
			case 1:
				this.speed += rand;
				break;
			case 2:
				this.strength += rand;
				break;
			case 3:
				this.resistance += rand;
				break;
			case 4:
				this.reproduction += rand;
				break;
		}
		
		this.mutationChance -= this.mutationChance / 2;
	}
	
	private int updateAbility(int a, int b) {
		return this.updateAbility(a, b, a > b);
	}
	
	private int updateAbility(int a, int b, boolean comp) {
		int rand = this.r.nextInt(3);
		switch(rand) {
			case 0:
				return a;
			case 1:
				return comp ? a : b;
			case 2:
				return b;
		}
		return 0;
	}
	
	private void checkVariables() {
		this.mutationChance = this.mutationChance <= 0 ? 1 : this.mutationChance;
		this.size = this.size < 0 ? 0 : this.size;
		this.speed = this.speed < 0 ? 0 : this.speed;
		this.strength = this.strength < 0 ? 0 : this.strength;
		this.resistance = this.resistance <= 0 ? 1 : this.resistance;
		this.reproduction = this.reproduction <= 0 ? 1 : this.reproduction;
	}
	
	public int getAbilityById(int id) {
		switch(id) {
			case 0:
				return this.man ? 1 : 0;
			case 1:
				return this.size;
			case 2:
				return this.speed;
			case 3:
				return this.strength;
			case 4:
				return this.resistance;
			case 5:
				return this.reproduction;
			case 6:
				return this.mutationChance;
			case 7:
				return this.getScore();
		}
		return -1;
	}
	
	public int getScore() {
		return this.size + this.speed + this.strength + this.resistance + this.reproduction;
	}
}
