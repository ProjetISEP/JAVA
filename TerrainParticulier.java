import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TerrainParticulier extends Terrain  {

	public static List<Terrain> myrectangle =new ArrayList<>();
	public void show(){
		myVaisseau=Isep.getListeVaisseau();	
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-speedTerrain;
	}
	public void setSpeed(int newSpeed){//NOUVELLE VITESSE DES TERRAINS
		myVaisseau=Isep.getListeVaisseau();
		speed=newSpeed;		
	}
	TerrainParticulier(double xter, double yter, double largeur, double hauteur,double vitesse) {
		super(xter, yter, largeur, hauteur, vitesse);
	}
}
