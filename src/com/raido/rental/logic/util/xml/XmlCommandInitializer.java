package com.raido.rental.logic.util.xml;

import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class XmlCommandInitializer {

    private static final Logger LOGGER =
            Logger.getLogger(XmlCommandInitializer.class);

    private Map<String, String> commandsMap;

    private XMLInputFactory xmlInputFactory;

    public XmlCommandInitializer()  {
        xmlInputFactory = XMLInputFactory.newInstance();
        commandsMap = new HashMap<>();
    }



    public Map<String, String> getCommandsMap() {
        return Collections.unmodifiableMap(commandsMap);
    }

    public void buildCommandsMap(String sourceFileName)
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
                    if ("command".equals(elementName)) {
                        addCommandEntry(reader);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new LogicalException(MessageBundle.getString("exception_message", "file_not_found"));
        } catch (XMLStreamException e1) {
            throw new TechnicalException(MessageBundle.getString("exception_message", "parsing_failed"));
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(MessageBundle.getString("exception_message", "closing_failed"));
            }
        }
    }

    private void addCommandEntry(XMLStreamReader reader)
            throws LogicalException, XMLStreamException {

        String localName;
        String commandName = "";
        String commandClassName = "";

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
            case XMLStreamConstants.START_ELEMENT:
                localName = reader.getLocalName();
                String tagText = getXmlText(reader);

                if("name".equals(localName)) {
                    commandName = tagText;
                } else if ("class-name".equals(localName)) {
                    commandClassName = tagText;
                }
                break;
            case XMLStreamConstants.END_ELEMENT:
                localName = reader.getLocalName();
                if ("command".equals(localName)) {
                    commandsMap.put(commandName, commandClassName);
                    return;
                }
                break;
            }
        }
        throw new LogicalException(MessageBundle.getString("exception_message", "unknown_tag"));
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

