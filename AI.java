import java.util.ArrayList;
import java.util.List;

public class AI extends Vaisseau {

	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	private boolean missile;
	private boolean mine;
	public static int k = 0;
	public static int h = 0;
	public static int b = 0;
	public static int n = 0;
	public static int droiteTour = 0;
	public static boolean bouclierOn=false;
	public static List<Terrain> myrectangle = new ArrayList<>();
	public static List<Asteroide> myAsteroide = new ArrayList<>();
	public static List<Missile> myMines = new ArrayList<>();
	public static List<Double> haut = new ArrayList<>();
	public static List<Double> bas = new ArrayList<>();
	

	AI(double x, double y, double vx, double vy, int life, int score,
			int pMatricule, boolean pBouclier, boolean pTop, boolean pBottom,
			boolean pLeft, boolean pRight, boolean pMissile, boolean pMine, int pStock,boolean pHaut,boolean pBas,boolean pDroite,boolean pGauche, boolean pInertie) {
		super(x, y, vx, vy, life, score, pMatricule, pBouclier, pStock,pHaut, pBas, pDroite, pGauche, pInertie, vy, vy, vy, vy);
		top = pTop;
		bottom = pBottom;
		left = pLeft;
		right = pRight;
		missile = pMissile;
		mine = pMine;
	}

	public boolean getTop() {
		return top;
	}

	public boolean getBottom() {
		return top;
	}

	public boolean getLeft() {
		return top;
	}

	public boolean getRight() {
		return top;
	}

	public void setTop(boolean pTop) {
		top = pTop;
	}

	public void setBottom(boolean pBottom) {
		bottom = pBottom;
	}

	public void setLeft(boolean pLeft) {
		left = pLeft;
	}

	public void setRight(boolean pRight) {
		right = pRight;
	}

	public void setMissile(boolean pMissile) {
		missile = pMissile;
	}

	public void setMine(boolean pMine) {
		mine = pMine;
	}
	
	public static double normeVect(double xv, double yv, double xrect, double yrect){
		
		double norme;
		norme=Math.sqrt(Math.pow(xv-xrect, 2)+Math.pow(yv-yrect, 2));
		return norme;
	}

	// FONCTIONS AI
	// ****************************************************************
	public void aiMove() {// Methode qui permet au vaisseau de s'adapter aux
							// situtation
		myrectangle = Terrain.getListeTerrain();
		myAsteroide = Isep.myAsteroide;
		myVaisseau = Isep.myVaisseau;
		boolean cercleH=false;
		boolean cercleB=false;
		int j=0;
		for (int i = 20; i != myrectangle.size()-1; i++) {// on prepare l'ensemble
														// des rectangles et
														// ensuite on les
														// analysera un par un
			if (Vaisseau.gravite) {
				if (i % 2 == 0
						&& this.y <= myrectangle.get(i).getyter()
								+ myrectangle.get(i).gethauteur() + 750) {
					this.top = true;
				}
			}
			// Dans le cas niveau = Hard
			
			if (Menu.niveau.equals("Hard")) {
				if(!bouclierOn)
					this.right = true;
				
				if (Math.pow(myrectangle.get(i).getxter() - this.x, 2)
						+ Math.pow(myrectangle.get(i).getyter()
								+ myrectangle.get(i).gethauteur() - this.y, 2) <= Math
							.pow(1000, 2) && i % 2 == 0) {//Lorsque les rectangles du bas sont dans le cercle de centre V(vaisseau) et de rayon 1000
					haut.add(normeVect(this.x-50,this.y,myrectangle.get(i).getxter(),myrectangle.get(i).getyter()+myrectangle.get(i).gethauteur()));
					cercleB=true;
				}else{
					haut.add(0.0);
				}
				

				if (Math.pow(myrectangle.get(i+1).getxter() - this.x, 2)
						+ Math.pow(myrectangle.get(i+1).getyter()
								- myrectangle.get(i+1).gethauteur() - this.y, 2) <= Math
							.pow(1000, 2) && i % 2 == 0 ) {//Lorsque les rectangles du haut sont dans le cercle de centre V(vaisseau) et de rayon 1000
					bas.add(normeVect(this.x-50,this.y,myrectangle.get(i+1).getxter(),myrectangle.get(i+1).getyter()-myrectangle.get(i+1).gethauteur()));
					cercleH=true;
				}else{
					bas.add(0.0);
				}
	
				if(haut.size()!=0 && bas.size()!=0){//On va comparer la distance Vaisseau-rectBas avec Vaisseau-rectHaut et à partir de là le faire monter ou descendre suivant sa position
					if(haut.get(haut.size()-1)>bas.get(bas.size()-1) && (cercleH || cercleB)){
						this.top=true;
					
					}
					else if(haut.get(haut.size()-1)<bas.get(bas.size()-1) && (cercleH || cercleB)){
						this.bottom=true;
					}
				}	
				
				
				if (this.x >= X_MAX - 300) {
					this.left = true;
				}
				if(cercleH||cercleB){
					cercleH=false;
					cercleB=false;
				}
			}// Dans le cas niveau = NORMAL*************************************************************************************
			else if (Menu.niveau.equals("Normal")) {
				if (droiteTour % 50 == 0 && !bouclierOn) {
					this.right = true;
				}
				// cas pour une descente ****************
				if (/* 1°: */i % 2 != 0
						&&
						/* 2°: */(this.y + 1000 >= myrectangle.get(i).getyter()
								- myrectangle.get(i).gethauteur())
						&& /* 3°: */Math.abs((this.x + 1000)
								- myrectangle.get(i).getxter()) <= 500) {

					// 1° : on s'interesse qu'au rect du bas donc i doit etre
					// impair,
					// 2° : si le vaisseau est plus haut
					// que la position en y du bout du rect
					// 3 °: si le vaisseau se trouve à une distance en x de
					// moins de 500 au rectangle
					this.bottom = true;
				}

				// cas pour une montée ****************
				if (i % 2 == 0
						&& (this.y - 1000 <= myrectangle.get(i).getyter()
								+ myrectangle.get(i).gethauteur())
						&& Math.abs((this.x + 1000)
								- myrectangle.get(i).getxter()) <= 500) {
					this.top = true;
				}

				if (this.x >= X_MAX - 1000) {
					this.left = true;
				}
				if (myVaisseau.size() >= 1) {

					if (n < myVaisseau.size()) {// CE CODE PERMET au vaisseauAI
												// d'avancer lorsque le vasseau
												// adverse essaie d'avancer

						if (n != this.matricule) {
							if (myVaisseau.get(0).getx() + 500 >= this.x) {
								if (droiteTour % 3 == 0) {//Le vaisseau va avancer progressivement
									this.right = true;
								}

							} else if (myVaisseau.get(0).getx() + 500 >= this.x) {
								if (droiteTour % 2 == 0) {
									this.right = true;
						
								}

							} else if (this.x >= X_MAX / 2.0) {
								if (droiteTour % 2 == 0) {
									this.left = true;
							
								}
							}
							
							if(myVaisseau.get(n).stockMine>0 && (myVaisseau.get(n).getx()-this.x)<=2200 && this.x<myVaisseau.get(n).getx()
									&& Math.abs(myVaisseau.get(n).gety()-this.y)<=700/*&& this.x>=3*X_MAX/4.0*/){
								this.left=true;
			
							}

						}

					}
				}
			}//**********************************************************************************************
		}
	}

	public void aiMissile() {
		myAsteroide = Isep.myAsteroide;
		boolean passage=false;
		for (int i = 0; i != myAsteroide.size()-1; i++) {
				if (myAsteroide.get(i).getPositionxAste()-this.x<=4000
					&& Math.abs(myAsteroide.get(i).getPositionyAste()-this.y) <= 1000
					&& myAsteroide.get(i).getPositionxAste() <= X_MAX - 1000 && !passage) {
					
				this.missile = true;// Si l'asteroide se trouve a une distance
									// assez proche du vaisseau AI, il lui tire
									// dessus
				passage=true;
			}
				else if(passage){
					if(Isep.compteurSeconde%18==0){
						missileJ2 = false;
					}
			}
				
			else {
				this.missile = false;
			}

		}

	}

	public void aiMine() {
		myMines = Isep.myMines;
		myVaisseau = Isep.myVaisseau;// L'erreur des mines c'etait qu'il
										// manquait d'importer la liste (non
										// vide) de "myVaisseau"
		// NIVEAU NORMAL
		if (Menu.niveau.equals("Normal")) {
			// LANCMENT MISSILE************
			if (myVaisseau.get(0).getx() + 2000 <= this.x) {
				this.mine = true;

			} else {

				this.mine = false;

			}// ************************************

	
			
		}// *****************************************************

		// NIVEAU HARD
		if (Menu.niveau.equals("Hard")) {

			while (k != myrectangle.size()) {
				double posYBoutRecthaut = myrectangle.get(k + 1).getyter()
						- myrectangle.get(k + 1).gethauteur();// la coordonnée
																// en y des
																// rectangles
																// bleus qu'on
																// voit
				double posYBoutRectbas = myrectangle.get(k).getyter()
						+ myrectangle.get(k).gethauteur();

				if (myVaisseau.get(0).getx() + 2000 <= this.x
						&& Math.abs(posYBoutRecthaut - posYBoutRectbas) <= 4700) {// Si
																					// le
																					// vaisseau
																					// adverse
																					// (J1)
																					// est
																					// proche
																					// du
																					// vaisseau
																					// AI
																					// et
																					// qu-il
																					// y
																					// a
																					// un
																					// passage
																					// etroit,
																					// il
																					// largue
																					// une
																					// mine
					this.mine = true;
					k = k + 2;

					break;
				} else {
					this.mine = false;
				}
				if (Math.abs(this.x + 1000 - myrectangle.get(k).getxter()) <= 100)
					k = k + 2;
				else
					break;
			}
			
			// LANCMENT MISSILE************
						if (this.x-myVaisseau.get(0).getx()<=1000 && Math.abs(this.x-myVaisseau.get(0).gety())<=500 && !bouclierOn) {//Des que le vaisseau se rapproche du vaisseauAI, il largue une mine (à moins qu'il ait un bouclier)
							this.mine = true;

						} else {

							this.mine = false;

						}// ************************************


		}
		
		// ESQUIVE DES MISSILES ADVERSES*************
		for (int i = 0; i != myMines.size(); i++) {
			if (myMines.get(i).joueur != this.matricule) {
				if (this.x <= myVaisseau.get(0).getx()
						&& myMines.get(i).getxmissile() - this.x <= 1000
						&& Math.abs(myMines.get(i).getymissile() - this.y) <= 1000
						&& myMines.get(i).getxmissile() >= this.x
						&& myMines.get(i).getymissile() <= this.y) {
					this.top = true;

				}
				if (this.x <= myVaisseau.get(0).getx()
						&& myMines.get(i).getxmissile() - this.x <= 1000
						&& Math.abs(myMines.get(i).getymissile() - this.y) <= 1000
						&& myMines.get(i).getxmissile() >= this.x
						&& myMines.get(i).getymissile() >= this.y) {
					this.bottom = true;

				}
			}
		}
		// ESQUIVE DES ASTEROIDES*************
		for (int i = 0; i != myAsteroide.size()-1; i++) {
				if (
						myAsteroide.get(i).getPositionxAste() - this.x <= 1000
						&& Math.abs(myAsteroide.get(i).getPositionyAste()  - this.y) <= 1000
						&& myAsteroide.get(i).getPositionxAste()  >= this.x
						&& myAsteroide.get(i).getPositionyAste()  <= this.y) {
					this.top = true;

				}
				if (
						myAsteroide.get(i).getPositionxAste() - this.x <= 1000
						&& Math.abs(myAsteroide.get(i).getPositionyAste()  - this.y) <= 1000
						&& myAsteroide.get(i).getPositionxAste()  >= this.x
						&& myAsteroide.get(i).getPositionyAste()  >= this.y) {
					this.bottom = true;

				}
		}
		

	}
	
	public void takeBouclier(){
		
		myAsteroide = Isep.myAsteroide;
		for(int k=2;k<myAsteroide.size();k++){
		Bouclier b;
		b=(Bouclier) myAsteroide.get(k);
		for(int i=0;i!=myrectangle.size();i++){
		if(b.xAste>0 && b.xAste<X_MAX 
				&& ((b.yAste>myrectangle.get(i).getyter()+myrectangle.get(i).gethauteur() && i%2==0)||(  b.yAste<myrectangle.get(i).getyter()-myrectangle.get(i).gethauteur() && i%2!=0   ))
				&& Math.abs(this.x-b.xAste)<=1500 
				){
			if(b.xAste>=this.x){
				this.right=true;
				bouclierOn=true;
			}
			else
				bouclierOn=false;
			if(b.xAste<this.x){
				this.left=true;
				bouclierOn=true;
			}
			else
				bouclierOn=false;
			if(b.yAste>=this.y){
				this.top=true;
				bouclierOn=true;
			}
			else
				bouclierOn=false;
			if(b.yAste<=this.y){
				this.bottom=true;
				bouclierOn=true;
			}
			else
				bouclierOn=false;
		}
		else{
			bouclierOn=false;
		}
		}
	}
	}

	// FONCTION de CONTROLE AI*******************************************
	public void controlAImove() {

		if (this.top)
			top();
		if (this.left)
			left();
		if (this.right)
			right();
		if (this.bottom)
			bottom();
	}

	public void controlAImissilemine() {
		if (!this.missile) {// Si on n'appuye pas sur espace
			missileJ2 = false;
		}

		if (missileJ2 == false) {
			if (this.missile) {

				myMissile.add(new Missile(myVaisseau.get(1).getx(), myVaisseau
						.get(1).gety(), Missile.getvxmissile(), 0, r,
						myVaisseau.get(1).getMat(), true));
				
				missileJ2=true;

			}
		}
		
	

		if (!this.mine) {//
			mineJ2 = false;
		}
		if (mineJ2 == false) {
			if (this.mine) {
				myMines.add(new Mines(myVaisseau.get(1).getx(), myVaisseau.get(
						1).gety(), Missile.getvxmissile(), 0, 0, myVaisseau
						.get(1).getMat(), true, Terrain.speedTerrain, 4));
				myVaisseau.get(1).stockMine=myVaisseau.get(1).stockMine-1;
				mineJ2 = true;

			}
		}

	}

}