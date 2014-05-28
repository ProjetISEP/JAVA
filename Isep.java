import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Isep {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle = 10000;
	public static float r = 60;
	public static int nbjoueurs;
	static double R = Math.random() * 255;
	static double G = Math.random() * 255;
	static double B = Math.random() * 255;
	protected static double speed=80;
	protected static int tailleterrain=600;
	public static double[] tab = new double[3000];
	public static boolean multi;
	public static boolean ai=false;
	public static boolean menu=true;
	public static boolean missileJ1 = false;
	public static boolean missileJ2 = false;
	public static boolean missileJ3 = false;
	public static boolean mineJ1 = false;
	public static boolean mineJ2 = false;
	public static boolean mineJ3 = false;
	public static String niveau;
	public static List<Asteroide> myAsteroide = new ArrayList<>();
	public static List<Missile> myMissile = new ArrayList<>();
	public static List<Vaisseau> myVaisseau = new ArrayList<>();
	public static List<Terrain> myrectangle = new ArrayList<>();
	public static int tabZonesParticuliere[]= zonesParticuliere(3);//Nombre de zones particulieres
	public static List<Missile> myMines = new ArrayList<>();
	public static int compteurSeconde;
	public static int seconde=0;
	public static boolean finDePartie=Vaisseau.endGame();

	// GETTERS DES LISTES
	public static List<Missile> getListeMissile() {
		return myMissile;
	}

	public static List<Asteroide> getListeAsteroide() {
		return myAsteroide;
	}

	public static List<Vaisseau> getListeVaisseau() {
		return myVaisseau;
	}

	public static int[] Ralentir(int nbvaleurs) {
		int[] tableauRalentissement = new int[nbvaleurs];
		double nb = 20000 + Math.random() * 40000;
		int entier=caster(nb,100);
		tableauRalentissement[0] = entier;

		for (int k = 1; k != tableauRalentissement.length; k++) {
			tableauRalentissement[k]=tableauRalentissement[k-1]+caster(40000+Math.random()*100000,100);
		}
		return tableauRalentissement;
	}

	public static int [] zonesParticuliere(int nbvaleurs){
		int [] tableauZonesParticulieres= new int[nbvaleurs];
		double nb=20+Math.random()*15;
		int entier=caster(nb,10);
		tableauZonesParticulieres[0]=entier;
		for(int k=1;k!=tableauZonesParticulieres.length;k++){
			tableauZonesParticulieres[k]=tableauZonesParticulieres[k-1]+caster(40+Math.random()*90,10);
		}
		return tableauZonesParticulieres;
	}

	public static int caster(double nbBrut, int multiple){
		int entier=(int)nbBrut;
		int nbIntermedaire=entier%multiple;
		int nb100=entier+(multiple-nbIntermedaire);
		return nb100;
	}







	public static void main(String[] args) throws InterruptedException {
		StdDraw.setCanvasSize(900, 500);
		StdDraw.setXscale(0, X_MAX);
		StdDraw.setYscale(0, Y_MAX);
		myAsteroide.add(new Asteroide(X_MAX, 5000, 0, 0, 4));
		myAsteroide.add(new Asteroide(X_MAX, 0, 0, 0, 4));
		myAsteroide.add(new Bouclier(X_MAX/2, 0, 0, 0, 4));

		// CREATION D'OBJETS POUR LE TERRAIN
		myrectangle = Terrain.getListeTerrain();
		System.out.println("Chargement...");
		Terrain.generateTerrain();

		// FIN CREATION D'OBJETS

		int tab[] = Ralentir(10);// tableau pour les zones de ralentissement

		//		Audio son = new Audio();
		//	son.start();



		boolean navigation=true;
		Menu.nav();
		compteurSeconde=0;
		while (finDePartie) {
			//System.out.println(finDePartie);
			StdDraw.clear();
			if(Menu.nbjoueurs==1){
				if(myVaisseau.get(0).getlife()<=3)
					if(seconde%2==0){
						StdDraw.clear(Color.green);
					}
			}
			if (Menu.multi1) {//Toute cette partie correspond au mode multi à 1, 2 ou 3 joueurs
				if(Menu.nbjoueurs==2){
					// JOUEUR2
					if(myVaisseau.get(1).getlife()>0){
					Vaisseau.controlPlayer2();
					}
					if (!StdDraw.isKeyPressed(69)) {// Si on n'appuye pas sur E
						missileJ2 = false;
					}
					if (missileJ2 == false) {
						if (StdDraw.isKeyPressed(69)) {// quand on appuie sur E
							myMissile.add(new Missile(myVaisseau.get(1).getx(),
									myVaisseau.get(1).gety(), Missile
									.getvxmissile(), 0, r, myVaisseau
									.get(1).getMat(),true));
							missileJ2 = true;
						}
					}

					if (!StdDraw.isKeyPressed(70)) {// 70 = touche F
						mineJ2 = false;
					}
					if (mineJ2 == false) {
						if (StdDraw.isKeyPressed(70)) {
							if(myVaisseau.get(1).stockMine>0){//
								myMines.add(new Mines(myVaisseau.get(1).getx(),
										myVaisseau.get(1).gety(), Missile
										.getvxmissile(), 0, 0, myVaisseau
										.get(1).getMat(), true, Terrain.speed, 4));
								mineJ2 = true;
								myVaisseau.get(1).stockMine=myVaisseau.get(1).stockMine-1;
							}
						}
					}
				}if(Menu.nbjoueurs==3){
					// JOUEUR2
					if(myVaisseau.get(1).getlife()>0){
					Vaisseau.controlPlayer2();
					}
					if (!StdDraw.isKeyPressed(69)) {// Si on n'appuye pas sur E
						missileJ2 = false;
					}
					if (missileJ2 == false) {
						if (StdDraw.isKeyPressed(69)) {// quand on appuie sur E
							myMissile.add(new Missile(myVaisseau.get(1).getx(),
									myVaisseau.get(1).gety(), Missile
									.getvxmissile(), 0, r, myVaisseau
									.get(1).getMat(),true));
							missileJ2 = true;
						}
					}

					if (!StdDraw.isKeyPressed(70)) {// 70 = touche F
						mineJ2 = false;
					}
					if (mineJ2 == false) {
						if (StdDraw.isKeyPressed(70)) {//
							if(myVaisseau.get(1).stockMine>0){
								myMines.add(new Mines(myVaisseau.get(1).getx(),
										myVaisseau.get(1).gety(), Missile
										.getvxmissile(), 0, 0, myVaisseau
										.get(1).getMat(), true, Terrain.speed, 4));
								mineJ2 = true;
								myVaisseau.get(1).stockMine=myVaisseau.get(1).stockMine-1;

							}
						}
					}
					// JOUEUR3
					if(myVaisseau.get(2).getlife()>0){
					Vaisseau.controlPlayer3();
					}
					if (!StdDraw.isKeyPressed(73)) {// 73 = touche I
						missileJ3 = false;
					}
					if (missileJ3 == false) {
						if (StdDraw.isKeyPressed(73)) {// quand on appuie sur espace
							myMissile.add(new Missile(myVaisseau.get(2).getx(),
									myVaisseau.get(2).gety(), Missile
									.getvxmissile(), 0, r, myVaisseau
									.get(2).getMat(),true));
							missileJ3 = true;
						}
					}
					if (!StdDraw.isKeyPressed(76)) {// 76 = touche L
						mineJ3 = false;
					}
					if (mineJ3 == false) {
						if (StdDraw.isKeyPressed(76)) {
							if(myVaisseau.get(2).stockMine>0){//
								myMines.add(new Mines(myVaisseau.get(2).getx(),
										myVaisseau.get(2).gety(), Missile
										.getvxmissile(), 0, 0, myVaisseau
										.get(2).getMat(), true, Terrain.speed, 4));
								mineJ3 = true;
								myVaisseau.get(2).stockMine=myVaisseau.get(2).stockMine-1;
							}
						}
					}
				}
			}else{
				// JOUEUR ordi
				AI vaisseauBleu=(AI) myVaisseau.get(1);//ici myVaisseau.get(1) correspond au vaisseau bleu (i.e l'ordi) mais defois i 

				vaisseauBleu.aiMove();
				vaisseauBleu.aiMissile();
				vaisseauBleu.aiMine();
				vaisseauBleu.controlAImove();
				vaisseauBleu.controlAImissilemine();

				for(int i=0;i<=myrectangle.size();i++){
					if(i%2!=0){
						StdDraw.setPenColor(Color.green);
						StdDraw.filledRectangle(myrectangle.get(i).getxter(), myrectangle.get(i).getyter(), (200)/2.0, (500)/2.0);
						StdDraw.setPenColor(Color.blue);
						StdDraw.filledRectangle(myrectangle.get(i).getxter(), myrectangle.get(i).getyter()-myrectangle.get(i).gethauteur(), (200)/2.0, (500)/2.0);
					}
				}
				vaisseauBleu.setTop(false);
				vaisseauBleu.setBottom(false);
				vaisseauBleu.setLeft(false);
				vaisseauBleu.setRight(false);
				//vaisseauBleu.setMissile(false);
				vaisseauBleu.setMine(false);

				AI.droiteTour=AI.droiteTour+1;

			}







			// RECTANGLES********************************************
			for (int i = 0; i != myrectangle.size(); i++) {
				myrectangle.get(i).show();// methode qui permet le defilement de chaque rectangle de la liste
				if (i % 2 == 0) {
					myrectangle.get(i).colision();// colision avec le bas
				} else {
					myrectangle.get(i).colision1();// colision avec le haut
				}
				for (int k = 0; k <= 5; k++) { // boucle pour les ralentissements
					if (myVaisseau.get(0).getScore() <= tab[k] + 100
							&& myVaisseau.get(0).getScore() >= tab[k] - 100) {
						myrectangle.get(i).setSpeed(20);// on baisse la vitesse
					}
					if (myVaisseau.get(0).getScore() <= tab[k] + 30100
							&& myVaisseau.get(0).getScore() >= tab[k] + 29900) {
						myrectangle.get(i).setSpeed(60);// on la reaugmente 20000 unités de score plus tard)
					}
				}
			}

			// ***************************************************
			// JOUEUR1

			if(myVaisseau.get(0).getlife()>0){
				Vaisseau.controlPlayer1();   //CHOISIR INERTIE OU NORMAL
				//myVaisseau.get(0).controlPlayerInertie();
			}
			if (!StdDraw.isKeyPressed(32)) {// Si on n'appuye pas sur espace
				missileJ1 = false;
			}

			if (missileJ1 == false) {
				if (StdDraw.isKeyPressed(32)) {// L'idee est qu'en restant
					// appuy sur espace il y aura
					// seulement un ajout la liste
					// puisque missile sera "tru
					// quand on appuie sur espace

					myMissile.add(new Missile(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(), Missile
							.getvxmissile(), 0, r, myVaisseau
							.get(0).getMat(),true));
					missileJ1 = true;
				}
			}
			if (!StdDraw.isKeyPressed(517)) {//
				mineJ1 = false;
			}
			if (mineJ1 == false) {
				if (StdDraw.isKeyPressed(517)) {
					if(myVaisseau.get(0).stockMine>0){// 517 correspond à la touche "!"
						myMines.add(new Mines(myVaisseau.get(0).getx(),
								myVaisseau.get(0).gety(), Missile
								.getvxmissile(), 0, 0, myVaisseau
								.get(0).getMat(), true, Terrain.speed, 4));// Deux paramètres ont été rajoutés : la vitesse de la mine et son 
						// acceleration, même si on voit getvxmissile on ne travaillera que sur vxMine
						mineJ1 = true;
						myVaisseau.get(0).stockMine=myVaisseau.get(0).stockMine-1;
					}
				}
			}
			//VAISSEAU
			// *************************************************************
			finDePartie=Vaisseau.endGame();
			for (int i = 0; i != myVaisseau.size(); i = i + 1) {
				if(!myVaisseau.get(i).getBouclier()){
					(myVaisseau.get(i)).paint(i+1);
				}else{
					(myVaisseau.get(i)).paintBouclier(i+1);

				}
				if(myVaisseau.get(i).getlife()>0){ //si le vaisseau est en vie il y a des borduress
					(myVaisseau.get(i)).bordure();
				}else{ //si il n'est plus en vie, il est attire sur la gauche 
					(myVaisseau.get(i)).setX(10);
				}
				if(myVaisseau.get(i).getx()<-500){
					(myVaisseau.get(i)).setX(100000000);// on sort le vaisseau en questiondu terrain pour plus qu'il interfère
				}
				(myVaisseau.get(i)).score();
				myVaisseau.get(i).vies();// c'est pour afficher les coeurs
				(myVaisseau.get(i)).colisionMissileVaisseau();
				(myVaisseau.get(i)).colisionMineVaisseau();


				//ZONES PARTICULIERES************************
				for(int k=0;k!=tabZonesParticuliere.length;k++){
					myVaisseau.get(i).toucheInversee(2*tabZonesParticuliere[k],2*tabZonesParticuliere[k]+60);	// INVERSEMENT DES TOUCHES************************
					myVaisseau.get(i).gravite(2*tabZonesParticuliere[k],2*tabZonesParticuliere[k]+60);// GRAVITE************************
				}
				//********* FIN ZONE PARTICULIERE
			}

			// ASTEROIDE******************************************************
			for (int i = 0; i != myAsteroide.size(); i = i + 1) {
				// si l'asteroide sort de l'écran on le positionne à droite de l'écran
				if (myAsteroide.get(i).getPositionxAste() >-500 && myAsteroide.get(i).getPositionxAste() <-400) {
					myAsteroide.get(i).setX(10000+Math.random()*20000);
					myAsteroide.get(i).setY(Math.random()*10000);
				}

				// on rajoute des vies si un asteroide colissionne un vaisseau
				if (myAsteroide.get(i).getlifeAste() <0) {
					myAsteroide.get(i).setX(10000+Math.random()*20000);
					myAsteroide.get(i).setY(Math.random()*10000);
					myAsteroide.get(i).setLifeAste(-3);
				}

				(myAsteroide.get(i)).colisionAsteroideVaisseau();
				(myAsteroide.get(i)).colisionMissileAsteroide();
				(myAsteroide.get(i)).move();
				(myAsteroide.get(i)).paint1();
			}
			// MISSILE**********************************************************
			for (int i = 0; i != myMissile.size(); i = i + 1) {
				if(myMissile.get(i).life)
					(myMissile.get(i)).missile();
				else
					(myMissile.get(i)).setxmissile(20000);	
			}
			// MINES**********************************************************

			for (int i = 0; i != myMines.size(); i = i + 1) {
				if(myMines.get(i).life)
					(myMines.get(i)).mines();
				else
					(myMines.get(i)).setxmissile(20000);

			}
			StdDraw.show(10);



			///////////PARTIE COMPTEUR SECONDE
			compteurSeconde=compteurSeconde+1;
			if(compteurSeconde%34==0){ // ca correspond environ à une seconde
				seconde=seconde+1;
				//
				//String secondeString = Integer.toString(seconde);
				//StdDraw.text(5000, 5000, secondeString);
				//StdDraw.setPenColor(Color.black);
				//StdDraw.setPenColor(210, 210, 210);
			}

			//
			Thread.sleep(10);
			///////////FIN PARTIE COMPTEUR SECONDE
		}
		int winnerScore=myVaisseau.get(0).score;
		int winner=myVaisseau.get(0).matricule;
		for (int i = 1; i != myVaisseau.size(); i = i + 1) {
			if(myVaisseau.get(i).score>winnerScore){
				winnerScore=myVaisseau.get(i).score;
				winner=myVaisseau.get(i).matricule;
			}
		}
		winner=winner+1;
		while(true){
			StdDraw.clear();
			StdDraw.clear(Color.orange);
			StdDraw.text(5000, 5000, "Le joueur"+winner+" est vainqueur avec un score de "+winnerScore+" ");
			StdDraw.rectangle(X_MAX/2, Y_MAX/2, 6000, 2000);
			StdDraw.show(1);
		}
	}
}
