
public class Bouclier extends Asteroide {
	public int laSeconde;//correspond à l'instant où le vaisseau capture le bouclier
	public int deltaT=20;//interval de temps pour le bouclier
	Bouclier(double xAste, double yAste, double vxAste, double vyAste,int lifeAste) {
		super(xAste, yAste, vxAste, vyAste, lifeAste);
	}

	public void setX(double m){
		xAste=xAste+m;
	}
	public void colisionAsteroideVaisseau() { //colision entre vaisseau et astéroide
		myVaisseau=Isep.getListeVaisseau();
		for(int i=0;i!=myVaisseau.size();i++){//colision pour tout les vaisseaux
			if(yAste<myVaisseau.get(i).gety()+1000 && yAste>myVaisseau.get(i).gety()-1000 && xAste<myVaisseau.get(i).getx()+200 && xAste>myVaisseau.get(i).getx()-200 && this.lifeAste!=0){
				myVaisseau.get(i).setBouclier(true);
				laSeconde=Isep.seconde;//on enregistre la seconde
				setX(10000+Math.random()*20000);
				setY(Math.random()*10000);
			}
			if(Isep.seconde==laSeconde+deltaT){ //on regarde si on depasse le deltaT
				myVaisseau.get(i).setBouclier(false);
			}
		}
	}
	
	public void paint1(){
		StdDraw.picture(xAste, yAste, "./src/bulle.png",360);
	}

}
