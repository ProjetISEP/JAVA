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
	public static int [] Ralentir(int nbvaleurs){
		int [] tableauRalentissement= new int[nbvaleurs];
		double nb=20000+Math.random()*40000;
		int entier=caster(nb);
		tableauRalentissement[0]=entier;
		System.out.println(tableauRalentissement[0]);
		for(int k=1;k!=tableauRalentissement.length;k++){
			tableauRalentissement[k]=tableauRalentissement[k-1]+caster(20000+Math.random()*100000);	
			System.out.println(tableauRalentissement[k]);
		}
		return tableauRalentissement;
	}
	public static int caster(double nbBrut){ //fonction qui permet de retourner un multiple de 100
		int entier=(int)nbBrut;
		int nbIntermedaire=entier%100;
		int nb100=entier+(100-nbIntermedaire);
		return nb100;
	}
<<<<<<< HEAD
	
	
=======
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
	public static void main(String[] args){
		StdDraw.setCanvasSize(900, 500);
		StdDraw.setXscale(0,X_MAX);
		StdDraw.setYscale(0,Y_MAX);


		myVaisseau.add(new Vaisseau(3000, 5000, 0, 0, 10, 0,0));//Les derniers chiffres correspondent aux matricules
		myVaisseau.add(new Vaisseau(6000, 5000, 0, 0, 10, 0,1));
		myAsteroide.add(new Asteroide(X_MAX, 0, 0, 0,2));
		myAsteroide.add(new Asteroide(X_MAX/2, 0, 0, 0,2));

		// CREATION  D'OBJETS POUR LE TERRAIN
		myrectangle=Terrain.getListeTerrain();
		Terrain.generateTerrain();
		// FIN CREATION D'OBJETS



		int tab[]=Ralentir(10);// tableau pour les zones de ralentissement
		boolean missileJ1 = false;
		boolean missileJ2 = false;
<<<<<<< HEAD
		Audio son = new Audio();
        son.start();
=======
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
		while (true) {
			StdDraw.clear();


			//RECTANGLES********************************************
			for(int i=0;i!=myrectangle.size();i++){
				myrectangle.get(i).show();
				if(i%2==0){
					myrectangle.get(i).colision();//colision avec le bas
				}else{
					myrectangle.get(i).colision1();//colision avec le haut
				}
				for(int k=0;k<=5;k++){ //boucle pour les ralentissements
					if(myVaisseau.get(0).getScore()<=tab[k]+100 && myVaisseau.get(0).getScore()>=tab[k]-100){
						myrectangle.get(i).setSpeed(20);// on baisse la vitesse 
					}
					if(myVaisseau.get(0).getScore()<=tab[k]+20100 && myVaisseau.get(0).getScore()>=tab[k]+19900){
						myrectangle.get(i).setSpeed(60);//on la r�augmente (10000 unit�s de score plus tard)
					}
				}
			}
			//***************************************************



			//VAISSEAU********************************************
<<<<<<< HEAD

			//JOUEUR1
			Vaisseau.controlPlayer1normal();

			if (!StdDraw.isKeyPressed(32)) {// Si on n'appuye pas sur espace
				missileJ1 = false;
			}
			if (missileJ1 == false) {
				if (StdDraw.isKeyPressed(32)) {// L'id�e est qu'en restant
												// appuy sur espace il y aura
												// seulement un ajout � la liste
												// puisque missile sera "tru
					                             // quand on appuie sur espace

=======
			
			//JOUEUR1
			Vaisseau.controlPlayer1normal();
			
			if (!StdDraw.isKeyPressed(32)) {// Si on n'appuye pas sur espace
				missileJ1 = false;
			}
			if (missileJ1 == false) {
				if (StdDraw.isKeyPressed(32)) {// L'id�e est qu'en restant
												// appuy� sur espace il y aura
												// seulement un ajout � la liste
												// puisque missile sera "tru
					// quand on appuie sur espace

>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
					myMissile.add(new Missile(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(), Missile.getvxmissile(),
							0, r, myVaisseau.get(0).getMat()));
					missileJ1 = true;
<<<<<<< HEAD

=======
					
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
				}
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
			if (!StdDraw.isKeyPressed(69)) {// Si on n'appuye pas sur espace
				missileJ2 = false;
			}
			if (missileJ2 == false) {
				if (StdDraw.isKeyPressed(69)) {// quand on appuie sur espace
					myMissile.add(new Missile(myVaisseau.get(1).getx(),
							myVaisseau.get(1).gety(), Missile.getvxmissile(),
							0, r,myVaisseau.get(1).getMat()));
					missileJ2 = true;
				}
			}


			//*************************************************************


			for(int i=0;i!=myVaisseau.size();i=i+1){
				if(i==0){
					(myVaisseau.get(i)).paint0();
				} else if(i==1){
					(myVaisseau.get(i)).paint1();
				}
				(myVaisseau.get(i)).bordure();
				(myVaisseau.get(i)).move();
				(myVaisseau.get(i)).score();
				myVaisseau.get(i).vies();
				if(myVaisseau.get(i).getlife()<=0){//Condition de fin de partie
					Vaisseau.FinDePartie();
				}

			}
			//ASTEROIDE******************************************************
			for(int i=0;i!=myAsteroide.size();i=i+1){
				(myAsteroide.get(i)).move();
				if(myAsteroide.get(i).getlifeAste()>0){//on cache l'ast�roide si il n'a plu de vie
					(myAsteroide.get(i)).paint1();
				}
				if(myAsteroide.get(i).getlifeAste()==0){// on en rajoute un si un at�roide a �t� supprim�
					myAsteroide.add(new Asteroide(X_MAX, Math.random()*10000, 0, 0,2));
				}
				(myAsteroide.get(i)).colision();
				(myAsteroide.get(i)).colisionMissileAsteroide();
			}
			//MISSILE**********************************************************
			for(int i=0;i!=myMissile.size();i=i+1){
				(myMissile.get(i)).missile();
			}
<<<<<<< HEAD

			for(int i=0;i!=myVaisseau.size();i++){

=======
			
			for(int i=0;i!=myVaisseau.size();i++){
				
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
				(myVaisseau.get(i)).colisionMissileVaisseau();
				}
			StdDraw.show(10);
		}
	}
}
