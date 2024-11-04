/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Db.Db;
import Repository.IndexRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author mark
 */
public class IndexController extends Db implements IndexRepository {
    public boolean isValidTableValue(String table, String column, Object value) {
        connect();
        try {
            String validQuery = String.format(VALIDATION_QUERY, table, column);
            prep = con.prepareStatement(validQuery);
            if (value instanceof String) {
                prep.setString(1,  value.toString());
            } else if (value instanceof Integer) {
                prep.setInt(1, (Integer) value);
            }
            result = prep.executeQuery();
            if (result.next()) {
                return result.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;

    }

    @Override
    public Object getValuebyValue(String column1, String table, String column2, Object value) {
        connect();
        try {
            String finderQuery = String.format(FINDER_QUERY, column1, table, column2);
            prep = con.prepareStatement(finderQuery);
            if (value instanceof String) {
                prep.setString(1, "%" + value + "%");
            } else if (value instanceof Integer) {
                prep.setInt(1, (Integer) value);
            }
            result = prep.executeQuery();
            if (result.next()) {
                return result.getObject(column1);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkConflict(String Table, LinkedHashMap<String, Object> values) {
        connect();
        try{
            String stringQuery = String.format(CONFLICT_CHECKER_QUERY_SMALL,
                    Table,
                    values.keySet().toArray()[0],
                    values.keySet().toArray()[1]
                    );
            prep = con.prepareStatement(stringQuery);
            int index = 1;
            for (Object value : values.values()) {
                if (value instanceof String) {
                    prep.setString(index++, (String) value);
                }
                else if (value instanceof Integer) {
                    prep.setInt(index++, (Integer) value);
                }
                else if (value instanceof java.sql.Date) {
                    prep.setDate(index++, (java.sql.Date) value);
                }
                else if (value instanceof java.sql.Time) {
                    prep.setTime(index++, (java.sql.Time) value);
                }
            }
            result = prep.executeQuery();
            if (result.next()) {
                return result.getInt(1) > 0;
            }


        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

