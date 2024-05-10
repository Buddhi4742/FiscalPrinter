import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpRequestResponseTest {

    public static void sendRequest(String command) throws IOException {
        URL url = new URL("http://127.0.0.1:15000/Request");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host","127.0.0.1");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(command.length()));
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        try{
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            byte[] body = command.getBytes((StandardCharsets.UTF_8));
            outputStream.write(body);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }

    public static void printSalesReceipt(Printer printer, String clerkId, String password, ArrayList<Item> items){
        try{
            sendRequest(printer.openSalesReceipt(clerkId, password, null, 1));
            for (int i = 0; i < items.size(); i++) {
                sendRequest(printer.addItem(items.get(i)));

            }
            sendRequest(printer.addPayment("","","P",400));
            sendRequest(printer.closeFiscalReceipt());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void printInvoice(Printer printer, String clerkId, String password, Customer customer, ArrayList<Item> items, String paidMode, double amount){
        try{
            sendRequest(printer.openInvoice(clerkId, password, customer, 1));
            for (int i = 0; i < items.size(); i++) {
                sendRequest(printer.addItem(items.get(i)));
            }
            sendRequest(printer.addPayment("","",paidMode,amount));
            sendRequest(printer.closeFiscalReceipt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printReturnReceipt(Printer printer, String apNumber, String date, String receiptType, int z1Count, int recNo, int reasonNo, Customer customer, String paidMode, HashMap<Integer, Double> itemsList){
        try{
            sendRequest(printer.returnReceipt(apNumber, date, receiptType, z1Count, recNo, reasonNo, customer, paidMode, itemsList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void dayOpen(Printer printer, String mode, HashMap<String,Double> pay){
        try{
            sendRequest(printer.dayOpen(mode, pay));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPayIn(Printer printer, int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        try {
            sendRequest(printer.payIn(reasonNo, pay,noOfCopies));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printPayOut(Printer printer, int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        try{
            sendRequest(printer.payOut(reasonNo, pay, noOfCopies));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPaymentMediaChange(Printer printer, String fromPaidMode, double amount, String toPaidMode){
        try {
            sendRequest(printer.changePaymentMode(fromPaidMode, amount, toPaidMode));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printZReport(Printer printer, int printOption){
        try {
            sendRequest(printer.getZReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printXReport(Printer printer, int printOption){
        try {
            sendRequest(printer.getXReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printCurrentDayEJReport(Printer printer, int printOption){
        try {
            sendRequest(printer.getCurrentDayEJReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printBeforeDayEJReport(Printer printer, int printOption){
        try {
            sendRequest(printer.getBeforeDayEJReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void checkAEEStatus(Printer printer){
        try {
            sendRequest(printer.checkAEEStatus());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printBottleRefund(Printer printer, String clerkId, String password, int reasonNo, Item bottle){
        try {
            sendRequest(printer.openRefundReceipt(clerkId, password, reasonNo));
            sendRequest(printer.addItem(bottle));
            sendRequest(printer.closeRefundReceipt(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void cancelFiscalReceipt(Printer printer){
        try {
            sendRequest(printer.cancelFiscalReceipt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printReceiptCopy(Printer printer, String z1Count, String recNumber){
        try {
            sendRequest(printer.printReceiptCopy(z1Count, recNumber));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getEJtoUSBbyDate(Printer printer, String fromDate, String toDate){
        try {
            sendRequest(printer.getEJtoUSBbyDate(fromDate, toDate));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getEJtoUSBbyNumber(Printer printer, String from, String to){
        try {
            sendRequest(printer.getEJtoUSBbyNumber(from, to));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) throws IOException {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("11111","Item 4","","Kg","","1","C","00",120,2,"0"));
        items.add(new Item("22222","Item 3","","Kg","","1","C","00",50,3,"0"));
        Customer customer = new Customer("12345678911", "Ashan Silva", "10230", "Madapatha", "Batakettara", "Polgahakottanuwa", "370/6");
        Item bottle = new Item("33333", "Vodka Bottle", "", "db", "","322144", "C","00", 150, 10, "0");
        Printer printer = new Printer();
        Boolean isAlive = true;
        while(isAlive){
            System.out.println("\n\nOptions: \n");
            System.out.println("A : Print Sales Receipt.\nB : Print Invoice\nC : Print Return Receipt\nD: Day Open\nE : Print Pay In\nF : Print Pay Out\nG : Print Payment Media Change\nH : Print Z Report\nI : Print X Report\nJ : Print Current Day EJ Report\nK : Print Before Day EJ Report\nL : Check AEE Status\nM : Print Bottle Refund\nN : Cancel Fiscal Receipt\nO : Print Receipt Copy\nP : EJ to USB by Date\nR : EJ to USB by Number\nQ : Quit"  );

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>Enter Option Letter: ");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch (option){
                case "A":
                    printSalesReceipt(printer,"0","000000", items);
                    break;
                case  "B":
                    printInvoice(printer, "0","000000", customer, items, "P", 400);
                    break;
                case "C":
                    HashMap returnItems = new HashMap<Integer, Double>();
                    returnItems.put(1, 1.2);
                    returnItems.put(2,1.5);
                    printReturnReceipt(printer, "Y04700256", "220620","I", 5, 5, 1, customer, "P", returnItems);
                    break;
                case "D":
                    HashMap<String, Double> dayOpen = new HashMap<String, Double>();
                    dayOpen.put("P", 50000.0);
                    dayOpen.put("F0", 100.12);
                    dayOpen.put("N", 1000.0);
                    dayOpen.put("O1", 12000.0);
                    dayOpen.put("O3", 1200.0);
                    dayOpen(printer, null, dayOpen);
                    break;
                case "E":
                    HashMap pay = new HashMap<String, Double>();
                    pay.put("P", 5000);
                    pay.put("F", 1000.12);
                    pay.put("N", 400);
                    pay.put("O1", 1200);
                    printPayIn(printer, 6, pay, 1);
                    break;
                case "F":
                    HashMap payOut = new HashMap<String, Double>();
                    payOut.put("P", 300);
                    printPayOut(printer, 6, payOut, 1);
                    break;
                case "G":
                    printPaymentMediaChange(printer, "P", 12.5, "N");
                    break;
                case "H":
                    printZReport(printer, 1);
                    break;
                case  "I":
                    printXReport(printer, 1);
                    break;
                case "J":
                    printCurrentDayEJReport(printer,1);
                    break;
                case "K":
                    printBeforeDayEJReport(printer,1);
                    break;
                case "L":
                    checkAEEStatus(printer);
                    break;
                case "M":
                    printBottleRefund(printer, "0","000000", 3, bottle);
                    break;
                case "N":
                    cancelFiscalReceipt(printer);
                    break;
                case "O":
                    printReceiptCopy(printer,"0005","00005");
                    break;
                case "P":
                    getEJtoUSBbyDate(printer,"20220623","20220627");
                    break;
                case "R":
                    getEJtoUSBbyNumber(printer,"00000000","00000002");
                    break;
                case "Q":
                    isAlive = false;
                    break;
                default:
                    break;


            }
        }


    }


}
