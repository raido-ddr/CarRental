package com.raido.rental.logic.util.xml;


import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class XmlPermissionInitializer {

    private static final Logger LOGGER =
            Logger.getLogger(XmlPermissionInitializer.class);

    private Map<String, Set<String>> permissionMap;

    private XMLInputFactory xmlInputFactory;

    public XmlPermissionInitializer()  {
        xmlInputFactory = XMLInputFactory.newInstance();
        permissionMap = new HashMap<>();
    }

    public Map<String, Set<String>> getPermissionMap() {
        return Collections.unmodifiableMap(permissionMap);
    }

    public void buildPermissionMap(String sourceFileName)
            throws TechnicalException, LogicalException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String elementName;

        try {
            inputStream = new FileInputStream(new File(sourceFileName));
            reader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                    if ("permission".equals(elementName)) {
                        addPermissionEntry(reader);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new LogicalException(MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "file_not_found"));
        } catch (XMLStreamException e) {
            throw new TechnicalException(MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "parsing_failed"));
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "closing_failed"));
            }
        }
    }

    private void addPermissionEntry(XMLStreamReader reader)
            throws LogicalException, XMLStreamException {

        String localName;
        String commandName = "";
        Set<String> roles = new HashSet<>();

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
            case XMLStreamConstants.START_ELEMENT:
                localName = reader.getLocalName();
                String tagText = getXmlText(reader);

                if ("command".equals(localName)) {
                    commandName = tagText;
                } else if ("role".equals(localName)) {
                    roles.add(tagText);
                }
                break;
            case XMLStreamConstants.END_ELEMENT:
                localName = reader.getLocalName();
                if ("permission".equals(localName)) {
                    permissionMap.put(commandName, roles);
                    return;
                }
                break;
            }
        }
    }

    private String getXmlText(XMLStreamReader reader)
            throws XMLStreamException {
        String xmlText = null;
        if (reader.hasNext()) {
            reader.next();
            xmlText = reader.getText();
        }

        return xmlText;
    }
}

