import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
public class Isep {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static float r = 60;
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;

	public static List<Asteroide> myAsteroide =new ArrayList<>();
	public static List<Missile> myMissile =new ArrayList<>();
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	//GETTERS DES LISTES
	public static List<Missile> getListeMissile(){
		return myMissile;
	}
	public static List<Asteroide> getListeAsteroide(){
		return myAsteroide;
	}
	public static List<Vaisseau> getListeVaisseau(){
		return myVaisseau;
	}



	public static void main(String[] args){
		StdDraw.setCanvasSize(900, 500);
		StdDraw.setXscale(0,X_MAX);
		StdDraw.setYscale(0,Y_MAX);


		myVaisseau.add(new Vaisseau(3000, 5000, 0, 0,10));
		myVaisseau.add(new Vaisseau(6000, 5000, 0, 0,10));
		myAsteroide.add(new Asteroide(X_MAX, 0, 0, 0,1));
		myAsteroide.add(new Asteroide(X_MAX/2, 0, 0, 0,1));

		// CREATION  D'OBJETS POUR LE TERRAIN
		double tab1[]=Terrain.tableauAleatoire();
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=200;i++){
			if(i<20){
				myrectangle.add(new Terrain(10000+i*450, 500,170,i*100+1000));//le bas
				myrectangle.add(new Terrain(10000+i*400, 9500,170,i*100+1000));//le haut
			}else{
				if(tab1[i]<0.5){
					myrectangle.add(new Terrain(xrectangle+i*400, 500,170,900+(3600-(tab1[i]*3600))));
					myrectangle.add(new Terrain(xrectangle+i*400, 9500,170,900+tab1[i]*3600));
				}else{
					myrectangle.add(new Terrain(xrectangle+i*400, 500,170,900+(3600-(tab1[i]*3600))));
					myrectangle.add(new Terrain(xrectangle+i*400, 9500,170,900+tab1[i]*3600));
				}

			}
		}
		// FIN CREATION D'OBJETS
		while (true) {

			StdDraw.clear();
			for(int i=0;i!=200;i++){			
				if(i%2==0){
					myrectangle.get(i).colision();
					myrectangle.get(i).show();
					StdDraw.filledRectangle(myrectangle.get(i).getxter(), myrectangle.get(i).getyter(), myrectangle.get(i).getlargeur(),myrectangle.get(i).gethauteur());
				}else{
					myrectangle.get(i).colision1();
					myrectangle.get(i).show();
					StdDraw.filledRectangle(myrectangle.get(i).getxter(), myrectangle.get(i).getyter(), myrectangle.get(i).getlargeur(),myrectangle.get(i).gethauteur());
				}
			}
			//Terrain.decor();


			//VAISSEAU********************************************
			//JOUEUR1
			if(StdDraw.isKeyPressed(38))
				(myVaisseau.get(0)).top();
			if(StdDraw.isKeyPressed(37))
				(myVaisseau.get(0)).left();
			if(StdDraw.isKeyPressed(39))
				(myVaisseau.get(0)).right();
			if(StdDraw.isKeyPressed(40))
				(myVaisseau.get(0)).bottom();
			if(StdDraw.isKeyPressed(32)){//quand on appuie sur espace
				myMissile.add( new Missile(myVaisseau.get(0).getx(), myVaisseau.get(0).gety(), 0, 0,r));
			}
			//JOUEUR2
			if(StdDraw.isKeyPressed(90))
				(myVaisseau.get(1)).top();
			if(StdDraw.isKeyPressed(81))
				(myVaisseau.get(1)).left();
			if(StdDraw.isKeyPressed(68))
				(myVaisseau.get(1)).right();
			if(StdDraw.isKeyPressed(83))
				(myVaisseau.get(1)).bottom();
			if(StdDraw.isKeyPressed(69)){//quand on appuie sur espace
				myMissile.add( new Missile(myVaisseau.get(1).getx(), myVaisseau.get(1).gety(), 0, 0,r));
			}
			for(int i=0;i!=myVaisseau.size();i=i+1){
				(myVaisseau.get(i)).bordure();
				if(i==0){
					(myVaisseau.get(i)).paint0();
				} else if(i==1){
					(myVaisseau.get(i)).paint1();
				}
				(myVaisseau.get(i)).move();
				(myVaisseau.get(i)).score();
				myVaisseau.get(i).vies();
			}
			System.out.println("vaisseau 0: " + myVaisseau.get(0).getlife());
			System.out.println("vaisseau 1: " + myVaisseau.get(1).getlife());
			//ASTEROIDE******************************************************
			for(int i=0;i!=myAsteroide.size();i=i+1){
				(myAsteroide.get(i)).move();
				(myAsteroide.get(i)).paint1();
				(myAsteroide.get(i)).colision();
				(myAsteroide.get(i)).colisionMissileAsteroide();
			}
			//MISSILE**********************************************************
			for(int i=0;i!=myMissile.size();i=i+1){
				(myMissile.get(i)).missile();
			}
			StdDraw.show(10);
		}
	}
}
