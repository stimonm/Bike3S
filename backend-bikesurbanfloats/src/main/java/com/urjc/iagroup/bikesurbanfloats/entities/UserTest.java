package com.urjc.iagroup.bikesurbanfloats.entities;

import com.urjc.iagroup.bikesurbanfloats.graphs.GeoPoint;
import java.util.List;

public class UserTest extends User {
	
	public UserTest() {
		super();
	}

	@Override
	public boolean decidesToLeaveSystemAfterTimeout(int instant) {
		return systemManager.consultStationsWithBikeReservationAttempt(this, instant).size() == systemManager.consultStations().size();
}
	

   @Override
    public boolean decidesToLeaveSystemAffterFailedReservation(int instant) {
        return systemManager.consultStationsWithBikeReservationAttempt(this, instant).size() == systemManager.consultStations().size();
    }


    @Override
    public boolean decidesToLeaveSystemWhenBikesUnavailable(int instant) {
        return systemManager.consultStationsWithBikeReservationAttempt(this, instant).size() == systemManager.consultStations().size();
    }
	
	@Override
	public Station determineStationToRentBike(int instant) {
		
		List<Station> stations = systemManager.consultStationsWithoutBikeReservationAttempt(this, instant);
		double minDistance = Double.MAX_VALUE;
		Station destination = null;
		for(Station currentStation: stations) {
			GeoPoint stationGeoPoint = currentStation.getPosition();
			GeoPoint userGeoPoint =	getPosition();
			double distance = stationGeoPoint.distanceTo(userGeoPoint);
			if(!userGeoPoint.equals(stationGeoPoint) && distance < minDistance) {
				minDistance = distance;
				destination = currentStation;
			}
		}
		if(destination == null) {
			int numberStations = systemManager.consultStations().size();
			int indexStation = systemManager.getRandom().nextInt(0,  numberStations - 1);
			destination = systemManager.consultStations().get(indexStation);
		}
		return destination;
	}
	
	@Override
	public Station determineStationToReturnBike(int instant) {
		List<Station> stations = systemManager.consultStationsWithoutSlotReservationAttempt(this, instant);
		double minDistance = Double.MAX_VALUE;
		Station destination = null;
		for(Station currentStation: stations) {
			GeoPoint stationGeoPoint = currentStation.getPosition();
			GeoPoint userGeoPoint =	getPosition();
			double distance = stationGeoPoint.distanceTo(userGeoPoint);
			if(!userGeoPoint.equals(stationGeoPoint) && distance < minDistance) {
				minDistance = distance;
				destination = currentStation;
			}
		}
		if(destination == null) {
			int numberStations = systemManager.consultStations().size();
			int indexStation = systemManager.getRandom().nextInt(0,  numberStations - 1);
			destination = systemManager.consultStations().get(indexStation);
		}
				return destination;
	}
	
	@Override
	public boolean decidesToReserveBikeAtSameStationAfterTimeout() {
		return systemManager.getRandom().nextBoolean();
	}
	
   public boolean decidesToReserveBikeAtNewDecidedStation() {
        return systemManager.getRandom().nextBoolean();
    }

	@Override
	public boolean decidesToReserveSlotAtSameStationAfterTimeout() {
		return systemManager.getRandom().nextBoolean();
	}
	
   @Override
    public boolean decidesToReserveSlotAtNewDecidedStation() {
        return systemManager.getRandom().nextBoolean();
    }
	
	@Override
	public GeoPoint decidesNextPoint() {
		return systemManager.generateBoundingBoxRandomPoint();
	}
	
	@Override
		public boolean decidesToReturnBike() {
		return systemManager.getRandom().nextBoolean();
	}

	// TODO: moving it to user class?
	@Override
	public void updatePosition(int time) {
		double distance = time * getPosition().distanceTo(getDestinationStation().getPosition()) / timeToReach(getDestinationStation().getPosition());
 		GeoPoint newPoint = getPosition().reachedPoint(distance, getDestinationStation().getPosition());
		setPosition(newPoint);
	}

	@Override
	public boolean decidesToDetermineOtherStationAfterTimeout() {
		return systemManager.getRandom().nextBoolean();
	}

	@Override
	public boolean decidesToDetermineOtherStationAfterFailedReservation() {
		return systemManager.getRandom().nextBoolean();
	}

}