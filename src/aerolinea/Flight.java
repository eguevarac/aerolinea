package aerolinea;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight {

    private String code;
    private String provenance;
    private String destination;
    private LocalTime flightDuration;
    private LocalDateTime departure;
    private LocalDateTime arriveDate;
    private Pilot pilot;
    private Airplane airplane;
    private float devolution;
    private float price;

    private int seats;



    public Flight(String code, String provenance, String destination, LocalDateTime departure, LocalTime flightDuration, Pilot pilot, Airplane airplane, float devolution, float price) {
        this.code = code;
        this.provenance = provenance;
        this.destination = destination;
        this.flightDuration = flightDuration;
        this.departure = departure;
        this.pilot = pilot;
        this.airplane = airplane;
        this.devolution = devolution;
        this.price = price;
        this.arriveDate = arriveDating(departure,flightDuration.getHour(), flightDuration.getMinute());
        this.seats = airplane.getSeats();

    }

    public Flight(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvenance() {
        return this.provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public Pilot getPilot() {
        return this.pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Airplane getAirplane() {
        return this.airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public float getDevolution() {
        return this.devolution;
    }

    public void setDevolution(float devolution) {
        this.devolution = devolution;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getFlightDuration() {
        return this.flightDuration;
    }

    public void setFlightDuration(LocalTime flightDuration) {
        this.flightDuration = flightDuration;
        this.arriveDate = arriveDating(this.departure,flightDuration.getHour(),flightDuration.getMinute());
    }

    public LocalDateTime getDeparture() {
        return this.departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
        this.arriveDate = arriveDating(departure,this.flightDuration.getHour(),this.flightDuration.getMinute());
    }

    public LocalDateTime getArriveDate() {
        return this.arriveDate;
    }

    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {

        return "Vuelo con código " +this.code +":"+
                "\nOrigen: " + this.provenance +
                "\nDestino: " + this.destination +
                "\nDía y hora de salida: " + this.departure +
                "\nDuración de vuelo: " + this.flightDuration +
                "\nHora de llegada: " + this.arriveDate +
                "\nPrecio por billete: "+ this.price+
                "\nPorcentaje de devolución: "+this.devolution+"\n"+
                "\n"+this.pilot+
                "\n"+this.airplane;
    }
    /**
     * Establece la hora y día de llegada en base a la fecha de partida y la duración del vuelo
     * @param departure el LocalDateTime que nos indica la fecha y hora de partida
     * @param hourFlight Las horas de duración que tiene el vuelo
     * @param minutesFlight los minutos de duración que tiene el vuelo
     * @return el LocalDateTime con la fecha y hora de llegada
     */
    private LocalDateTime arriveDating (LocalDateTime departure, int hourFlight, int minutesFlight){

        LocalDateTime arriveDate = departure.plusHours(hourFlight).plusMinutes(minutesFlight);

        return arriveDate;

    }


    /**
     * Busca un vuelo en el ArrayList de vuelos a través del código
     * @param flights el ArrayList de vuelos
     * @param code el código que previamente había introducido el usuario
     * @return el vuelo con el código solicitado o null si no existe
     */
    public static Flight lookingForAFlight(ArrayList<Flight> flights, String code) {
        Flight flight = new Flight(code);
        int position;
       // List<Flight> flightList = flights;


        if(flights.contains(flight)){

           position = flights.indexOf(flight);

           flight = flights.get(position);

        }
        else {

            flight = null;
        }

        return flight;
    }

    /**
     * Muestra los datos del avión solicitado o un texto de error en caso de no existir
     * @param flights el ArrayList con los vuelos almacenados en su interior
     */
    public static void showFlight(ArrayList<Flight> flights, String code) {
        Flight flight = new Flight(code);
        int position;


        if (flights.contains(flight)) {

            position = flights.indexOf(flight);

            System.out.println(flights.get(position));

        } else {

            Text.wrongCodeFlight();
        }
    }

    /**
     * muestra los datos de la lista de aviones para los clientes
     * @param flights El ArrayList con los aviones
     */
    public static void showFlightsForClient(ArrayList<Flight> flights){

        if(!flights.isEmpty()) {
            System.out.println("Vuelos agendados:\n");
            for (Flight f : flights
            ) {
                System.out.println("Vuelo con código " + f.code + ":");
                System.out.println("Origen: " + f.provenance);
                System.out.println("Destino: " + f.destination);
                System.out.println("Fecha y hora de salida: " + f.departure);
                System.out.println("Duración del vuelo: " + f.flightDuration);
                System.out.println("Día y hora de llegada: " + f.arriveDate);
                System.out.println("Precio por persona: " + f.price);
                System.out.println("Porcentaje de devolución: " + f.devolution);
                System.out.println("Piloto asignado: " + f.pilot.getName());
                System.out.println("Plazas disponibles: " + f.seats + "\n");
            }
        } else {

            Text.noFlightsCreated();
            }
    }

    /**
     * muestra los destinos de los vuelos con sus códigos
     * @param flights ArrayList con los vuelos guardados en su interior
     */
    public static void showFlightCodes (ArrayList<Flight> flights){

        if(!flights.isEmpty()) {
            System.out.println("Códigos de vuelos agendados:\n");
            for (Flight f : flights
            ) {
                System.out.println("Vuelo con destino "+f.destination+", código "+f.code+".\n");
            }
        } else {
            Text.noFlightsCreated();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return code.equals(flight.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
