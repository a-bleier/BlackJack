package BlackJack_Core;
import java.util.ArrayList;


//TODO Inheritance from class Deck
public class PlayerDeck extends Deck {
	
	private boolean isFinished;
	
	public PlayerDeck() {
		super();
		isFinished = false;
	}

	public boolean isTripleSeven() {
		boolean iTS = deck.size() == 3;
		for(Card c : deck) {
			iTS = iTS && c.getNumber() == 7;
		}
		return iTS;
	}
	
	public boolean canSplit() {
		return !isBlackJack() && deck.size() == 2 && deck.get(0).getValue() == deck.get(1).getValue();
	}

	public PlayerDeck split() {
		Card c = this.deck.remove(0);
		PlayerDeck deck = new PlayerDeck();
		deck.addCard(c);
		return deck;
	}
	
	@Override
	public void flush () {
		super.flush();
		isFinished = false;		
	}
	
	public void isFinishedNow() {
		isFinished = true;
	}

}
