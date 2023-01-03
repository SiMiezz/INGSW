package com.example.natour21.Entity.DAO;

import static com.example.natour21.Entity.DAO.DAOTypes.RETROFIT;

import com.example.natour21.Entity.DAO.CRetrofitDAO.CompilationDAO;
import com.example.natour21.Entity.DAO.CRetrofitDAO.FotoItinerarioDAO;
import com.example.natour21.Entity.DAO.CRetrofitDAO.InterestingPointDAO;
import com.example.natour21.Entity.DAO.CRetrofitDAO.ItinerarioDAO;
import com.example.natour21.Entity.DAO.CRetrofitDAO.TappaDAO;
import com.example.natour21.Entity.DAO.CRetrofitDAO.UtenteDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ICompilationDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IFotoItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IInterestingPointDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ITappaDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IUtenteDAO;
import com.example.natour21.Utils.Throwable.UnsupportedDAOException;

public abstract class FactoryDAO {

    private static DAOTypes daoTypes = RETROFIT;

    public static ICompilationDAO getCompilationDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new CompilationDAO();
            default:
                throw new UnsupportedDAOException( daoTypes + " non supportato.");
        }
    }

    public static IFotoItinerarioDAO getFotoItinerarioDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new FotoItinerarioDAO();
            default:
                throw new UnsupportedDAOException( daoTypes + " non supportato.");
        }
    }

    public static IInterestingPointDAO getInterestingPointDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new InterestingPointDAO();
            default:
                throw new UnsupportedDAOException( daoTypes + " non supportato.");
        }
    }

    public static IItinerarioDAO getItinerarioDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new ItinerarioDAO();
            default:
                throw new UnsupportedDAOException( daoTypes + " non supportato.");
        }
    }

    public static ITappaDAO getTappaDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new TappaDAO();
            default:
                throw new UnsupportedDAOException(daoTypes + " non supportato.");
        }
    }

    public static IUtenteDAO getUserDAO() {
        switch (daoTypes) {
            case RETROFIT:
                return new UtenteDAO();
            default:
                throw new UnsupportedDAOException( daoTypes + " non supportato.");
        }
    }

    //Getter e Setter
    public static DAOTypes getDaoTypes() {
        return daoTypes;
    }

    public static void setDaoTypes(DAOTypes daoTypes) {
        FactoryDAO.daoTypes = daoTypes;
    }
}
