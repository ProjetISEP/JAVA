import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Terrain {
	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static double xrectangle=10000;
	public static List<Vaisseau> myVaisseau =new ArrayList<>();
	public static List<Terrain> myrectangle =new ArrayList<>();
	private double xter;
	private double yter;
	private double largeur;
	private double hauteur;
	public static double[] tab = new double[300];
	static double R=Math.random()*255;
	static double G=Math.random()*255;
	static double B=Math.random()*255;


	Terrain(double xter, double yter, double largeur, double hauteur) {
		System.out.print("fre");
		this.xter = xter;
		this.yter = yter;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	public static List<Terrain> getListeTerrain(){
		return myrectangle;
	}


	public static void tableauAleatoire(){ // Génere un tableau aléatoire pour la fonction decor
		for(int i=0;i!=300;i++){
			double nb=Math.random();
			tab[i]=nb;
		}
	}	
	public static void decor(){
		for(int i=0;i!=100;i++){
			if(i<20){
				StdDraw.filledRectangle(xrectangle+i*400, 500, 180,i*100+1000);//le haut
				StdDraw.filledRectangle(xrectangle+i*400, 9500, 180,i*100+1000);//le bas
				System.out.println(xrectangle+i*400);
			}else{
				StdDraw.filledRectangle(xrectangle+i*400, 500,tab[i]*140+50,1000+tab[i]*2300);//le haut
				StdDraw.filledRectangle(xrectangle+i*400, 9500,tab[i]*140+50,1000+(2300-(tab[i]*2300)));//le bas	
			}
			xrectangle=xrectangle-0.5;
			Color RANDOM=new Color((int)R,(int)G,(int)B);
			StdDraw.setPenColor(RANDOM);
		}
	}
	public static void colision(){
		for(int i=0;i!=3;i++){
			if(i<20){
				StdDraw.filledRectangle(xrectangle+i*400, 500, 180,i*100+1000);//le haut
				StdDraw.filledRectangle(xrectangle+i*400, 9500, 180,i*100+1000);//le bas
				System.out.println(xrectangle+i*400);
			}else{
				StdDraw.filledRectangle(xrectangle+i*400, 500,tab[i]*140+50,1000+tab[i]*2300);//le haut
				StdDraw.filledRectangle(xrectangle+i*400, 9500,tab[i]*140+50,1000+(2300-(tab[i]*2300)));//le bas	
			}
			xrectangle=xrectangle-0.5;
			Color RANDOM=new Color((int)R,(int)G,(int)B);
			StdDraw.setPenColor(RANDOM);
		}
		myVaisseau=Isep.getListeVaisseau();
		myVaisseau.get(0).getx();
		myVaisseau.get(0).gety();
	}
	public static void show(){
		//StdDraw.filledRectangle(5000, 5000, 1000,1000);
		Color RANDOM=new Color((int)R,(int)G,(int)B);
		StdDraw.setPenColor(RANDOM);
	}
	public static void show2(){
		StdDraw.filledRectangle(5000, 5000, 1000,1000);
	}
	

}