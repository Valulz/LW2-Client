package ovh.valulz.cvclient.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import ovh.valulz.cvclient.model.CV;
import ovh.valulz.cvclient.model.ComputerScienceSkill;
import ovh.valulz.cvclient.model.Experience;
import ovh.valulz.cvclient.model.Language;
import ovh.valulz.cvclient.model.Name;
import ovh.valulz.cvclient.model.School;

public class CVHandler extends DefaultHandler {


    private static final String TAG_ID = "ID";
    private static final String TAG_NAME = "NAME";
    private static final String TAG_FIRSTNAME = "FIRSTNAME";
    private static final String TAG_GENDER = "GENDER";
    private static final String TAG_OBJECTIVE = "OBJECTIVE";
    private static final String TAG_SKILL = "SKILL";
    private static final String TAG_EXP = "EXPERIENCES";
    private static final String TAG_SCHO = "SCHOOLS";
    private static final String TAG_LANGS = "LANGUAGES";
    private static final String TAG_SKILLS = "SKILLS";


    private boolean bfID;
    private boolean bfName;
    private boolean bfFirstName;
    private boolean bfGender;
    private boolean bfObjective;
    private boolean bfSkill;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());

    private CV cv;
    private boolean maiden;
    private Experience exp;
    private School school;
    private Language lang;
    private ComputerScienceSkill css;
    private boolean bfExp;
    private boolean bfSchool;

    public CVHandler() {
        cv = new CV();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(TAG_ID)) bfID = true;
        if (qName.equalsIgnoreCase(TAG_NAME)) {
            maiden = Boolean.parseBoolean(attributes.getValue(0));
            bfName = true;
        }
        if (qName.equalsIgnoreCase(TAG_FIRSTNAME)) bfFirstName = true;
        if (qName.equalsIgnoreCase(TAG_GENDER)) bfGender = true;
        if (qName.equalsIgnoreCase(TAG_OBJECTIVE)) bfObjective = true;
        if (qName.equalsIgnoreCase(TAG_SKILL)) bfSkill = true;
        if (qName.equalsIgnoreCase(TAG_EXP)) {
            exp = new Experience();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equalsIgnoreCase("begin")) {
                    String date = attributes.getValue(i).substring(0, 7);

                    try {
                        exp.setBegin(dateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (attributes.getQName(i).equalsIgnoreCase("end")) {
                    String date = attributes.getValue(i).substring(0, 7);

                    try {
                        exp.setEnd(dateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            bfExp = true;
        }

        if (qName.equalsIgnoreCase(TAG_SCHO)) {
            school = new School();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equalsIgnoreCase("begin")) {
                    String date = attributes.getValue(i).substring(0, 7);

                    try {
                        school.setBegin(dateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (attributes.getQName(i).equalsIgnoreCase("end")) {
                    String date = attributes.getValue(i).substring(0, 7);

                    try {
                        school.setEnd(dateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            bfSchool = true;
        }

        if (qName.equalsIgnoreCase(TAG_LANGS)) {
            lang = new Language();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equalsIgnoreCase("level")) {
                    lang.setLevel(Integer.parseInt(attributes.getValue(i)));
                } else if (attributes.getQName(i).equalsIgnoreCase("name")) {
                    lang.setName(attributes.getValue(i));
                } else if (attributes.getQName(i).equalsIgnoreCase("description")) {
                    lang.setDescription(attributes.getValue(i));
                }
            }
            cv.addLangs(lang);
        }

        if (qName.equalsIgnoreCase(TAG_SKILLS)) {
            css = new ComputerScienceSkill();

            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equalsIgnoreCase("level")) {
                    css.setLevel(Integer.parseInt(attributes.getValue(i)));
                } else if (attributes.getQName(i).equalsIgnoreCase("name")) {
                    css.setName(attributes.getValue(i));
                }
            }
            cv.addCSS(css);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bfID) {
            cv.setId(new String(ch, start, length));
            bfID = false;
        }

        if (bfName) {
            cv.setName(new Name(new String(ch, start, length), maiden));
            maiden = false;
            bfName = false;
        }

        if (bfFirstName) {
            cv.setFirstName(new String(ch, start, length));
            bfFirstName = false;
        }

        if (bfGender) {
            String g = new String(ch, start, length);
            cv.setGender(g.equalsIgnoreCase("MR") ? CV.Gender.MR : CV.Gender.MRS);
            bfGender = false;
        }

        if (bfObjective) {
            cv.setObjective(new String(ch, start, length));
            bfObjective = false;
        }

        if (bfSkill) {
            cv.setSkill(new String(ch, start, length));
            bfSkill = false;
        }

        if (bfExp) {
            exp.setName(new String(ch, start, length));
            bfExp = false;
        }

        if (bfSchool) {
            school.setName(new String(ch, start, length));
            bfSchool = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(TAG_EXP)) {
            cv.addExp(exp);
            exp = new Experience();
        }

        if (qName.equalsIgnoreCase(TAG_SCHO)) {
            cv.addScho(school);
            school = new School();
        }
    }

    public CV getCV() {
        return cv;
    }


}
