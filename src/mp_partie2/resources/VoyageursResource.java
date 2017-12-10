package mp_partie2.resources;

import mp_partie2.internals.Gare;
import mp_partie2.internals.Voyageur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class VoyageursResource extends ServerResource{

    /** Gare. */
    private Gare gare;

    public VoyageursResource()
    {
        super();
        this.gare = (Gare) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     *
     * Retourne la liste de tous les voyageurs
     *
     * @return  La représentation en JSON des voyageurs
     * @throws JSONException
     */
    @Get("json")
    public Representation getVoyageurs() throws JSONException
    {
        Collection<Voyageur> voyageurs = gare.getVoyageursEnGare();
        Collection<JSONObject> jsonUsers = new ArrayList<>();

        for (Voyageur voyageur : voyageurs)
        {
            JSONObject current = new JSONObject();
            current.put("nom", voyageur.getNom());
            current.put("etat", voyageur.etat);
            jsonUsers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonUsers);
        return new JsonRepresentation(jsonArray);
    }

    /**
     * Créer un nouveau voyageur
     * @param representation
     * @return
     * @throws Exception
     */
    @Post("json")
    public Representation createVoyageur(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();
        String name = object.getString("nom");

        // Save the user
        Voyageur voyageur = new Voyageur(name, this.gare);
        gare.addVoyageur(voyageur);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("nom", voyageur.getNom());
        resultObject.put("etat", voyageur.etat);
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }


}
