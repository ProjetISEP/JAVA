import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Missile {

	public static List<Vaisseau> myVaisseau =new ArrayList<>();

	protected double ymissile;
	protected double xmissile;
	protected double vymissile;
	protected static double vxmissile=120;//variable de classe ///////////////////////
	protected double rmissile;
	protected int joueur;
	//protected int acceleration;
	Missile(double xmissile, double ymissile, double vxmissile, double vymissile, double rmissile, int pJoueur/*, int pAcceleration*/) {//Ajout d'un parametre en plus : le missile appartient � un seul joueur
		//System.out.println("Cr�ation d'un missile avec des param�tres !");
		this.xmissile = xmissile;
		this.ymissile = ymissile;
		this.vxmissile = vxmissile;
		this.vymissile = vymissile;
		this.rmissile=rmissile;
		joueur=pJoueur;
		//acceleration=pAcceleration;
	}
	//LES GETTERS
	public double getxmissile(){
		return xmissile;
	}
	public double getymissile(){
		return ymissile;
	}
	public static double getvxmissile(){///////////////////////////////////////////////////////////////
		 		return vxmissile;
		 }
	public int getJoueurMissile(){
		return joueur;
	}
	// LES SETTERS
	public void setxmissile(double setxmissile){
		xmissile=setxmissile+xmissile;
	}

	public void missile() {
				if(this.joueur==0)
					StdDraw.setPenColor(Color.green);
				else
					StdDraw.setPenColor(Color.red);
		 		StdDraw.filledRectangle(this.xmissile, this.ymissile, this.rmissile+100, this.rmissile);//le +qqch est la longeur du missile
		  		this.xmissile=this.xmissile+this.vxmissile;	

		  	}

	public void mines(){//A ne pas effacer, la methode herité se trouve a la classe Mines

	}



/*	public void demiparabole(double x0, double y0, double v0x, double g) {///////////////////////////////////////////////////////////////////////
	
	///	x0=myVaisseau.get(0).getx();
	//	y0=myVaisseau.get(0).gety();
		
		double y;
		double x;

		// double t=0;
		double t = 0;
		double tmax = Math.sqrt(2 * y0 / g);// ici tmax correspond à l'arret du
											// missime une fois au sol mais dans
											// le jeu le missile peut s'arreter
											// sur le vaisseau adverse ou dans
											// le decor
		// StdDraw.picture(x0, y0, "./src/mines.png",180);
		for (int i = 0; i <= 10000000; i=i+100) {
			t = i * ((double) (tmax / (y0 / 2.0)));
			if (t >= tmax)
				break;

			x = v0x * t + x0;
			y = -(g / 2.0) * Math.pow((double) (x / v0x), 2) - (g / 2.0)
					* Math.pow((double) (x0 / v0x), 2) + g * x * x0
					* Math.pow((double) (1 / v0x), 2) + y0;
		
			StdDraw.picture(x, y, "./src/mines.png", 180);
			StdDraw.show(1);
			System.out.println(y);

		}
	}*/


}