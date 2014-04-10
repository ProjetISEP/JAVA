import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Vaisseau {
	public static List<Vaisseau> myVaisseau =new ArrayList<>();

	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static float r = 6;
	public static double t = 0;
	private double y;
	private double x;
	private double vy;
	private double vx;
	public static int score=0;

	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;
	private static int life;

	Vaisseau(double x, double y, double vx, double vy, int life) {
		System.out.println("Cr�ation d'un Vaisseau avec des param�tres !");
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.life=life;
	}
	public void vies(String t){
		myVaisseau=Isep.getListeVaisseau();
		if(t.equals("vrai")){
			life=life-1;
			StdDraw.picture(myVaisseau.get(0).getx(), myVaisseau.get(0).gety(), "./src/crash.png");
		}
		String lifeString=Integer.toString(life);
		StdDraw.text(7000,9000,lifeString);
		StdDraw.setPenColor(Color.WHITE);
	}
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	public void score() {
		myVaisseau=Isep.getListeVaisseau();
		if(0<x && x<4000)
			score=score+100;
		if(4000<x && x<6000)
			score=score+200;
		if(6000<x && x<8000)
			score=score+400;
		if(8000<x && x<10000)
			score=score+600;

		String rrr=Integer.toString(score);
		StdDraw.text(5000,9000,rrr);
		StdDraw.setPenColor(Color.magenta);	
	}
	public void move() {
		x=x-7;
		
	}
	public void bordure() {
		if (y<=0){
			y=100;
		}else if (x>X_MAX){
			x= X_MAX-100;
		}else if(x<=20){
			x=100;
		}else if(y>Y_MAX){
			y= Y_MAX-100;
		}
		
	}

	public void top() {
		y = y + 100;
	}
	public void bottom() {
		y = y - 100;
	}
	public void left() {
		x = x - 100;
	}
	public void right() {
		x = x + 100;
	}
	public void paint0(){
		StdDraw.picture(x, y, "./src/vaisseau1.png",180);
	}
	public void paint1(){
		StdDraw.picture(x, y, "./src/vaisseau2.png",180);
	}
}
