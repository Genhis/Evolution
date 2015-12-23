package sk.genhis.evolution.util;

import java.util.Comparator;

import sk.genhis.evolution.Organism;

public class AbilityComparator implements Comparator<Organism> {
	private final int abilityId;
	
	public AbilityComparator(int abilityId) {
		this.abilityId = abilityId == 0 ? 7 : abilityId;
	}
	
	@Override
	public int compare(Organism a, Organism b) {
		if(a.getAbilityById(this.abilityId) == b.getAbilityById(this.abilityId))
			return 0;
		return a.getAbilityById(this.abilityId) > b.getAbilityById(this.abilityId) ? -1 : 1;
	}
}
