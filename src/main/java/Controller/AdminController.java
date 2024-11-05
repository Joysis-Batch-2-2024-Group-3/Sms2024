/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Db.Db;
import Model.AdminModel;
import Repository.AdminRepository;
import static Repository.QueryConstant.ADMIN_LOGIN;


/**
 *
 * @author mark
 */
public class AdminController extends Db implements AdminRepository {
        @Override
    public boolean authenticateAdmin(AdminModel admin){
       
        try{
            connect();
            prep = con.prepareStatement(ADMIN_LOGIN);
            prep.setString(1, admin.getAdminUsername());
            prep.setString(2, admin.getAdminPassword());
            result = prep.executeQuery();
            if(result.next()){
   
//                return true;
     return result.getInt(1)>0;
            }
        }catch(Exception e){
            System.out.println("Error authenticate admin: " + e.getMessage());
        }
            return false;
        }
}
