import java.util.ArrayList;
import java.util.List;

public class Menu {

	public static double X_MAX = 10000;
	public static double Y_MAX = 10000;
	public static boolean multi=false;
	public static boolean multi1;
	public static boolean ai=false;
	public static boolean ai1;
	public static boolean instruction=false;
	public static boolean menu=true;
	public static List<Vaisseau> myVaisseau = new ArrayList<>();
	public static int nbjoueurs;
	public static String niveau;
	public static boolean navigation=true;


	public static void nav() {
		myVaisseau=Isep.getListeVaisseau();

		while(navigation){
			StdDraw.show();

			//ai, multi, navigation, instruction et menu sont des varaibles propres au menu
			//ai1 et multi1 sert à la classe Isep
			while(multi){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_multi.png");
				if(1480<StdDraw.mouseX() && 3240>StdDraw.mouseX() && 204<StdDraw.mouseY() && 2316>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=1;
						multi1=true;
						myVaisseau.add(new Vaisseau(3000, 3000, 0, 0, 10, 0, 0,false,5));
						multi=false;
						navigation=false;
					}
				}else if(4266<StdDraw.mouseX() && 6038>StdDraw.mouseX() && 204<StdDraw.mouseY() && 2316>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=2;
						for(int g=0;g<nbjoueurs;g++){
							myVaisseau.add(new Vaisseau(3000, 3000+g*1000, 0, 0, 10, 0, g,false,5));
						}
						multi1=true;
						multi=false;
						navigation=false;
					}
				}else if(6992<StdDraw.mouseX() && 8776>StdDraw.mouseX() && 204<StdDraw.mouseY() && 2316>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						nbjoueurs=3;
						for(int g=0;g<nbjoueurs;g++){
							myVaisseau.add(new Vaisseau(3000, 3000+g*1000, 0, 0, 10, 0, g,false,5));
						}
						multi1=true;
						multi=false;
						navigation=false;
					}
				}else if(9277<StdDraw.mouseX() && 10060>StdDraw.mouseX() && 8542<StdDraw.mouseY() && 9972>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						menu=true;
						multi=false;
					}
				}
			}while(ai){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_ai.png");
				if(380<StdDraw.mouseX() && 3863>StdDraw.mouseX() && 842<StdDraw.mouseY() && 4978>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						myVaisseau.add(new Vaisseau(3000, 5000, 0, 0, 10, 0, 0,false,5));
						myVaisseau.add(new AI(6000, 5000, 0, 0, 10, 0, 1,false,false,false,false,false, false, false,5));
						niveau="Normal";
						ai1=true;
						ai=false;
						navigation=false;

					}
				}else if(5900<StdDraw.mouseX() && 9400>StdDraw.mouseX() && 842<StdDraw.mouseY() && 4978>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						myVaisseau.add(new Vaisseau(3000, 5000, 0, 0, 10, 0, 0,false,5));
						myVaisseau.add(new AI(6000, 5000, 0, 0, 10, 0, 1,false,false,false,false,false, false, false,5));
						niveau="Hard";
						ai1=true;
						ai=false;
						navigation=false;

					}
				}else if(9277<StdDraw.mouseX() && 10060>StdDraw.mouseX() && 8542<StdDraw.mouseY() && 9972>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						menu=true;
						ai=false;
					}
				}
			}while(menu){
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
				else if(7640<StdDraw.mouseX() && 9827>StdDraw.mouseX() && 358<StdDraw.mouseY() && 3020>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						instruction=true;
						multi=false;
						menu=false;
					}
				}
			}while(instruction){
				StdDraw.picture( X_MAX/2,  Y_MAX/2, "./src/menu_insctruction.png");        
				if(9277<StdDraw.mouseX() && 10060>StdDraw.mouseX() && 8542<StdDraw.mouseY() && 9972>StdDraw.mouseY()){
					if(StdDraw.mousePressed()){
						menu=true;
						instruction=false;
					}
				}
			}
		}
	}
}
