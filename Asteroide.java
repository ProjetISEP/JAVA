import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Asteroide {
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	private double yAste;
	private double vyAste;
	private double vxAste;
	private int rAste;
	private static double xAste=10000;
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static int seconde=0;
	private int lifeAste;
	public static List<Missile> myMissile =new ArrayList<>();
	public static List<Asteroide> myAsteroide =new ArrayList<>();
	Asteroide(double xAste, double yAste, double vxAste, double vyAste, int lifeAste) {
		System.out.println("Création d'un asteroide avec des paramètres !");
		this.xAste = xAste;
		this.yAste = yAste;
		this.vxAste = vxAste;
		this.vyAste = vyAste;
		this.lifeAste=lifeAste;
	}
	public double getPositionyAste() {
		return yAste;
	}
	public void move() {
		xAste=xAste-100;
		if(xAste==0){
			xAste=X_MAX+600;
			double p=Math.random()*10000;
			yAste=p;
		}
	}

	public void colision() { //colision entre vaisseau et astéroide
		myVaisseau=Isep.getListeVaisseau();
		if(yAste<myVaisseau.get(0).gety()+1000 && yAste>myVaisseau.get(0).gety()-1000 && xAste<myVaisseau.get(0).getx()+200 && xAste>myVaisseau.get(0).getx()-200){
			myVaisseau.get(0).setLife();
		}
	}
	public void colisionMissileAsteroide() { //colision entre missile et astéroide

		myMissile=Isep.getListeMissile();
		myAsteroide=Isep.getListeAsteroide();

		if(myMissile.size()!=0 && myAsteroide.size()!=0){

			for(int i=0;i!=myMissile.size();i=i+1){
				//for(int j=myAsteroide.size()-1;j!=-1;){
					//System.out.println(" "+(myMissile.get(i)).getymissile() +" "+ (myAsteroide.get(j)).yAste+"");
				if((myAsteroide.get(0)).yAste<(myMissile.get(i)).getymissile()+300 && myAsteroide.get(0).yAste>(myMissile.get(i)).getymissile()-300 && (myAsteroide.get(0)).xAste<(myMissile.get(i)).getxmissile()+300 && myAsteroide.get(0).xAste>(myMissile.get(i)).getxmissile()-300){
					//if((myAsteroide.get(j)).yAste<(myMissile.get(i)).getymissile()+300 && myAsteroide.get(j).yAste>(myMissile.get(i)).getymissile()-300 && (myAsteroide.get(j)).xAste<(myMissile.get(i)).getxmissile()+300 && myAsteroide.get(j).xAste>(myMissile.get(i)).getxmissile()-300){
						System.out.println("********************************************************");
						System.out.println("*********************Collison*****************************");
						System.out.println("*******************Collison*****************");
						System.out.println("********************************************************");
						System.out.println();
					}else{
					}
				}
			}
	//	}	
	}
	public void paint1(){
		StdDraw.picture(xAste, yAste, "./src/asteroide.png",360);
	}
}