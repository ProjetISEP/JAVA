
public class Mines extends Missile{

//	private double xMine;
//	private double yMine;
//	private double vxMine;
	//private double vyMine;
	private int acceleration;//
	private double vxMine;//La vxmine a ete rajouté pour ne pas modifier la valeur de vxmissile;
	
	Mines(double xmissile, double ymissile, double vxmissile, double vymissile, double rmissile, int pJoueur, boolean pLife, double pVxMine, int pAcceleration) {
		super(xmissile, ymissile, vxmissile, vymissile, rmissile, pJoueur, pLife);
		vxMine=pVxMine;
		acceleration=pAcceleration;
	}
	
	
	public void mines(){
		StdDraw.picture(this.xmissile, this.ymissile, "./src/mine2.png", 180);
	//	boolean acceleration=false;
		if(this.acceleration>0){// la mine largué aura une certaine acceleration pdt un temps bref
			this.xmissile=this.xmissile-this.acceleration*(this.vxMine);
			this.acceleration=this.acceleration-1;
		}
		else{
			this.xmissile=this.xmissile-this.vxMine;
		}
		
	}

}
