package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.ElementRepository;
import com.ingsw.backend.Service.Interface.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainElementService")
public class ElementService implements IElementService {

    @Autowired
    private ElementRepository elementRepository;

    public ElementService() {
    }
}
