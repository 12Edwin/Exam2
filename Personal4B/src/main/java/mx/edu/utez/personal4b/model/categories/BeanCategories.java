package mx.edu.utez.personal4b.model.categories;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanCategories {
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}