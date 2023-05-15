package modelo;

public class Estadisticas {
	
	public int kills;
	public int death;
	public int assists;
	
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public Estadisticas(int kills, int death, int assists)
	{
		this.kills=kills;
		this.death=death;
		this.assists=assists;
	}
	
	public Estadisticas() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return  + kills + "/" + death + "/" + assists ;
	}
	
}