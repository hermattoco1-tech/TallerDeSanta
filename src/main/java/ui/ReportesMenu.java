/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import Storage.DataStore;
/**
 *
 * @author Matthew Hernandez
 */


import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class ReportesMenu {
    public static void mostrar(DataStore ds, Scanner sc) throws IOException {
        int opcion;
        do {
            System.out.println("\n=== Reportes ===");
            System.out.println("1. Inventario actual de regalos");
            System.out.println("2. Listado completo de niños");
            System.out.println("3. Detalle de asignaciones");
            System.out.println("4. Niños sin regalo");
            System.out.println("5. Regalos por marca (guardar en txt)");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> inventario(ds);
                case 2 -> listadoNinos(ds);
                case 3 -> detalleAsignaciones(ds);
                case 4 -> ninosSinRegalo(ds);
                case 5 -> regalosPorMarca(ds, sc);
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void inventario(DataStore ds) {
        System.out.println("\n🎁 Inventario:");
        ds.getRegalos().forEach(g -> System.out.println(g.getCode() + " - " + g.getName() + " (" + g.getQuantityAvailable() + ")"));
    }

    private static void listadoNinos(DataStore ds) {
        System.out.println("\n👦 Niños registrados:");
        ds.getNinos().forEach(n -> System.out.println(n.getId() + " - " + n.getFullName()));
    }

    private static void detalleAsignaciones(DataStore ds) {
        System.out.println("\n📌 Asignaciones:");
        ds.getAsignaciones().forEach(a -> System.out.println("Niño " + a.getChildId() + " → Regalo " + a.getGiftCode()));
    }

    private static void ninosSinRegalo(DataStore ds) {
        System.out.println("\n👦 Niños sin regalo:");
        ds.getNinos().stream()
            .filter(n -> ds.getAsignaciones().stream().noneMatch(a -> a.getChildId().equals(n.getId())))
            .forEach(n -> System.out.println(n.getId() + " - " + n.getFullName()));
    }

    private static void regalosPorMarca(DataStore ds, Scanner sc) throws IOException {
        System.out.print("Marca: "); String marca = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        ds.getRegalos().stream()
            .filter(g -> g.getBrand().equalsIgnoreCase(marca))
            .forEach(g -> sb.append(g.getCode()).append(" - ").append(g.getName()).append("\n"));

        try {
            Path path = Paths.get("data", "reporte_regalos_" + marca + ".txt");
            Files.writeString(path, sb.toString());
            System.out.println("Reporte generado en: " + path.toString());
    }    catch (IOException e) {
        System.out.println("Error al guardar reporte: " + e.getMessage());
    
     }  
  }
   } 
    
