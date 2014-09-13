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

    private String commandNameRegex;

    public CommandResolver(String configFileName)
            throws TechnicalException, LogicalException {
        XmlCommandInitializer commandBuilder = new XmlCommandInitializer();
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
        if(commandClassName == null) {
            return ActionNotFoundCommand.getInstance();
        }


        ActionCommand command;
        try {
            Class commandClass = Class.forName(commandClassName);
                    //(ActionCommand) Class.forName(commandClassName).newInstance();
            Method getInstanceMethod = commandClass.getDeclaredMethod("getInstance");
            command = (ActionCommand) getInstanceMethod.invoke(null, null);
        } catch (ClassNotFoundException
                | IllegalAccessException e) {
            command =  ActionNotFoundCommand.getInstance();
        } catch (NoSuchMethodException e) {
            command =  ActionNotFoundCommand.getInstance();
        } catch (InvocationTargetException e) {
            command =  ActionNotFoundCommand.getInstance();
        }

        return command;

    }






}
