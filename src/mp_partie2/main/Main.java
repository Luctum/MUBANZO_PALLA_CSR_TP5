package mp_partie2.main;

import mp_partie2.application.GareApp;
import mp_partie2.internals.Gare;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

public class Main {
    /** Hide constructor. */
    private Main()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Main method.
     *
     * @param args  The arguments of the commande line
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8124);

        // Create an application
        Application application = new GareApp(context);

        // Add the backend into component's context
        Gare gare = new Gare();
        context.getAttributes().put("backend", gare);
        component.getDefaultHost().attach(application);

        // Start the component
        component.start();
    }


}
