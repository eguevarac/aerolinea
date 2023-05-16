package aerolinea;

/**
 * todos los textos del programa
 */
public class Text {


    /**
     * Texto que avisa de que no se ha encontrado ningún piloto con ese código
     */
    public static void badPilotLookingFor() {
        System.out.println("No existe un piloto con ese código.");
        System.out.println("Por favor, inténtelo de nuevo.\n");
    }

    /**
     * Texto que avisa de que no se ha encontrado ningún avión con ese nombre
     */
    public static void badPlaneLookingFor() {
        System.out.println("No existe un avión con ese nombre.");
        System.out.println("Por favor, inténtelo de nuevo.\n");
    }

    /**
     * texto en caso de elección incorrecta en el menú de admin y cliente
     */
    public static void badChoiceMenu() {

        System.out.println("Opción no válida.");
        System.out.println("volvemos al inicio.\n");
    }


    /**
     * texto cuando se sale del menú de admin
     */
    public static void exitingAdminMenu() {
        System.out.println("Muy bien.");
        System.out.println("Regresamos al panel de administrador.\n");
    }


    /**
     * mensaje en caso de introducir el código de un vuelo inexistente
     */
    public static void wrongCodeFlight() {
        System.out.println("No existe ningún vuelo con ese código.");
        System.out.println("Retrocedemos al menú.\n");
    }

    /**
     * Texto en caso de no existir un billete con ese ID
     */
    public static void wrongIDTicket() {
        System.out.println("No existe ningún ticket con ese código.");
        System.out.println("Volvemos al menú.\n");
    }

    /**
     * Muestra las opcionres de modificación de vuelo al administrador
     */
    public static void adminModificationMenu() {
        System.out.println("Seleccione qué desea modificar:");
        System.out.println("1. Lugar de partida");
        System.out.println("2. Destino");
        System.out.println("3. Fecha de partida");
        System.out.println("4. Duración del vuelo");
        System.out.println("5. Precio por plaza");
        System.out.println("6. Porcentaje de devolución");
        System.out.println("7. Piloto");
        System.out.println("8. Avión");
        System.out.println("9. No deseo hacer más modificaciones");
    }

    /**
     * despedida en el panel de admin
     */
    public static void closeSesion() {
        System.out.println("Cerramos su sesión de administrador.");
        System.out.println("Muchas gracias.\n");
    }

    /**
     * texto de error en el panel de admin
     */
    public static void errorPanel() {

        System.out.println("El dato que ha introducido es incorrecto.");
        System.out.println("Por favor, vuelva a intentarlo.\n");
    }


    /**
     * menú que se muestra al cliente
     */
    public static void clientMenu() {
        System.out.println("Escoja la acción que desea realizar:");
        System.out.println("1. Visualizar vuelos");
        System.out.println("2. Comprar billetes");
        System.out.println("3. Solicitar devolución");
        System.out.println("4. Cerrar sesión");
    }

    /**
     * El menú que se muestra al usuario antes de saber si es admin o cliente
     */
    public static void userMenu() {
        System.out.println("Por favor, indique con un número si quiere acceder como cliente,\ncomo admministrador o si desea salir de la aplicación.");
        System.out.println("1. Administrador");
        System.out.println("2. Cliente");
        System.out.println("3. Salir del programa");
    }

    /**
     * menú de administrador que se muestra al usuario
     */
    public static void adminMenu() {
        System.out.println("Introduzca un número para escoger una opción:");
        System.out.println("1. Crear vuelo");
        System.out.println("2. Visualizar datos de vuelo");
        System.out.println("3. Modificar vuelo");
        System.out.println("4. Borrar vuelo");
        System.out.println("5. Mostrar aviones");
        System.out.println("6. Mostrar pilotos");
        System.out.println("7. Cerrar sesión");
    }

    /**
     * texto de despedida
     */
    public static void goodbye() {
        System.out.println("Le agradecemos el uso de nuestra aplicación.");
        System.out.println("Esperamos haberle sido de ayuda.");
        System.out.println("Que tenga un propicio día.");
    }

    /**
     * mensaje de bienvenida para clientes
     */
    public static void clientGreetings() {
        System.out.println("Está accediendo al panel de cliente.");
        System.out.println("Sea bienvenido.\n");
    }

    /**
     * mensaje en caso de fallar con el passwd de admin
     */
    public static void wrongPasswd() {
        System.out.println("Nombre de usuario y/o contraseña incorrectos.");
        System.out.println("Regresamos al menú de inicio.\n");
    }

    /**
     * Texto de bienvenida al admin
     *
     * @param name el nombre del administrador en cuestión
     */
    public static void adminGreetings(String name) {
        System.out.println("Sea bienvenido/a, " + name + ".");
        System.out.println("Accedemos al panel de administración.\n");
    }

    /**
     * da la bienvenida al usuario
     */
    public static void greetings() {

        System.out.println("Bienvenido/a al nuevo gestor de vuelos de Polivols,\nsu compañía aérea de confianza.\n");

    }

    /**
     * Mensaje de cierre de sesión del cliente
     */
    public static void goodbyeClient() {
        System.out.println("Esperamos haberle sido de ayuda.");
        System.out.println("Cerramos su sesión.");
        System.out.println("Muchas gracias.\n");
    }

    /**
     * Mensaje en caso de no haber tantas plazas como se solicitan para comprar
     */
    public static void noSeatsToFlight() {
        System.out.println("El vuelo indicado no dispone de las plazas solicitadas.");
        System.out.println("Volvemos al menú de cliente.\n");
    }

    /**
     * Mensaje en caso de solicitar un reembolso por más plazas de las que tiene reservadas
     */
    public static void noSeatsInTicket() {
        System.out.println("No tiene tantas plazas reservadas en ese billete.");
        System.out.println("Volvemos al menú de cliente.\n");
    }

    /**
     * Texto en caso de no introducir bien el ID del billete
     */
    public static void wrongNumTickets() {
        System.out.println("El dato introducido es incorrecto.");
        System.out.println("Volvemos al menú de cliente.\n");
    }

    /**
     * Mensaje en caso de no confirmar la compra
     */
    public static void clientBuyingDenied() {
        System.out.println("Ha cancelado la compra.");
        System.out.println("Volvemos al menú de cliente.\n");
    }

    /**
     * Texto en caso de no confirmar la devolución de billete
     */
    public static void clientSellingDenied() {
        System.out.println("Ha cancelado la devolución.");
        System.out.println("Volvemos al menú de cliente.\n");
    }

    /**
     * Mensaje de bienvenida a los clientes nuevos
     *
     * @param client Client cliente nuevo que se ha generado
     */
    public static void welcomingNewClient(Client client) {

        System.out.println("Su código de identificación a partir de ahora será " + client.getID()+".");
        System.out.println("Necesitará este número de identificación para volver a iniciar sesión.");
        System.out.println("Por favor, guárdelo en un lugar seguro.\n");
    }


    /**
     * Mensaje para el cliente si su ID no se encuentra en la lista de clientes
     */
    public static void noIDInDatabase() {

        System.out.println("Lamentamos informarle de que su número\nde identificación no se encuentra en nuestra base de datos.");
        System.out.println("Retrocedemos al menú principal.\n");
    }

    /**
     * Texto de bienvenida a clientes ya registrados
     */
    public static void welcomingOldClient() {
        System.out.println("Nos alegra tenerle de vuelta.\n");
    }

    /**
     * texto en caso de no haber billetes comprados
     */
    public static void noTicketsBought() {
        System.out.println("No tiene ningún billete comprado.");
        System.out.println("Retrocedemos al menú de cliente.\n");
    }

    /**
     * Texto en caso de crear el vuelo correctamente
     */
    public static void flightCreationSuccesfull() {
        System.out.println("\nSu vuelo se ha creado correctamente.\n");
    }

    /**
     * texto de confirmación de la devolución
     */
    public static void returnMoneySuccesfull (){
        System.out.println("Devolución realizada con éxito.");
        System.out.println("Muchas gracias.\n");
    }

    /**
     * texto de confirmación de la compra
     */
    public static void buyingTicketSuccesfull(){
        System.out.println("Su billete ha sido comprado con éxito.");
        System.out.println("Muchas gracias por usar este servicio.\n");
    }

    /**
     * Bienvenida al menú de compra de billetes
     */
    public static void welcomingBuyMenu (){
        System.out.println("Está en el asistente de compra de billetes.\n");
    }
    public static void welcomingReturnMenu (){
        System.out.println("Está accediendo al menú de devolución de billetes.\n");
    }

    /**
     * Texto en caso de no haber vuelos agendados
     */
    public static void noFlightsCreated(){
        System.out.println("No hay vuelos agendados.");
        System.out.println("Retrocedemos al menú.\n");
    }
}
