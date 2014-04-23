import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	protected double xter;
	protected double yter;
	protected double largeur;
	protected double hauteur;
	protected static double speed=80;
	protected static int tailleterrain=290;
	public static double[] tab = new double[3000];
	public static int tabZonesParticuliere[];
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;


	Terrain(double xter, double yter, double largeur, double hauteur, double speed) {
		this.xter = xter;
		this.yter = yter;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.speed = speed;
	}
	public static List<Terrain> getListeTerrain(){
		return myrectangle;
	}
	public double getxter(){
		return xter;
	}
	public double getyter(){
		return yter;
	}
	public double getlargeur(){
		return largeur;
	}
	public double gethauteur(){
		return hauteur;
	}
	public void setSpeed(int newSpeed){
		myVaisseau=Isep.getListeVaisseau();
		speed=newSpeed;		
	}
	public void show(){
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(Color.black);
		xter=xter-speed;
	}


	public void colision(){//POUR LES COLISIONS AVEC LES RECTANGLES DU BAS
		myVaisseau=Isep.getListeVaisseau();		
		double intermediaire=yter+hauteur;
		for(int k=0;k!=myVaisseau.size();k++){
			if(xter+100>myVaisseau.get(k).getx() && xter-100<myVaisseau.get(k).getx() && intermediaire>myVaisseau.get(k).gety()){
				myVaisseau.get(k).setLife();	
			}
		}
	}
	public void colision1(){//POUR LES COLISIONS AVEC LES RECTANGLES DU HAUT
		myVaisseau=Isep.getListeVaisseau();
		double intermediaire=yter-hauteur;
		for(int k=0;k!=myVaisseau.size();k++){
			if(xter+100>myVaisseau.get(k).getx() && xter-100<myVaisseau.get(k).getx() && intermediaire<myVaisseau.get(k).gety()){
				myVaisseau.get(k).setLife();
			}
		}
	}
}