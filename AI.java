import java.util.ArrayList;
import java.util.List;


public class AI extends Vaisseau{
private boolean top;
private boolean bottom;
private boolean left;
private boolean right;
public static int droiteTour=0;
public static List<Terrain> myrectangle =new ArrayList<>();

	AI(double x, double y, double vx, double vy, int life, int score, int pMatricule, boolean pTop,boolean pBottom,boolean pLeft,boolean pRight) {
		super(x, y, vx, vy, life, score, pMatricule);
		top=pTop;
		bottom=pBottom;
		left=pLeft;
		right=pRight;
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

			public void ai(){
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
					
					if(Isep.niveau.equals("Hard")){
						this.right=true;
						if(this.x>=X_MAX-100){
							this.left=true;
						}
					}
					else if(Isep.niveau.equals("Normal")){
						
						
							if(droiteTour%50==0){
								this.right=true;
								System.out.println("ok"+droiteTour);}
							if(this.x>=X_MAX-1000){
								this.left=true;
							}
				
						
						
					}
				
					
				}
			}
			
			
			
			public void controlAI(){
		
				if(this.top)
					top();
				if(this.left)
					left();
				if(this.right)
					right();
				if(this.bottom)
					bottom();
			}
}
