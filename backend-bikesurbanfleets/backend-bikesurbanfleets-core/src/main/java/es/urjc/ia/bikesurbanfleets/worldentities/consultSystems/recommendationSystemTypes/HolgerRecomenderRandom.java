package es.urjc.ia.bikesurbanfleets.worldentities.consultSystems.recommendationSystemTypes;

import com.google.gson.JsonObject;
import java.util.List;

import es.urjc.ia.bikesurbanfleets.common.util.SimpleRandom;
import es.urjc.ia.bikesurbanfleets.core.services.SimulationServices;
import es.urjc.ia.bikesurbanfleets.worldentities.consultSystems.RecommendationSystemType;
import es.urjc.ia.bikesurbanfleets.worldentities.infraestructure.InfraestructureManager;
 

@RecommendationSystemType("HOLGERRECOMENDER_RANDOM")
public class HolgerRecomenderRandom extends HolgerRecomender {

    private SimpleRandom rand;

    public HolgerRecomenderRandom(JsonObject recomenderdef, SimulationServices ss) throws Exception {
        super(recomenderdef, ss);
        this.rand = new SimpleRandom(1);
    }

    protected void calculateFinalUtilities(List<StationData> stations, double stationutilityequilibrium, double closestsdistance) {

        super.calculateFinalUtilities(stations, stationutilityequilibrium, closestsdistance);
        
        double utilitysum=0.0D;
        for (StationData sd : stations) {
             utilitysum+=sd.utility;
        }
        selectRandom(stations,utilitysum);
    }

    private void selectRandom(List<StationData> aux, double utilitysum) {
        //set the utility of one station randomly to 1.1 (highest)
        double aux2 = rand.nextDouble(0.0, utilitysum);
        double aux3 = 0.0D;
        for (StationData sd : aux) {
            aux3 += sd.utility;
            if (aux3 >= aux2) {
                sd.utility = 1.1D;
                break;
            }
        }
    }

}
