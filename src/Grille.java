import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Grille {
	
	private Carte[][] cartes=new Carte[4][4];
	private boolean[][] carteTrouve =new boolean[4][4];
	private int[] secondeCarte= new int[2];
	private int compteur=0;//compte le nombre de carte retourné
	private int tentative=0;
	private int essaie=0;
	private int points=2000;
			
	public void dessinerPlateau(Graphics g) {// Dessiner la grille 4*4
		int k = 30, m = 30;
		for(int i=1;i<=4;i++) {
			k=30;
			for (int j = 1; j <= 4; j++) {
				g.setColor(Color.lightGray);
				g.fillRect(k, m, 150, 150);
				k+=180;
			}
			m+=180;
		}
	}
	
	public void initCarte() throws SlickException {
		
//		int type[]= {1,2,1,3,7,8,3,2,6,5,6,4,5,7,8,4};
		int type[]=this.aleatoire();
		
		cartes[0][0]= new Carte(0, 0, type[0], true);// initialiser les cartes
		cartes[0][1]= new Carte(0, 1, type[1], true);
		cartes[0][2]= new Carte(0, 2, type[2], true);
		cartes[0][3]= new Carte(0, 3, type[3], true);
		cartes[1][0]= new Carte(1, 0, type[4], true);
		cartes[1][1]= new Carte(1, 1, type[5], true);
		cartes[1][2]= new Carte(1, 2, type[6], true);
		cartes[1][3]= new Carte(1, 3, type[7], true);
		cartes[2][0]= new Carte(2, 0, type[8], true);
		cartes[2][1]= new Carte(2, 1, type[9], true);
		cartes[2][2]= new Carte(2, 2, type[10], true);
		cartes[2][3]= new Carte(2, 3, type[11], true);
		cartes[3][0]= new Carte(3, 0, type[12], true);
		cartes[3][1]= new Carte(3, 1, type[13], true);
		cartes[3][2]= new Carte(3, 2, type[14], true);
		cartes[3][3]= new Carte(3, 3, type[15], true);
		
		for(int i=0;i<4;i++) {// initialise le tableau carteTrouve a false
			for(int j=0;j<4;j++) {
				carteTrouve[i][j]=false;
			}
		}
	}
	
	public void dessinerCarte(Graphics g) throws SlickException {
		g.drawImage(new Image("images/fond"+(Theme.getTheme()-1)+".png"), 0, 0);
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(0, 0, 950, 750);

		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(cartes[i][j]!=null)
					cartes[i][j].dessiner(g);
			}
		}
	}
	
	public int[] quelleCase(int x,int y) {// recupérer les coordonées de la case sur laquelle on se trouve
		int[] tab= new int[2];
		tab[0]= x>=30 && x<=180 ? 0 : x>=210 && x<=360 ? 1 : x>=390 && x<=540 ? 2 : x>=570 && x<=720 ? 3 : -1;//colonne
		tab[1]= y>=30 && y<=180 ? 0 : y>=210 && y<=360 ? 1 : y>=390 && y<=540 ? 2 : y>=570 && y<=720 ? 3 : -1;//ligne
		
		return tab;
	}
	
	public void clicCase(Input inp) {
		int tab[]=new int[2];
		int x=inp.getMouseX(), y=inp.getMouseY();
		tab=this.quelleCase(x, y);
		int colonne=tab[0],ligne=tab[1];
		secondeCarte[0]=-1;
		secondeCarte[1]=-1;
		boolean clic=inp.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		if(clic && x>=750 && x<=860 && y>=660 && y<=720) 
			MonJeu.setBack(true);
		System.out.println("ligne ="+ligne+" colonne="+colonne);
		if(ligne!=-1 && colonne!=-1) {
			if(carteTrouve[ligne][colonne]) {//si carteTrouve==vrai i.e la carte est deja retourné
				
			}else {
				if(cartes[ligne][colonne].isEstVerso() &&  clic) {
					cartes[ligne][colonne].setEstVerso(false);
					compteur++;
				}
				else if(compteur==2) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tentative++;
					//mecanisme de pause de 1s
					//this.dessinerCarte(g);
					if(this.identique(ligne, colonne)) {//vérifie si 2 carte sont de meme type
						System.out.println("trouve");
						this.remplirCaseTrouve();//affecte a carteTrouve la valeur true
						setEssaie(getEssaie() + 1); 
						cartes[ligne][colonne].setEstVerso(false);

					}	
					else {
						System.out.println("pas trouve");
						secondeCarte[0]=ligne;
						secondeCarte[1]=colonne;
						this.retournerCartes();// remet les cartes face verso
						setPoints(getPoints() - 100);
					}	
					System.out.println("compteur ="+compteur);
					compteur=0;
				}
					
			}

		}
		
	}
	
	public void afficherSecondeCarte(Graphics g) {
		if(secondeCarte[0]!=-1 && secondeCarte[1]!=-1) {
			cartes[secondeCarte[0]][secondeCarte[1]].dessiner(g);
			System.out.println("Seconde carte est :"+secondeCarte[0]+" : "+secondeCarte[1]);
			secondeCarte[0]=-1; secondeCarte[1]=-1; 

		}
	}
	
	public boolean identique(int ligne,int colonne) {

		boolean etat=true;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(carteTrouve[i][j]==false)
					if(!cartes[i][j].isEstVerso() && cartes[i][j].getType()!=cartes[ligne][colonne].getType()) {
						etat=false;
				}
			}
		}
		return etat;	
	}
	
	public void remplirCaseTrouve() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(!cartes[i][j].isEstVerso())
					carteTrouve[i][j]=true;
			}
		}
	}
	
	public void retournerCartes() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(carteTrouve[i][j]==false)
					cartes[i][j].setEstVerso(true);
			}
		}
	}
	
	public int[] aleatoire() {
		int[] type=new int[16];
		for(int i=0;i<type.length;i++) {
			int a=(int)(Math.random()*8+1);
			int compteur=0;
			for(int j=0;j<i;j++) {
				if(type[j]==a)
					compteur++;
			}
			if(compteur>=2)
				i--;
			else
				type[i]=a;
		}
		return type;
	}

	public int getTentative() {
		return tentative;
	}

	public void setTentative(int tentative) {
		this.tentative = tentative;
	}

	public int getEssaie() {
		return essaie;
	}

	public void setEssaie(int essaie) {
		this.essaie = essaie;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	
	}
