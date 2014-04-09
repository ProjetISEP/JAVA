class Temps {

    public static void main(String [] args) {

        int leTempsEnMillisecondes=1000;

        for (int i=10;i>=0;i--) {
            try {
                Thread.sleep (leTempsEnMillisecondes);
            } 
            catch (InterruptedException e) {
                System.out.print("erreur");
            }
            System.out.print(i);
        }
    }
}