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
	public static int secondeRalentissement;
	public static int dureeDuRalentissement=4; //durée des ralentissements en seconds
	static double[] xter2=new double[50000];
	static double[] yter2=new double[50000];
	public static int bcl=0;
	public static boolean pass=false;
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
	public static int[] Ralentir2(int nbvaleurs) { // retourne au tableau de valeur qui permet de ralentir le terrain
		int[] tableauRalentissement = new int[nbvaleurs];
		double nb = 7 + Math.random() * 10;
		int entier=caster(nb,1);
		tableauRalentissement[0] = entier;
		for (int k = 1; k != tableauRalentissement.length; k++){
			tableauRalentissement[k]=tableauRalentissement[k-1]+caster(7+Math.random()*15,1);
			System.out.println(tableauRalentissement[k]);
		}
		return tableauRalentissement;
	}

	public static int [] zonesParticuliere(int nbvaleurs){ // permet de retourner un tableau pour les zones particulieres
		// ces nombres correspondent aux numéros des rectangles pour les mettre en couleur 
		int [] tableauZonesParticulieres= new int[nbvaleurs];
		double nb=20+Math.random()*15;
		int entier=caster(nb,10);
		tableauZonesParticulieres[0]=entier;
		for(int k=1;k!=tableauZonesParticulieres.length;k++){
			tableauZonesParticulieres[k]=tableauZonesParticulieres[k-1]+caster(80+Math.random()*90,10);
		}
		return tableauZonesParticulieres;
	}
	// cette fonction permet de retourner un nombre entier à partir d'un nombre à virgule
	//soit un multiple de 10(zones parti) ou de 1 (ralentissement)
	public static int caster(double nbBrut, int multiple){
		int entier=(int)nbBrut;
		int nbIntermedaire=entier%multiple;
		int nb100=entier+(multiple-nbIntermedaire);
		return nb100;
	}

	// LE MAIN /////////////////////////////////////
	public static void main(String[] args) throws InterruptedException {
		//On agrandit la fenetre d'origine
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

		int tab[] = Ralentir2(10);// tableau pour les zones de ralentissement à partir des secondes

		//Audio son = new Audio();
		//son.start();
		Menu.nav();
		compteurSeconde=0; // on initialise le compteur de seconde à 0.
		while (finDePartie) {
			StdDraw.clear();

			//on fait clignoter l'écran si pour 1 joueur, ce dernier a moins de 3 vies
			if(Menu.nbjoueurs==1){
				if(myVaisseau.get(0).getlife()<=3)
					if(seconde%2==0){
						StdDraw.clear(Color.green);
					}
			}

			if (Menu.multi1) {
				if(Menu.nbjoueurs==2){
					// JOUEUR2
					if(myVaisseau.get(1).getlife()>0){
						if(Menu.inertieJ2==false){
							Vaisseau.controlPlayer2();   //CHOISIR INERTIE OU NORMAL
						}else if (Menu.inertieJ2==true){
							myVaisseau.get(1).setInertie(true);
							myVaisseau.get(1).controlPlayerInertie();

						}
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
			if(Terrain.niveau1){
				for (int i = 0; i != myrectangle.size(); i++) {
					myrectangle.get(i).show();// methode qui permet le defilement de chaque rectangle de la liste
					if (i % 2 == 0) {
						myrectangle.get(i).colision();// colision avec le bas
					} else {
						myrectangle.get(i).colision1();// colision avec le haut
					}
					for (int k = 0; k <= tab.length-2; k++) { // boucle pour les ralentissements
						if (seconde==tab[k]) {
							secondeRalentissement=seconde;
							myrectangle.get(i).setSpeed(20);// on baisse la vitesse
						}
						if (seconde==secondeRalentissement+dureeDuRalentissement) {
							myrectangle.get(i).setSpeed(80);// on la reaugmente x secondes plus tard
						}
					}
				}
			}
			else{
				System.out.println("NIVEAU2");
				double a;
				double b;
				double c;
				myrectangle.add(new Terrain(0, 0.3*Y_MAX,0,0,speed));
				if(!pass){
					for(int k=bcl;k!=bcl+Terrain.tailleterrain;k++){
						//bcl=k;
						if(k==bcl+Terrain.tailleterrain-1){

							myrectangle.add(new Terrain(myrectangle.get(k-1).getxter(), 0,0,0,speed));
							xter2[k]=myrectangle.get(k-1).getxter(); yter2[k]=0;
						}
						else{
							if(k%7==0){
								a=((double)(Math.random()*(0.65*Y_MAX-0.25*Y_MAX+1))+0.25*Y_MAX);
								myrectangle.add(new Terrain(k*(0.01*X_MAX), a-1000,0,0,speed));
								xter2[k] =myrectangle.get(k).getxter();  yter2[k] = a;
							}
							if(k%19==0){
								c=((double)(Math.random()*(0.55*Y_MAX-0.13*Y_MAX+1))+0.13*Y_MAX);
								myrectangle.add(new Terrain(k*(0.01*X_MAX), c,0,0,speed));
								xter2[k] =myrectangle.get(k).getxter();  yter2[k] = c;
							}
							else{
								b=((double)(Math.random()*(0.35*Y_MAX-0.25*Y_MAX+1))+0.25*Y_MAX);
								myrectangle.add(new Terrain(k*0.01*X_MAX, b,0,0,speed));
								xter2[k] = myrectangle.get(k).getxter();  yter2[k] = b;
							}
						}
						pass=true;


					}
				}
				//StdDraw.polygon(xter2, yter2);

				Color RANDOM=new Color((int)R,(int)G,(int)B);
				StdDraw.setPenColor(Color.blue);
				for(int k=bcl;k!=bcl+Terrain.tailleterrain;k++){
					StdDraw.line(myrectangle.get(k).getxter(), myrectangle.get(k).getyter(),myrectangle.get(k+1).getxter(),myrectangle.get(k+1).getyter());
					myrectangle.get(k).setxter(myrectangle.get(k).getxter()-myrectangle.get(k).speed);
				}

				//COLISION*********************************************************
				for(int k=0;k!=myVaisseau.size();k++){
					for(int j=bcl;j!=bcl+Terrain.tailleterrain;j++){
						double intermediaire=myrectangle.get(k).getyter()+350;
						//	if(myrectangle.get(j).getxter()+100>myVaisseau.get(k).getx() && myrectangle.get(j).getxter()-100<myVaisseau.get(k).getx() && intermediaire>myVaisseau.get(k).gety()){
						/*	if(myVaisseau.get(k).getx()+500<=myrectangle.get(j+1).getxter() && myVaisseau.get(k).getx()-500<=myrectangle.get(j+1).getxter() 
							&& myVaisseau.get(k).getx()+100>=myrectangle.get(j).getxter() && myVaisseau.get(k).getx()-100>=myrectangle.get(j).getxter() && 
							myVaisseau.get(k).gety()+100<=myrectangle.get(j+1).getyter() && 	myVaisseau.get(k).gety()-100<=myrectangle.get(j+1).getyter()
							&& myVaisseau.get(k).gety()+100>=myrectangle.get(j).getyter() && myVaisseau.get(k).gety()-100>=myrectangle.get(j).getyter() ){*/
						if(300>=-myVaisseau.get(k).gety()+((myrectangle.get(j+1).getyter()-myrectangle.get(j).getyter())*(myVaisseau.get(k).getx()))/(myrectangle.get(j+1).getxter()-myrectangle.get(j).getxter()) +myrectangle.get(j).getyter()- 
								((myrectangle.get(j+1).getyter()-myrectangle.get(j).getyter())*(myrectangle.get(j).getxter()))/(myrectangle.get(j+1).getxter()-myrectangle.get(j).getxter()) &&

								-300<=-myVaisseau.get(k).gety()+((myrectangle.get(j+1).getyter()-myrectangle.get(j).getyter())*(myVaisseau.get(k).getx()))/(myrectangle.get(j+1).getxter()-myrectangle.get(j).getxter()) +myrectangle.get(j).getyter()- 
								((myrectangle.get(j+1).getyter()-myrectangle.get(j).getyter())*(myrectangle.get(j).getxter()))/(myrectangle.get(j+1).getxter()-myrectangle.get(j).getxter())
								&& myVaisseau.get(k).getx()<=myrectangle.get(j+1).getxter() && myVaisseau.get(k).getx()>=myrectangle.get(j).getxter()
								){


							if(intermediaire>myVaisseau.get(k).gety()){
								myVaisseau.get(k).setY(-200);
							}
							if(!myVaisseau.get(k).getBouclier()){
								System.out.println("ok");
								myVaisseau.get(k).setLife();
							}
						}
					}
				}
			}
			// ***************************************************
			// JOUEUR1

			if(myVaisseau.get(0).getlife()>0){ // Si le vaisseau 1 a des vies ou peut le controler, sans on ne peut plus
				if(Menu.inertieJ1==false){
					Vaisseau.controlPlayer1();   //CHOISIR INERTIE OU NORMAL
				}else if(Menu.inertieJ1==true){
					myVaisseau.get(0).setInertie(true);
					myVaisseau.get(0).controlPlayerInertie();
				}
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
			finDePartie=Vaisseau.endGame(); // condition de fin de partie
			for (int i = 0; i != myVaisseau.size(); i = i + 1) {
				if(!myVaisseau.get(i).getBouclier()){ // si les vaisseaux n'ont pas de bouclier
					(myVaisseau.get(i)).paint(i+1);
				}else{  // si les vaisseaux ont des boucliers
					(myVaisseau.get(i)).paintBouclier(i+1);

				}
				if(myVaisseau.get(i).getlife()>0){ //si le vaisseau est en vie il y a des borduress
					(myVaisseau.get(i)).bordure();
				}else{ //si il n'est plus en vie, il est attire sur la gauche 
					(myVaisseau.get(i)).setX(10);
				}
				if(myVaisseau.get(i).getx()<-500){
					(myVaisseau.get(i)).setX(100000000);// on sort le vaisseau en question du terrain pour plus qu'il interfère
				}
				(myVaisseau.get(i)).score();
				myVaisseau.get(i).vies();// c'est pour afficher les coeurs
				(myVaisseau.get(i)).colisionMissileVaisseau();
				(myVaisseau.get(i)).colisionMineVaisseau(); 
				(myVaisseau.get(i)).colisionVaisseauAVaisseau();


				//ZONES PARTICULIERES************************
				for(int k=0;k!=tabZonesParticuliere.length;k++){
					myVaisseau.get(i).toucheInversee(2*tabZonesParticuliere[k],2*tabZonesParticuliere[k]+120);	// INVERSEMENT DES TOUCHES************************
					myVaisseau.get(i).gravite(2*tabZonesParticuliere[k],2*tabZonesParticuliere[k]+120);// GRAVITE************************
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
				//String secondeString = Integer.toString(seconde);
				//StdDraw.text(5000, 5000, secondeString);
				//StdDraw.setPenColor(Color.black);
				//StdDraw.setPenColor(210, 210, 210);
			}

			//
			Thread.sleep(10); // on fait patienter le programme 10ms pour le compteur de seconde
			///////////FIN PARTIE COMPTEUR SECONDE
		}
		int winnerScore=myVaisseau.get(0).score;
		int winner=myVaisseau.get(0).matricule; // le gagnant est le joueur 1
		for (int i = 1; i != myVaisseau.size(); i = i + 1) { // si le joueur 2 ou 3 est meilleur que le joueur 1 le gagnant change
			if(myVaisseau.get(i).score>winnerScore){
				winnerScore=myVaisseau.get(i).score;
				winner=myVaisseau.get(i).matricule;
			}
		}
		winner=winner+1;
		while(true){ // affichage du gagnant
			StdDraw.clear();
			StdDraw.clear(Color.orange);
			StdDraw.text(5000, 5000, "Le joueur"+winner+" est vainqueur avec un score de "+winnerScore+" ");
			StdDraw.rectangle(X_MAX/2, Y_MAX/2, 6000, 2000);
			StdDraw.show(1);
		}
	}
}
