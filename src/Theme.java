import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Theme {
	
	private static int theme=1;
	private Case case1;
	private Case case2;
	private Image theme1;
	private Image theme2;
	
	public Theme() throws SlickException {
		this.case1=new Case(100, 500, 225, 250, 1.f);
		this.case2=new Case(100, 500, 225, 600, 2.f);
		theme1= new Image("images/theme1.png");
		theme2= new Image("images/theme2.png");	
	}
	
	public void afficherTheme(Graphics g, GameContainer gc) {
		
		case1.dessiner(g, gc);
		case2.dessiner(g, gc);
		g.drawImage(theme1, 385, 50);
		g.drawImage(theme2, 385, 400);
		
	}

	public static int getTheme() {
		return theme;
	}

	public static void setTheme(int theme) {
		Theme.theme = theme;
	}

	public int quelleCase(int x,int y) {// recupérer les coordonées de la case sur laquelle on se trouve
//		tab[0]= x>=100 && x<=250 ? 0 : x>=210 && x<=360 ? 1 : x>=390 && x<=540 ? 2 : x>=570 && x<=720 ? 3 : -1;//colonne
//		tab[1]= y>=30 && y<=180 ? 0 : y>=210 && y<=360 ? 1 : y>=390 && y<=540 ? 2 : y>=570 && y<=720 ? 3 : -1;//ligne
		
		if(x>=case1.getX() && x<=(case1.getX()+case1.getLargeur()))
			if(y>=case1.getY() && y<=(case1.getY()+case1.getLongueur()))
				return 1;
		
		if(x>=case2.getX() && x<=(case2.getX()+case2.getLargeur()))
			if(y>=case2.getY() && y<=(case2.getY()+case2.getLongueur()))
				return 2;
	
		return -1;
	}
	
	public void clicCase(Input inp,GameContainer gc) throws SlickException {
		int colonne;
		int x=inp.getMouseX();
		int y=inp.getMouseY();
		colonne=this.quelleCase(x, y);
		if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON))	{	
			if(colonne>0) {
				theme=colonne;
				MonJeu.select=0;
				gc.reinit();
			}
			if(x>=750 && x<=860 && y>=660 && y<=720) 
				MonJeu.setBack(true);
		}
	}
}
