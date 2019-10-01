package TestPackage;
import BlackJack_Core.*;

public class Main {
	
	public static void main (String[] args) {
		Game game = new Game();
		ChangeObserver changeObserver = new ChangeObserver();
		game.addGameObserver(changeObserver);
		game.newRound();
	}
}
