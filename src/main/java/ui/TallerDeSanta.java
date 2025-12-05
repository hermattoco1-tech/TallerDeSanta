/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Matthew Hernandez
 */
import Model.Gift;
import Storage.DataStore;
import java.io.IOException;
import java.util.Scanner;

public class TallerDeSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataStore ds = new DataStore();

        try {
            ds.loadAll(); // Cargar datos existentes
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }

        int opcion;

        do {
            System.out.println("\n=== El Taller de Santa ===");
            System.out.println("1. Registrar regalo");
            System.out.println("2. Ver regalos");
             System.out.println("3. Gestión de Niños");
            System.out.println("4. Asignación de Regalos");
            System.out.println("5. Reportes");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> registrarRegalo(ds, scanner);
                case 2 -> mostrarRegalos(ds);
                case 3 -> NinosMenu.mostrar(ds, scanner);
                case 4 -> AsignacionesMenu.mostrar(ds, scanner);
                case 5 -> {
                    try {
                        ReportesMenu.mostrar(ds, scanner);
                    } catch (IOException ex) {
                        System.getLogger(TallerDeSanta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }



                case 0 -> {
                    System.out.println("Adiós.");
                    try {
                        ds.saveAll(); // Guardar datos en JSON
                        System.out.println("Datos guardados correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar datos: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void registrarRegalo(DataStore ds, Scanner scanner) {
        System.out.print("Código: ");
        String code = scanner.nextLine();
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Marca: ");
        String brand = scanner.nextLine();
        System.out.print("Cantidad: ");
        int qty = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        ds.getRegalos().add(new Gift(code, name, desc, brand, qty));
        System.out.println("Regalo registrado.");
    }

    private static void mostrarRegalos(DataStore ds) {
        System.out.println("\n🎁 Regalos registrados:");
        for (Gift g : ds.getRegalos()) {
            System.out.println(g.getCode() + " - " + g.getName() + " (" + g.getQuantityAvailable() + ")");
        }
    }
}

