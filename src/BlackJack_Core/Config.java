package BlackJack_Core;

public class Config {

	private boolean withSplit;
	private double bJPayRatio;
	private int minBet;
	private int maxBet;
	private int packSize;
	private double RemixPoint;
	
	//dealer
	private boolean standOnSoftSeven; 
	private boolean holeCard;
	
	//Split
	private int resplitMax;
	private boolean resplitAce;
	private boolean hitAfterAce;
	
	//double down
	private int doubleDownMin;
	private int doubleDownMax;
	private int doubleDownAfterSplit;
	
	//Surrender
	private boolean withSurrender;
	
	public Config() {
		withSplit = true;
		bJPayRatio = 1.5;
		minBet = 10;
		maxBet = 1000;
		packSize = 5;
		RemixPoint = 0.5;
		standOnSoftSeven = true;
		resplitMax = 1;
		doubleDownMin = 9;
		doubleDownMax = 11;		
	}

	public boolean isWithSplit() {
		return withSplit;
	}

	public void setWithSplit(boolean withSplit) {
		this.withSplit = withSplit;
	}

	public double getbJPayRatio() {
		return bJPayRatio;
	}

	public void setbJPayRatio(double bJPayRatio) {
		this.bJPayRatio = bJPayRatio;
	}

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}

	public int getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	public int getPackSize() {
		return packSize;
	}

	public void setPackSize(int packSize) {
		this.packSize = packSize;
	}

	public double getRemixPoint() {
		return RemixPoint;
	}

	public void setRemixPoint(double remixPoint) {
		RemixPoint = remixPoint;
	}

	public boolean isStandOnSoftSeven() {
		return standOnSoftSeven;
	}

	public void setStandOnSoftSeven(boolean standOnSoftSeven) {
		this.standOnSoftSeven = standOnSoftSeven;
	}

	public boolean isHoleCard() {
		return holeCard;
	}

	public void setHoleCard(boolean holeCard) {
		this.holeCard = holeCard;
	}

	public int getResplitMax() {
		return resplitMax;
	}

	public void setResplitMax(int resplitMax) {
		this.resplitMax = resplitMax;
	}

	public boolean isResplitAce() {
		return resplitAce;
	}

	public void setResplitAce(boolean resplitAce) {
		this.resplitAce = resplitAce;
	}

	public boolean isHitAfterAce() {
		return hitAfterAce;
	}

	public void setHitAfterAce(boolean hitAfterAce) {
		this.hitAfterAce = hitAfterAce;
	}

	public int getDoubleDownMin() {
		return doubleDownMin;
	}

	public void setDoubleDownMin(int doubleDownMin) {
		this.doubleDownMin = doubleDownMin;
	}

	public int getDoubleDownMax() {
		return doubleDownMax;
	}

	public void setDoubleDownMax(int doubleDownMax) {
		this.doubleDownMax = doubleDownMax;
	}

	public int getDoubleDownAfterSplit() {
		return doubleDownAfterSplit;
	}

	public void setDoubleDownAfterSplit(int doubleDownAfterSplit) {
		this.doubleDownAfterSplit = doubleDownAfterSplit;
	}

	public boolean isWithSurrender() {
		return withSurrender;
	}

	public void setWithSurrender(boolean withSurrender) {
		this.withSurrender = withSurrender;
	}
	
	
}
