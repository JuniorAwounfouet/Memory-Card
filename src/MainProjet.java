import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainProjet {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		
		MonJeu monJeu= new MonJeu("Memory");
		AppGameContainer app= new AppGameContainer(monJeu,950 ,750 ,false);
		app.setShowFPS(false);
		app.start();

	}

}
