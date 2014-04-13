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
		tab[0]=Math.random();
		System.out.println(tab[0]);
		int k=0;
		for(int i=1;i!=300;i++){
			double nb=Math.random();
			if(tab[i-1]<0.1){
				tab[i]=tab[i-1]+0.3*nb;
				k=0;
			}
			if(tab[i-1]>0.9){
				tab[i]=tab[i-1]-0.3*nb;
				k=1;
			}
			if(k==0){
				tab[i]=tab[i-1]+0.3*nb;
			}else if(k==1){
				tab[i]=tab[i-1]-0.3*nb;
			}
			System.out.println(tab[i]);
		}
		return tab;
	}
	public static void generateTerrain(){
		double tab1[]=Terrain.tableauAleatoire();
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=290;i++){
			if(i<20){
				myrectangle.add(new Terrain(10000+i*450, 200,170,i*100+1000));//le bas
				myrectangle.add(new Terrain(10000+i*400, 9800,170,i*100+1000));//le haut
			}else{
				myrectangle.add(new Terrain(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550))));
				myrectangle.add(new Terrain(xrectangle+i*400, 9800,170,700+tab1[i]*3900));
			}
		}
	}
	public void show(){
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
		xter=xter-110;
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