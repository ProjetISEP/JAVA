import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
public class Isep {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static float r = 60;
	public static int d = 0;
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


		myVaisseau.add(new Vaisseau(3000, 5000, 0, 0,3));
		myVaisseau.add(new Vaisseau(3000, 5000, 0, 0,3));
		myAsteroide.add(new Asteroide(X_MAX, 0, 0, 0,1));
		myAsteroide.add(new Asteroide(X_MAX/2, 0, 0, 0,1));
		
		myrectangle.add(new Terrain(5000, 5000, 1000, 1000));
		
		Terrain.tableauAleatoire();
		while (true) {
			//StdDraw.clear(Color.black);
			StdDraw.clear();
			//Terrain.show();
			
			//Terrain.show2();
			
			
			
			Terrain.decor();
			//Terrain.colision();
			
			
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
			}
			myVaisseau.get(0).vies("false");
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
