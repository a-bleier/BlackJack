package BlackJack_Core;
import java.util.ArrayList;
//0: setting bets 1: hit / stand 2: end of round: loose or win ?

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
	private GameState state;
	private Config config;
	
	public Game() {
		config = new Config();
		
		playerDecks = new ArrayList<PlayerDeck>();
		dealerDeck = new DealerDeck();
		activePlayerDeck = new PlayerDeck();

		
		//Fill cardStack with 5 full card decks
		pack = new ArrayList<Card>();
		for(int i = 0; i < config.getPackSize(); i++) {
			for(int color = 0; color<4; color++) {
				for(int number = 2; number <=14; number++) {
					pack.add(new Card(color, number));
				}
			}
		}
		observers = new ArrayList<GameObserver>();
		

		
	}
	
	private Card cardFromPack() {
		int randomNum = (int) (Math.random() * pack.size());
		Card c = pack.remove(randomNum);
		if(pack.size() == 36) {
			pack = new ArrayList<Card>();
			for(int i = 0; i < config.getPackSize(); i++) {
				for(int color = 0; color<4; color++) {
					for(int number = 2; number <=14; number++) {
						pack.add(new Card(color, number));
					}
				}
			}
		}
		return c;
	}
	
	private int calculatePayout() {
		
		return 0;
	}
	//can change
	public void hit() {
		activePlayerDeck.addCard(cardFromPack());		
		if(playerDecks.size() == 1 && activePlayerDeck.isTripleSeven()) {
			state = GameState.PAY_OUT;
			payOut = 2*bet; //TODO temporary
		}
		else if(activePlayerDeck.isBust()) {	
			stand();
		}
		
	}
	
	
	//wont change
	public void stand() {
		activePlayerDeck.isFinishedNow();
		boolean hasFoundUnfinishedDeck = false;
		for(PlayerDeck pd : playerDecks) {
			
			if(!pd.isFinished()) {
				hasFoundUnfinishedDeck = true;
				activePlayerDeck = pd;
			}
		}
		if(!hasFoundUnfinishedDeck) {
			state = GameState.PAY_OUT;	
			playDealer();
		}
		notifyGameObserver();
	}
	//can change
	public void split() {
		PlayerDeck deck = activePlayerDeck.split();
		deck.addCard(cardFromPack());
		activePlayerDeck.addCard(cardFromPack());
		playerDecks.add(deck);

	}
	
	public void setBet (int cash) {
		this.bet = cash;
		state = GameState.PLAYER_TURN;
		notifyGameObserver();
	}
	
	public void setInsurance(int cash) {
		this.insurance = cash;
		notifyGameObserver();
	}
	
	public void newRound() {
		state = GameState.SETUP;
		dealerDeck.flush();
		activePlayerDeck.flush();
		playerDecks.clear();
		playerDecks.add(activePlayerDeck);		
		dealerDeck.addCard(cardFromPack());
		dealerDeck.addCard(cardFromPack());
		activePlayerDeck.addCard(cardFromPack());
		activePlayerDeck.addCard(cardFromPack());
		if(activePlayerDeck.isBlackJack() && dealerDeck.isBlackJack()) {
			state = GameState.PAY_OUT;
			payOut = bet;
		}
		else if (activePlayerDeck.isBlackJack()) {
			state = GameState.PAY_OUT;
			payOut = 2*bet; //TODO temp
		}
		else if (dealerDeck.isBlackJack()) {
			state = GameState.PAY_OUT;
		}
		notifyGameObserver();
	}
	
	public void resetGame() {
		
	}
	
	private void playDealer() {
		while(dealerDeck.getValue() < 17) {
			dealerDeck.addCard(cardFromPack());
		}		
		for(PlayerDeck pd : playerDecks) {
			int valueDD = dealerDeck.getValue();
			if((valueDD > 21 || 21-pd.getValue() < 21-valueDD) && pd.getValue()<21) {
				payOut += 2*bet;
			}
		}
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
		state = GameState.PAY_OUT;
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
		return activePlayerDeck.getSize() == 2 && playerDecks.size() == 1 && 
				activePlayerDeck.canSplit();
				
	}

	@Override
	public boolean playerCanDouble() {
		//TODO Config refactor later on
		return activePlayerDeck.getSize() == 2 && playerDecks.size() == 1 && 
				activePlayerDeck.getValue() > 8 && activePlayerDeck.getValue() < 12;
	}

	@Override
	public int getPayOut() {		
		return payOut;
	}

	@Override
	public GameState getState() {
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

	@Override
	public PlayerDeck getActivePlayerDeck() {
		return this.activePlayerDeck;
	}
	
}
