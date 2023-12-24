import javafx.scene.input.KeyCode;

import java.lang.Math;
import java.util.Random;

public class Jeu extends SujetObserve{
    private int[][] tabJeu;
    private int objectif, nbJouee, nbGagnee;
    private boolean versionOriginale;

    public Jeu(int taille){
        super();
        versionOriginale = false;
        tabJeu = new int[taille][taille];
        objectif = 2048;
        nbJouee = 0;
        nbGagnee = 0;
        nouveau();
    }

    public void nouveau(){
        if(!versionOriginale){
            for(int i=0 ; i<tabJeu.length ; i++){
                for(int j=0 ; j<tabJeu[0].length ; j++){
                    tabJeu[i][j] = generateRandom();
                }
            }
        }else {
            initVersionOriginale();
        }
        nbJouee++;
        notifierObservateurs();
    }

    public void jouer(int l, int c){
        if(tabJeu[l][c] != objectif){
            int res = tabJeu[l][c];

            if(!OutOfBound(l+1,c)){
                if (tabJeu[l][c] == tabJeu[l+1][c]) {
                    res += tabJeu[l+1][c];
                    tabJeu[l+1][c] = generateRandom();
                }
            }
            if(!OutOfBound(l-1,c)){
                if (tabJeu[l][c] == tabJeu[l-1][c]) {
                    res += tabJeu[l-1][c];
                    tabJeu[l-1][c] = generateRandom();
                }
            }
            if(!OutOfBound(l,c+1)){
                if (tabJeu[l][c] == tabJeu[l][c+1]) {
                    res += tabJeu[l][c+1];
                    tabJeu[l][c+1] = generateRandom();
                }
            }
            if(!OutOfBound(l,c-1)){
                if (tabJeu[l][c] == tabJeu[l][c-1]) {
                    res += tabJeu[l][c-1];
                    tabJeu[l][c-1] = generateRandom();
                }
            }

            tabJeu[l][c] = res;
            if(res == objectif){
                nbGagnee++;
                nouveau();
            }
            notifierObservateurs();
        }
    }

    private boolean OutOfBound(int x, int y){
        return (x < 0 || x >= tabJeu.length || y < 0 || y >= tabJeu.length);
    }

    public int getNbGagnees(){
        return nbGagnee;
    }

    public int getNbJouees(){
        return nbJouee;
    }

    public void setTaille(int taille){
        if (taille>10){
            taille = 10;
        }
        this.tabJeu = new int[taille][taille];
        nouveau();
    }

    public void setObjectif(int objectif){
        this.objectif = objectif;
        nouveau();
    }

    public int[][] getTabJeu() {
        return tabJeu;
    }

    public int getTaille() {
        return tabJeu.length;
    }

    public int getObjectif() {
        return objectif;
    }

    private int generateRandom(){
        Random rand = new Random();
        return (int)Math.pow(2,rand.nextInt(3)+1);
    }

    private void initVersionOriginale(){
        tabJeu = new int[tabJeu.length][tabJeu.length];
        Random rand = new Random();
        int nb2 = 2;
        while (nb2!=0){
            int x = rand.nextInt(tabJeu.length);
            int y = rand.nextInt(tabJeu.length);
            if(tabJeu[x][y] == 0){
                tabJeu[x][y] = 2;
                nb2--;
            }
        }
    }

    public void switchGame(){
        if(versionOriginale){
            versionOriginale = false;
        }else {
            versionOriginale = true;
        }
        nouveau();
    }

    public boolean getVersionOriginale(){
        return versionOriginale;
    }

    public void jouerHaut(){
        boolean changement = false;
        for(int i=0 ; i<tabJeu.length ; i++){
            for(int j=0 ; j<tabJeu[0].length ; j++){
                if(j!=0) {
                    if (tabJeu[i][j] != 0){
                        int relativ = j;
                        boolean placer = false;
                        while (!placer) {
                            if (relativ != 0) {
                                if (tabJeu[i][relativ - 1] == 0) {
                                    tabJeu[i][relativ - 1] = tabJeu[i][relativ];
                                    tabJeu[i][relativ] = 0;
                                    relativ--;
                                    changement = true;
                                } else if (tabJeu[i][relativ - 1] == tabJeu[i][relativ]) {
                                    tabJeu[i][relativ - 1] += tabJeu[i][relativ];
                                    tabJeu[i][relativ] = 0;
                                    placer = true;
                                    changement = true;
                                } else {
                                    placer = true;
                                }
                            } else {
                                placer = true;
                            }
                        }
                    }
                }
            }
        }
        if(changement){
            if(!checkGagner()){
                ajouterNB();
            }
            notifierObservateurs();
        }
    }

    public void jouerBas(){
        boolean changement = false;
        int tabS = tabJeu[0].length-1;
        for(int i=0 ; i<tabJeu.length ; i++){
            for(int j=tabS ; j>=0 ; j--){
                if(j!=tabS){
                    if(tabJeu[i][j]!=0) {
                        int relativ = j;
                        boolean placer = false;
                        while (!placer) {
                            if (relativ != tabS) {
                                if (tabJeu[i][relativ + 1] == 0) {
                                    tabJeu[i][relativ + 1] = tabJeu[i][relativ];
                                    tabJeu[i][relativ] = 0;
                                    relativ++;
                                    changement = true;
                                } else if (tabJeu[i][relativ + 1] == tabJeu[i][relativ]) {
                                    tabJeu[i][relativ + 1] += tabJeu[i][relativ];
                                    tabJeu[i][relativ] = 0;
                                    placer = true;
                                    changement = true;
                                } else {
                                    placer = true;
                                }
                            } else {
                                placer = true;
                            }
                        }
                    }
                }
            }
        }
        if(changement){
            if(!checkGagner()){
                ajouterNB();
            }
            notifierObservateurs();
        }
    }

    public void jouerDroite(){
        boolean changement = false;
        int tabS = tabJeu.length-1;
        for(int i=tabS ; i>=0 ; i--){
            for(int j=0 ; j<tabJeu[0].length ; j++){
                if(i!=tabS){
                    if(tabJeu[i][j]!=0) {
                        int relativ = i;
                        boolean placer = false;
                        while (!placer) {
                            if (relativ != tabS) {
                                if (tabJeu[relativ + 1][j] == 0) {
                                    tabJeu[relativ + 1][j] = tabJeu[relativ][j];
                                    tabJeu[relativ][j] = 0;
                                    relativ++;
                                    changement = true;
                                } else if (tabJeu[relativ + 1][j] == tabJeu[relativ][j]) {
                                    tabJeu[relativ + 1][j] += tabJeu[relativ][j];
                                    tabJeu[relativ][j] = 0;
                                    placer = true;
                                    changement = true;
                                } else {
                                    placer = true;
                                }
                            } else {
                                placer = true;
                            }
                        }
                    }
                }
            }
        }
        if(changement){
            if(!checkGagner()){
                ajouterNB();
            }
            notifierObservateurs();
        }
    }

    public void jouerGauche(){
        boolean changement = false;
        for(int i=0 ; i<tabJeu.length ; i++){
            for(int j=0 ; j<tabJeu[0].length ; j++){
                if(i!=0){
                    if(tabJeu[i][j]!=0) {
                        int relativ = i;
                        boolean placer = false;
                        while (!placer) {
                            if (relativ != 0) {
                                if (tabJeu[relativ - 1][j] == 0) {
                                    tabJeu[relativ - 1][j] = tabJeu[relativ][j];
                                    tabJeu[relativ][j] = 0;
                                    relativ--;
                                    changement = true;
                                } else if (tabJeu[relativ - 1][j] == tabJeu[relativ][j]) {
                                    tabJeu[relativ - 1][j] += tabJeu[relativ][j];
                                    tabJeu[relativ][j] = 0;
                                    placer = true;
                                    changement = true;
                                } else {
                                    placer = true;
                                }
                            } else {
                                placer = true;
                            }
                        }
                    }
                }
            }
        }
        if(changement){
            if(!checkGagner()){
                ajouterNB();
            }
            notifierObservateurs();
        }
    }

    private void ajouterNB(){
        if(!perdu()){
            Random rand = new Random();
            boolean placer = false;
            while (!placer){
                int x = rand.nextInt(tabJeu.length);
                int y = rand.nextInt(tabJeu.length);
                if(tabJeu[x][y] == 0){
                    tabJeu[x][y] = 2*(rand.nextInt(2)+1);
                    placer=true;
                }
            }
        }else {
            nouveau();
        }
    }

    private boolean perdu(){
        boolean trouver0 = false;
        int i=0;
        while(!trouver0 && i<tabJeu.length){
            int j=0;
            while (!trouver0 && j<tabJeu[0].length){
                if(tabJeu[i][j]==0){
                    trouver0=true;
                }
                j++;
            }
            i++;
        }
        return !trouver0;
    }

    private boolean checkGagner(){
        boolean gagner = false;
        int i=0;
        while(!gagner && i<tabJeu.length){
            int j=0;
            while (!gagner && j<tabJeu[0].length){
                if(tabJeu[i][j] == objectif){
                    gagner=true;
                }
                j++;
            }
            i++;
        }
        if (gagner){
            nbGagnee++;
            nouveau();
            return true;
        }
        else return false;
    }

    // Méthodes que je n'ai pas utilisées

    public int size(){
        return observateurs.size();
        // Pas sûr de ce que l'on doit retourner
    }

    public int getCase(int l, int c){
        return tabJeu[l][c];
    }
}
