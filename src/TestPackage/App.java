package TestPackage;

import java.util.Scanner;

import BlackJack_Core.Game;
import BlackJack_Core.GameState;
import BlackJack_Core.PlayerDeck;

public class App {
	
	private Game game;
	
	enum AppState{
		RUNNING,
		TERMINATED
	}
	
	public void run() {
		printWelcomeText();
		
		game = new Game();
		//ChangeObserver changeObserver = new ChangeObserver();
		//game.addGameObserver(changeObserver);
		game.newRound();		
		Scanner sc = new Scanner(System.in);
		AppState state = AppState.RUNNING;
		while(state != AppState.TERMINATED) {
			inputLineText();
			String input = sc.next();
			
			if(input.equals("q") || input.equals("quit")){
				state = AppState.TERMINATED;
			}
			else if(input.equals("help")) {
				System.out.println("help");
			}
			else if(input.equals("settings")) {
				System.out.println("settings");
			}
			
			else if(game.getState() == GameState.SETUP) {
				int cash = 0;
				try {
					cash = Integer.parseInt(input);
				}
				catch(NumberFormatException e) {
					System.out.println("Please enter a valid number");
					continue;
				}
				
				game.setBet(cash);
				printTable();
				
			}
			else if(game.getState() == GameState.PLAYER_TURN) {
				if(input.equals("h") || input.equals("hit") ) {
					game.hit();
				}
				else if(input.equals("s") || input.equals("stand") ) {
					game.stand();
				}
				else if((input.equals("d") || input.equals("double")) && game.playerCanDouble() ) {
					game.doubleBet();
				}
				else if((input.equals("sp") || input.equals("split")) && game.playerCanSplit() ) {
					game.split();
				}
				printTable();
			}
			else if(game.getState() == GameState.PAY_OUT) {
				printPayOut();
				game.payOutPlayer();
				game.newRound();
			}
			

		}
		sc.close();
	}
	
	private void inputLineText() {
		if(game.getState() == GameState.SETUP) {
			System.out.println("Your bet: ");
		}
		else if(game.getState() == GameState.PLAYER_TURN) {
			//TODO Should only show options when they are valid
			System.out.println("(h)it, (s)tand, (d)ouble or (sp)lit");
		}
		else if(game.getState() == GameState.PAY_OUT) {
			System.out.println("Press Enter key to receive your payout....");
		}
	}
	
	public void printTable() {
		//TODO show only one dealer card in the beginning
		String dealer = "Dealer: " + game.getDealerDeck().toString() + "(" + game.getDealerDeck().getValue() + ")\n";
		String player = "Player: ";
		for(PlayerDeck dp : game.getPlayerDecks()) {
			player += dp.toString() + "(" + dp.getValue() + ")";
			if(dp.equals(game.getActivePlayerDeck())) {
				player += " <==";
			}
			player += "\n";
		}
		
		//player = game.getActivePlayerDeck().toString();
		System.out.print(dealer + player);
	}
	
	public void printPayOut() {
		System.out.println("Your payout: " + game.getPayOut());
	}
	
	private void printWelcomeText() {
		System.out.println("Welcome to Terminal Black Jack !!!!");
	}
	
}
