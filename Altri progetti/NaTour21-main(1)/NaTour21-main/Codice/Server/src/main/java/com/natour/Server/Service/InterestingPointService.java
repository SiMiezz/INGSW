package com.natour.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natour.Server.Model.InterestingPoint;
import com.natour.Server.Repository.InterestingPointRepository;
import com.natour.Server.Utils.IInterestingPointService;

@Service("mainInterestingPointService")
public class InterestingPointService implements IInterestingPointService {

	@Autowired
	private InterestingPointRepository interestingPointRep;

	/*********************************************************************************************/

	//Constructor
	@Autowired
	public InterestingPointService() {}

	/*********************************************************************************************/

	//Methods
	@Override
	public List<InterestingPoint> getAllInterestinPoint() {
		return this.interestingPointRep.findAll();
	}

	@Override
	public Optional<InterestingPoint> getInterestingPointByID(Long idinterestingpoint) {
		return this.interestingPointRep.findById(idinterestingpoint);
	}

	@Override
	public List<InterestingPoint> getInterestingPointByItinerario(Long iditinerario) {
		return this.interestingPointRep.findByItinerario(iditinerario);
	}	
	
	@Override
	public boolean creaInterestingPoint(InterestingPoint interestingPoint) {
		try {
			if(!this.interestingPointRep.existsById(interestingPoint.getId_interestingpoint()))
				this.interestingPointRep.save(interestingPoint);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean creaInterestingPoints(List<InterestingPoint> interestingPoint) {
		try {
			if(!interestingPoint.isEmpty())
				this.interestingPointRep.saveAll(interestingPoint);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean modificaInterestingPoint(InterestingPoint interestingPoint) {
		try {
			if(this.interestingPointRep.existsById(interestingPoint.getId_interestingpoint()))
				this.interestingPointRep.save(interestingPoint);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean cancellaInterestingPoint(Long idInterestingPoint) {
		try {
			if(this.interestingPointRep.existsById(idInterestingPoint))
				this.interestingPointRep.deleteById(idInterestingPoint);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean cancellaFotoInterestingPoint(Long idInterestingPoint) {
		try {
			if(this.interestingPointRep.existsById(idInterestingPoint)) {
				Optional<InterestingPoint> optionalInterestingPoint = this.getInterestingPointByID(idInterestingPoint);
				InterestingPoint interestingPoint = optionalInterestingPoint.get();
				interestingPoint.setUrlfoto(null);
				this.interestingPointRep.save(interestingPoint);
			}else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	/*********************************************************************************************/

	//Getter e Setter
	public InterestingPointRepository getInterestingPointRep() {
		return interestingPointRep;
	}

	public void setInterestingPointRep(InterestingPointRepository interestingPointRep) {
		this.interestingPointRep = interestingPointRep;
	}

	/*********************************************************************************************/
}
