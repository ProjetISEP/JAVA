﻿import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Vaisseau {
	public static List<Vaisseau> myVaisseau = new ArrayList<>();
	public static List<Missile> myMissile = new ArrayList<>();
	public static List<Terrain> myrectangle = new ArrayList<>();
	public static List<Missile> myMines =new ArrayList<>();
	public static boolean toucheinversee;
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double t = 0;
	public static boolean gravite;
	protected double y;
	protected double x;
	protected double vy;
	protected double vx;
	protected int score;
	protected int matricule;

	static double R = Math.random() * 255;
	static double G = Math.random() * 255;
	static double B = Math.random() * 255;
	private int life;

	Vaisseau(double px, double py, double pvx, double pvy, int plife, int pscore,
			int pMatricule) {// Un vaisseau possède une matricule pour la
		// reconnaissance des missiles
		this.x = px;
		this.y = py;
		this.vx = pvx;
		this.vy = pvy;
		this.life = plife;
		this.score = pscore;
		matricule = pMatricule;

	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public void sety(double py){
		y=py;
	}

	public int getlife() {
		return life;
	}

	public int getScore() {
		return score;
	}

	public int getMat() {
		return matricule;
	}

	public void vies() {
		myVaisseau = Isep.getListeVaisseau();
		for (int k = 0; k != myVaisseau.size(); k++) {
			for (int g = 0; g != myVaisseau.get(k).life; g++) {
				StdDraw.picture(6000+1500*k+g*120, 8000, "./src/vie.png", 250, 280);
			}
			if(myVaisseau.get(k).life==0){
				StdDraw.text(X_MAX/2, Y_MAX/2, "GAME OVER");
				StdDraw.setPenColor(Color.blue);
			}
		}
	}

	public void setLife() {
		myVaisseau = Isep.getListeVaisseau();
		life = getlife() - 1;
		StdDraw.picture(x, y, "./src/crash.png");
	}

	public void setY(double gravite) { // POUR LA GRAVITE
		y = y - gravite;
	}

	public void score() {
		myVaisseau = Isep.getListeVaisseau();
		if (0 < x && x < 4000)
			score = score + 100;
		if (4000 < x && x < 6000)
			score = score + 200;
		if (6000 < x && x < 8000)
			score = score + 400;
		if (8000 < x && x < 10000)
			score = score + 600;

		for (int k = 0; k != myVaisseau.size(); k++) {
			String scoreString = Integer.toString(myVaisseau.get(k).score);
			StdDraw.text(7000 + k * 1000, 8500, scoreString);
			StdDraw.setPenColor(Color.WHITE);
		}

	}
	public void bordure() {
		if (y <= 0) {
			y = 100;
		} else if (x > X_MAX) {
			x = X_MAX - 100;
		} else if (x <= 20) {
			x = 100;
		} else if (y > Y_MAX) {
			y = Y_MAX - 100;
		}

	}

	public void top() {
		y = y + 75;
	}
	public void bottom() {
		y = y - 75;
	}
	public void left() {
		x = x - 40;
	}
	public void right() {
		x = x + 40;
	}
	public static void controlPlayer1() {
		if (!toucheinversee) {
			if (StdDraw.isKeyPressed(38))
				(myVaisseau.get(0)).top();
			if (StdDraw.isKeyPressed(37))
				(myVaisseau.get(0)).left();
			if (StdDraw.isKeyPressed(39))
				(myVaisseau.get(0)).right();
			if (StdDraw.isKeyPressed(40))
				(myVaisseau.get(0)).bottom();
		} else {
			if (StdDraw.isKeyPressed(40))
				(myVaisseau.get(0)).top();
			if (StdDraw.isKeyPressed(39))
				(myVaisseau.get(0)).left();
			if (StdDraw.isKeyPressed(37))
				(myVaisseau.get(0)).right();
			if (StdDraw.isKeyPressed(38))
				(myVaisseau.get(0)).bottom();

		}
	}

	public static void controlPlayer2() {
		if (!toucheinversee) {
			if(StdDraw.isKeyPressed(90))
				(myVaisseau.get(1)).top();
			if(StdDraw.isKeyPressed(81))
				(myVaisseau.get(1)).left();
			if(StdDraw.isKeyPressed(68))
				(myVaisseau.get(1)).right();
			if(StdDraw.isKeyPressed(83))
				(myVaisseau.get(1)).bottom();
		} else {
			if (StdDraw.isKeyPressed(83))
				(myVaisseau.get(1)).top();
			if (StdDraw.isKeyPressed(68))
				(myVaisseau.get(1)).left();
			if (StdDraw.isKeyPressed(81))
				(myVaisseau.get(1)).right();
			if (StdDraw.isKeyPressed(90))
				(myVaisseau.get(1)).bottom();

		}
	}
	public static void controlPlayer3() {
		if (!toucheinversee) {
			if(StdDraw.isKeyPressed(85))
				(myVaisseau.get(2)).top();
			if(StdDraw.isKeyPressed(72))
				(myVaisseau.get(2)).left();
			if(StdDraw.isKeyPressed(75))
				(myVaisseau.get(2)).right();
			if(StdDraw.isKeyPressed(74))
				(myVaisseau.get(2)).bottom();
		} else {
			if (StdDraw.isKeyPressed(74))
				(myVaisseau.get(2)).top();
			if (StdDraw.isKeyPressed(75))
				(myVaisseau.get(2)).left();
			if (StdDraw.isKeyPressed(72))
				(myVaisseau.get(2)).right();
			if (StdDraw.isKeyPressed(85))
				(myVaisseau.get(2)).bottom();

		}
	}

	public static void FinDePartie() {// A TERMINER
		int totalLife = 0;
		int[] tableauLife = new int[myVaisseau.size()];
		for (int k = 0; k != myVaisseau.size(); k++) {
			if (myVaisseau.get(k).getlife() == 0) {

			}
		}
	}

	public static String intToString(int i) {

		String s = "" + i;
		return s;
	}

	public void paint(int i) {
		StdDraw.picture(x, y, "./src/vaisseau"+intToString(i)+".png", 180);
	}




	public void colisionMissileVaisseau() {// methode qui permet de faire
		// apparaitre un crash lorsqu'un
		// vaisseau se prend un missile
		myVaisseau = Isep.getListeVaisseau();

		myMissile = Isep.getListeMissile();
		for (int i = 0; i != myMissile.size(); i++) {

			for (int k = 0; k != myVaisseau.size(); k++) {
				if (this.matricule != k) {

					if (myMissile.get(i).getxmissile() < myVaisseau.get(k)
							.getx() + 300
							&& myMissile.get(i).getxmissile() > myVaisseau.get(
									k).getx() - 300
									&& myMissile.get(i).getymissile() < myVaisseau.get(
											k).gety() + 300
											&& myMissile.get(i).getymissile() > myVaisseau.get(
													k).gety() - 300
													&& ((myMissile.get(i)).getJoueurMissile()) == (this.matricule)) {////seul les missiles "etrangers" ont de l'impact les vaisseau ne seront pas detruits par leurs propres missiles
						StdDraw.picture(myVaisseau.get(k).getx(), myVaisseau
								.get(k).gety(), "./src/crash.png");

						//	System.out.println(this.matricule);
						// MANQUE L'ENLEVEMENT DES VIES
					}

				}

			}

		}
	}



	public void colisionMineVaisseau(){
		myMines=Isep.myMines;
		myVaisseau = Isep.myVaisseau;
		for (int i = 0; i != myMines.size(); i++) {

			for (int k = 0; k != myVaisseau.size(); k++) {
				if (this.matricule != k) {

					if (myMines.get(i).getxmissile() < myVaisseau.get(k)
							.getx() + 300
							&& myMines.get(i).getxmissile() > myVaisseau.get(
									k).getx() - 300
									&& myMines.get(i).getymissile() < myVaisseau.get(
											k).gety() + 700
											&& myMines.get(i).getymissile() > myVaisseau.get(
													k).gety() - 700
													&& ((myMines.get(i)).getJoueurMissile()) == (this.matricule)) {////seul les missiles "etrangers" ont de l'impact les vaisseau ne seront pas detruits par leurs propres missiles
						StdDraw.picture(myVaisseau.get(k).getx(), myVaisseau
								.get(k).gety(), "./src/crash.png");

						//	System.out.println(this.matricule);
						// MANQUE L'ENLEVEMENT DES VIES
					}

				}

			}

		}




	}

	public void toucheInversee(int x1, int x2) {// la fonction de controle des touches a besoin du while true pour etre executé du
		// coup elle est remise dans la
		// classe Isep
		myrectangle = Terrain.getListeTerrain();

		if (this.x >= myrectangle.get(x1).getxter()
				&& this.x <= myrectangle.get(x2).getxter()) {//Il faut que la position en x du vaisseau soit compris entre x1eme rectangle et x2eme rectangle
			//pour que l'effet s'applique
			toucheinversee = true;
			//	System.out.println("inverse");
		} else {
			toucheinversee = false;
			//		System.out.println("pas inversée");
		}

	}

	public void gravite(int x1, int x2) {

		myrectangle = Terrain.getListeTerrain();
		if (this.x >= myrectangle.get(x1).getxter()
				&& this.x <= myrectangle.get(x2).getxter()) {// PARTIE GRAVITE
			this.setY(10); // setY est dans la class Vaisseau
			gravite=true;
			//	System.out.println("gravité");
		} else {
			gravite=false;
			//	System.out.println("pas de gravité");
		}
	}


}