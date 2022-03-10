import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ArrayUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Store {

    String name;
    Currency currency;
    Product[] products;
    Manufacturer[] manufacturers;
    Currency[] currencies;
    Discount[] discounts;

    public Store(){
        this.name = "POO STORE";
        this.currency = new Currency("Euro", "€", 1.0);
        this.currencies = ArrayUtils.add(this.currencies, this.currency);
    }

    Product[] readCSV(String file) throws IOException, CsvValidationException {

        FileReader reader = new FileReader(file);
        CSVReader csv = new CSVReader(reader);
        String[] next;
        next = csv.readNext();

        while((next = csv.readNext()) != null){

            Manufacturer manufacturer = new Manufacturer(next[2]);
            String price_string = next[3].substring(1);
            price_string = price_string.replaceAll(",", "");
            double price = Double.parseDouble(price_string);
            String[] f = next[4].split("\\u00a0");

            Product.productBuilder builder = new Product.productBuilder();
            builder.setUniqueId(next[0]);
            builder.setName(next[1]);
            builder.setManufacturer(manufacturer);
            builder.setPrice(price);
            builder.setQuantity(Integer.parseInt(f[0]));

            Product product = new Product(builder);
            try{
                this.addProduct(product);
            }
            catch (Exception ex){
                System.out.println("Something went wrong. Please try again.");
            }

            try{
                this.addManufacturer(manufacturer);
            }
            catch (Exception ex){
                System.out.println("Something went wrong. Please try again.");
            }
        }

    return products;
}

    void addProduct(Product product) throws Product.DuplicateProductException{

        this.products = ArrayUtils.add(this.products, product);

    }

    void addManufacturer(Manufacturer manufacturer) throws  Manufacturer.DuplicateManufacturerException {

        this.manufacturers = ArrayUtils.add(this.manufacturers, manufacturer);
    }

    Currency createCurrency(String name, String symbol, String parityToEur){

        //create currencies[]
        Currency currency = new Currency(name,symbol ,Double.parseDouble(parityToEur));
        this.currencies = ArrayUtils.add(this.currencies, currency);
        return currency;
    }

    void changeCurrency(Currency currency) throws Currency.CurrencyNotFoundException {

        int ok = 0;
        for(Currency curren : currencies){
            if(curren.getName().equals(currency.getName())){
               ok = 1;
            }
        }

        if(ok == 0){
            throw new Currency.CurrencyNotFoundException("Something went wrong(currency)");
        }
        else{
            for (Product product : products) {
                product.price = product.price * this.currency.parityToEur / currency.parityToEur;
            }
        }

    }

    Discount createDiscounnt(Discount.DiscountType type, String name, String value){

        //create discounts
        Discount discount = new Discount(name, type, Double.parseDouble(value));
        discounts = ArrayUtils.add(discounts, discount);

        return discount;
    }

    void applyDiscount(Discount discount) throws Discount.DiscountNotFoundException, Product.NegativePriceException {

        for(Discount dis : discounts){
            if(dis.getName().equals(discount.getName()))
                throw new Discount.DiscountNotFoundException("Something went wrong (discount)");
        }

        for(Product product : products){
            if(discount.getDiscountType() == Discount.DiscountType.FIXED_DISCOUNT){
                product.price = product.price - discount.value;
            }
            else{
                product.price = product.price - product.price * discount.value / 100;
            }

            if(product.price < 0)
                throw new Product.NegativePriceException("Something went wrong(negative price)");
        }
    }


    Product[] getProductsByManufacturer(Manufacturer manufacturer){

        Product[] sameManufacturers = new Product[products.length];

        for(Product product : products){
            if(product.manufacturer.getName().equals(manufacturer.getName()))
                ArrayUtils.add(sameManufacturers, product);
        }

        return sameManufacturers;
    }

    double calculateTotal(Product[] products){

        double sum = 0.0;
        for(Product product : products){
            sum += product.price * product.quantity;
        }

        return sum;
    }

}
