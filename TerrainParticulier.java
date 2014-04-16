import java.awt.Color;
public class TerrainParticulier extends Terrain  {

	public void show(){
<<<<<<< HEAD
		myVaisseau=Isep.getListeVaisseau();	
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-speed;
		
		//PARTIE GRAVITE
		for(int i=0;i!=myVaisseau.size();i=i+1){
			if(xter<10000 && xter>0){
				myVaisseau.get(i).setY(1.5);
			}
		}
	}
	public void setSpeed(int newSpeed){
		myVaisseau=Isep.getListeVaisseau();
		speed=newSpeed;		
	}
=======
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-110;
	}
	
>>>>>>> 5f484d90ae434ecfaf5827c57bb0310c8a977bf8
	TerrainParticulier(double xter, double yter, double largeur, double hauteur,double vitesse) {
		super(xter, yter, largeur, hauteur, vitesse);
	}

}
