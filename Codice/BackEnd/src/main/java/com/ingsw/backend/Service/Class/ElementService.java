package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Repository.ElementRepository;
import com.ingsw.backend.Service.Interface.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainElementService")
public class ElementService implements IElementService {

    @Autowired
    private ElementRepository elementRepository;

    public ElementService() {
    }

    @Override
    public Element create(Element element){
        return elementRepository.save(element);
    }

    @Override
    public Boolean deleteById(Integer id){
        Optional<Element> optionalElement = elementRepository.findById(id);

        if(optionalElement.isEmpty()){
            return false;
        }

        elementRepository.delete(optionalElement.get());
        return true;
    }
}
