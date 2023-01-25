package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Repository.ElementRepository;
import com.ingsw.backend.Service.Interface.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mainElementService")
public class ElementService implements IElementService {

    @Autowired
    private ElementRepository elementRepository;

    public ElementService() {
    }

    @Override
    public void create(Element element){
        elementRepository.save(element);
    }

    @Override
    public void delete(Element element){
        elementRepository.delete(element);
    }

    @Override
    public List<Element> getByCategoryId(Integer id){
        return elementRepository.findByCategoryId(id);
    }
}
