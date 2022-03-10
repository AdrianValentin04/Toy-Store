public class Currency {

    String name;
    String symbol;
    double parityToEur;

    static class CurrencyNotFoundException extends Exception{
        CurrencyNotFoundException(String message){
            System.err.println(message);
        }
    }

    public Currency(String name, String symbol, double parityToEur){
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    public Currency(){
        this.name = "Euro";
        this.symbol = "€";
        this.parityToEur = 1.0;
    }


    String getName(){
        return name;
    }

    String getSymbol(){
        return symbol;
    }

    void updateParity(double parityToEur){
        this.parityToEur = parityToEur;
    }

    public String toString(){
        return this.name + " , " + this.symbol + " , " + this.parityToEur;
    }


}
