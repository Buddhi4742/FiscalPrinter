public class Customer {
    private String taxRefNum;
    private String customerName;
    private String postCode;
    private String city;
    private String address;
    private String streetType;
    private String houseNumber;

    public  Customer(String taxRefNum, String customerName, String postCode, String city, String address, String streetType, String houseNumber){
        this.taxRefNum = taxRefNum;
        this.customerName = customerName;
        this.postCode = postCode;
        this.city = city;
        this.address = address;
        this.streetType = streetType;
        this.houseNumber =houseNumber;

    }

    public  String getTaxRefNum(){
        return this.taxRefNum;
    }
    public  String getCustomerName(){
        return  this.customerName;
    }
    public String getPostCode(){
        return  this.postCode;
    }
    public String getCity(){
        return  this.city;
    }
    public  String getAddress(){
        return this.address;
    }
    public  String getStreetType(){
        return this.streetType;
    }
    public  String getHouseNumber(){
        return  this.houseNumber;
    }
}
