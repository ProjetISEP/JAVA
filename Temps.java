public class Temps extends Thread
{
	// D�finition des attributs
	int heures;
	int minutes;
	int secondes;

	// D�finition des m�thodes
	public void definitHeure()
	{
		heures = 12;
		minutes = 30;
		secondes = 30;
	}

	public void incrementeHeure()
	{
		secondes++;
		if (secondes==60)
		{
			secondes=0;
			minutes++;
			if (minutes==60)
			{
				minutes=0;
				heures++;
				if (heures==24)
				{
					heures=0;
				}
			}
		}
	}

	protected void afficheHeure() 
	{
		System.out.println("Il est "+heures+":"+minutes+":"+secondes);
	}

	public static void main (String[] args) throws InterruptedException
	{
		Temps montre = new Temps(); // Nouvelle instance de la classe

		// Acc�s aux membres de la classe de l'objet avec le caract�re point : <objet>.<membre>
		montre.definitHeure();
		for (int i=0 ; i<1000 ; i=i+1)
		{
			sleep(1000);
			montre.incrementeHeure();
			montre.afficheHeure();
		}
	}
}