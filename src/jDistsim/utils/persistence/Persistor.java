package jDistsim.utils.persistence;

import jDistsim.utils.logging.Logger;
import org.jdom.Element;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.FileInputStream;

public class Persistor {

    public static void save(Object ob, String filename) {
        try {
            ObjectWriter writer = new SimpleWriter();
            Element el = writer.write(ob);
            XMLOutputter out = new XMLOutputter();
            FileWriter file = new FileWriter(filename);
            out.output(el, file);
            file.close();
            System.out.println("Saved object to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object load(String filename) {
        try {
            SAXBuilder builder = new SAXBuilder();
            InputStream is = new FileInputStream(filename);
            Document doc = builder.build(is);
            Element el = doc.getRootElement();
            ObjectReader reader = new SimpleReader();
            return reader.read(el);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log(e);
            Logger.log(e.getStackTrace());
            return null;
        }
    }
}
