/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterface.IDAOMahasiswa;
import Helper.KoneksiDB;
import Model.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMahasiswa implements IDAOMahasiswa{

    Connection con;
    
    String strRead = "SELECT * FROM mahasiswa;";
    String strInsert = "INSERT INTO mahasiswa (nim,nama,kelas,prodi) VALUE (?,?,?,?);";
    String strUpdate = "UPDATE mahasiswa SET nama=?,kelas=?,prodi=? where nim=?;";
    String strDelete = "DELETE FROM mahasiswa WHERE nim=?;";
    
    public DAOMahasiswa(){
        con = KoneksiDB.getConnection();
    }
            
    @Override
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> lstMhs = null;
        try{
            lstMhs = new ArrayList<Mahasiswa>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(strRead);
            while(rs.next()){
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setKelas(rs.getString("kelas"));
                mhs.setProdi(rs.getString("prodi"));
                lstMhs.add(mhs);
            }
        } catch(SQLException e){
            System.out.println("Masalah : "+e);
        }
        return lstMhs;
    }

    @Override
    public void insert(Mahasiswa b) {
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(strInsert);
            statement.setString(1, b.getNim());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getKelas());
            statement.setString(4, b.getProdi());
            statement.execute();
        } catch(Exception e){
            System.out.println("Gagal Inset!");
        }
        finally{
            try{
                statement.close();
            }catch(SQLException ex){
                System.out.println("Gagal Insert!");
            }
        }
    }

    @Override
    public void update(Mahasiswa b) {
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(strUpdate);
            statement.setString(1, b.getNama());
            statement.setString(2, b.getKelas());
            statement.setString(3, b.getProdi());
            statement.setString(4, b.getNim());
            statement.execute();
        } catch(Exception e){
            System.out.println("Gagal Update!");
        }
        finally{
            try{
                statement.close();
            }catch(SQLException ex){
                System.out.println("Gagal Update!");
            }
        }
    }

    @Override
    public void delete(String nim) {
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(strDelete);
            statement.setString(1, nim);
            statement.execute();
        } catch(Exception e){
            System.out.println("Gagal Delete!");
        }
        finally{
            try{
                statement.close();
            }catch(SQLException ex){
                System.out.println("Gagal Delete!");
            }
        }
    }
    
}
