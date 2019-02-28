package com.erenerdilli;

import org.w3c.dom.Document;

import java.util.List;

import static com.erenerdilli.Strings.TAG_ID;

public enum Utilities {


    /*
    * @Author erenerdilli
    * The class for basic utility methods
     */
    INITIALIZE;

    public static void populateList(Document doc, List<String> pList){
        for (int i=0; i<doc.getElementsByTagName(TAG_ID).getLength(); i++){
            pList.add(doc.getElementsByTagName(TAG_ID).item(i).getTextContent());
        }
    }

    public static boolean isDocEmpty(Document doc){
        if (doc.getElementsByTagName(TAG_ID).getLength() == 0)
            return true;
        return false;
    }

    public static void printListElements(List<String> list){
        for (String s : list){
            System.out.println(s);
        }
    }

    // Get the links in the Arraylist and put them in a string with new lines for each.
    public static String getLinksAsString(List<String> list){
        String content = "";
        for (String s : list)
            content+= s+"\n";

        return content;
    }
}
