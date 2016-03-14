/**
 *TestXmlParse.java[V 1.0.0]
 *classes : com.sen5labs.test.TestXmlParse
 * Xlee Create at 2016-3-14 上午11:30:02
 */
package com.sen5labs.xml;

import java.util.List;

/**
 * com.sen5labs.test.TestXmlParse
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 上午11:30:02
 */
public class TestXmlParse {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String xmlFile = Constant.XML_FILE_NAME;
        ServerParser parser = null;
        try {
            String parseType = Constant.PARSE_TYPE_JDOM;
            int count = 1;
            if (args.length > 0) {
                parseType = args[0];
            } else if (args.length > 1) {
                count = Integer.parseInt(args[1]);
            }

            long startTime = System.currentTimeMillis();
            if (Constant.PARSE_TYPE_SAX.equalsIgnoreCase(parseType)) {
                parser = new JDKSaxParser();
            } else if (Constant.PARSE_TYPE_DOM.equals(parseType)) {
                parser = new JDKDomParser();
            } else if (Constant.PARSE_TYPE_DOM4J.equals(parseType)) {
                parser = new Dom4JParser();
            } else if (Constant.PARSE_TYPE_STAX.equals(parseType)) {
                parser = new STAXParser();
            } else if (Constant.PARSE_TYPE_JDOM.equals(parseType)) {
                parser = new JDomParser();
            }
            List<Host> hostList = null;
            for (int i = 0; i < count; i++) {
                if (i == (count - 1)) {
                    hostList = parser.getServer(xmlFile);
                } else {
                    parser.getServer(xmlFile);
                }
            }
            for (Host host : hostList) {
                System.out.println(host);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Exceute parse \"" + count + "\" times. and spent \"" + (endTime - startTime)
                    + "\" milliseconds.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
