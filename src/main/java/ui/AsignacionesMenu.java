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
import Model.assignment;
import Model.child;
import Storage.DataStore;
import java.util.Scanner;



class AsignacionesMenu {
     public static void mostrar(DataStore ds, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== Asignación de Regalos ===");
            System.out.println("1. Asignar regalo a niño");
            System.out.println("2. Consultar asignación por niño");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> asignar(ds, sc);
                case 2 -> consultar(ds, sc);
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void asignar(DataStore ds, Scanner sc) {
        System.out.print("ID del niño: "); String id = sc.nextLine();
        child c = ds.getNinos().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
        if (c == null) { System.out.println("Niño no existe."); return; }

        boolean yaAsignado = ds.getAsignaciones().stream().anyMatch(a -> a.getchildId().equals(id));
        if (yaAsignado) { System.out.println("Ese niño ya tiene un regalo."); return; }

        System.out.print("Código del regalo: "); String code = sc.nextLine();
        Gift g = ds.getRegalos().stream().filter(r -> r.getCode().equals(code)).findFirst().orElse(null);
        if (g == null) { System.out.println("Regalo no existe."); return; }
        if (g.getQuantityAvailable() <= 0) { System.out.println("No hay stock disponible."); return; }

        ds.getAsignaciones().add(new assignment(id, code));
        g.setQuantityAvailable(g.getQuantityAvailable() - 1);
        System.out.println("Regalo asignado.");
    }

    private static void consultar(DataStore ds, Scanner sc) {
        System.out.print("ID del niño: "); String id = sc.nextLine();
        assignment a = ds.getAsignaciones().stream().filter(as -> as.getchildId().equals(id)).findFirst().orElse(null);
        if (a == null) System.out.println("El niño no posee asignaciones.");
        else System.out.println("Niño " + a.getchildId() + " tiene regalo " + a.getGiftCode());
    }


}
