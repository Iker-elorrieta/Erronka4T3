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
	
	@Override
	public String toString() {
		return  + kills + "/" + death + "/" + assists ;
	}
	
}

/*
Random rand = new Random();
int kills=rand.nextInt((20 - 1) + 1) + 1;
int death=rand.nextInt((5 - 0) + 1) + 0;
int assits=rand.nextInt((22 - 0) + 1) + 0;*/