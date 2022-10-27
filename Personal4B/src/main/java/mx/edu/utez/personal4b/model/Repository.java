package mx.edu.utez.personal4b.model;

import mx.edu.utez.personal4b.model.clothes.BeanClothes;
import mx.edu.utez.personal4b.utils.Response;

import java.util.List;

public interface Repository <T>{
    List<T> findAll();
    BeanClothes findById(Long id);
    Response save(T object);
    Response update(T object);

    Response delete(Long id);

}
