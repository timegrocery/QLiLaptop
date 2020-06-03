/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author WindZ
 */
public class ChitietLaptop {
    String maChitiet, cpu, ram, gpu, manhinh, ocung;
    
    public ChitietLaptop() {
        
    }
    
    public ChitietLaptop(String maChitiet, String cpu, String ram, String gpu, String manhinh, String ocung) {
        this.maChitiet = maChitiet;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.manhinh = manhinh;
        this.ocung = ocung;
    }
    
    public String getMaChitiet(){
        return this.maChitiet;
    }
    public String getCpu() {
        return this.cpu;
    }
    public String getRam() {
        return this.ram;
    }
    public String getGpu() {
        return this.gpu;
    }
    public String getManhinh() {
        return this.manhinh;
    }
    public String getOcung() {
        return this.ocung;
    }
    
    public void setMaChitiet(String maChitiet) {
        this.maChitiet = maChitiet;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }
    public void setGpu(String gpu) {
        this.gpu = gpu;
    }
    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }
    public void setOcung(String ocung) {
        this.ocung = ocung;
    }
    
}
