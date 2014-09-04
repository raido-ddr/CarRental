package com.raido.rental.logic.command.resolver;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.impl.ActionNotFoundCommand;
import com.raido.rental.logic.command.xmlparser.XmlCommandBuilder;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandResolver {

    private Map<String, String> commandMap;

    private String commandNameRegex;

    public CommandResolver(String configFileName)
            throws TechnicalException, LogicalException {
        XmlCommandBuilder commandBuilder = new XmlCommandBuilder();
        commandBuilder.buildCommandsMap(configFileName);
        commandMap = commandBuilder.getCommandsMap();

        ResourceBundle bundle = ResourceBundle.getBundle("regex");
        commandNameRegex = bundle.getString("command.name.regex");
    }

    public ActionCommand resolveCommand(String commandName) {

        //Pattern pattern = Pattern.compile(commandNameRegex);
        //Matcher matcher = pattern.matcher(commandPath);
        //String commandName = matcher.group(0);

        String commandClassName = commandMap.get(commandName);

        ActionCommand command;
        try {
            command = (ActionCommand) Class.forName(commandClassName).newInstance();
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException e) {
            command =  new ActionNotFoundCommand();
        }

        return command;

    }






}
