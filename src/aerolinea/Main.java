package aerolinea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Programa de aerolínea
 */
public class Main {
    public static void main(String[] args) {
        int userChoice;
        boolean finish = false;
        ArrayList<Admin> admins;
        ArrayList<Pilot> pilots;
        ArrayList<Airplane> airplanes;
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Flight> flights = new ArrayList<>();
        Admin adminInUse;


        admins = arrayAdminsCreation();
        pilots = arrayPilotsCreation();
        airplanes = arrayAirplanesCreation();


        Text.greetings();

        do {
            userChoice = userMenu();

            switch (userChoice) {
                case 1:

                    adminInUse = authentication(admins);

                    if (adminInUse != null) {

                        Text.adminGreetings(adminInUse.getName());

                        adminMenu(airplanes, pilots, flights);

                    } else {
                        Text.wrongPasswd();
                    }
                    break;

                case 2:
                    Text.clientGreetings();
                    clientMenu(flights, clients);
                    break;

                case 3:

                    finish = true;
                    Text.goodbye();
                    break;

                default:
                    Text.badChoiceMenu();
                    break;
            }

        } while (!finish);

    }


    /**
     * Menú en caso de entrar como cliente
     *
     * @param flights el ArrayList de vuelos
     * @param clients el ArrayList de clientes
     */
    private static void clientMenu(ArrayList<Flight> flights, ArrayList<Client> clients) {
        Client client;
        int clientChoices;
        boolean exit = false;

        client = checkingIfIsNewClient(clients);

        if (client != null) {

            do {
                Text.clientMenu();
                clientChoices = SpellBook.insertInt(null);
                switch (clientChoices) {
                    case 1:
                        Flight.showFlightsForClient(flights);
                        break;
                    case 2:
                        if(!flights.isEmpty()) {
                        Text.welcomingBuyMenu();
                        buyTickets(flights, client);}else {
                            Text.noFlightsCreated();
                        }
                        break;
                    case 3:
                        Text.welcomingReturnMenu();
                        returnTickets(flights, client);
                        break;
                    case 4:
                        exit = true;
                        Text.goodbyeClient();
                        break;
                    default:
                        Text.badChoiceMenu();
                        break;
                }
            } while (!exit);
        }

    }

    /**
     * Solicita al cliente que indique si es nuevo o si ya está registrado
     *
     * @param clients el ArrayList de clientes
     * @return el Client con el que trabajaremos dentro del menú en caso de existir (en caso contrario devoverá null)
     */
    private static Client checkingIfIsNewClient(ArrayList<Client> clients) {
        Client client = null;
        char clientChoices;
        String IDString;
        boolean isNumber;
        int ID;


        clientChoices = SpellBook.yesOrNotQuestion("Es un nuevo cliente (conteste S/N)?");

        if (clientChoices == 's') {
            client = new Client();

            Text.welcomingNewClient(client);

            clients.add(client);
        } else {
            IDString = SpellBook.insertString("Indique su número de identificación, por favor.");
            isNumber = SpellBook.checkingIfIsNumString(IDString, 4);

            if (isNumber) {
                ID = Integer.parseInt(IDString);

                client = Client.lookingForAClient(clients, ID);

            }
            if (client == null) {

                Text.noIDInDatabase();

            }else{Text.welcomingOldClient();}
        }
        return client;

    }

    /**
     * menú de devolución de billetes
     *
     * @param flights ArrayList con todos los vuelos creados
     * @param client  objeto de Client sobre el que se ejercerán los cambios
     */
    private static void returnTickets(ArrayList<Flight> flights, Client client) {
        String IDString, numSeatsString;
        Ticket ticketToReturn;
        boolean isNum;
        int ID, numSeatsToReturn;

        if (client.getTickets() == null || client.getTickets().isEmpty()) {

            Text.noTicketsBought();

        } else {
            System.out.println("Éstos son los billetes que puede cambiar:");

            Ticket.allTicketShowing(client.getTickets());

            IDString = SpellBook.insertString("Indique la ID del billete que quiere modificar.");

            isNum = SpellBook.checkingIfIsNumString(IDString, 10);
            if (isNum) {
                ID = Integer.parseInt(IDString);

                ticketToReturn = Ticket.lookingForATicket(client.getTickets(), ID);

                if (ticketToReturn != null) {

                    numSeatsString = SpellBook.insertString("Cuántas plazas quiere devolver?");

                    isNum = SpellBook.checkingIfIsNumString(numSeatsString, 3);

                    if (isNum) {
                        numSeatsToReturn = Integer.parseInt(numSeatsString);

                        if (numSeatsToReturn <= ticketToReturn.getSeats()) {

                            returningTickets(client, ticketToReturn, flights, numSeatsToReturn);


                        } else {
                            Text.noSeatsInTicket();
                        }

                    } else {
                        Text.wrongNumTickets();
                    }

                } else {
                    Text.wrongIDTicket();
                }
            }

        }
    }

    /**
     * Función de devolución final de billete
     *
     * @param client           el objeto Client sobre el que se van a efectuar los cambios
     * @param ticketToReturn   el objeto Ticket que será modificado
     * @param flights          ArrayList con todos los vuelos almacenados
     * @param numSeatsToReturn int con la cantidad de plazas que se desean devolver
     */
    private static void returningTickets(Client client, Ticket ticketToReturn, ArrayList<Flight> flights, int numSeatsToReturn) {
        int arrayIndex;
        float moneyToReturn;
        char confirmation;
        Flight flightToBuyAndSell;

        moneyToReturn = ticketToReturn.moneyToReturnCalculating(flights, numSeatsToReturn);
        System.out.println("La devolución será de " + moneyToReturn + "€ por " + numSeatsToReturn + " plazas.");

        confirmation = SpellBook.yesOrNotQuestion("Está seguro de esta devolución (conteste S/N)?");


        if (confirmation == 's') {
            ticketToReturn.setSeats(ticketToReturn.getSeats() - numSeatsToReturn);

            flightToBuyAndSell = Flight.lookingForAFlight(flights, ticketToReturn.getFlightCode());

            flightToBuyAndSell.setSeats(flightToBuyAndSell.getSeats() + numSeatsToReturn);

            Text.returnMoneySuccesfull();

            ticketToReturn.setPrice(ticketToReturn.getPrice()-moneyToReturn);

            if (ticketToReturn.getSeats() == 0) {
                arrayIndex = client.getTickets().indexOf(ticketToReturn);
                client.getTickets().remove(arrayIndex);
            }
        } else {

            Text.clientSellingDenied();

        }
    }


    /**
     * menú de compra de billetes
     *
     * @param flights ArrayList con los vuelos almacenados
     * @param client  objeto Client sobre el que efectuaremos los cambios
     */
    private static void buyTickets(ArrayList<Flight> flights, Client client) {
        Flight flightToBuyAndShell;
        String code, numTicketsToBuyString;
        int numSeatsToBuy;
        boolean isNumber;

        code = SpellBook.insertString("Indique el código del vuelo:");

        flightToBuyAndShell = Flight.lookingForAFlight(flights, code);

        if (flightToBuyAndShell != null) {

            System.out.println("Cada plaza costará " + flightToBuyAndShell.getPrice());

            numTicketsToBuyString = SpellBook.insertString("Introduzca el número de plazas que desea comprar.");

            isNumber = SpellBook.checkingIfIsNumString(numTicketsToBuyString, 4);


            if (isNumber && !numTicketsToBuyString.equals("0")) {

                numSeatsToBuy = Integer.parseInt(numTicketsToBuyString);

                if (flightToBuyAndShell.getSeats() >= numSeatsToBuy) {


                    buyingTickets(flightToBuyAndShell, numSeatsToBuy, client);

                } else {
                    Text.noSeatsToFlight();
                }

            } else {
                Text.wrongNumTickets();
            }
        } else {
            Text.wrongCodeFlight();
        }
    }

    /**
     * la compra definitiva o no de un ticket
     *
     * @param flightToBuyAndShell objeto Flight sobre el que se efectuarán los cambios de plazas disponibles
     * @param numSeatsToBuy       int con la cantidad de plazas que se quiere comprar
     * @param client              objeto Client sobre el que se harán las modificaciones
     */
    private static void buyingTickets(Flight flightToBuyAndShell, int numSeatsToBuy, Client client) {
        char confirmation;
        Ticket ticketToBuy;
        ArrayList<Ticket> tickets = new ArrayList<>();

        ticketToBuy = new Ticket(numSeatsToBuy, flightToBuyAndShell.getCode(), flightToBuyAndShell.getPrice());

        System.out.println("El precio total será de " + ticketToBuy.getPrice() + "€ para un total de " + numSeatsToBuy + " plazas.");

        confirmation = SpellBook.yesOrNotQuestion("Está seguro de esta compra (conteste S/N)?");

        if (confirmation == 's') {

            if (client.getTickets() != null) {

                tickets = client.getTickets();
            }
            tickets.add(ticketToBuy);
            client.setTickets(tickets);


            flightToBuyAndShell.setSeats(flightToBuyAndShell.getSeats() - numSeatsToBuy);

            Text.buyingTicketSuccesfull();

        } else {
            Text.clientBuyingDenied();
        }

    }


    /**
     * el menú que se muestra en caso de acceder como administrador
     *
     * @param airplanes el ArrayList de aviones
     * @param pilots    el ArrayList de pilotos
     * @param flights   el ArrayList de vuelos
     */

    private static void adminMenu(ArrayList<Airplane> airplanes, ArrayList<Pilot> pilots, ArrayList<Flight> flights) {
        boolean closeMenu = false;
        int adminChoice;
        String code;

        do {

            Text.adminMenu();

            adminChoice = SpellBook.insertInt(null);

            switch (adminChoice) {
                case 1:
                    flightCreation(flights, pilots, airplanes);
                    break;
                case 2:
                    Flight.showFlightCodes(flights);
                    if(!flights.isEmpty()) {
                    code = SpellBook.insertString("Indique el código de vuelo\npara revisar todos sus datos:");
                    Flight.showFlight(flights, code);}
                    break;
                case 3:
                    Flight.showFlightCodes(flights);
                    if(!flights.isEmpty()) {
                    flightModification(flights, pilots, airplanes);}
                    break;
                case 4:
                    Flight.showFlightCodes(flights);
                    if(!flights.isEmpty()) {
                    deleteFlight(flights);}
                    break;
                case 5:
                    Airplane.showAirplanes(airplanes);
                    break;
                case 6:
                    Pilot.showPilots(pilots);
                    break;
                case 7:
                    closeMenu = true;
                    Text.closeSesion();
                    break;
                default:
                    Text.errorPanel();
                    break;
            }

        } while (!closeMenu);
    }

    /**
     * Pide al usuario el código del vuelo que quiere borrar y lo borra en caso de existir
     *
     * @param flights El ArrayList de vuelos
     */
    private static void deleteFlight(ArrayList<Flight> flights) {
        String code;
        Flight flightToDelete;
        int arrayIndex;


        code = SpellBook.insertString("Introduzca el código que desea borrar:");

        flightToDelete = Flight.lookingForAFlight(flights, code);

        if (flightToDelete == null) {
            System.out.println("no existe ese vuelo.");

        } else {

            arrayIndex = flights.indexOf(flightToDelete);
            flights.remove(arrayIndex);
            System.out.println("El vuelo se ha borrado con éxito.");
        }
    }

    /**
     * Menú con las posibles modificaciones que se pueden realizar en los vuelos
     *
     * @param flights   El ArrayList de vuelos
     * @param pilots    El ArrayList de pilotos
     * @param airplanes El ArrayList de aviones
     */
    private static void flightModification(ArrayList<Flight> flights, ArrayList<Pilot> pilots, ArrayList<Airplane> airplanes) {
        String code;
        Flight flight;
        int choice;
        String stringToModif;
        float floatToModif;
        LocalDateTime dateToModif;
        LocalTime timeToModif;
        Pilot pilot;
        Airplane airplane;


        code = SpellBook.insertString("Indique el código del vuelo que desea modificar:");

        flight = Flight.lookingForAFlight(flights, code);

        if (flight == null) {

            Text.wrongCodeFlight();

        } else {

            do {
                Text.adminModificationMenu();
                choice = SpellBook.insertInt(null);
                switch (choice) {

                    case 1:
                        stringToModif = SpellBook.insertString("Introduzca lugar de partida:");
                        flight.setProvenance(stringToModif);
                        break;
                    case 2:
                        stringToModif = SpellBook.insertString("Introduzca destino:");
                        flight.setDestination(stringToModif);
                        break;
                    case 3:
                        dateToModif = SpellBook.dateAndTimeCreation("Introduzca la fecha de partida:");
                        flight.setDeparture(dateToModif);
                        break;
                    case 4:
                        timeToModif = SpellBook.timeCreation("Introduzca las horas y minutos que dura el vuelo:");
                        flight.setFlightDuration(timeToModif);
                        break;
                    case 5:
                        floatToModif = SpellBook.insertFloat("Introduzca el precio de cada plaza:");
                        floatToModif = checkingIfCorrectPrice(floatToModif);
                        flight.setPrice(floatToModif);
                        break;
                    case 6:
                        floatToModif = SpellBook.insertFloat("Introduzca el porcentaje de devolución:");
                        floatToModif = checkingIfCorrectPercent(floatToModif);
                        flight.setDevolution(floatToModif);
                        break;
                    case 7:
                        pilot = Pilot.lookingForAPilot(pilots);
                        flight.setPilot(pilot);
                        break;
                    case 8:
                        airplane = Airplane.lookingForAPlane(airplanes);
                        flight.setAirplane(airplane);
                        break;
                    case 9:
                        Text.exitingAdminMenu();
                        break;

                    default:
                        Text.badChoiceMenu();
                        break;

                }

            } while (choice != 9);
        }

    }


    /**
     * solicita al usuario los datos para crear un nuevo vuelo
     *
     * @param flights   la lista de vuelos en la que añadir el vuelo
     * @param pilots    la lista de pilotos para asignar al vuelo
     * @param airplanes la lista de aviones para asignar al vuelo
     */
    private static void flightCreation(ArrayList<Flight> flights, ArrayList<Pilot> pilots, ArrayList<Airplane> airplanes) {
        String code, provenance, destination;
        LocalDateTime departure;
        LocalTime flightDuration;
        Pilot pilot;
        Airplane airplane;
        float devolution, price;

        code = SpellBook.insertString("Introduzca código de vuelo:");
        provenance = SpellBook.insertString("Introduzca lugar de partida:");
        destination = SpellBook.insertString("Introduzca destino:");
        departure = SpellBook.dateAndTimeCreation("Introduzca la fecha de partida:");
        flightDuration = SpellBook.timeCreation("Introduzca las horas y minutos que dura el vuelo:");
        price = SpellBook.insertFloat("Introduzca el precio de cada plaza:");

        price = checkingIfCorrectPercent(price);

        devolution = SpellBook.insertFloat("Introduzca el porcentaje de devolución:");

        devolution = checkingIfCorrectPercent(devolution);

        pilot = Pilot.lookingForAPilot(pilots);

        airplane = Airplane.lookingForAPlane(airplanes);

        flights.add(new Flight(code, provenance, destination, departure, flightDuration, pilot, airplane, devolution, price));

        Text.flightCreationSuccesfull();

    }

    /**
     * chequea si el porcentaje está por encima de 100 o por debajo de 0
     * @param devolution el float que indica el porcentaje
     * @return el float con el porcentaje establecido a 0 (en caso de error)
     */
    private static float checkingIfCorrectPercent (float devolution){

        if (devolution <0 || devolution >100){
            System.out.println("No es posible establecer esa cifra.");
            System.out.println("Establecemos el porcentaje de devolución a 0.0");
            devolution =0.0F;
        }
        return devolution;
    }

    private static float checkingIfCorrectPrice (float price){

        if (price <0){
            System.out.println("No es posible establecer esa cifra.");
            System.out.println("Establecemos el precio a 0.0");
            price =0.0F;
        }
        return price;
    }




    /**
     * Solicita al usuario un nombre y un passwd y busca en el ArrayList de admins si coincide con alguno
     *
     * @param admins el ArrayList de admins en el que buscará
     * @return el admin encontrado (null en caso de no haberlo)
     */
    private static Admin authentication(ArrayList<Admin> admins) {
        String name, passwd;
        Admin adminInUse = null;

        name = SpellBook.insertString("Por favor, indique su nombre:");

        passwd = SpellBook.insertString("Ahora indique su contraseña:");

        for (Admin a :
                admins) {
            if (a.getName().equalsIgnoreCase(name)) {
                if (a.getPasswd().equalsIgnoreCase(passwd)) {
                    adminInUse = a;
                }
            }
        }
        return adminInUse;
    }


    /**
     * el menú de selección de usuario
     *
     * @return la opción escogida
     */
    private static int userMenu() {

        int choice;


            Text.userMenu();

          choice = SpellBook.insertInt(null);


        return choice;
    }

    /**
     * crea el ArrayList de aviones
     *
     * @return el ArrayList con los aviones
     */
    private static ArrayList<Airplane> arrayAirplanesCreation() {

        ArrayList<Airplane> airplanes = new ArrayList<>();

        airplanes.add(new Airplane("Boeing 747-8", 228980, 467, LocalDate.of(2021, 11, 14)));
        airplanes.add(new Airplane("Airbus A380", 297000, 853, LocalDate.of(2020, 10, 25)));
        airplanes.add(new Airplane("Eclipse 500", 1023, 5, LocalDate.of(2008, 12, 12)));

        return airplanes;

    }

    /**
     * Crea el ArrayList de pilotos
     *
     * @return el ArrayList con los pilotos
     */
    private static ArrayList<Pilot> arrayPilotsCreation() {

        ArrayList<Pilot> pilots = new ArrayList<>();

        pilots.add(new Pilot("Sukio", "Seki", "45216782J", LocalDate.of(1982, 10, 23), 654879654, "kami1"));
        pilots.add(new Pilot("Motoharu", "Okamura", "54678798Y", LocalDate.of(1991, 2, 12), 699979654, "kami2"));
        pilots.add(new Pilot("Takijiro", "Onishi", "45216782J", LocalDate.of(1971, 1, 30), 654879654, "kami0"));


        return pilots;
    }

    /**
     * crea el Arraylist de admins
     *
     * @return el ArrayList con los admins
     */
    private static ArrayList<Admin> arrayAdminsCreation() {

        ArrayList<Admin> admins = new ArrayList<>();

        admins.add(new Admin("Jack", "Rackham"));
        admins.add(new Admin("John", "Silver"));
        admins.add(new Admin("Charles", "Vane"));

        return admins;

    }

}