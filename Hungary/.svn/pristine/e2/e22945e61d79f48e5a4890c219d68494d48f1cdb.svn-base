import java.util.ArrayList;
import java.util.HashMap;



public class Printer {
    public String addItem(Item item){
        return "command=sbt\t"+item.getUniqueId()+"\t"+item.getLineOne()+"\t"+item.getLineTwo()+"\t"+item.getUnitName()+"\t"+item.getComment()+"\t"+item.getItemNumber()+"\t"+item.getTaxLetter()+"\t"+item.getCollector()+"\t"+item.getPrice()+"\t"+item.getQuantity()+"\t"+item.getDiscount();
    }

    public String addSubTotal(boolean printSubTotal, boolean displaySubTotal, String discount){
        return "command=st\t"+printSubTotal+"\t"+displaySubTotal+"\n"+discount;
    }

    public String openSalesReceipt(String clerkId, String password, Customer customer, int noOfCopies){
        String out = "command=ofr\t"+clerkId+"\t"+password+"\t0\t0";
        if(customer != null){
            out+="\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t";
        }
        if(noOfCopies ==1){
            out+="\t1";
        }
        else{
            out+="\t2";
        }
        return out;
    }

    public String addPayment(String lineOne, String lineTwo, String paidMode, double amount){
        return "command=pmt\t"+lineOne+"\t"+lineTwo+"\t"+paidMode+"\t"+amount;
    }

    public String closeFiscalReceipt(){
        return "command=cfr";
    }
    public String dayOpen(String mode, HashMap<String,Double> pay){
        if(mode == null && pay == null){
            return "command=fod";
        } else if (mode=="R") {
            return "command=fod\tR";
        }else{
            String out = "command=fod\t";
            Object[] keys = pay.keySet().toArray();
            for (int i = 0; i < keys.length; i++) {
                out+=keys[i]+","+pay.get(keys[i]);
                if(i<keys.length-1){
                    out+="|";
                }
            }
//            System.out.println(out);
            return out;
        }
    }

    public String openInvoice(String clerkId, String password, Customer customer, int noOfCopies){
        String out = "command=ofr\t"+clerkId+"\t"+password+"\t0\tI\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t";
        if(noOfCopies ==1){
            out+="\t1";
        }
        else{
            out+="\t2";
        }
        return out;
    }



    public String returnReceipt(String apNumber, String date, String receiptType, int z1Count, int recNo, int reasonNo, Customer customer, String paidMode, HashMap<Integer, Double> itemsList){
        String out = "command=bbr\t"+apNumber+"\t"+date+"\t"+receiptType+"\t"+z1Count+"\t"+recNo+"\t"+reasonNo+"\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t"+paidMode;
        if(itemsList==null){
            return out;
        }
        else{
            out+="\t";
            Object[] keys = itemsList.keySet().toArray();
            for (int i = 0; i < itemsList.size(); i++) {
                if(itemsList.get(keys[i]) == null){
                    out+=String.valueOf(keys[i]);
                }
                else{
                    out+= keys[i] +":"+ itemsList.get(keys[i]);

                }
                if(i<itemsList.size()-1){
                    out+="\t";
                }


            }
            System.out.println(out);
            return out;
        }

    }

    public String payIn(int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        String out = "command=ra\t"+reasonNo+"\t";
        Object[] keys = pay.keySet().toArray();
        for (int i = 0; i < pay.size(); i++) {
            out+=keys[i]+","+pay.get(keys[i])+"|";

        }
        if(noOfCopies==1){
            return out+="COPY1";
        }
        else{
            out+="COPY2";
        }
        return out;
    }

    public String payOut(int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        String out = "command=po\t"+reasonNo+"\t";
        Object[] keys = pay.keySet().toArray();
        for (int i = 0; i < pay.size(); i++) {
            out+=keys[i]+","+pay.get(keys[i])+"|";

        }
        if(noOfCopies==1){
            return out+="COPY1";
        }
        else{
            out+="COPY2";
        }
        return out;
    }

    public String changePaymentMode(String fromPaidMode, double amount, String toPaidMode){
        System.out.println("command=cpmm\t"+fromPaidMode+","+amount+"\t"+toPaidMode);
        return "command=cpmm\t"+fromPaidMode+","+amount+"\t"+toPaidMode;
    }

    public String getZReport(int printOption){
        return "command=pgr\t"+printOption+"\t"+19;
    }

    public String getXReport(int printOption){
        return "command=pgr\t"+printOption+"\t"+2;
    }
    public String getCurrentDayEJReport(int printOption){
        return "command=pcdej\t"+printOption;
    }
    public String getBeforeDayEJReport(int printOption){
        return "command=pbdej\t"+printOption;
    }
    public String checkAEEStatus(){
        return "command=rs";
    }

    ///for bottle refund
    public String openRefundReceipt(String clerkId, String password, int reasonNo){
        return "command=ofr\t"+clerkId+"\t"+password+"\t0\tR\t"+reasonNo;
    }

    public String changeFtEuroRate(double ftEuroRate){
        return "command=wfc\t"+ftEuroRate;
    }

    public String readForeignCurrencyRate(){
        return "command=rfc";
    }

    public String writeTailMessage(int lineNumber, String message){
        return "command=wtm\t"+lineNumber+"\t"+message;
    }

    public String readTailMessage(int lineNumber){
        return "command=rtm\t"+lineNumber;
    }

    public String writeTailSpaceLine(int spaceLine){
        return "command=wts\t"+spaceLine;
    }

    public String readTailSpaceLine(){
        return "command=rts";
    }

    public String giveNormaldiscount(String mode, double value, String comment){
        String out="command=d\t"+mode+","+value;
        if(comment != null){
            out+=","+comment;
        }
        return out;
    }

    public String giveTaxDiscount(String mode, String value, String comment, String taXLetter, String collector){
        String out="command=d\t"+mode+","+value;
        if(comment != null){
            out+=","+comment;
        }
        out+="\n"+taXLetter+","+collector;
        return out;
    }

    public String writeDevTime(int dd, int mm, int yy, int hh, int min){
        return "command=wdt\t"+dd+"-"+mm+"-"+yy+" "+hh+":"+min;
    }
    public String closeRefundReceipt(int recyclePayment){
        return "command=crr\t"+recyclePayment;
    }

    public String cancelFiscalReceipt(){
        return "command=celfr";
    }

    public String readXData(int dataType){
        return "command=rxd\t"+dataType;
    }

    public String writeCustomPaymentsName(ArrayList<String> paymentNames){
        String out = "command=wcpm\t";
        for (int i = 0; i < paymentNames.size(); i++) {
            out+= paymentNames.get(i);
            if(i< paymentNames.size()-1){
                out+="\n";
            }
        }
        return out;
    }
    public String writeOperator(int id, String name, String password){
        return "command=wo\t"+id+"\t"+name+"\t"+password;
    }

    public String openDrawer(){
        return "command=od";
    }

    public String clear(){
        return "command=clr";
    }

    public String clearLastAddedItem(){
        return "command=ec";
    }

    public String openNonFiscalReceipt(){
        return "command=onfr";
    }
//    TODO: Test print non fiscal Text Function
    public String printNonFiscalText(HashMap<String, String> data){
        String out = "command=pnft\t";
        Object[] formats = data.keySet().toArray();
        for (int i = 0; i < data.size(); i++) {
            out+=formats[i]+"\r"+data.get(formats[i]);
            if(i<data.size()-1){
                out+="\n";
            }
        }
        return out;
    }

    public String closeNonFiscalReceipt(){
        return "command=cnfr";
    }

    public String printFiscalMemoryDetailReportByNumber(int fromZCount, int toZCount){
        return "command=pfdrn\t"+fromZCount+"\t"+toZCount;
    }

    public String printFiscalMemoryDetailReportByDate(int fromDate, int toDate){
        return "command=pfdrd\t"+fromDate+"\t"+toDate;
    }

    public String printFiscalMemoryShortReportByNumber(String fromZCount, String toZCount){
        return "command=pfsrn\t"+fromZCount+"\t"+toZCount;
    }

    public String printFiscalMemoryShortReportByDate(String fromDate, String toDate){
        return "command=pfsrd\t"+fromDate+"\t"+toDate;
    }

    public String printDiagnInfo(int option){
        return "command=pdi\t"+option;
    }

    public String printBarcode(String type, String text){
        return "command=pcd\tC\t"+type+","+text;
    }

//    TODO: Test print custom tail message functionality
    public String printCustomTailMessage(int packIndex, HashMap<String, String > data){
        String out="command=pcd\tT"+packIndex+"\t";
        Object[] formats = data.keySet().toArray();
        for (int i = 0; i < data.size(); i++) {
            out+=formats[i]+"\r"+data.get(formats[i]);
            if(i<data.size()-1){
                out+="\n";
            }
        }
        return out;
    }

    public String clearDisplay(){
        return "command=cd";
    }

    public String showDateTime(){
        return "command=sdt";
    }

//    TODO: Test readReceipt functionality
    public String readReceipt(int receiptMode, int zCount, int receiptNum){
        return "command=rfr\t"+receiptMode+"\t"+zCount+"\t"+receiptMode;
    }

    public String testConnection(int type){
        return "command=tcg\t"+type;
    }

    public String readCustomForeignCurrency(){
        return "command=rcfc";
    }

    public String setCustomForeignCurrency(HashMap<String, Double> currencies){
        String out = "command=wcfc";
        Object[] keys = currencies.keySet().toArray();
        for (int i = 0; i < currencies.size(); i++) {
            out+="\t"+keys[i]+"\t"+currencies.get(keys[i]);
        }
        return out;

    }

    public String readLastFullReceiptStatus(){
        return "command=rlrn";
    }

    public String readDrawerContentByVATCategory(int type){
        return "command=rvdd\t"+type;
    }


    public String printReceiptCopy(String z1Count, String recNumber){
        return "command=pzrej\t1\t1\t"+z1Count+recNumber;
    }

    public String getEJtoUSBbyDate(String fromDate, String toDate){
        return "command=exml\t0,0,"+fromDate+","+toDate;
    }

    public String getEJtoUSBbyNumber(String from, String to){
        return "command=exml\t0,0,"+from+","+to;
    }



}
