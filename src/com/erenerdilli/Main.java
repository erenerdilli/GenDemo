package com.erenerdilli;

import com.sun.deploy.net.HttpRequest;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        List<String> pIDList = new ArrayList<>();
        List<String> paperLinksList = new ArrayList<>();
        String paperLink ="https://www.ncbi.nlm.nih.gov/pubmed/";

        Patient mursel = new
                Patient("Mürsel Çalışkan", "11587985674", "R202Q", "2018", "01", "10");
        final String USER_AGENT = "Mozilla/5.0";

        String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=" + mursel.getMutation() +
                "%5BAll%20Fields%5D%20AND%20(%22" + mursel.getEntryYear() + "%2F" + mursel.getEntryMonth() + "%2F" + mursel.getEntryDay() +
                "%22%5BPDAT%5D%20%3A%20%223000%2F12%2F31%22%5BPDAT%5D)&RetMax=50";

        SendMail newMail = new SendMail("erenerdilli@gmail.com", "gen@gmail.com", "localhost");

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
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
            if (!isDocEmpty(doc)){
                //String pID = doc.getElementsByTagName("Id").item(1).getTextContent();
                populateList(doc, pIDList);
                for (String s: pIDList){
                    paperLinksList.add(paperLink+s);
                }
                printListElements(pIDList);
                System.out.println(pIDList.size());
                System.out.println("Sahip olduğunuz mutasyonla ilgili yeni eklenmiş " + pIDList.size() + " makale mevcuttur. Aşağıdaki linklerden ilgili makaleleri inceleyebilirsiniz:");
                String mailContent = "Sahip olduğunuz mutasyonla ilgili yeni eklenmiş " + pIDList.size() + " makale mevcuttur. Aşağıdaki linklerden ilgili makaleleri inceleyebilirsiniz:\n" + getLinksAsString(paperLinksList);
                printListElements(paperLinksList);
                newMail.sendEmail(mailContent);
            } else {
                System.out.println("No new paper!");
            }



            //print result
//            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void populateList(Document doc, List<String> pList){
        for (int i=0; i<doc.getElementsByTagName("Id").getLength(); i++){
            pList.add(doc.getElementsByTagName("Id").item(i).getTextContent());
        }
    }

    public static boolean isDocEmpty(Document doc){
        if (doc.getElementsByTagName("Id").getLength() == 0)
            return true;
        return false;
    }

    public static void printListElements(List<String> list){
        for (String s : list){
            System.out.println(s);
        }
    }

    public static String getLinksAsString(List<String> list){
        String content = "";
        for (String s : list)
            content+= s+"\n";

        return content;
    }
}
