package ovh.valulz.cvclient.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import ovh.valulz.cvclient.model.ShortCV;

public class ShortCVHandler extends DefaultHandler {

    private static final String TAG_CV = "CVENTRIES";
    private static final String TAG_ID = "ID";
    private static final String TAG_NAME = "NAME";
    private static final String TAG_FIRSTNAME = "FIRSTNAME";

    private boolean bfID;
    private boolean bfName;
    private boolean bfFirstName;

    private ShortCV cv = null;
    private List<ShortCV> cvs;

    public ShortCVHandler(){
        cvs = new ArrayList<ShortCV>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase(TAG_CV))          cv = new ShortCV();
        if(qName.equalsIgnoreCase(TAG_ID))          bfID = true;
        if(qName.equalsIgnoreCase(TAG_NAME))        bfName = true;
        if(qName.equalsIgnoreCase(TAG_FIRSTNAME))   bfFirstName= true;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(bfID){
            cv.setId(new String(ch, start, length));
            bfID = false;
        }

        if(bfName){
            cv.setName(new String(ch, start, length));
            bfName = false;
        }

        if(bfFirstName){
            cv.setFirstName(new String(ch, start, length));
            bfFirstName = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase(TAG_CV))  cvs.add(cv);
    }

    public List<ShortCV> getCvs() {
        return cvs;
    }
}
