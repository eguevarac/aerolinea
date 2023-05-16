package aerolinea;

import java.util.ArrayList;

public class Client {

    private int ID;
    private ArrayList<Ticket> tickets;

    static int quantity;

    public Client() {

        this.ID=quantity;
        quantity++;

    }
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Éstos son los tickets comprados hasta el momento:" + tickets+"\n";
    }


    /**
     * busca un cliente a partir de la ID
     * @param clients ArrayList con los clientes en su interior
     * @param ID int con la ID que queremos localizar
     * @return objeto de Client con la ID indicada (null si no lo encuentra)
     */
    public static Client lookingForAClient (ArrayList<Client> clients, int ID){
        Client client =null;
        for (Client c : clients
        ) {
            if (ID == c.getID()) {
                client = c;
            }
        }
        return client;
    }

}
