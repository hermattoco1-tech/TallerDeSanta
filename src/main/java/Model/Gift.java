/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Matthew Hernandez
 */
public class Gift {
    private String code;
    private String name;
    private String description;
    private String brand;
    private int quantityAvailable;

    public Gift() {}

    public Gift(String code, String name, String description, String brand, int quantityAvailable) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.quantityAvailable = quantityAvailable;
    }

    // Getters y setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }
}



