import java.util.ArrayList;
import java.util.List;

public class Menu {

	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static boolean multi=false;
	public static boolean ai=false;
	public static boolean menu=true;
	public static List<Vaisseau> myVaisseau = new ArrayList<>();
	public static int nbjoueurs;
	public static String niveau;
	public static boolean navigation=true;


	public static void nav() {
		myVaisseau=Isep.getListeVaisseau();

		while(navigation){
			StdDraw.show();
			if(multi){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_multi.png");
				if(380<StdDraw.mouseX() && 1858>StdDraw.mouseX() && 754<StdDraw.mouseY() && 3900>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=1;
						multi=true;
						myVaisseau.add(new Vaisseau(3000, 3000, 0, 0, 1000, 0, 0));
						navigation=false;
					}
				}else if(3777<StdDraw.mouseX() && 6332>StdDraw.mouseX() && 754<StdDraw.mouseY() && 3900>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=2;
						for(int g=0;g<nbjoueurs;g++){
							myVaisseau.add(new Vaisseau(3000, 3000+g*1000, 0, 0, 1000, 0, g));
						}
						multi=true;
						navigation=false;
					}
				}else if(7114<StdDraw.mouseX() && 9681>StdDraw.mouseX() && 754<StdDraw.mouseY() && 3900>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=3;
						for(int g=0;g<nbjoueurs;g++){
							myVaisseau.add(new Vaisseau(3000, 3000+g*1000, 0, 0, 1000, 0, g));
						}
						multi=true;
						navigation=false;
					}
				}
			}else if(ai){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_ai.png");
				if(380<StdDraw.mouseX() && 3863>StdDraw.mouseX() && 842<StdDraw.mouseY() && 4978>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						myVaisseau.add(new Vaisseau(3000, 5000, 0, 0, 10, 0, 0));
						myVaisseau.add(new AI(6000, 5000, 0, 0, 1000, 0, 1,false,false,false,false, false, false));
						niveau="Normal";
						navigation=false;

					}
				}else if(5900<StdDraw.mouseX() && 9400>StdDraw.mouseX() && 842<StdDraw.mouseY() && 4978>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						myVaisseau.add(new Vaisseau(3000, 5000, 0, 0, 10, 0, 0));
						myVaisseau.add(new AI(6000, 5000, 0, 0, 1000, 0, 1,false,false,false,false, false, false));
						niveau="Hard";
						navigation=false;

					}
				}
			}else if(menu){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_principal.png");        
				if(210<StdDraw.mouseX() && 3200>StdDraw.mouseX() && 4400<StdDraw.mouseY() && 7046>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						multi=true;
						ai=false;
						menu=false;
					}
				}else if(6784<StdDraw.mouseX() && 9803>StdDraw.mouseX() && 4472<StdDraw.mouseY() && 7024>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						ai=true;
						multi=false;
						menu=false;
					}
				}
			}
		}
	}}
