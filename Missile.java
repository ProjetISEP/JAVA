import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Missile {
	
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	
	private double ymissile;
	private double xmissile;
	private double vymissile;
	private double vxmissile;
	private double rmissile;

	Missile(double xmissile, double ymissile, double vxmissile, double vymissile, double rmissile) {
		System.out.println("Création d'un missile avec des paramètres !");
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
	// LES SETTERS
	public void setxmissile(double setxmissile){
		xmissile=setxmissile+xmissile;
	}
	
	public void missile() {
		StdDraw.filledSquare(xmissile,ymissile,100);
		StdDraw.setPenColor(Color.green);
		xmissile=xmissile+100;
	}
}
