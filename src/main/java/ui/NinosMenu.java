/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Matthew Hernandez
 */
import Model.child;
import Storage.DataStore;
import java.util.Scanner;



class NinosMenu {
    public static void mostrar(DataStore ds, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== Gestión de Niños ===");
            System.out.println("1. Registrar niño");
            System.out.println("2. Modificar niño");
            System.out.println("3. Eliminar niño");
            System.out.println("4. Consultar por identificación");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> registrar(ds, sc);
                case 2 -> modificar(ds, sc);
                case 3 -> eliminar(ds, sc);
                case 4 -> consultar(ds, sc);
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void registrar(DataStore ds, Scanner sc) {
        System.out.print("Identificación: "); String id = sc.nextLine();
        if (ds.getNinos().stream().anyMatch(n -> n.getId().equals(id))) {
            System.out.println("Ya existe ese niño.");
            return;
        }
        System.out.print("Nombre completo: "); String name = sc.nextLine();
        System.out.print("Edad: "); int age = sc.nextInt(); sc.nextLine();
        System.out.print("Ciudad: "); String city = sc.nextLine();
        System.out.print("Dirección: "); String address = sc.nextLine();

        ds.getNinos().add(new child(id, name, age, city, address));
        System.out.println("Niño registrado.");
    }

    private static void modificar(DataStore ds, Scanner sc) {
        System.out.print("Identificación: "); String id = sc.nextLine();
        child c = ds.getNinos().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
        if (c == null) { System.out.println("No existe."); return; }
        System.out.print("Nuevo nombre: "); c.setFullName(sc.nextLine());
        System.out.print("Nueva edad: "); c.setAge(sc.nextInt()); sc.nextLine();
        System.out.print("Nueva ciudad: "); c.setCity(sc.nextLine());
        System.out.print("Nueva dirección: "); c.setAddress(sc.nextLine());
        System.out.println("Niño modificado.");
    }

    private static void eliminar(DataStore ds, Scanner sc) {
        System.out.print("Identificación: "); String id = sc.nextLine();
        boolean asignado = ds.getAsignaciones().stream().anyMatch(a -> a.getchildId().equals(id));
        if (asignado) { System.out.println("No se puede eliminar, tiene asignación."); return; }
        ds.getNinos().removeIf(n -> n.getId().equals(id));
        System.out.println("Niño eliminado.");
    }

    private static void consultar(DataStore ds, Scanner sc) {
        System.out.print("Identificación: "); String id = sc.nextLine();
        child c = ds.getNinos().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
        if (c == null) System.out.println("No existe.");
        else System.out.println(c.getId() + " - " + c.getFullName() + " (" + c.getAge() + " años)");
    }


}
