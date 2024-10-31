/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.HashMap;

/**
 *
 * @author mark
 */
public interface IndexRepository {
    public boolean isValidTableValue (String table, String column, Object value);
    public Object getValuebyValue(String column1, String table, String column2, Object value);
    public boolean checkConflict(HashMap<String, Object>values);

}
