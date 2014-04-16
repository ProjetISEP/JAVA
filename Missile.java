import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Missile {
	
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	
	private double ymissile;
	private double xmissile;
	private double vymissile;
	private static double vxmissile=120;//variable de classe(static) ///////////////////////
	private double rmissile;

	Missile(double xmissile, double ymissile, double vxmissile, double vymissile, double rmissile) {
		//System.out.println("Création d'un missile avec des paramètres !");
		this.xmissile = xmissile;
		this.ymissile = ymissile;
		this.vxmissile = vxmissile;
		this.vymissile = vymissile;
		this.rmissile=rmissile;
	}
	//LES GETTERS
	public double getxmissile(){
		return xmissile;
	}
	public double getymissile(){
		return ymissile;
	}
	public static double getvxmissile(){//Creation du getter pour la variable de classe vxmissile
		
		return vxmissile;
	}
	// LES SETTERS
	public void setxmissile(double setxmissile){
		xmissile=setxmissile+xmissile;
	}
	
	
//	boolean retour=false;
	public void missile() {
		//StdDraw.filledSquare(xmissile,ymissile,100);
		StdDraw.filledRectangle(this.xmissile, this.ymissile, this.rmissile+100, this.rmissile);//le +qqch est la longeur du missile
		StdDraw.setPenColor(Color.green);
		this.xmissile=this.xmissile+vxmissile;	
	}
}
