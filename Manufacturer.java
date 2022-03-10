public class Manufacturer {

    String name;
    int countProducts;

    public Manufacturer(String name) {
        this.name = name;
    }


    static class DuplicateManufacturerException extends Exception{
        DuplicateManufacturerException(String message){
            System.err.println(message);
        }
    }

    String getName(){
        return this.name;
    }

    int getCountProducts(){
        return  this.countProducts;
    }

    public String toString(){
        return name;
    }
}
