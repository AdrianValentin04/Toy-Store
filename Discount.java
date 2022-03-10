import java.time.LocalDateTime;

public class Discount{

    public enum DiscountType{
        PERCENTAGE_DISCOUNT, FIXED_DISCOUNT
    }

    String name;
    Discount.DiscountType discountType;
    double value;
    LocalDateTime lastDateApplied = null;

    static class DiscountNotFoundException extends Exception{
        DiscountNotFoundException(String message){
            System.err.println(message);
        }
    }


    public Discount(String name, DiscountType type, double value){
        this.name = name;
        this.discountType = type;
        this.value = value;
    }

    String getName(){
        return this.name;
    }

    DiscountType getDiscountType(){
        return  this.discountType;
    }

    double getValue(){
        return this.value;
    }

    public String toString(){
        return name + "," + discountType + "," + value;
    }
}
