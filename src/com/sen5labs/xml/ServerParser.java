/**
 *ServerParser.java[V 1.0.0]
 *classes : com.sen5labs.xml.ServerParser
 * Xlee Create at 2016-3-14 上午11:36:47
 */
package com.sen5labs.xml;

import java.util.List;

/**
 * com.sen5labs.xml.ServerParser
 * 
 * @author Xlee <br/>
 *         create at 2016-3-14 上午11:36:47
 */
public interface ServerParser {

    /**
     * Return the server list which in the given xml file.
     * 
     * @param xmlFilePath
     *            The xml file path which contains all server info.
     * @return formatted String of server.
     */
    public List<Host> getServer(String xmlFilePath) throws Exception;
}
