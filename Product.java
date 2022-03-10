public class Product{

    String uniqueId;
    String name;
    Manufacturer manufacturer;
    double price;
    int quantity;
    Discount discount;

    static class DuplicateProductException extends  Exception{
        DuplicateProductException(String message){
            System.err.println(message);
        }
    }

    static class NegativePriceException extends Exception{
        NegativePriceException(String message){
            System.err.println(message);
        }
    }


    static class productBuilder{

        String uniqueId;
        String name;
        Manufacturer manufacturer;
        double price;
        int quantity;
        Discount discount;

        public productBuilder(){
        }

        public productBuilder setUniqueId(String uniqueId){
            this.uniqueId = uniqueId;
            return this;
        }

        public productBuilder setName(String name){
            this.name = name;
            return this;
        }

        public productBuilder setManufacturer(Manufacturer manufacturer){
            this.manufacturer = manufacturer;
            return this;
        }

        public productBuilder setPrice(double price){
            this.price = price;
            return this;
        }

        public productBuilder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public productBuilder setDiscount(Discount discount){

            this.discount = discount;
            return this;
        }
    }

    public Product (productBuilder builder){
        this.uniqueId = builder.uniqueId;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.discount = builder.discount;

    }

    public String getUniqueId(){
        return uniqueId;
    }

    public String getName(){
        return name;
    }

    public void getPrice(double price){
        this.price = price;
    }

    public Manufacturer getManufacturer(){
        return manufacturer;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public Discount getDiscount(){
        return discount;
    }

    public String toString(String symbol) {
        return this.uniqueId + "," + this.name + "," + this.manufacturer + "," + symbol + this.price + "," + this.quantity;
    }
}
