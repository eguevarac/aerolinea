package aerolinea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Airplane implements Comparable {

    private String name;
    private int fuelCapacity;
    private int seats;

    private LocalDate dateCreation;

    public Airplane(String name, int fuelCapacity, int seats, LocalDate dateCreation) {
        this.name = name;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
        this.dateCreation = dateCreation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFuelCapacity() {
        return this.fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getSeats() {
        return this.seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {

        return "Avión:" +
                "\nNombre: " + this.name +
                "\nFecha de creación: " + this.dateCreation +
                "\nPlazas: " + this.seats +
                "\nCombustible: " + this.fuelCapacity+ "\n";
    }
    /**
     * muestra todos los aviones
     *
     * @param airplanes el ArrayList de aviones
     */
    public static void showAirplanes(ArrayList<Airplane> airplanes) {

        for (Airplane a : airplanes
        ) {
            System.out.println(a);
        }

    }

    /**
     * Muestra los aviones disponibles y solicita al usuario que seleccione uno escribiendo su nombre
     * @param airplanes El ArrayList con todos los aviones en su interior
     * @return El avión seleccionado
     */
    public static Airplane lookingForAPlane (ArrayList<Airplane> airplanes) {
        String name;
        Airplane asignedAirplane = null;


        do {
            Airplane.showAirplanes(airplanes);
            name = SpellBook.insertString("Introduzca el nombre del avión que desea asignar\nde entre los que se han mostrado:");

            for (Airplane a : airplanes
            ) {
                if (name.equalsIgnoreCase(a.getName())) {
                    asignedAirplane = a;
                }
            }
            if (asignedAirplane == null) {
                Text.badPlaneLookingFor();
            }
        } while (asignedAirplane == null);

        return asignedAirplane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return fuelCapacity == airplane.fuelCapacity && seats == airplane.seats && name.equals(airplane.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fuelCapacity, seats);
    }ñkjlkj


    @Override
    public int compareTo(Object o) {
        if ( o instanceof Airplane){
            int value = this.seats - ((Airplane)o).seats;

            if (value == 0){
                value = this.fuelCapacity - ((Airplane)o).fuelCapacity;

                if (value == 0){
                    return this.name.compareTo(((Airplane)o).name);
                }
            }
        }else{

            throw new ClassCastException();
        }


    }
}
