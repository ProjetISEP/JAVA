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
	protected static double speed=80;
	protected static int tailleterrain=290;
	public static double[] tab = new double[3000];

	public static List<Asteroide> myAsteroide =new ArrayList<>();
	public static List<Missile> myMissile =new ArrayList<>();
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	public static int tabZonesParticuliere[]= zonesParticuliere(3);//Nombre de zones particulieres
	public static List<Missile> myMines =new ArrayList<>();////////////////////////////////////////////////////////
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
		int entier=caster(nb,100);
		tableauRalentissement[0]=entier;
		for(int k=1;k!=tableauRalentissement.length;k++){
			tableauRalentissement[k]=tableauRalentissement[k-1]+caster(20000+Math.random()*100000,100);	
		}
		return tableauRalentissement;
	}
	public static int [] zonesParticuliere(int nbvaleurs){
		int [] tableauZonesParticulieres= new int[nbvaleurs];
		double nb=20+Math.random()*15;
		int entier=caster(nb,10);
		tableauZonesParticulieres[0]=entier;
		System.out.println(tableauZonesParticulieres[0]);
		for(int k=1;k!=tableauZonesParticulieres.length;k++){
			tableauZonesParticulieres[k]=tableauZonesParticulieres[k-1]+caster(40+Math.random()*90,10);
			System.out.println(tableauZonesParticulieres[k]);
		}
		return tableauZonesParticulieres;
	}
	public static int caster(double nbBrut, int multiple){ //fonction qui permet de retourner un multiple 
		int entier=(int)nbBrut;
		int nbIntermedaire=entier%multiple;
		int nb100=entier+(multiple-nbIntermedaire);
		return nb100;
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
		int nbzones=2;
		double tab1[]=tableauAleatoire(tailleterrain);
		myrectangle=Terrain.getListeTerrain();
		for(int i=0;i!=tailleterrain;i++){
			
			if(i<20){
				myrectangle.add(new Terrain(10000+i*450, 200,170,i*100+1000,speed));//le bas /* variation lineaire croissante de la hauteur */
				myrectangle.add(new Terrain(10000+i*400, 9800,170,i*100+1000,speed));//le haut
			}else if(i>tabZonesParticuliere[0] && i<tabZonesParticuliere[0]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}else if(i>tabZonesParticuliere[1] && i<tabZonesParticuliere[1]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}else if(i>tabZonesParticuliere[2] && i<tabZonesParticuliere[2]+30){ // POUR LES ZONES PARTICULIERES
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));//le 3900 est une translation
				myrectangle.add(new TerrainParticulier(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}else{
				myrectangle.add(new Terrain(xrectangle+i*400, 200,170,700+(3900-(tab1[i]*3550)),speed));
				myrectangle.add(new Terrain(xrectangle+i*400, 9800,170,700+tab1[i]*3900,speed));
			}
		}
	}

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
		System.out.println("Chargement...");
		generateTerrain();

		// FIN CREATION D'OBJETS



		int tab[]=Ralentir(10);// tableau pour les zones de ralentissement
		boolean missileJ1 = false;
		boolean missileJ2 = false;
		boolean mineJ1=false;
		boolean mineJ2=false;
		//	Audio son = new Audio();
		//	son.start();
		int mineCount=0;
		while (true) {

			StdDraw.clear();
			//RECTANGLES********************************************
			for(int i=0;i!=myrectangle.size();i++){
				//	System.out.println(myrectangle.size());
				myrectangle.get(i).show();//methode qui permet le defilement de chaque rectangle de la liste
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
						myrectangle.get(i).setSpeed(60);//on la reaugmente (10000 unit�s de score plus tard)
					}
				}
			}
			//***************************************************



			//VAISSEAU*******************************************

			//JOUEUR1
			Vaisseau.controlPlayer1();
			Vaisseau.controlPlayer2();

			//INVERSEMENT DES TOUCHES************************
			for(int i=0;i!=myVaisseau.size();i++){
				for(int k=0;k!=tabZonesParticuliere.length;k++){
					myVaisseau.get(i).toucheInversee(2*tabZonesParticuliere[k],2*tabZonesParticuliere[k]+100);	//	Intervalle (numero du rectangle) a choisir
				}
			}//*****************
			//GRAVITE************************
			for(int i=0;i!=myVaisseau.size();i++){
				for(int j=0;j!=tabZonesParticuliere.length;j++){
					myVaisseau.get(i).gravite(2*tabZonesParticuliere[j],2*tabZonesParticuliere[j]+60);	// Intervalle (numero du rectangle) a choisir
				}
			}//*****************


			if (!StdDraw.isKeyPressed(32)) {// Si on n'appuye pas sur espace
				missileJ1 = false;
			}

			if (missileJ1 == false) {
				if (StdDraw.isKeyPressed(32)) {// L'idee est qu'en restant
					// appuy sur espace il y aura
					// seulement un ajout  la liste
					// puisque missile sera "tru
					// quand on appuie sur espace

					myMissile.add(new Missile(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(), Missile.getvxmissile(),
							0, r, myVaisseau.get(0).getMat()));
					missileJ1 = true;

				}
			}

			/*if (!StdDraw.isKeyPressed(17)) {// Si on n'appuye pas sur espace
				mineJ1 = false;
			}
			if (mineJ1 == false) {
				if (StdDraw.isKeyPressed(17)) {//


					myMines.add(new Missile(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(), Missile.getvxmissile(),
							0, r, myVaisseau.get(0).getMat()));
					mineJ1 = true;

				}
			}*/

			if (!StdDraw.isKeyPressed(517)) {// 
				mineJ1 = false;
			}
			if (mineJ1 == false) {
				if (StdDraw.isKeyPressed(517)) {// 517 correspond à la touche "!"


					myMines.add(new Mines(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(),  Missile.getvxmissile(),
							0, 0, myVaisseau.get(0).getMat(),Terrain.speed,4));//Deux paramètres ont été rajoutés : la vitesse de la mine et son acceleration, même si on voit getvxmissile on ne travaillera que sur vxMine
					mineJ1 = true;

				}
			}

			//JOUEUR2

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


			if (!StdDraw.isKeyPressed(70)) {// 70 = touche F
				mineJ2 = false;
			}
			if (mineJ2 == false) {
				if (StdDraw.isKeyPressed(70)) {// 


					myMines.add(new Mines(myVaisseau.get(1).getx(),
							myVaisseau.get(1).gety(),  Missile.getvxmissile(),
							0, 0, myVaisseau.get(1).getMat(),Terrain.speed,4));//Deux paramètres ont été rajoutés : la vitesse de la mine et son acceleration, même si on voit getvxmissile on ne travaillera que sur vxMine
					mineJ2 = true;

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
				//(myVaisseau.get(i)).move();
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
				if(myAsteroide.get(i).getlifeAste()==0){// on en rajoute un si un asteroide est supprime
					myAsteroide.add(new Asteroide(X_MAX, Math.random()*10000, 0, 0,2));
				}
				(myAsteroide.get(i)).colision();
				(myAsteroide.get(i)).colisionMissileAsteroide();
			}
			//MISSILE**********************************************************
			for(int i=0;i!=myMissile.size();i=i+1){
				(myMissile.get(i)).missile();
			}


			for(int i=0;i!=myVaisseau.size();i++){

				(myVaisseau.get(i)).colisionMissileVaisseau();
			}

			//MINES**********************************************************


			for(int i=0;i!=myMines.size();i=i+1){
				(myMines.get(i)).mines();
			}


			for(int i=0;i!=myVaisseau.size();i++){

				(myVaisseau.get(i)).colisionMineVaisseau();
			}

			/*for(int i=mineCount;i!=myMines.size();i=i+1){
				(myMines.get(i)).demiparabole(myVaisseau.get(0).getx(),myVaisseau.get(0).gety(), -10, 10);

				mineCount=mineCount+1;
			}*/



			StdDraw.show(10);
		}
	}
}
