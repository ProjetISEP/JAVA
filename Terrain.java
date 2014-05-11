import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	public static List<Missile> myMissile =new ArrayList<>();
	protected double xter;
	protected double yter;
	protected double largeur;
	protected double hauteur;
	protected static double speed=220;
	protected static int tailleterrain=600;
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
	public static double[] tableauAleatoire(int nbvaleurs){ // G�nere un tableau al�atoire pour la fonction decor
		tab[0]=Math.random();
		int k=0;
		for(int i=1;i!=nbvaleurs;i++){
			double nb=Math.random();
			if(tab[i-1]<0.02){
				tab[i]=tab[i-1]+0.13*nb;
				k=0;
			}
			if(tab[i-1]>0.98){
				tab[i]=tab[i-1]-0.13*nb;
				k=1;
			}
			if(k==0){
				tab[i]=tab[i-1]+0.13*nb;
			}else if(k==1){
				tab[i]=tab[i-1]-0.13*nb;
			}
		}
		return tab;
	}
	public static void generateTerrain(){
		double tab1[]=tableauAleatoire(tailleterrain);
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=tailleterrain;i++){
			if(i<20){
				myrectangle.add(new Terrain(10000+i*200, 200,90,i*100+1000,speed));//le bas /* variation lineaire croissante de la hauteur */
				myrectangle.add(new Terrain(10000+i*200, 9800,90,i*100+1000,speed));//le haut
			}else if(i>Isep.tabZonesParticuliere[0] && i<Isep.tabZonesParticuliere[0]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,90,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,90,500+tab1[i]*4500,speed));
			}else if(i>Isep.tabZonesParticuliere[1] && i<Isep.tabZonesParticuliere[1]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,90,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,90,500+tab1[i]*4500,speed));
			}else if(i>Isep.tabZonesParticuliere[2] && i<Isep.tabZonesParticuliere[2]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,90,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,90,500+tab1[i]*4500,speed));
			}else{
				myrectangle.add(new Terrain(xrectangle+i*200, 200,90,500+(tab1[i]*1500+2500-(tab1[i]*3550)),speed));
				myrectangle.add(new Terrain(xrectangle+i*200, 9800,90,500+tab1[i]*3900,speed));
			}
		}
	}

	public void colision(){//POUR LES COLISIONS AVEC LES RECTANGLES DU BAS
		myVaisseau=Isep.getListeVaisseau();	
		myMissile=Isep.getListeMissile();
		double intermediaire=yter+hauteur+350;
		for(int i=0;i!=myMissile.size();i=i+1){ //colisions entre les missiles et le bas du tunnel
			if(xter+100>myMissile.get(i).getxmissile() && xter-100<myMissile.get(i).getxmissile() && intermediaire>myMissile.get(i).getymissile()){
				myMissile.get(i).setxmissile(100000);
			}
		}
		
		for(int k=0;k!=myVaisseau.size();k++){
			if(xter+100>myVaisseau.get(k).getx() && xter-100<myVaisseau.get(k).getx() && intermediaire>myVaisseau.get(k).gety()){
				myVaisseau.get(k).setLife();	
				myVaisseau.get(k).setY(-200);
			}
		}
	}
	public void colision1(){//POUR LES COLISIONS AVEC LES RECTANGLES DU HAUT
		myVaisseau=Isep.getListeVaisseau();
		myMissile=Isep.getListeMissile();
		double intermediaire=yter-hauteur-350;
		for(int i=0;i!=myMissile.size();i=i+1){ //colisions entre les missiles et le bas du tunnel
			if(xter+100>myMissile.get(i).getxmissile() && xter-100<myMissile.get(i).getxmissile() && intermediaire<myMissile.get(i).getymissile()){
				myMissile.get(i).setxmissile(100000);
			}
		}
		
		for(int k=0;k!=myVaisseau.size();k++){
			if(xter+100>myVaisseau.get(k).getx() && xter-100<myVaisseau.get(k).getx() && intermediaire<myVaisseau.get(k).gety()){
				myVaisseau.get(k).setLife();
				myVaisseau.get(k).setY(200);
			}
		}
	}
}