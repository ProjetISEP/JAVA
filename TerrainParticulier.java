import java.awt.Color;

public class TerrainParticulier extends Terrain  {
	public static boolean toucheinversee;
	public void show(){
		myVaisseau=Isep.getListeVaisseau();	
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-speed;
		
		
		for(int i=0;i!=myVaisseau.size();i=i+1){
			if(xter<10000 && xter>0){//PARTIE GRAVITE
				toucheinversee=true;//la fonction de controle des touches a besoin du while true pour etre executé du coup elle est remise dans la classe Isep
				myVaisseau.get(i).setY(1.3); // setY est dans la class Vaisseau
				System.out.println("gravité");
			
			}
			else{
				toucheinversee=false;
				System.out.println("pas de gravité");
			}
			
		}
	}
	public void setSpeed(int newSpeed){//NOUVELLE VITESSE DES TERRAINS
		myVaisseau=Isep.getListeVaisseau();
		speed=newSpeed;		
	}
	TerrainParticulier(double xter, double yter, double largeur, double hauteur,double vitesse) {
		super(xter, yter, largeur, hauteur, vitesse);
	}

}
