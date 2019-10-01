package BlackJack_Core;

import java.util.ArrayList;

public class Deck {
	
	protected ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		deck.add(card);

	}

	public boolean isBlackJack() {
		return getValue() == 21 && deck.size() == 2;
	}

	public int getValue() {
		int sum = 0;
		for(Card c : deck) {
			sum += c.getValue();
		}
		if(sum <= 21) {
			return sum;
		}
		for(Card c : deck) {
			if(c.isAce()) {
				sum -= 10;
				if(sum < 21) {
					break;
				}
			}
		}
		return sum;
	}
	
	public void flush() {
		deck.clear();
	}
	
	public int getSize() {
		return deck.size();
	}
	@Override
	public String toString () {
		String s = "";
		for(Card c : deck) {
			s = s + c.toString()+ " " ;
		}
		s += "\n";
		return s;
	}
}
