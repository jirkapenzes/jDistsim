package jDistsim.utils.resource;

import jDistsim.utils.logging.Logger;

import java.awt.*;
import java.net.URL;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 14:47
 */
public class Resources {

    private static Resources resource = new Resources();

    public static Image getImage(String name) {
        URL url = resource.getClass().getResource("/jDistsim/resources/"  + name);
        //URL url = Resources.class.getResource(name);

        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
