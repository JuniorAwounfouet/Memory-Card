import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Carte {
	
	private static final String[]THEME= {"yu-gi-oh","fleur"};
	private int ligne;
	private int colonne;
	private int type;
	private boolean estVerso;
	private Image img;
	private Image Verso = new Image("images/verso"+(Theme.getTheme()-1)+".png");
	
	public Carte(int ligne,int colonne,int type,boolean estVerso) throws SlickException {
		if(ligne>=0 && ligne<=4 && colonne>=0 && colonne<=4 && type>=1 && type<=8) {
			this.setLigne(ligne);
			this.setColonne(colonne);
			this.setType(type);
			this.estVerso= estVerso;
			img = new Image("images/"+THEME[Theme.getTheme()-1]+type+".png");
			/*
				switch(type) {
				case 1: img= new Image("images/fleur1.png");
				break;
				
				case 2: img= new Image("images/fleur2.png");
				break;
				
				case 3: img= new Image("images/fleur3.png");
				break;
				
				case 4: img= new Image("images/fleur4.png");
				break;
				
				case 5: img= new Image("images/fleur5.png");
				break;
				
				case 6: img= new Image("images/fleur6.png");
				break;
				
				case 7: img= new Image("images/fleur7.png");
				break;
					
				case 8: img= new Image("images/fleur8.png");
				break;
				}
			*/	
			
		}
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public boolean isEstVerso() {
		return estVerso;
	}

	public void setEstVerso(boolean estVerso) {
		this.estVerso = estVerso;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

	public void dessiner(Graphics g) {
		if(estVerso)
			g.drawImage(Verso, colonne* 185 + 30 - 5*colonne, ligne* 185 + 30 - 5*ligne);
		else
			g.drawImage(img, colonne * 185 + 30 - 5*colonne , ligne * 185 + 30 -5*ligne );
	}


}
