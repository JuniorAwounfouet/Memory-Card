import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Menu {
	
	private Case[] menu=new Case[3];
	
	
	
	
	public Menu() throws SlickException {
		super();
		int k=225; int m=250;
		for(int i=0;i<menu.length;i++) {	
			menu[i]=new Case(150, 500, k, m, i+1);
			m+=150;
		}
		
	}

	public void dessinerMenu(Graphics g, GameContainer gc) {
		
		for(int i=0;i<menu.length;i++) {
			menu[i].dessiner(g,gc);
		}
		
	}
	
	public int quelleCase(int x,int y) {// recupérer les coordonées de la case sur laquelle on se trouve
//		tab[0]= x>=100 && x<=250 ? 0 : x>=210 && x<=360 ? 1 : x>=390 && x<=540 ? 2 : x>=570 && x<=720 ? 3 : -1;//colonne
//		tab[1]= y>=30 && y<=180 ? 0 : y>=210 && y<=360 ? 1 : y>=390 && y<=540 ? 2 : y>=570 && y<=720 ? 3 : -1;//ligne
		for(int i=0;i<menu.length;i++) {
			if(x>=menu[i].getX() && x<=(menu[i].getX()+menu[i].getLargeur()))
				if(y>=menu[i].getY() && y<=(menu[i].getY()+menu[i].getLongueur()))
					return i+1;
		}
		return -1;
	}
	
	public void clicCase(int x,int y) {
		int colonne;
		colonne=this.quelleCase(x, y);
		
		if(colonne>0)
			MonJeu.select=colonne;	
	}
}
