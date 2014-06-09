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
	protected static double speed;
	public static double speedTerrain=80;
	protected static int tailleterrain=800;
	protected static int tailleterrain2=100;
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
	static double[] yter2haut=new double[50000];
	public static int bcl2=0;


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
	public double getSpeed(){
		return speed;
	}
	public void setSpeed(double newSpeed){
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
		//System.out.println("tab0  "+tab[0]);
		for(int i=1;i!=nbvaleurs;i++){

			double nb=Math.random();
			if(tab[i-1]<0.06){
				tab[i]=tab[i-1]+0.15*nb;
				k=0;
			}else if(tab[i-1]>0.9){
				tab[i]=tab[i-1]-0.15*nb;
				k=1;
			}else{
				if(nb>0.7)
				tab[i]=tab[i-1]+0.2*nb;
				else{
					tab[i]=tab[i-1]-0.2*nb;
				}
			}
			//System.out.println(tab[i]);
		}
		return tab;
	}
	public static void generateTerrain(){
		if(niveau1){
			double tab1[]=tableauAleatoire(tailleterrain);
			myrectangle=Terrain.getListeTerrain();
			for(int i=0;i!=tailleterrain;i++){
				if(i<20){
					myrectangle.add(new Terrain(10000+i*200, 200,largeurRectangle,i*100+1000,speedTerrain));//le bas /* variation lineaire croissante de la hauteur */
					myrectangle.add(new Terrain(10000+i*200, 9800,largeurRectangle,i*100+1000,speedTerrain));//le haut
				}else if(i>Isep.tabZonesParticuliere[0] && i<Isep.tabZonesParticuliere[0]+60){ // POUR LES ZONES PARTICULIERES
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,5000+-(tab1[i]*3550),speedTerrain));//le 3900 est une translation
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speedTerrain));
				}else if(i>Isep.tabZonesParticuliere[1] && i<Isep.tabZonesParticuliere[1]+60){ // POUR LES ZONES PARTICULIERES
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,5000+-(tab1[i]*3550),speedTerrain));//le 3900 est une translation
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speedTerrain));
				}else if(i>Isep.tabZonesParticuliere[2] && i<Isep.tabZonesParticuliere[2]+60){ // POUR LES ZONES PARTICULIERES
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 200,largeurRectangle,5000+-(tab1[i]*3550),speedTerrain));//le 3900 est une translation
					myrectangle.add(new TerrainParticulier(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speedTerrain));
				}else{
					myrectangle.add(new Terrain(xrectangle+i*200, 200,largeurRectangle,5000+-(tab1[i]*3550),speedTerrain));
					myrectangle.add(new Terrain(xrectangle+i*200, 9800,largeurRectangle,500+tab1[i]*4500,speedTerrain));
				}
			}
		}

	}
	
	public static void generateTerrain2(){
		
	
			
			double a=0;
			double b = 0;
			double c;
			double h;
		//	myrectangle.add(new Terrain(0, 0.3*Y_MAX,0,0,speed));
			
			//	Isep.bcl=0;
				for(int k=0;k!=Terrain.tailleterrain2;k++){
					
				
				//	else{
					if(xter2[k]==0){
				/*		if(k==Terrain.tailleterrain2-1){

							//	myrectangle.add(new Terrain(myrectangle.get(k-1).getxter(), 0,0,0,speed));
								xter2[k]=k*(0.01*X_MAX); yter2[k]=0;
							//	Isep.bcl=Isep.bcl+Terrain.tailleterrain;
							}*/
						if(Isep.compteurSeconde%7==0){
							a=((double)(Math.random()*(0.65*Y_MAX-0.15*Y_MAX+1))+0.15*Y_MAX);
							//myrectangle.add(new Terrain(k*(0.01*X_MAX), a,0,0,speed));
							xter2[k] =k*(0.01*X_MAX);  yter2[k] = a;
							h=((double)(Math.random()*(0.8*Y_MAX-0.7*Y_MAX+1))+0.7*Y_MAX);
							yter2haut[k] = h;
							myrectangle.add(new Terrain(k*(0.01*X_MAX), a,0,0,120));
						}
						else if(Isep.compteurSeconde%23==0){
							c=((double)(Math.random()*(0.55*Y_MAX-0.13*Y_MAX+1))+0.13*Y_MAX);
						
							xter2[k] =k*(0.01*X_MAX);  yter2[k] = c;
							h=((double)(Math.random()*(0.9*Y_MAX-0.75*Y_MAX+1))+0.75*Y_MAX);
							yter2haut[k] = h;
							myrectangle.add(new Terrain(k*(0.01*X_MAX), c,0,0,120));
						}
						else{
					
							b=((double)(Math.random()*(0.35*Y_MAX-0.20*Y_MAX+1))+0.20*Y_MAX);
						
							xter2[k] =k*(0.01*X_MAX);  yter2[k] = b; 
							h=((double)(Math.random()*(0.9*Y_MAX-0.75*Y_MAX+1))+0.75*Y_MAX);
							yter2haut[k] = h;
							myrectangle.add(new Terrain((k+Isep.bcl)*0.01*X_MAX, b,0,0,120));
						
						}
					//	System.out.println("ok");
					}
				//	}
				//	Isep.pass=true;
							
			//	System.out.println(k);
				}
		//	System.out.println(Isep.compteurSeconde);
		
			//StdDraw.polygon(xter2, yter2);
			
			/*System.out.println("ok");
			for (int i=0;i!=1000;i++){
			System.out.println(xter2[i]+"   "+yter2[i]);}*/
		System.out.println(b);
		
	}
	
	public static void show2(){
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(Color.blue);
		double inter;
		for(int k=0;k!=Terrain.tailleterrain2-1;k++){
		//	StdDraw.line(myrectangle.get(k+bcl2).getxter(),myrectangle.get(k+bcl2).getyter(),myrectangle.get(k+1+bcl2).getxter(),myrectangle.get(k+1+bcl2).getyter());
			StdDraw.line(xter2[k],yter2[k],xter2[k+1],yter2[k+1]);
			StdDraw.line(xter2[k],yter2haut[k],xter2[k+1],yter2haut[k+1]);
			xter2[k]=xter2[k]-Terrain.speedTerrain;
			
		//	xter2[k]=xter2[k+1];
		//	yter2[k]=yter2[k+1];
		//	myrectangle.get(k+bcl2).setxter(myrectangle.get(k+bcl2).getxter()-myrectangle.get(k).speed);
			//System.out.println("ok");
			/*if(k==Terrain.tailleterrain)
				bcl2=Isep.bcl;*/
		}
		for(int k=0;k!=Terrain.tailleterrain2;k++){
			
			xter2[k]=xter2[k+1];
			yter2[k]=yter2[k+1];
			yter2haut[k]=yter2haut[k+1];
		}
	}
	

	
	public static void colision2(){
		
		myVaisseau=Isep.getListeVaisseau();	
		
		for(int k=0;k!=myVaisseau.size();k++){
			for(int j=0;j!=0+Terrain.tailleterrain;j++){
				double intermediaire=Terrain.yter2[j]+350;
				double intermediaire2=Terrain.yter2[j]-350;
			
						
						//Colision avec le bas*****
						if(300>=-myVaisseau.get(k).gety()+((Terrain.yter2[j+1]-Terrain.yter2[j])*(myVaisseau.get(k).getx()))/(Terrain.xter2[j+1]-Terrain.xter2[j]) +Terrain.yter2[j]- 
						((Terrain.yter2[j+1]-Terrain.yter2[j])*(Terrain.xter2[j]))/(Terrain.xter2[j+1]-Terrain.xter2[j]) &&

						-300<=-myVaisseau.get(k).gety()+((Terrain.yter2[j+1]-Terrain.yter2[j])*(myVaisseau.get(k).getx()))/(Terrain.xter2[j+1]-Terrain.xter2[j]) +Terrain.yter2[j]- 
								((Terrain.yter2[j+1]-Terrain.yter2[j])*(Terrain.xter2[j]))/(Terrain.xter2[j+1]-Terrain.xter2[j]) &&
						 myVaisseau.get(k).getx()<=Terrain.xter2[j+1] && myVaisseau.get(k).getx()>=Terrain.xter2[j]){


								if(intermediaire>myVaisseau.get(k).gety()){
										myVaisseau.get(k).setY(-200);
								}
								if(!myVaisseau.get(k).getBouclier()){
						
									myVaisseau.get(k).setLife();
								}
						}
						//******************
						
						if(300>=-myVaisseau.get(k).gety()+((Terrain.yter2haut[j+1]-Terrain.yter2haut[j])*(myVaisseau.get(k).getx()))/(Terrain.xter2[j+1]-Terrain.xter2[j]) +Terrain.yter2haut[j]- 
								((Terrain.yter2haut[j+1]-Terrain.yter2haut[j])*(Terrain.xter2[j]))/(Terrain.xter2[j+1]-Terrain.xter2[j]) &&

								-300<=-myVaisseau.get(k).gety()+((Terrain.yter2haut[j+1]-Terrain.yter2haut[j])*(myVaisseau.get(k).getx()))/(Terrain.xter2[j+1]-Terrain.xter2[j]) +Terrain.yter2haut[j]- 
										((Terrain.yter2haut[j+1]-Terrain.yter2haut[j])*(Terrain.xter2[j]))/(Terrain.xter2[j+1]-Terrain.xter2[j]) &&
								 myVaisseau.get(k).getx()<=Terrain.xter2[j+1] && myVaisseau.get(k).getx()>=Terrain.xter2[j]){


										if(intermediaire2<myVaisseau.get(k).gety()){
												myVaisseau.get(k).setY(+200);
										}
										if(!myVaisseau.get(k).getBouclier()){
								
											myVaisseau.get(k).setLife();
										}
								}
								//******************
								
		
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