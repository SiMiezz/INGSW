package com.example.natour21.Entity.DAO.IRetrofitDAO;

import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Utils.Handler.Callback;

import java.util.List;

public interface IInterestingPointDAO {

    void listInterestingPoint(Callback callback);

    void getCompilationByID(long idInterestingPoint, Callback callback);

    void getInterestingPointByItinerario(long idItinerario, Callback callback);

    void getFotoItinerarioSingle(long idInterestingPoint, Callback callback);

    void getFotoItinerarioMultiple(long idItinerario,Callback callback);

    void createInterestingPoint(InterestingPoint interestingPoint, Callback callback);

    void createInterestingPoints(List<InterestingPoint> interestingPoints, Callback callback);

    void modifyInterestingPoint(InterestingPoint interestingPoint, Callback callback);

    void deleteInterestingPoint(long idInterestingPoint, Callback callback);

    void deleteFotoInterestingPoint(long idInterestingPoint, Callback callback);

}
