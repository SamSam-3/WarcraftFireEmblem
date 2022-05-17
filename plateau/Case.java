package plateau;

import affrontement.Bataille;
import etreVivant.EtreVivant;

public class Case {

	private Coordonne position;
	private Terrain terrain;
	private EtreVivant occupant;
	
	
	public Case(Coordonne a,Bataille q) {
		position = a;
		setOccupant(null);
	}
	
	
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	public Coordonne getPosition() {
		return position;
	}
	public void setPosition(Coordonne position) {
		this.position = position;
	}


	public EtreVivant getOccupant() {
		return occupant;
	}


	public void setOccupant(EtreVivant occupant) {
		this.occupant = occupant;
	}
	
	
}
