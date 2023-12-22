/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOMahasiswa;
import DAOInterface.IDAOMahasiswa;
import Model.Mahasiswa;
import Model.TabelModelMahasiswa;
import View.FormMhs;
import java.util.List;

public class ControllerMahasiswa {
    FormMhs frmMahasiswa;
    IDAOMahasiswa iMahasiswa;
    List<Mahasiswa> lstMahasiswa;
    
    public ControllerMahasiswa(FormMhs frmMahasiswa){
        this.frmMahasiswa = frmMahasiswa;
        iMahasiswa = new DAOMahasiswa();
    }
    
    public void isiTabel(){
        lstMahasiswa = iMahasiswa.getAll();
        TabelModelMahasiswa tabelMhs = new TabelModelMahasiswa(lstMahasiswa);
        frmMahasiswa.getTabelData().setModel(tabelMhs);
    }
    
    public void insert(){
        Mahasiswa b = new Mahasiswa();
        b.setNim(frmMahasiswa.gettextNim().getText());
        b.setNama(frmMahasiswa.gettextNama().getText());
        b.setKelas(frmMahasiswa.gettextKelas().getText());
        b.setProdi(frmMahasiswa.gettextProdi().getText());
        iMahasiswa.insert(b);
    }
    
    public void reset(){
        frmMahasiswa.gettextNim().setText("");
        frmMahasiswa.gettextNama().setText("");
        frmMahasiswa.gettextKelas().setText("");
        frmMahasiswa.gettextProdi().setText("");
    }
    
    public void isiField(int row){
        frmMahasiswa.gettextNim().setText(lstMahasiswa.get(row).getNim().toString());
        frmMahasiswa.gettextNama().setText(lstMahasiswa.get(row).getNama().toString());
        frmMahasiswa.gettextKelas().setText(lstMahasiswa.get(row).getKelas().toString());
        frmMahasiswa.gettextProdi().setText(lstMahasiswa.get(row).getProdi().toString());
    }
    
    public void update(){
        Mahasiswa b = new Mahasiswa();
        b.setNama(frmMahasiswa.gettextNama().getText());
        b.setKelas(frmMahasiswa.gettextKelas().getText());
        b.setProdi(frmMahasiswa.gettextProdi().getText());
        b.setNim(frmMahasiswa.gettextNim().getText());
        iMahasiswa.update(b);
    }
    
    public void delete(){
        Mahasiswa b = new Mahasiswa();
        b.setNim(frmMahasiswa.gettextNim().getText());
        iMahasiswa.delete(frmMahasiswa.gettextNim().getText());
    }
}
