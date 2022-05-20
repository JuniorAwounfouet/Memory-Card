import org.newdawn.slick.BasicGame;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class MonJeu extends BasicGame {
	private static boolean back=false;
	Grille gr;
	Carte c;
	Menu m;
	static int select=0;
	TrueTypeFont ttf;
	Font font;
	Theme th;

	public MonJeu(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		switch(select) {
		case 0:
			g.setColor(new Color(255, 215, 0, 200));
			g.fillRect(0, 0, 950, 750);
			g.drawImage(new Image("images/memory.png"), 150, 50);


			m.dessinerMenu(g,gc);
			break;
		case 1:
			gr.dessinerPlateau(g);
			gr.afficherSecondeCarte(g);
			gr.dessinerCarte(g);
			g.setColor(Color.lightGray);
			g.fillRoundRect(750, 30, 190, 190, 20);
			g.setColor(Color.black);
			if(gr.getEssaie()==8) {
				g.drawString("Bravo \nVous avez gagnez en\n"+gr.getTentative()+" tentatives", 760, 100);
				if(gr.getPoints()>0)
					g.drawString("Score : "+gr.getPoints(), 760, 180);
				else
					g.drawString("Score : "+0, 760, 180);
			}
			g.drawString("tentatives : "+gr.getTentative(), 760, 60);
			
			this.afficherBack(g);
			
			
			break;
		case 2:
			g.setColor(new Color(255, 215, 0, 200));
			g.fillRect(0, 0, 950, 750);
			th.afficherTheme(g, gc);
			this.afficherBack(g);
			break;
		case 3:
			
			break;
		}
	
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		gr=new Grille();
		gr.initCarte();
		m=new Menu();
		font=new Font("Arial",Font.BOLD,35);
		ttf=new TrueTypeFont(font, true);
		th=new Theme();

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
			Input inp = gc.getInput();
		
			switch(select) {
			case 0:
				if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					m.clicCase(inp.getMouseX(), inp.getMouseY());
				break;
			case 1:
				if(gr.getEssaie()==8 && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					select=0;
					gc.reinit();
				}
				else {
					gr.clicCase(inp);
					this.clicBack(inp, gc);
				}
				break;
			case 2:
				th.clicCase(inp,gc);
				this.clicBack(inp, gc);
				
				break;
			case 3:
				gc.exit();
				break;
			}

	}
	
	public void afficherBack(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRoundRect(750, 660, 110, 60, 20);
		g.setColor(Color.white);
		ttf.drawString(760, 670, "Back",Color.white);
	}

	public void clicBack(Input inp, GameContainer gc) throws SlickException {
		if(isBack()) {
			select=0;
			gc.reinit();
			setBack(false);
			System.out.println("hey");	
		}
	}

	public static boolean isBack() {
		return back;
	}

	public static void setBack(boolean back) {
		MonJeu.back = back;
	}
	
	
}
