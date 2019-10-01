package TestPackage;

import BlackJack_Core.GameInterface;
import BlackJack_Core.GameObserver;

public class ChangeObserver implements GameObserver {

	@Override
	public void update(GameInterface game) {
		
		System.out.println("Change observed");
		
		

	}

}
