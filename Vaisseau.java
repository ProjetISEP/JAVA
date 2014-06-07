import java.awt.Color;
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
	public static boolean collisionmissile;
	public static boolean collisionmine;
//	public static boolean top=false;
//	public static boolean bottom=false;
//	public static boolean right=false;
//	public static boolean left=false;
	protected  boolean droite=false;
	protected  boolean gauche;
	protected  boolean haut;
	protected boolean bas;
	protected boolean inertie;
	//public static boolean top1;
	protected double y;
	protected double x;
	protected double vy;
	protected double vx;
	protected int score;
	protected boolean bouclier;
	protected int matricule;
	protected int stockMine;
	private double inertieTop;
	private double inertieBottom;
	private double inertieRight;
	private double inertieLeft;
	public static float r = 60;
	public static boolean missileJ1 = false;
	public static boolean missileJ2 = false;
	public static boolean missileJ3 = false;
	public static boolean mineJ1 = false;
	public static boolean mineJ2 = false;
	public static boolean mineJ3 = false;
	


	static double R = Math.random() * 255;
	static double G = Math.random() * 255;
	static double B = Math.random() * 255;
	private int life;

	Vaisseau(double px, double py, double pvx, double pvy, int plife, int pscore,
			int pMatricule, boolean pbouclier, int pStock, boolean pHaut,boolean pBas,boolean pDroite,boolean pGauche,boolean pInertie, double pItop,double pIbottom, double pIright,double pIleft) {// Un vaisseau possède une matricule pour la
		// reconnaissance des missiles
		this.x = px;
		this.y = py;
		this.vx = pvx;
		this.vy = pvy;
		this.life = plife;
		this.score = pscore;
		this.bouclier = pbouclier;
		matricule = pMatricule;
		stockMine=pStock;
		droite=pDroite;
		gauche=pGauche;
		haut=pHaut;
        bas=pBas;
        inertie=pInertie;
        inertieTop=pItop;
    	inertieBottom=pIbottom;
    	inertieRight=pIright;
    	inertieLeft=pIleft;
        
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
	public void setx(double px){
		x=px;
	}
	public void setHaut(boolean h){
		haut=h;
	}
	public void setBas(boolean h){
		bas=h;
	}
	public void setGauche(boolean h){
		gauche=h;
	}
	public void setDroite(boolean h){
		droite=h;
	}
	public void setInertie(boolean pInertie){
		inertie=pInertie;
	}
	public int getlife() {
		return life;
	}
	public int getScore() {
		return score;
	}
	public int getmine() {
		return stockMine;
	}
	public int getMat() {
		return matricule;
	}
	public boolean getBouclier() {
		return bouclier;
	}
	public void vies() {

		myVaisseau = Isep.getListeVaisseau();
		for (int k = 0; k != myVaisseau.size(); k++) {// on parcourt tous les vaisseaux
			if(myVaisseau.get(k).life>1){
				for (int g = 0; g != myVaisseau.get(k).life; g++) {
					if(myVaisseau.get(k).getlife()!=0)
						StdDraw.picture(5200+1800*k+g*155, 8800, "./src/vie.png", 250, 280);
				}
			}else{
				int SecondeStart=Isep.seconde;
				StdDraw.text(5000, 5000, "Le joueur "+k+ " est mort");
			}
		}
	}
	
	public void affichageMines() {

		myVaisseau = Isep.getListeVaisseau();
		for (int k = 0; k != myVaisseau.size(); k++) {// on parcourt tous les vaisseaux
			if(myVaisseau.get(k).stockMine>=0){
						StdDraw.picture(5500+1700*k, 8000, "./src/mine2.png");
					    String pstockMine = Integer.toString(stockMine);
					    StdDraw.text(6000+1700*k, 8000, pstockMine);
			}
		}
	}
	public static boolean endGame(){
		int p=0;
		for (int k = 0; k != myVaisseau.size(); k++) {
			if(myVaisseau.get(k).life<0){
				p=p+1;
				if (p==myVaisseau.size()){
					return false;
				}
			}
			//System.out.println("p :"+p);
			//System.out.println("size :"+myVaisseau.size());
		}
		return true;
	}

	public void setLife() {
		myVaisseau = Isep.getListeVaisseau();
		life = getlife() - 1;
		StdDraw.picture(x, y, "./src/crash.png");
		//	for (int k = 0; k != myVaisseau.size(); k++) {
		if(this.matricule==0){
			StdDraw.clear(Color.green);
		}
		if(this.matricule==1){
			StdDraw.clear(Color.red);
		}
		if(this.matricule==2){
			StdDraw.clear(Color.blue);
		}	
		//	}
	}

	public void setY(double gravite) { // POUR LA GRAVITE
		y = y - gravite;
	}
	public void setX(double dead) { // POUR LA GRAVITE
		x = x - dead;
	}
	public void setBouclier(boolean pbouclier) { // POUR LA GRAVITE
		bouclier = pbouclier;
	}
	public void score() {
		myVaisseau = Isep.getListeVaisseau();
		if(Isep.compteurSeconde%9==0){
			score = score + (int)x/100;
		}
		for (int k = 0; k != myVaisseau.size(); k++) {
			String scoreString = Integer.toString(myVaisseau.get(k).score);
			StdDraw.text(6000 + k * 1700, 9300, scoreString);
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
		y  = y + 120;
	}
	public void bottom() {
		y = y - 120;
	}
	public void left() {
		x = x - 50;
	}
	public void right() {
		x = x + 50;
	}

	public void top1(){
		int k=0;
		if(matricule==0)
			k=38;
		else if(matricule==1)
			k=90;
		else if(matricule==2)
			k=85;
		if(StdDraw.isKeyPressed(k)){
			haut=true;
			bas=false;
			gauche=false;
			droite=false;
			if(inertieTop<75){	
				y=y+inertieTop;
				inertieTop=inertieTop+1;
			}
			else{
				y=y+75;
			}
		}
		else{
			if(haut){
				if(inertieTop>0){
					y=y+inertieTop;
					inertieTop=inertieTop-1;
				}
			}
			else{
				inertieTop=0;
			}
		}
	}

	public void bottom1() {
		int k=0;
		if(matricule==0)
			k=40;
		else if(matricule==1)
			k=83;
		else if(matricule==2)
			k=74;
		if(StdDraw.isKeyPressed(k)){
			haut=false;
			bas=true;
			gauche=false;
			droite=false;
			if(inertieBottom<75){	
				y=y-inertieBottom;
				inertieBottom=inertieBottom+1;
			}
			else{
				y=y-75;
			}
		}
		else{
			if(bas){
				if(inertieBottom>0){
					y=y-inertieBottom;
					inertieBottom=inertieBottom-1;
				}
			}
			else{
				inertieBottom=0;
			}
		}
	}
	public void left1() {
		int k=0;
		if(matricule==0)
			k=37;
		else if(matricule==1)
			k=81;
		else if(matricule==2)
			k=72;
		if(StdDraw.isKeyPressed(k)){
			haut=false;
			bas=false;
			gauche=true;
			droite=false;
			if(inertieLeft<40){	
				x=x-inertieLeft;
				inertieLeft=inertieLeft+0.5;
			}
			else{
				x=x-40;
			}
		}
		else{
			if(gauche){
				if(inertieLeft>0){
					x=x-inertieLeft;
					inertieLeft=inertieLeft-0.5;
				}
			}
			else{
				inertieLeft=0;
			}
		}
	}
	public void right1() {
		int k=0;
		if(matricule==0)
			k=39;
		else if(matricule==1)
			k=68;
		else if(matricule==2)
			k=75;
		if(StdDraw.isKeyPressed(k)){
			haut=false;
			bas=false;
			gauche=false;
			droite=true;
			if(inertieRight<40){	
				x=x+inertieRight;
				inertieRight=inertieRight+0.5;
			}
			else{
				x=x+40;
			}
		}
		else{
			if(droite){
				if(inertieRight>0){
					x=x+inertieRight;
					inertieRight=inertieRight-0.5;
				}
			}
			else{
				inertieRight=0;
			}
		}
	}
	public static void controlPlayer1() {
		if (!toucheinversee) {

			if (StdDraw.isKeyPressed(38)){
				(myVaisseau.get(0)).top();
				(myVaisseau.get(0)).setHaut(true);		
				
			}
		//	else
			//	(myVaisseau.get(0)).setHaut(false);
			if (StdDraw.isKeyPressed(37)){
				(myVaisseau.get(0)).left();
				(myVaisseau.get(0)).setGauche(true);
			}
	//		else
			//	(myVaisseau.get(0)).setGauche(false);
			if (StdDraw.isKeyPressed(39)){
				(myVaisseau.get(0)).right();
				(myVaisseau.get(0)).setDroite(true);
			}
		//	else
			//	(myVaisseau.get(0)).setDroite(false);
			if (StdDraw.isKeyPressed(40)){
				(myVaisseau.get(0)).bottom();
				(myVaisseau.get(0)).setBas(true);
			}
			//else
				//(myVaisseau.get(0)).setBas(false);
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
				if(myVaisseau.get(0).stockMine>0){// 517 correspond � la touche "!"
					myMines.add(new Mines(myVaisseau.get(0).getx(),
							myVaisseau.get(0).gety(), Missile
							.getvxmissile(), 0, 0, myVaisseau
							.get(0).getMat(), true, Terrain.speedTerrain, 4));// Deux param�tres ont �t� rajout�s : la vitesse de la mine et son 
					// acceleration, m�me si on voit getvxmissile on ne travaillera que sur vxMine
					mineJ1 = true;
					myVaisseau.get(0).stockMine=myVaisseau.get(0).stockMine-1;
				}
			}
		}
	}

	public void controlPlayerInertie(){

		top1();
		bottom1();
		left1();
		right1();


	}
	public static void controlPlayer2() {
		if (!toucheinversee) {
			if (StdDraw.isKeyPressed(90)){
				(myVaisseau.get(1)).top();
				(myVaisseau.get(1)).setHaut(true);		
				
			}
		//	else
			//	(myVaisseau.get(0)).setHaut(false);
			if (StdDraw.isKeyPressed(81)){
				(myVaisseau.get(1)).left();
				(myVaisseau.get(1)).setGauche(true);
			}
	//		else
			//	(myVaisseau.get(0)).setGauche(false);
			if (StdDraw.isKeyPressed(68)){
				(myVaisseau.get(1)).right();
				(myVaisseau.get(1)).setDroite(true);
			}
		//	else
			//	(myVaisseau.get(0)).setDroite(false);
			if (StdDraw.isKeyPressed(83)){
				(myVaisseau.get(1)).bottom();
				(myVaisseau.get(1)).setBas(true);
			}
			//else
				//(myVaisseau.get(0)).setBas(false);
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
							.get(1).getMat(), true, Terrain.speedTerrain, 4));
					mineJ2 = true;
					myVaisseau.get(1).stockMine=myVaisseau.get(1).stockMine-1;
				}
			}
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
							.get(2).getMat(), true, Terrain.speedTerrain, 4));
					mineJ3 = true;
					myVaisseau.get(2).stockMine=myVaisseau.get(2).stockMine-1;
				}
			}
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
	public void paintBouclier(int i) {
		StdDraw.picture(x, y, "./src/vaisseau"+intToString(i)+"_bouclier.png", 180);
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
						myMissile.get(i).life=false;

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
						myMines.get(i).life=false;
						myVaisseau.get(k).setLife();
						// MANQUE L'ENLEVEMENT DES VIES
					}

				}

			}

		}




	}
	
	public void colisionVaisseauAVaisseau(){
		myVaisseau = Isep.myVaisseau;
		for(int i=0;i!=myVaisseau.size();i++){
			if(this.matricule!=myVaisseau.get(i).matricule && !this.inertie){
			//	System.out.print(this.droite);
				if (Math.abs(this.x-myVaisseau.get(i).getx())<=500 && Math.abs(this.y-myVaisseau.get(i).gety())<=250 && this.x-300<myVaisseau.get(i).getx() && this.droite ){
				myVaisseau.get(i).setx(myVaisseau.get(i).getx()+80);//
			//	System.out.println("okdr");
				}
				if (Math.abs(this.x-myVaisseau.get(i).getx())<=500 && Math.abs(this.y-myVaisseau.get(i).gety())<=250 && this.x+300>myVaisseau.get(i).getx() && this.gauche){
					myVaisseau.get(i).setx(myVaisseau.get(i).getx()-80);//
				//	System.out.println("okgau");
				}
				if (Math.abs(this.x-myVaisseau.get(i).getx())<=250 && Math.abs(this.y-myVaisseau.get(i).gety())<=1000 && this.y-300<myVaisseau.get(i).gety() && this.haut){
					myVaisseau.get(i).sety(myVaisseau.get(i).gety()+75);//
			//		System.out.println("okhau");
				}
				if (Math.abs(this.x-myVaisseau.get(i).getx())<=250 && Math.abs(this.y-myVaisseau.get(i).gety())<=1000 && this.y+300<myVaisseau.get(i).gety() && this.bas){
					myVaisseau.get(i).sety(myVaisseau.get(i).gety()-75);//
			//		System.out.println("okhau");
				}
				
			}
		}
		
		
		
		
	}

	public void toucheInversee(int x1, int x2) {// la fonction de controle des touches a besoin du while true pour etre executé du
		// coup elle est remise dans la
		// classe Isep
		myrectangle = Terrain.getListeTerrain();
		if(Terrain.niveau1){
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
	}
	
	

	public void gravite(int x1, int x2) {

		myrectangle = Terrain.getListeTerrain();
		if(Terrain.niveau1){
		if (this.x >= myrectangle.get(x1).getxter()
				&& this.x <= myrectangle.get(x2).getxter()) {// PARTIE GRAVITE
			this.setY(20); // setY est dans la class Vaisseau
			gravite=true;
			//	System.out.println("gravité");
		} else {
			gravite=false;
			//	System.out.println("pas de gravité");
		}
	}
	}

}
