import java.awt.Color;
public class TerrainParticulier extends Terrain  {

	public void show(){
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-110;
	}
	
	TerrainParticulier(double xter, double yter, double largeur, double hauteur,double vitesse) {
		super(xter, yter, largeur, hauteur, vitesse);
	}

}
