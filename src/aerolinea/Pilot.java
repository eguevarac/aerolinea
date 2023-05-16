package aerolinea;


import java.time.LocalDate;
import java.util.ArrayList;


public class Pilot {
    private String name;
    private String lastname;
    private String IDCard;
    private LocalDate bornDate;
    private int phone;
    private String code;

    public Pilot(String name, String lastname, String IDCard, LocalDate bornDate, int phone, String code) {
        this.name = name;
        this.lastname = lastname;
        this.IDCard = IDCard;
        this.bornDate = bornDate;
        this.phone = phone;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIDCard() {
        return this.IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public LocalDate getBornDate() {
        return this.bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Piloto:" +
                "\nCódigo: " + this.code +
                "\nNombre: " + this.name +
                "\nApellidos: " + this.lastname +
                "\nDNI: " + this.IDCard +
                "\nFecha de nacimiento: " + this.bornDate +
                "\nTeléfono: " + this.phone+ "\n";
    }

    /**
     * muestra todos los pilotos
     *
     * @param pilots el ArrayList de pilotos
     */
    public static void showPilots(ArrayList<Pilot> pilots) {
        for (Pilot p : pilots
        ) {
            System.out.println(p);
        }

    }

    /**
     * Muestra todos los pilotos disponibles y solicita al usuario que escoja uno
     * @param pilots el ArrayList de pilotos
     * @return el piloto seleccionado en caso de encontrarlo (en caso contrario devuelve null)
     */
    public static Pilot lookingForAPilot (ArrayList<Pilot> pilots) {
        String code;
        Pilot asignedPilot = null;

        do {
            Pilot.showPilots(pilots);
            code = SpellBook.insertString("Introduzca el código del piloto que desea\nasignar de entre los que se han mostrado:");
            for (Pilot p : pilots
            ) {
                if (code.equalsIgnoreCase(p.getCode())) {
                    asignedPilot = p;
                }
            }
            if (asignedPilot == null) {

                Text.badPilotLookingFor();
            }
        } while (asignedPilot == null);

        return asignedPilot;
    }

}

