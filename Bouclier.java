
public class Bouclier extends Asteroide {

	Bouclier(double xAste, double yAste, double vxAste, double vyAste,int lifeAste) {
		super(xAste, yAste, vxAste, vyAste, lifeAste);
	}

	public void setX(double m){
		xAste=xAste+m;
	}
	public void colision() { //colision entre vaisseau et astéroide
		myVaisseau=Isep.getListeVaisseau();
		for(int i=0;i!=myVaisseau.size();i++){//colision pour tout les vaisseaux
			if(yAste<myVaisseau.get(i).gety()+1000 && yAste>myVaisseau.get(i).gety()-1000 && xAste<myVaisseau.get(i).getx()+200 && xAste>myVaisseau.get(i).getx()-200 && this.lifeAste!=0){
				myVaisseau.get(i).setBouclier(true);;
			}
		}
	}
	
	public void paint1(){
		StdDraw.picture(xAste, yAste, "./src/bulle.png",360);
	}

}
