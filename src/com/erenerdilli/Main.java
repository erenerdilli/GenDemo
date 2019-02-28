package com.erenerdilli;

import static com.erenerdilli.Strings.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        List<String> pIDList = new ArrayList<>();
        List<String> paperLinksList = new ArrayList<>();

        // Create a new Patient instance
        Patient mursel = new
                Patient("Mürsel Çalışkan", "11587985674", "R202Q", "2018", "01", "10");
        RequestBuilder reqBuilder = new RequestBuilder(mursel.getMutation(), mursel.getEntryYear(), mursel.getEntryMonth(), mursel.getEntryDay());

        // Build the URL
        String url = reqBuilder.getRequestURL();

        // Prepare mail properties.
        SendMail newMail = new SendMail("erenerdilli@gmail.com", MAIL_FROM, MAIL_HOST);

        // Send request to NCBI
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(REQUEST_GET);
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();

            System.out.println("\n Sending GET request to url " + url);
            System.out.println("Response Code: " + responseCode);


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src= new InputSource();
            src.setCharacterStream(new StringReader(response.toString()));

            Document doc = builder.parse(src);
            if (!Utilities.isDocEmpty(doc)){
                Utilities.populateList(doc, pIDList);
                for (String s: pIDList){
                    paperLinksList.add(PAPER_ROOT_LINK+s);
                }
                Utilities.printListElements(pIDList);
                System.out.println(pIDList.size());
                String mailContent = "Sahip olduğunuz mutasyonla ilgili yeni eklenmiş " +
                        pIDList.size() + " makale mevcuttur. Aşağıdaki linklerden ilgili makaleleri inceleyebilirsiniz:\n" + Utilities.getLinksAsString(paperLinksList);

                Utilities.printListElements(paperLinksList);
                System.out.println("Sending mail...");
                newMail.sendEmail(mailContent);
            } else {
                System.out.println(NO_NEW_PAPER);
            }



            //print result
//            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all elements with Id tag from the xml response and put them in a list
//    public static void populateList(Document doc, List<String> pList){
//        for (int i=0; i<doc.getElementsByTagName(TAG_ID).getLength(); i++){
//            pList.add(doc.getElementsByTagName(TAG_ID).item(i).getTextContent());
//        }
//    }

    // Check if the given document is empty
//    public static boolean isDocEmpty(Document doc){
//        if (doc.getElementsByTagName(TAG_ID).getLength() == 0)
//            return true;
//        return false;
//    }

//    public static void printListElements(List<String> list){
//        for (String s : list){
//            System.out.println(s);
//        }
//    }

    // Get the links in the Arraylist and put them in a string with new lines for each.
//    public static String getLinksAsString(List<String> list){
//        String content = "";
//        for (String s : list)
//            content+= s+"\n";
//
//        return content;
//    }
}
