import java.awt.Color;
public class TerrainParticulier extends Terrain  {

	public void show(){
		myVaisseau=Isep.getListeVaisseau();	
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-speed;
		
		
		for(int i=0;i!=myVaisseau.size();i=i+1){
			if(xter<10000 && xter>0){//PARTIE GRAVITE
				myVaisseau.get(i).setY(1.3); // setY est dans la class Vaisseau
			}else{
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
