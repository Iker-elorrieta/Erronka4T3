package modelo;


import java.util.Random;

public class Estadisticas {
	Random rand = new Random();
	int kills=rand.nextInt((20 - 1) + 1) + 1;
	int death=rand.nextInt((5 - 0) + 1) + 0;;
	int assits=rand.nextInt((22 - 0) + 1) + 0;;
	
	@Override
	public String toString() {
		return  + kills + "/" + death + "/" + assits ;
	}
	
}
