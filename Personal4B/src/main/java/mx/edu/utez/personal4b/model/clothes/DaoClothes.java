package mx.edu.utez.personal4b.model.clothes;

import mx.edu.utez.personal4b.model.Repository;
import mx.edu.utez.personal4b.model.categories.BeanCategories;
import mx.edu.utez.personal4b.model.clothe_types.BeanClotheTypes;
import mx.edu.utez.personal4b.utils.MySQLConnection;
import mx.edu.utez.personal4b.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoClothes implements Repository <BeanClothes> {
    Connection connection;
    PreparedStatement pstm;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();
    @Override
        public List<BeanClothes> findAll() {
            List<BeanClothes> prendas = new ArrayList<>();
            BeanClothes clothes = null;
            BeanClotheTypes clotheTypes = null;
            BeanCategories categories = null;
            try{
                connection = client.getConnection();
                pstm = connection.prepareStatement("SELECT cl.*,ca.*,ct.* FROM clothes cl JOIN categories ca JOIN clothe_types ct ON ca.id = cl.category_id");
                rs = pstm.executeQuery();
                while (rs.next()){
                    clothes = new BeanClothes();
                    clotheTypes = new BeanClotheTypes();
                    categories = new BeanCategories();
                    clothes.setId(rs.getLong("id"));
                    clothes.setName(rs.getString("name"));
                    clothes.setPrice(rs.getDouble("price"));
                    clothes.setSize(rs.getString("size"));
                    clothes.setBrand(rs.getString("brand"));
                    clothes.setStock(rs.getInt("stock"));
                    clothes.setCreatedAt(rs.getString("created_at"));
                    clothes.setStatus(rs.getInt("status"));
                    categories.setId(rs.getLong("ca.id"));
                    categories.setName(rs.getString("name"));
                    clotheTypes.setId(rs.getLong("ct.id"));
                    clotheTypes.setName(rs.getString("name"));
                    clothes.setClotheTypes(clotheTypes);
                    clothes.setCategories(categories);

                    prendas.add(clothes);
                }
            }catch (SQLException e){
                Logger.getLogger(DaoClothes.class.getName()).log(Level.SEVERE,"Error -> findAll"+ e.getMessage());
            }finally {
                client.close(connection,pstm,rs);
            }
            return prendas;
        }

    @Override
    public BeanClothes findById(Long id) {
        BeanClothes clothes = new BeanClothes();
        try{
            connection = client.getConnection();
            pstm = connection.prepareStatement("SELECT cl.*,ca.*,ct.* FROM clothes cl JOIN categories ca JOIN clothe_types ct ON ca.id = cl.category_id WHERE cl.id = ?;");
            pstm.setLong(1,id);
            rs = pstm.executeQuery();
            while(rs.next()) {
                BeanCategories categories = new BeanCategories();
                BeanClotheTypes clotheTypes = new BeanClotheTypes();
                clothes = new BeanClothes();
                clotheTypes = new BeanClotheTypes();
                categories = new BeanCategories();
                clothes.setId(rs.getLong("id"));
                clothes.setName(rs.getString("name"));
                clothes.setPrice(rs.getDouble("price"));
                clothes.setSize(rs.getString("size"));
                clothes.setBrand(rs.getString("brand"));
                clothes.setStock(rs.getInt("stock"));
                clothes.setCreatedAt(rs.getString("created_at"));
                clothes.setStatus(rs.getInt("status"));
                clothes.setClothType(rs.getString("cloth_type"));
                categories.setId(rs.getLong("ca.id"));
                categories.setName(rs.getString("ca.name"));
                clotheTypes.setId(rs.getLong("ct.id"));
                clotheTypes.setName(rs.getString("ct.name"));
                clothes.setClotheTypes(clotheTypes);
                clothes.setCategories(categories);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoClothes.class.getName()).log(Level.SEVERE,"Error -> findAll"+ e.getMessage());
        }finally{
            client.close(connection,pstm,rs);
        }
        return clothes;
    }

    @Override
    public Response save(BeanClothes clothes) {
        try{
            connection = client.getConnection();
            pstm = connection.prepareStatement("INSERT INTO clothes (name,price,size,brand,stock,cloth_type,status,category_id,clothe_type_id ) " +
                    "VALUES (?,?,?,?,?,?,?,?,?);");
            pstm.setString(1,clothes.getName());
            pstm.setDouble(2,clothes.getPrice());
            pstm.setString(3,clothes.getSize());
            pstm.setString(4,clothes.getBrand());
            pstm.setInt(5,clothes.getStock());
            pstm.setString(6,clothes.getClothType());
            pstm.setInt(7,clothes.getStatus());
            pstm.setLong(8,clothes.getCategories().getId());
            pstm.setLong(9,clothes.getClotheTypes().getId());
            if(pstm.executeUpdate() == 1){
                return new Response<BeanClothes>(200,"Registro exitoso",clothes,false);
            }else{
                return new Response<BeanClothes>(200,"Error de registro. Intente nuevamente",clothes,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoClothes.class.getName()).log(Level.SEVERE,"Error -> findAll"+e.getMessage());
            return new Response<>(400,"Error con el servidor",null,true);

        }finally {
            client.close(connection,pstm,rs);
        }
    }

    @Override
    public Response update(BeanClothes clothes) {
        try {
            connection = client.getConnection();
            pstm = connection.prepareStatement("UPDATE clothes SET name = ?,price=?,size=?,brand=?,stock=?,cloth_type=?,status=?,category_id=?,clothe_type_id=? where id = ?;");
            pstm.setString(1,clothes.getName());
            pstm.setDouble(2,clothes.getPrice());
            pstm.setString(3,clothes.getSize());
            pstm.setString(4,clothes.getBrand());
            pstm.setInt(5,clothes.getStock());
            pstm.setString(6,clothes.getClothType());
            pstm.setInt(7,clothes.getStatus());
            pstm.setLong(8,clothes.getCategories().getId());
            pstm.setLong(9,clothes.getClotheTypes().getId());
            pstm.setLong(10,clothes.getId());
            if (pstm.executeUpdate() == 1) {
                return new Response<BeanClothes>(200, "Actualizado exitoso", clothes, false);
            } else {
                return new Response<BeanClothes>(200, "Error de actualizado. Intente nuevamente", clothes, true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoClothes.class.getName()).log(Level.SEVERE,"Error -> findAll"+ e.getMessage());
            return new Response<BeanClothes>(200,"Error con el servidor",clothes,true);
        }
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}