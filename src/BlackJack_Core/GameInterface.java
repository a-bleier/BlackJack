package BlackJack_Core;
import java.util.ArrayList;

public interface GameInterface {
	
	public void addGameObserver(GameObserver go);
	
	public void newRound();
	public void resetGame();
	public void hit();
	public void stand();
	
	public void split();
	
	public void setInsurance(int cash);
	public void setBet(int cash);
	public void setPlayerMoney(int cash);
	public void doubleBet();
	
	public DealerDeck getDealerDeck();
	public ArrayList<PlayerDeck> getPlayerDecks();
	public int getBet();
	public int getPlayerMoney();
	public int getInsurance();
	public int getPayOut();
	public Config getConfig();
	public State getState();
	
	public boolean playerCanSplit();
	public boolean playerCanDouble();
	
	public void payOutPlayer();
	
	
	
}
