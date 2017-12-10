package mp_partie2.application;

import mp_partie2.resources.TrainsResource;
import mp_partie2.resources.VoyageursResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class GareApp extends Application
{
    public GareApp(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/voyageurs", VoyageursResource.class);
        router.attach("/trains", TrainsResource.class);
        return router;
    }
}