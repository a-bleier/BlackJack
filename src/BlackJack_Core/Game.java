package BlackJack_Core;
import java.util.ArrayList;
//0: setting bets 1: hit / stand 2: end of round: loose or win ?

import com.sun.source.tree.NewClassTree;

enum State {
	SETUP,
	RUNNING,
	PAY_OUT
}

public class Game implements GameInterface{
	
	
	
	private ArrayList<PlayerDeck> playerDecks;
	private PlayerDeck activePlayerDeck;
	private DealerDeck dealerDeck;
	private ArrayList<Card> pack;
	private int playerMoney;	
	private int bet;
	private int insurance;
	private int payOut;
	private ArrayList<GameObserver> observers;
	private State state;
	private Config config;
	
	public Game() {
		playerDecks = new ArrayList<PlayerDeck>();
		dealerDeck = new DealerDeck();
		activePlayerDeck = new PlayerDeck();
		
		//Fill cardStack with 5 full card decks
		pack = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			for(int color = 0; color<4; color++) {
				for(int number = 2; number <=14; number++) {
					pack.add(new Card(color, number));
				}
			}
		}
		observers = new ArrayList<GameObserver>();
		
		config = new Config();
		
	}
	
	private Card cardFromPack() {
		int randomNum = (int) (Math.random() * pack.size());
		Card c = pack.remove(randomNum);
		return c;
		//TODO: Not finished here;
	}
	
	private int calculatePayout() {
		
		return 0;
	}
	//can change
	public void hit() {
		activePlayerDeck.addCard(cardFromPack());
		/*
		 *if tr7 and one deck
		 *	setPayout
		 * 	payout
		 * if bust
		 * 	payout (0)
		 * 
		 * 
		 */
		notifyGameObserver();
		
	}
	//wont change
	public void stand() {
		playDealer();
		notifyGameObserver();
	}
	//can change
	public void split() {
		playerDecks.add(activePlayerDeck.split());
	}
	
	public void setBet (int cash) {
		this.bet = cash;
		notifyGameObserver();
	}
	
	public void setInsurance(int cash) {
		this.insurance = cash;
		notifyGameObserver();
	}
	
	public void newRound() {
		state = State.SETUP;
		dealerDeck.flush();
		activePlayerDeck.flush();
		playerDecks.clear();
		dealerDeck.addCard(cardFromPack());
		dealerDeck.addCard(cardFromPack());
		activePlayerDeck.addCard(cardFromPack());
		activePlayerDeck.addCard(cardFromPack());
		//TODO: Winning ?
		notifyGameObserver();
	}
	
	public void resetGame() {
		
	}
	
	private void playDealer() {
		while(dealerDeck.getValue() < 17) {
			dealerDeck.addCard(cardFromPack());
		}
		//TODO
		/*
		 * for each playerdeck
		 * 		if dealerdeck >21 || playerDeck näher an 21 dran
		 * 			add bet to payout
		 * 		else
		 * 			add 0 to payout
		 *
		 */
	}
	
	private void notifyGameObserver() {
		for (GameObserver ob : observers) {
			ob.update(this);
		}
	}
	@Override
	public void setPlayerMoney(int cash) {
		playerMoney = cash;		
	}
	@Override
	public void doubleBet() {
		playerMoney -= bet;
		bet *= 2;
		activePlayerDeck.addCard(cardFromPack());
		playDealer();
		notifyGameObserver();	
	}
	@Override
	public DealerDeck getDealerDeck() {
		return dealerDeck;
	}
	@Override
	public ArrayList<PlayerDeck> getPlayerDecks() {
		return playerDecks;
	}
	@Override
	public int getBet() {
		return bet;
	}
	@Override
	public int getPlayerMoney() {
		return playerMoney;
	}
	@Override
	public int getInsurance() {
		return insurance;
	}
	@Override
	public Config getConfig() {		
		return config;
	}

	@Override
	public boolean playerCanSplit() {
		//TODO Config refactor later on
		return activePlayerDeck.getSize() == 2 && playerDecks.size() == 0 && 
				activePlayerDeck.canSplit();
				
	}

	@Override
	public boolean playerCanDouble() {
		//TODO Config refactor later on
		return activePlayerDeck.getSize() == 2 && playerDecks.size() == 0 && 
				activePlayerDeck.getValue() > 8 && activePlayerDeck.getValue() < 12;
	}

	@Override
	public int getPayOut() {		
		return payOut;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void addGameObserver(GameObserver go) {
		observers.add(go);
		
	}

	@Override
	public void payOutPlayer() {
		// TODO Auto-generated method stub
		
	}
	
}
