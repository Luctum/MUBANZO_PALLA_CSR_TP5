package mp_partie2.resources;

import mp_partie2.internals.Gare;
import mp_partie2.internals.Train;
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

public class TrainsResource extends ServerResource{

    /** Gare. */
    private Gare gare;

    public TrainsResource()
    {
        super();
        this.gare = (Gare) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     *
     * Retourne la liste de tous les trains
     *
     * @return  La représentation en JSON des trains
     * @throws JSONException
     */
    @Get("json")
    public Representation getTrains() throws JSONException
    {
        Collection<Train> trains = gare.getTrainsPrévus();
        Collection<JSONObject> jsonTrains = new ArrayList<>();

        for (Train train : trains)
        {
            JSONObject current = new JSONObject();
            current.put("nom", train.getName());
            current.put("id", train.getidTrain());
            current.put("vitesse", train.getVitesse());
            current.put("capacité", train.getCapacite());
            current.put("temps d'arret", train.getTmpsArret());
            current.put("etat", train.etat);
            jsonTrains.add(current);
        }

        JSONArray jsonArray = new JSONArray(jsonTrains);
        return new JsonRepresentation(jsonArray);
    }


    /**
     * Créer un nouveau train
     * @param representation
     * @return
     * @throws Exception
     */
    @Post("json")
    public Representation createTrain(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();
        int idTrain = gare.getTrainsPrévus().size()+1;
        int vitesse = object.getInt("vitesse");
        int capacite = object.getInt("capacite");
        int tmpsArret= object.getInt("temps_arret");


        JSONObject resultObject = new JSONObject();

        //Si la vitesse du train n'est pas comprise entre 50 et 300 km/h renvoie une erreur
        if(vitesse < 50 || vitesse > 300){
            resultObject.put("erreur", "La vitesse doit être comprise entre 50 et 300");
            JsonRepresentation result = new JsonRepresentation(resultObject);
            return result;
        }

        // Save the user
        Train train = new Train(idTrain, vitesse, capacite,tmpsArret ,this.gare);
        gare.addTrain(train);

        // generate result

        resultObject.put("id", train.getidTrain());
        resultObject.put("vitesse", train.getVitesse());
        resultObject.put("capacité", train.getCapacite());
        resultObject.put("temps d'arret", train.getTmpsArret());
        resultObject.put("etat", train.etat);
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }


}
