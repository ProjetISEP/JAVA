import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Vaisseau {
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Missile> myMissile = new ArrayList<>();
	
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static float r = 6;
	public static double t = 0;
	private double y;
	private double x;
	private double vy;
	private double vx;
	private int score;
	private int matricule;
	
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;
	private int life;
	Vaisseau(double x, double y, double vx, double vy, int life, int score,
			int pMatricule) {//Un vaisseau possède une matricule pour la reconnaissance des missiles
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
<<<<<<< HEAD
<<<<<<< HEAD
		this.life=life;
		this.score=score;
=======
=======
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
		this.life = life;
		this.score = score;
		matricule = pMatricule;

<<<<<<< HEAD
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
=======
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
	}
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	public int getlife(){
		return life;
	}
	public int getScore(){
		return score;
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
	public int getMat() {
		return matricule;
	}
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
=======
	public int getMat() {
		return matricule;
	}
>>>>>>> 58c80d6203c6c802f7754e9140ee875c8debf9b5
	public void vies(){
		myVaisseau=Isep.getListeVaisseau();
		for(int k=0;k!=myVaisseau.size();k++){
			String lifeString=Integer.toString(myVaisseau.get(k).life);
			StdDraw.text(7000+k*1000,9500,lifeString);
			StdDraw.setPenColor(Color.WHITE);
		}
	}
	public void setLife(){
		myVaisseau=Isep.getListeVaisseau();
		life=getlife()-1;		
		StdDraw.picture(x,y, "./src/crash.png");
	}
	public void setY(double gravite){ //POUR LA GRAVITE
		y=y-gravite;
	}
	public void score() {
		myVaisseau=Isep.getListeVaisseau();
		if(0<x && x<4000)
			score=score+100;
		if(4000<x && x<6000)
			score=score+200;
		if(6000<x && x<8000)
			score=score+400;
		if(8000<x && x<10000)
			score=score+600;
		
		for(int k=0;k!=myVaisseau.size();k++){
			String scoreString=Integer.toString(myVaisseau.get(k).score);
			StdDraw.text(7000+k*1000,8500,scoreString);
			StdDraw.setPenColor(Color.WHITE);
		}
			
	}
	public void move() {
		x=x-10;
	}
	public void bordure() {
		if (y<=0){
			y=100;
		}else if (x>X_MAX){
			x= X_MAX-100;
		}else if(x<=20){
			x=100;
		}else if(y>Y_MAX){
			y= Y_MAX-100;
		}

	}

	public void top() {
		y = y + 100;
	}
	public void bottom() {
		y = y - 100;
	}
	public void left() {
		x = x - 100;
	}
	public void right() {
		x = x + 100;
	}
	public static void controlPlayer1normal(){
		if(StdDraw.isKeyPressed(38))
			(myVaisseau.get(0)).top();
		if(StdDraw.isKeyPressed(37))
			(myVaisseau.get(0)).left();
		if(StdDraw.isKeyPressed(39))
			(myVaisseau.get(0)).right();
		if(StdDraw.isKeyPressed(40))
			(myVaisseau.get(0)).bottom();
	}
	public static void controlPlayer1inverse(){
		if(StdDraw.isKeyPressed(38))
			(myVaisseau.get(0)).bottom();
		if(StdDraw.isKeyPressed(37))
			(myVaisseau.get(0)).right();
		if(StdDraw.isKeyPressed(39))
			(myVaisseau.get(0)).left();
		if(StdDraw.isKeyPressed(40))
			(myVaisseau.get(0)).top();
	}
	public static void FinDePartie(){// A TERMINER
		int totalLife=0;
		int [] tableauLife= new int[myVaisseau.size()];
		for(int k=0;k!=myVaisseau.size();k++){
			if(myVaisseau.get(k).getlife()==0){
				
			}
		}
	}
	public void paint0(){
		StdDraw.picture(x, y, "./src/vaisseau1.png",180);
	}
	public void paint1(){
		StdDraw.picture(x, y, "./src/vaisseau2.png",180);
	}
	
	public void colisionMissileVaisseau() {//methode qui permet de faire apparaitre un crash lorsqu'un vaisseau se prend un missile 
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
							&& ((myMissile.get(i)).getJoueurMissile()) == (this.matricule)) {//seul les missiles "etrangers" ont de l'impact les vaisseau ne seront pas detruits par leurs propres missiles
						StdDraw.picture(myVaisseau.get(k).getx(), myVaisseau
								.get(k).gety(), "./src/crash.png");
						System.out.println(this.matricule);
						// MANQUE L'ENLEVEMENT DES VIES
					}

				}

			}

		}
	}
}
