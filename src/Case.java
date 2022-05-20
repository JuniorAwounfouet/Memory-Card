import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Case {
	private Image[] img=new Image[2];
	private int longueur;
	private int largeur;
	private int x;
	private int y;
	
	public Case(int longueur, int largeur, int x, int y, int i) throws SlickException {
		this.longueur=longueur;
		this.largeur=largeur;
		this.x=x;
		this.y=y;
		this.img[0] =new Image("images/option"+i+"_0.png");
		this.img[1] =new Image("images/option"+i+"_1.png");
	}
	
	public Case(int longueur, int largeur, int x, int y, float i) throws SlickException {
		this.longueur=longueur;
		this.largeur=largeur;
		this.x=x;
		this.y=y;
		this.img[0] =new Image("images/themeOp"+(int)i+"_0.png");
		this.img[1] =new Image("images/themeOp"+(int)i+"_1.png");
	}
	
	public int getLongueur() {
		return longueur;
		
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void dessiner(Graphics g, GameContainer gc) {
		int xm, ym;
		Input inp=gc.getInput();
		xm=inp.getMouseX();
		ym=inp.getMouseY();
		if(xm>=x && xm<=x+largeur && ym>=y && ym<= y+longueur) {
			g.drawImage(img[1], x, y);
			//System.out.println("hey");
		}
		else
			g.drawImage(img[0], x, y);
//		g.setColor(Color.blue);
//		g.drawRect(x, y, largeur, longueur);
	}
	
	
}
