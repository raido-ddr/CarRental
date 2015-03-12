package com.raido.rental.logic.command.resolver;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.impl.ActionNotFoundCommand;
import com.raido.rental.logic.util.xml.XmlCommandInitializer;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandResolver {

    private Map<String, String> commandMap;



    public CommandResolver(String configFileName)
            throws TechnicalException, LogicalException {
        XmlCommandInitializer commandBuilder = new XmlCommandInitializer();
        commandBuilder.buildCommandsMap(configFileName);
        commandMap = commandBuilder.getCommandsMap();

    }

    public ActionCommand resolveCommand(String commandName) {

        String commandClassName = commandMap.get(commandName);
        if(commandClassName == null) {
            return ActionNotFoundCommand.getInstance();
        }

        ActionCommand command;
        try {
            Class commandClass = Class.forName(commandClassName);
            Method getInstanceMethod = commandClass.getDeclaredMethod("getInstance");
            command = (ActionCommand) getInstanceMethod.invoke(null, null);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException e) {
            command =  ActionNotFoundCommand.getInstance();
        }

        return command;

    }






}
