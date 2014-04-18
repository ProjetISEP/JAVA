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
	protected static double speed=60;
	public static double[] tab = new double[300];
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
	public static double[] tableauAleatoire(int nbvaleurs){ // Génere un tableau aléatoire pour la fonction decor
		tab[0]=Math.random();
		int k=0;
		for(int i=1;i!=nbvaleurs;i++){
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
		}
		return tab;
	}
	public static void generateTerrain(){
		double tab1[]=Terrain.tableauAleatoire(300);
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=290;i++){
			if(i<20){// 
				myrectangle.add(new Terrain(10000+i*450, 200,170,i*100+1000,speed));//le bas
				myrectangle.add(new Terrain(10000+i*400, 9800,170,i*100+1000,speed));//le haut
			}else if (i<50 && i>=30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}else if (i<100 && i>=80){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}else{
				myrectangle.add(new Terrain(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));
				myrectangle.add(new Terrain(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}
		}
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