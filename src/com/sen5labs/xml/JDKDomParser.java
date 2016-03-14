/**
 *JDKDomParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.JDKDomParser
 * Xlee Create at 2016-3-14 下午4:45:28
 */
package com.sen5labs.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * com.sen5labs.xml.JDKDomParser
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 下午4:45:28
 */
public class JDKDomParser implements ServerParser {
    private DocumentBuilder builder = null;

    /**
     * Constructor
     * 
     * @throws Exception
     */
    public JDKDomParser() throws Exception {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /*
     * @see com.sen5labs.xml.ServerParser#getServer(java.lang.String)
     */
    @Override
    public List<Host> getServer(String xmlFilePath) throws SAXException, IOException {
        Document doc = builder.parse(xmlFilePath);
        // Find All Host
        NodeList nodeHostList = doc.getElementsByTagName(Constant.HOST);
        int hostCount = nodeHostList.getLength();
        if (hostCount <= 0) {
            return null;
        }
        List<Host> hostList = new ArrayList<Host>();
        for (int i = 0; hostCount > i; i++) {
            // XML attribute value can be parse with Node#getTextContent() or
            // Node#getNodeValue() method
            NamedNodeMap groupAttrMap = nodeHostList.item(i).getAttributes();
            String hostName = groupAttrMap.getNamedItem(Constant.HOST_NAME).getNodeValue();
            Host host = new Host();
            host.setName(hostName);
            parseUrlOfHost(nodeHostList.item(i), host);
            hostList.add(host);
        }
        return hostList;
    }

    private void parseUrlOfHost(Node hostNode, Host host) {
        NodeList hostChild = hostNode.getChildNodes();
        if (hostChild == null || hostChild.getLength() <= 0) {
            return;
        }
        // Iterate <url> nodes of one <host> node.

        for (int i = 0; hostChild.getLength() > i; i++) {
            Node nodeUrl = hostChild.item(i);
            if (!Constant.URL.equals(nodeUrl.getNodeName())) {
                continue;
            }
            host.setUrl(nodeUrl.getTextContent());
        }
    }
}
