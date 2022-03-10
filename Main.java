import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Currency.CurrencyNotFoundException, IOException, CsvValidationException {

        Scanner scan = new Scanner(System.in);
        String  command = scan.next();
        Store store = new Store();
        Currency c = new Currency();

        while(!command.equals("quit") && !command.equals("exit")){

            switch(command){
                case "listcurrencies": //done

                   for(Currency currency : store.currencies){
                       System.out.println(currency.toString());
                   }

                    break;

                case "getstorecurrency": //done

                    System.out.println(store.currency.toString());

                    break;

                case "addcurrency" : //done

                    String name_currency = scan.next();
                    String symbol_currency = scan.next();
                    String value = scan.next() ;
                    store.createCurrency(name_currency, symbol_currency,
                            value);

                    break;

                case "loadcsv" : //done ?

                    String file_name = scan.next();
                    store.products = store.readCSV(file_name);

                    break;

                case "setstorecurrency": //not done

                    name_currency = scan.next();
                    for(Currency currency: store.currencies){
                        if(currency.getName().equals(name_currency)){
                            store.currency = currency;
                            break;
                        }
                    }

                    break;

                case "updateparity": //done

                    name_currency = scan.next();
                    String new_value = scan.next();

                    for(Currency currency : store.currencies){
                        if(currency.getName().equals(name_currency)){
                            currency.updateParity(Double.parseDouble(new_value));
                            break;
                        }
                    }

                    break;

                case "listproducts": //done

                    if (store.products == null) {
                        System.out.println("We don't have any products");
                    }
                    else{
                        for(Product pr : store.products){
                            System.out.println(pr.toString(store.currency.getSymbol()));
                        }
                    }
                    break;

                case "showproduct": //done

                    String id = scan.next();
                    if(store.products == null){
                        System.out.println("We don't have any products");
                    }
                    else{
                        for(Product pr : store.products){
                            if(pr.getUniqueId().equals(id)){
                                System.out.println(pr.toString(store.currency.getSymbol()));
                                break;
                            }
                        }
                    }

                    break;

                case "listmanufacturers": //done

                    for(Manufacturer man : store.manufacturers){
                        System.out.println(man.toString());
                    }
                    break;

                case "listproductsbymanufacturer": //done
                    String name_manufacturer = scan.nextLine();
                    if(store.manufacturers == null){
                        System.out.println("We don't have any manufacturers!");
                    }
                    else{
                        for(Product product : store.products){
                            if(product.getManufacturer().equals(name_manufacturer)){
                                System.out.println(product.toString(store.currency.getSymbol()));
                                break;
                            }
                        }
                    }

                    break;

                case "listdiscounts": //done

                    if(store.discounts != null){
                        for(Discount ds : store.discounts){
                            System.out.println(ds.toString());
                        }
                    }
                    else{
                        System.out.println("We don't have any discount!!!");
                    }

                    break;

                case "addiscount":

                    String type_discount = scan.next();
                    String value_discount = scan.next();
                    String name_discount = scan.nextLine();

                    if(type_discount == "PERCENTAGE"){
                        Discount new_discount = new Discount(name_discount, Discount.DiscountType.PERCENTAGE_DISCOUNT,
                                Double.parseDouble(value_discount));
                        store.createDiscounnt(Discount.DiscountType.PERCENTAGE_DISCOUNT, name_discount, value_discount);
                    }
                    else{
                        Discount new_discount = new Discount(name_discount, Discount.DiscountType.FIXED_DISCOUNT,
                                Double.parseDouble(value_discount));
                        store.createDiscounnt(Discount.DiscountType.FIXED_DISCOUNT, name_discount, value_discount);
                    }


                    break;

                case "calculatetotal": //done

                    String input = scan.nextLine();
                    String[] ids = input.split(" ");
                    ids[0] = "0";
                    double sum = 0.0;

                   for(int i = 1; i <= ids.length; i++){
                       for(Product pr: store.products){
                           if(ids[i].equals(pr.getUniqueId())){
                               sum += pr.quantity * pr.price;
                           }
                       }
                   }
                   System.out.println(sum);

                   break;

                default:
                    System.out.println("Command not found");
            }
            command = scan.next();
        }
    }
}
