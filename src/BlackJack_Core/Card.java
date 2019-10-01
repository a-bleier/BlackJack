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
		String s = "";
		return s = s + "||" + color + "|" + number + "|" + value + "||";
	}
	
	
}
