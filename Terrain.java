import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	private double xter;
	private double yter;
	private double largeur;
	private double hauteur;
	public static double[] tab = new double[300];
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;


	Terrain(double xter, double yter, double largeur, double hauteur) {
		this.xter = xter;
		this.yter = yter;
		this.largeur = largeur;
		this.hauteur = hauteur;
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


	public static double[] tableauAleatoire(){ // Génere un tableau aléatoire pour la fonction decor
		for(int i=0;i!=300;i++){
			double nb=Math.random();
			tab[i]=nb;
		}
		return tab;
	}
	/*
	public static void decor(){
		for(int i=0;i!=200;i++){
			if(i<20){
				StdDraw.filledRectangle(xrectangle+i*400, 500, 180,i*100+1000);//le bas
				StdDraw.filledRectangle(xrectangle+i*400, 9500, 180,i*100+1000);//le haut
				System.out.println(xrectangle+i*400);
			}else{
				StdDraw.filledRectangle(xrectangle+i*400, 500,tab[i]*140+50,1000+tab[i]*2300);//le bas
				StdDraw.filledRectangle(xrectangle+i*400, 9500,tab[i]*140+50,1000+(2300-(tab[i]*2300)));//le haut	
			}
			xrectangle=xrectangle-0.5;
			Color RANDOM=new Color((int)R,(int)G,(int)B);
			StdDraw.setPenColor(RANDOM);
		}
	}*/
	public void show(){
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-200;
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