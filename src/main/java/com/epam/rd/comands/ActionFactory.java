package com.epam.rd.comands;

import com.epam.rd.comands.cars.*;
import com.epam.rd.comands.trips.*;
import com.epam.rd.comands.user.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, ActionCommand> actions;
    private static final ActionFactory factory = new ActionFactory();
    private static final String GET = "GET:";
    private static final String POST = "POST:";
    private final String context = "/autobase";

    public static ActionFactory getActionFactory() {
        return factory;
    }

    private ActionFactory() {
        actions = new HashMap<>();
        actions.put(GET + context + "/newcar", new GetCreateCarCommand());
        actions.put(POST + context + "/newcar", new CreateCarCommand());
        actions.put(GET + context + "/singin", new GetRegisterCommand());
        actions.put(POST + context + "/singin", new RegisterCommand());
        actions.put(GET + context + "/login", new GetLoginCommand());
        actions.put(POST + context + "/login", new LoginCommand());
        actions.put(GET + context + "/logout", new LogoutCommand());
        actions.put(GET + context + "/user", new GetUserPage());
        actions.put(GET + context + "/users", new GetAllUsersCommand());
        actions.put(GET + context + "/admin", new GetAdminPage());
        actions.put(GET + context + "/manager", new GetManagerPage());
        actions.put(GET + context + "/cars", new GetAllCarsCommand());
        actions.put(GET + context + "/deletecar", new GetDeleteCarCommand());
        actions.put(POST + context + "/deletecar", new DeleteCarCommand());
        actions.put(GET + context + "/updatecar", new GetUpdateCarCommand());
        actions.put(POST + context + "/updatecar", new UpdateCarCommand());
        actions.put(GET + context + "/newtrip", new GetCreateTripCommand());
        actions.put(POST + context + "/newtrip", new CreateTripCommand());
        actions.put(GET + context + "/trips", new GetAllTripsCommand());
        actions.put(GET + context + "/setuser", new GetSetUserCommand());
        actions.put(GET + context + "/setcar", new GetSetCarCommand());
        actions.put(POST + context + "/setcar", new SetCarCommand());
        actions.put(GET + context + "/setstatus", new GetSetStatusCommand());
        actions.put(POST + context + "/setstatus", new SetStatusCommand());
        actions.put(GET + context + "/usertrips", new GetUserTripsCommand());
        actions.put(GET + context + "/closetrip", new GetCloseTripCommand());
    }

    public ActionCommand defineCommand(String key){
        ActionCommand emptyCommand = new EmptyCommand();
        if (key == null || key.isEmpty()){
            return emptyCommand;
        }
        return actions.getOrDefault(key,emptyCommand);
    }
}
