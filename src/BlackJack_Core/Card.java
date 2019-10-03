package BlackJack_Core;

public class Card {
	private int color;
	private int number;
	private int value;
	
	public Card(int color, int number) {
		this.color = color;
		this.number = number;
		if(number <= 10) {
			value = number;
		}
		else if(number != 14){
			value = 10;
		}
		else {
			value = 11;
		}
	}
	
	public int getColor() {
		return color;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isAce() {
		return number == 14;
	}
	@Override
	public String toString() {
		String color = null;
		String number = null;
		
		switch(this.color) {
		case 0:
			color = "\u2660"; //Spade
			break;
		case 1:
			color = "\u2665"; //Heart
			break;
		case 2:
			color = "\u2666"; //Diamond
			break;
		case 3:
			color = "\u2663"; //Club
			break;
		}
		
		if(this.number < 11) {
			number = Integer.toString(this.number);
		}
		else {
			switch(this.number) {
			case 11:
				number = "J";
				break;
			case 12: 
				number = "Q";
				break;
			case 13:
				number = "K";
				break;
			case 14:
				number = "A";
				break;
			}
		}
		String s = "";
		return s = s + color + "" + number + " ";
	}
	
	
}
