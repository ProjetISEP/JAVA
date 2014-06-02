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
	protected double largeur=110;
	protected double hauteur;
	protected static double speed=80;
	protected static int tailleterrain=600;
	public static double[] tab = new double[3000];
	public static int tabZonesParticuliere[];
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;
	public static int largeurRectangle=110;
	public static boolean niveau1=true;
	public static boolean niveau2=false;
	static double[] xter2=new double[50000];
	static double[] yter2=new double[50000];


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
	public void setxter(double d){
		xter=d;
	}
	public void setyter(double a){
		yter=a;
	}
	public void show(){
		//if(niveau1)
		StdDraw.filledRectangle(xter, yter, largeur,hauteur);
		/*else{
			double[] xTer=new double[50000];
			double[] yTer=new double[50000];
			double a;
			double b;
			for(int k=1;k!=myrectangle.size();k++){
			//	if(k==100){
			//		xter[k]=xter[k-1]; yter[k]=0;
				//	myrectangle.add(new Terrain(myrectangle.get(k-1).getxter(), 0,0,0,speed));
			//	}
		//		else{
				//	if(k%5==0){
						//a=((double)(Math.random()*(0.65*Y_MAX-0.15*Y_MAX+1))+0.15*Y_MAX);
					//	myrectangle.add(new Terrain(k*10, a,0,0,speed));
					//	xter[k] = k*10;  yter[k] = a;
						xTer[k] = myrectangle.get(k).getxter();  yTer[k] = myrectangle.get(k).getyter();
					//}
				//	else{
					//	b=((double)(Math.random()*(0.35*Y_MAX-0.25*Y_MAX+1))+0.25*Y_MAX);
				//		myrectangle.add(new Terrain(k*10, b,0,0,speed));
						//xter[k] = k*10;  yter[k] = b;
				//	}
		//		}
						StdDraw.polygon(xTer, yTer);
			}
			
		
		}*/
		//	StdDraw.polygon(xter, yter);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(Color.black);
		xter=xter-speed;
	}
	
	/*public static void showniv2(){
		double a;
		double b;
		myrectangle.add(new Terrain(0, 0.3*Y_MAX,0,0,speed));
		for(int k=1;k!=110;k++){
			if(k==110-1){
			
				myrectangle.add(new Terrain(myrectangle.get(k-1).getxter(), 0,0,0,speed));
				xter2[k]=myrectangle.get(k-1).getxter(); yter2[k]=0;
			}
			else{
				if(k%5==0){
					a=((double)(Math.random()*(0.65*Y_MAX-0.15*Y_MAX+1))+0.15*Y_MAX);
					myrectangle.add(new Terrain(k*(0.01*X_MAX), a,0,0,speed));
					xter2[k] =myrectangle.get(k).getxter();  yter2[k] = a;
				}
				else{
					b=((double)(Math.random()*(0.35*Y_MAX-0.25*Y_MAX+1))+0.25*Y_MAX);
					myrectangle.add(new Terrain(k*0.01*X_MAX, b,0,0,speed));
					xter2[k] = myrectangle.get(k).getxter();  yter2[k] = b;
				}
			}
		
		}
		StdDraw.polygon(xter2, yter2);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(Color.black);
	}*/
	
	public static double[] tableauAleatoire(int nbvaleurs){ // G�nere un tableau al�atoire pour la fonction decor
		tab[0]=Math.random();
		int k=0;
		for(int i=1;i!=nbvaleurs;i++){
			double nb=Math.random();
			if(tab[i-1]<0.02){
				tab[i]=tab[i-1]+0.13*nb;
				k=0;
			}
			if(tab[i-1]>0.9){
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
		if(niveau1){
		double tab1[]=tableauAleatoire(tailleterrain);
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=tailleterrain;i++){
			if(i<20){
				myrectangle.add(new Terrain(10000+i*200, 200,largeurRectangle,i*100+1000,speed));//le bas /* variation lineaire croissante de la hauteur */
				myrectangle.add(new Terrain(10000+i*200, 9800,largeurRectangle,i*100+1000,speed));//le haut
			}else if(i>Isep.tabZonesParticuliere[0] && i<Isep.tabZonesParticuliere[0]+60){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speed));
			}else if(i>Isep.tabZonesParticuliere[1] && i<Isep.tabZonesParticuliere[1]+60){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speed));
			}else if(i>Isep.tabZonesParticuliere[2] && i<Isep.tabZonesParticuliere[2]+60){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,4000+-(tab1[i]*3550),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speed));
			}else{
				myrectangle.add(new Terrain(xrectangle+i*200, 200,largeurRectangle,4000+-(tab1[i]*3550),speed));
				myrectangle.add(new Terrain(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speed));
			}
		}
		}
		else if(niveau2){
		
			
			System.out.println("ok");
			for (int i=0;i!=1000;i++){
			System.out.println(xter2[i]+"   "+yter2[i]);}
		
		}
	}
	
	public static void colision2(){
		
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
				myVaisseau.get(k).setY(-200);
				if(!myVaisseau.get(k).getBouclier()){
					
					myVaisseau.get(k).setLife();
				}
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
				myVaisseau.get(k).setY(200);
				if(!myVaisseau.get(k).getBouclier()){
					myVaisseau.get(k).setLife();
				}
			}
		}
	}
}