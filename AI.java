import java.util.ArrayList;
import java.util.List;


public class AI extends Vaisseau{
private boolean top;
private boolean bottom;
private boolean left;
private boolean right;
private boolean missile;
private boolean mine;
public static int k=0;
public static int droiteTour=0;
public static List<Terrain> myrectangle =new ArrayList<>();
public static List<Asteroide> myAsteroide =new ArrayList<>();


	AI(double x, double y, double vx, double vy, int life, int score, int pMatricule, boolean pTop,boolean pBottom,boolean pLeft,boolean pRight,boolean pMissile,boolean pMine) {
		super(x, y, vx, vy, life, score, pMatricule);
		top=pTop;
		bottom=pBottom;
		left=pLeft;
		right=pRight;
		missile=pMissile;
		mine=pMine;
	}

public boolean getTop(){
	return top;
}
public boolean getBottom(){
	return top;
}
public boolean getLeft(){
	return top;
}
public boolean getRight(){
	return top;
}
public void setTop(boolean pTop){
	top=pTop;
}
public void setBottom(boolean pBottom){
	bottom=pBottom;
}
public void setLeft(boolean pLeft){
	left=pLeft;
}
public void setRight(boolean pRight){
	right=pRight;
}

public void setMissile(boolean pMissile){
	missile=pMissile;
}

public void setMine(boolean pMine){
	mine=pMine;
}

		//FONCTION AI ****************************************************************
			public void aiMove(){//Methode qui permet au vaisseau de s'adapter aux situtation
				myrectangle=Terrain.getListeTerrain();
				for(int i=20;i!=myrectangle.size();i++){//on prepare l'ensemble des rectangles et ensuite on les analysera un par un



					//cas pour une descente ****************
					if(/*1°: */ i%2!=0 && /*2°: */ Math.abs(myrectangle.get(i).getyter()-myrectangle.get(i).gethauteur())<=(Y_MAX/2.0)+1000 &&
							/*3°: */(this.y+1000>=myrectangle.get(i).getyter()-myrectangle.get(i).gethauteur()) && /*4°: */Math.abs((this.x+1000)-myrectangle.get(i).getxter())<=500){

						// 1° : on s'interesse qu'au rect du bas donc i doit etre impair,
						// 2 ° : si le bout du rectangle depasse un peu moins de la moitié de l'ecran, // 3° : si le vaisseau est plus haut que la position en y du bout du rect
						//4 °: si le vaisseau se trouve à une distance en x de moins de 500 au rectangle
						this.bottom=true;
					}

					//cas pour une montée ****************
					if(i%2==0 && Math.abs(myrectangle.get(i).getyter()+myrectangle.get(i).gethauteur())>=(Y_MAX/2.0)-1000 &&
							(this.y-1000<=myrectangle.get(i).getyter()+myrectangle.get(i).gethauteur()) && Math.abs((this.x+1000)-myrectangle.get(i).getxter())<=500){
						this.top=true;
					}
					if(Vaisseau.gravite){
						if(i%2==0 && this.y<=myrectangle.get(i).getyter()+myrectangle.get(i).gethauteur()+250){
							this.top=true;
						}
					}
					//Dans le cas niveau = Hard
					if(Isep.niveau.equals("Hard")){
						this.right=true;
						if(this.x>=X_MAX-100){
							this.left=true;
						}

					}//Dans le cas niveau = Normal
					else if(Isep.niveau.equals("Normal")){
						if(droiteTour%50==0){
							this.right=true;
						}
						if(this.x>=X_MAX-1000){
							this.left=true;
						}
					}
				}
			}

			public void aiMissile(){
				myAsteroide=Isep.myAsteroide;
				for(int i=0;i!=myAsteroide.size();i++){
					if(Math.abs(this.x-myAsteroide.get(i).getPositionxAste())<=1500 && Math.abs(this.y-myAsteroide.get(i).getPositionyAste())<=500 && myAsteroide.get(i).getPositionxAste()<=X_MAX-100){
						this.missile=true;//Si l'asteroide se trouve a une distance assez proche du vaisseau AI, il lui tire dessus

					}
					else{
						this.missile=false;
					}


				}


			}

			public void aiMine(){
				myVaisseau=Isep.myVaisseau;//L'erreur des mines c'etait qu'il manquait d'importer la liste (non vide) de "myVaisseau"
				if(Isep.niveau.equals("Normal")){

					if(myVaisseau.get(0).getx()+2000<=this.x){
						this.mine=true;
				
					}
					else{
						System.out.println("ok");
						this.mine=false;

					}


				}

				if(Isep.niveau.equals("Hard")){
					
					while(k!=myrectangle.size()){
						double posYBoutRecthaut=myrectangle.get(k+1).getyter()-myrectangle.get(k+1).gethauteur();//la coordonnée en y des rectangles bleus qu'on voit
						double posYBoutRectbas=myrectangle.get(k).getyter()+myrectangle.get(k).gethauteur();

						if(myVaisseau.get(0).getx()+2000<=this.x && Math.abs(posYBoutRecthaut-posYBoutRectbas)<=4700){//Si le vaisseau adverse (J1) est proche du vaisseau AI et qu-il y a un passage etroit, il largue une mine 
							this.mine=true;
							k=k+2;
							System.out.println("ok");
							break;
						}
						else{
							this.mine=false;
						}
						if(Math.abs(this.x+1000-myrectangle.get(k).getxter())<=100)
							k=k+2;
						else
							break;
					}

				}

			}


		//FONCTION de CONTROLE AI*******************************************	
			public void controlAImove(){

				if(this.top)
					top();
				if(this.left)
					left();
				if(this.right)
					right();
				if(this.bottom)
					bottom();
			}

			public void controlAImissilemine(){
				if (!this.missile) {// Si on n'appuye pas sur espace
					Isep.missileJ2 = false;
				}

				if (Isep.missileJ2 == false) {
					if (this.missile) {

						myMissile.add(new Missile(myVaisseau.get(1).getx(),
								myVaisseau.get(1).gety(), Missile
										.getvxmissile(), 0, Isep.r, myVaisseau
										.get(1).getMat(),true));

						Isep.missileJ2 = true;

					}
				}

				if (!this.mine) {//
					Isep.mineJ2 = false;
				}
				if (Isep.mineJ2 == false) {
					if (this.mine) {
							myMines.add(new Mines(myVaisseau.get(1).getx(),
								myVaisseau.get(1).gety(), Missile
										.getvxmissile(), 0, 0, myVaisseau
										.get(1).getMat(), true, Terrain.speed, 4));

						Isep.mineJ2 = true;

					}
				}

			}
		
}