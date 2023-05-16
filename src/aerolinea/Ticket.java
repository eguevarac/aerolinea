package aerolinea;

import java.util.ArrayList;

public class Ticket {

    private int ID;
    static int quantity;
    private int seats;
    private float price;
    private String flightCode;

    public Ticket(int seats, String flightCode, float price) {
        this.ID = quantity;
        this.seats = seats;
        this.flightCode = flightCode;
        this.price = priceCalculator(price, seats);
        quantity++;

    }


    public int getSeats() {
        return this.seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFlightCode() {
        return this.flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Ticket.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ticket con ID "+ ID +":"+
                "\nPlazas compradas: " + seats +
                "\nPrecio total: " + price +
                "\nCódigo de vuelo: " + flightCode+"\n";
    }


    /**
     * Muestra todos los billetes almacenados en el ArrayList
     * @param tickets ArrayList con los billetes almacenados en su interior
     */
    public static void allTicketShowing(ArrayList<Ticket> tickets){

        for (Ticket t:tickets
             ) {
            System.out.println(t);
        }
    }

    /**
     * Busca un billete con la ID solicitada
     * @param tickets ArrayList con todos los billetes en su interior
     * @param ID la ID que queremos encontrar
     * @return objeto de tipo Ticket con la ID solicitada (o null en caso de no encontrarlo)
     */
    public static Ticket lookingForATicket(ArrayList<Ticket> tickets, int ID) {
        Ticket ticket = null;
        for (Ticket t : tickets
        ) {
            if (ID == t.getID()) {
                ticket = t;
            }
        }
        return ticket;
    }

    /**
     * Función que calcula el precio del billete que se desea comprar
     * @param price float con el valor individual de cada plaza
     * @param seats int con la cantidad de plazas que se desea comprar
     * @return float con la cantidad final del billete
     */
    public float priceCalculator (float price, int seats){
        float totalPrice;
        totalPrice = price * seats;
        return totalPrice;

    }

    /**
     * calcula cuánto dinero se recuperará con una devolución dependiendo del porcentaje
     * @param flights ArrayList con todos los vuelos existentes
     * @param numSeatsToReturn int con la cantidad de plazas que se quieren devolver
     * @return float con la cantidad final que será devuelta
     */
    public float moneyToReturnCalculating (ArrayList<Flight> flights, int numSeatsToReturn){
        float devolution;
        Flight flight;
       flight= Flight.lookingForAFlight(flights, this.flightCode);

              devolution= flight.getPrice()*numSeatsToReturn*flight.getDevolution()/100;

       return devolution;
    }

}

