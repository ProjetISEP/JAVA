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
	//	System.out.println("Cr�ation d'un asteroide avec des param�tres !");
		this.xAste = xAste;
		this.yAste = yAste;
		this.vxAste = vxAste;
		this.vyAste = vyAste;
		this.lifeAste=lifeAste;
	}
	public double getPositionyAste() {
		return yAste;
	}
	public double getPositionxAste() {
		return xAste;
	}
	public double getlifeAste() {
		return lifeAste;
	}
	public void setLifeAste(){
		lifeAste=lifeAste-1;
	}
	public void move() {
		xAste=xAste-50;
		if(xAste==0){
			xAste=X_MAX+600;
			double p=Math.random()*10000;
			yAste=p;
		}
	}

	public void colision() { //colision entre vaisseau et ast�roide
		myVaisseau=Isep.getListeVaisseau();
		for(int i=0;i!=myVaisseau.size();i++){//colision pour tout les vaisseaux
			if(yAste<myVaisseau.get(i).gety()+1000 && yAste>myVaisseau.get(i).gety()-1000 && xAste<myVaisseau.get(i).getx()+200 && xAste>myVaisseau.get(i).getx()-200){
				myVaisseau.get(i).setLife();
			}
		}
	}
	public void colisionMissileAsteroide() { //colision entre missile et ast�roide
		myMissile=Isep.getListeMissile();
		myAsteroide=Isep.getListeAsteroide();
		if(myMissile.size()!=0 && myAsteroide.size()!=0){
			for(int i=0;i!=myMissile.size();i=i+1){
				for(int k=0;k!=myAsteroide.size();k=k+1){
				if((myAsteroide.get(k)).yAste<(myMissile.get(i)).getymissile()+300 && myAsteroide.get(k).yAste>(myMissile.get(i)).getymissile()-300 && (myAsteroide.get(k)).xAste<(myMissile.get(i)).getxmissile()+300 && myAsteroide.get(k).xAste>(myMissile.get(i)).getxmissile()-300){
					//if((myAsteroide.get(j)).yAste<(myMissile.get(i)).getymissile()+300 && myAsteroide.get(j).yAste>(myMissile.get(i)).getymissile()-300 && (myAsteroide.get(j)).xAste<(myMissile.get(i)).getxmissile()+300 && myAsteroide.get(j).xAste>(myMissile.get(i)).getxmissile()-300){
				/*	System.out.println("********************************************************");
					System.out.println("*********************Collison*****************************");
					System.out.println("*******************Collison*****************");
					System.out.println("********************************************************");
					System.out.println();*/
					myAsteroide.get(k).setLifeAste();
				}
				}
				
			}
		}	
	}
	public void paint1(){
			StdDraw.picture(xAste, yAste, "./src/asteroide.png",360);

	}
}