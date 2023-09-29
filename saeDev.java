import java.util.Scanner;

public class saeDev {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int colonne = 7;//nombre de colonne
        int ligne = 6;//nombre de ligne

        char[][] plateau = new char[ligne][colonne]; //creation d'un tableau qui va acceuillir le plateau

        remplissagePlateau(plateau);//remplissage du tableau

        char joueur1 = 'X';
        char joueur2 = '0';
        int choix;


        for (int tour = 1; tour < 43; tour++) {// il peut y avoir que 42 coup au puissance 4
            siPlein(plateau); //regarde si le plateau est plein à chaque tour
            if (tour % 2 == 1) { // au tour du joueur1 si le tour est impaire et on commence par impaire car on commence par 1
                do {
                    System.out.println("tour " + tour);
                    System.out.print("choisissez une colonne entre 1 et 7 :");
                    choix = scanner.nextInt();// choix du colonne

                }
                while (choix < 1 || choix > 7); //verifie si la saisie est compris entre 1 et 7

                if (plateau[0][choix - 1] != '.') { // verifie si la collone est pleine
                    afficherPlateau(plateau);
                    System.out.println("pleine veuillez choisir un autre case");
                    choix = scanner.nextInt();
                }
                verif(plateau, choix - 1, joueur1); // verifie si le coup est valide
                afficherPlateau(plateau);

                if (siGagne(plateau,joueur1) == true){
                    System.out.println("VICTOIRE DU JOUEUR 1!!!");
                    break;
                }
                else
                    if (siPlein(plateau)== true) {
                        System.out.println("MATCH NUL!");
                        break;
                    }


            } else {
                do { // pour joueur 2 si le tour est paire
                    System.out.println("tour " + tour);
                    System.out.print("choisissez une colonne entre 1 et 7 :");
                    choix = scanner.nextInt();


                }
                while (choix < 1 || choix > 7);//si la saisi n'est pas compris entre 1 et 7
                int col2 = 6;

                if (plateau[0][choix - 1] != '.') { // verifie si le plateau est plein en regardant si la premiere ligne est pleine
                    afficherPlateau(plateau);
                    System.out.println("pleine veuillez choisir un autre case");
                    choix = scanner.nextInt();
                }
                verif(plateau, choix - 1, joueur2);
                afficherPlateau(plateau);

                if (siGagne(plateau,joueur2) == true){ //si il y a victoire
                    System.out.println("VICTOIRE DU JOUEUR 2!!!");
                    break; // arrete la boucle for
                }
                 else
                     if (siPlein(plateau)== true) { //si le plateau est plein
                        System.out.println("MATCH NUL!");
                        break;//arrete la boucle for
                    }

            }
        }


    }

    public static void remplissagePlateau(char[][] tab){
        int nb=1;
        System.out.print(" ");//affiche un espace avant les chiffre
        for (int i = 0; i < tab.length ; i++) { //pour la numerotation des colones
            System.out.print(" "+nb+ " ");
            nb++;
        }
        System.out.println();

        for (int repetition = 0; repetition < 1; repetition++) { //pour afficher les ! sur les coter
            for (int k = 0; k < tab.length; k++) { // pour le remplir de point

                for (int j = 0; j < tab[k].length; j++) {
                    tab[k][j] = '.';

                }

            }
            for (int y = 0; y < 6; y++) {//pour chaque ligne
                System.out.print('!');
                for (int x = 0; x < tab.length; x++) {//pour chaque colonne
                    System.out.print(" " + tab[x][y] + " ");
                }
                System.out.print('!');
                System.out.println();
                }

        }

        for (int tiret = 0; tiret < (tab.length * 3) + 2; tiret++) { //pour mettre des tiret en bas

            System.out.print('~');

        }
        System.out.println();


    }

    public static void afficherPlateau(char[][] tab) {
		for (int ligne = 0 ; ligne < tab.length ; ligne++) {
			for (int colonne = 0 ; colonne < tab[ligne].length ; colonne++) {
				System.out.print(tab[ligne][colonne] + "\t");
			}
			System.out.println();
		}
	}

    public static void verif(char[][] plateau , int choix ,char joueur){ //verifie l ou on peut placer notre coup
        int ligneBas;
        ligneBas = plateau.length -1; // = à la derniere ligne du plateau

        while (plateau[ligneBas][choix] != '.'){//en partant du bas tant qu'il ne trouve pas une case vide il remonte de un
            ligneBas--;
        }
        plateau[ligneBas][choix] = joueur; // remplit la case vide trouver
    }

    public static boolean siPlein(char[][] plateau){ //verifie si le plateau est plein

        for (int colonne = 0; colonne < plateau[0].length; colonne++) {
            if (plateau[0][colonne] == '.'){
                return false;
            }
        }
        return true;
    }

    public static boolean siGagne(char[][] plateau,char joueur){ //verifie si il y a un gagnant
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                char pion = plateau[ligne][colonne];
                if (pion == joueur){
                    if ( ( ligne >= 3 && colonne <= plateau[ligne].length -4 &&diagonalDroit(plateau,ligne,colonne) == 4) || (ligne <= plateau.length -4 && colonne<= plateau[ligne].length -4 && diagonalbas(plateau,ligne,colonne) ==4) ||(colonne<= plateau[ligne].length-4&& horizontaleDroit(plateau,ligne,colonne)==4) || (ligne>=0&&ligne<=2 &&verticaleBas(plateau,ligne,colonne)==4)){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public static int diagonalDroit(char[][] plateau ,int ligneTab, int colonneTab){
        int compteur = 0;

        int ligne = ligneTab;
        int colonne = colonneTab;
        while (colonne != 7 &&plateau[ligne][colonne] == plateau[ligneTab][colonneTab] &&ligne>=0 && ligne < plateau.length && colonne>=0 && colonne < plateau[ligne].length){
            compteur++;

            if ((ligne-1)<0){//pour eviter de déborder du tableau
                break;
            }
            else {
                ligne = ligne-1;
            }
            if ((colonne+1)== plateau[ligne].length){//pour eviter de déborder du tableau
                break;
            }
            else {
                colonne = colonne+1;
            }


        }
        return compteur;

    }
    public static int diagonalbas(char[][] plateau ,int ligneTab, int colonneTab){
        int compteur = 0;

        int ligne = ligneTab;
        int colonne = colonneTab;
        while (plateau[ligne][colonne] == plateau[ligneTab][colonneTab] &&ligne>=0 && ligne < plateau.length && colonne>=0 && colonne < plateau[ligne].length){
            compteur++;
            if ((ligne+1)== plateau.length){//pour eviter de déborder du tableau
                break;
            }
            else {
                ligne = ligne+1;
            }

            if ((colonne+1)== plateau[ligne].length){//pour eviter de déborder du tableau
                break;
            }
            else {
                colonne = colonne+1;
            }

        }
        return compteur;

    }
    public static int horizontaleDroit(char[][] plateau ,int ligneTab, int colonneTab){
        int compteur = 0;

        int ligne = ligneTab;
        int colonne = colonneTab;
        while (plateau[ligne][colonne] == plateau[ligneTab][colonneTab] &&ligne>=0 && ligne < plateau.length && colonne>=0 && colonne < plateau[ligne].length){
            compteur++;
            //ligne = 0;
            if ((colonne+1)== plateau[ligne].length){//pour eviter de déborder du tableau
                break;
            }
            else {
                colonne = colonne+1;
            }


        }
        return compteur;

    }
    public static int verticaleBas(char[][] plateau ,int ligneTab, int colonneTab){
        int compteur = 0;

        int ligne = ligneTab;
        int colonne = colonneTab;
        while (plateau[ligne][colonne] == plateau[ligneTab][colonneTab] &&ligne>=0 && ligne < plateau.length&& colonne>=0 && colonne < plateau[ligne].length){
            compteur++;

            if ((ligne+1)== plateau.length){//pour eviter de déborder du tableau
                break;
            }
            else {
                ligne = ligne+1;
            }


        }
        return compteur;

    }




}


