package mx.edu.utez.personal4b.controller.personal;

import mx.edu.utez.personal4b.model.clothes.BeanClothes;
import mx.edu.utez.personal4b.model.clothes.DaoClothes;
import mx.edu.utez.personal4b.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/shop")
public class ShopServices {
    Map<String,Object> response = new HashMap<>();

    @GET @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanClothes> getAll(){
        return new DaoClothes().findAll();
    }

    @POST @Path("/")
    @Consumes(value = {"application/json"}) @Produces(value = {"application/json"})
    public Map<String, Object> save(BeanClothes clothes){
        Response<BeanClothes> result = new DaoClothes().save(clothes);
        response.put("result",result);
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanClothes getById(@PathParam("id") Long id){
        BeanClothes personal = new BeanClothes();
        System.out.println(id);
        return new DaoClothes().findById(id);
    }
    @PUT
    @Path("/")
    @Consumes(value = {"application/json"})
    @Produces(value = {"application/json"})
    public Map<String, Object> update(BeanClothes clothes){
        Response<BeanClothes> result = new DaoClothes().update(clothes);
        response.put("result",result);
        return response;
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        System.out.println(id);
        return new DaoClothes().delete(id);
    }
}