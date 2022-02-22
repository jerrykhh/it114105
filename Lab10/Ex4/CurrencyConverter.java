class CurrencyConverter {
	private double exchangeRate;
	private double commissionRate;
	private int largeAmount;
 
	public CurrencyConverter(double er , double cr){
		exchangeRate = er;
		commissionRate = cr;
	}
 
	public double fromUSDollar(double dollar){
		if (dollar >= largeAmount)
			return (dollar * exchangeRate * (1-commissionRate*0.5));
		else
			return (dollar * exchangeRate * (1-commissionRate));
	}
 
	public double toUSDollar(double foreignMoney){
		if (foreignMoney/exchangeRate >= largeAmount)
			return (foreignMoney/exchangeRate*(1-commissionRate*0.5));
		else
			return (foreignMoney/exchangeRate*(1-commissionRate));
	}
 
	public void setExchangeRate(double rate){
		exchangeRate = rate;
	}
 
	public double getExchangeRate(){
		return exchangeRate;
	}
 
	public void setCommissionRate(double rate){
		commissionRate = rate;
	}
 
	public double getCommissionRate(){
		return commissionRate;
	}
 
	public void setLargeAmount(int amount){
		largeAmount = amount;
	}
 
	public int getLargeAmount(){
		return largeAmount;
	}
}
