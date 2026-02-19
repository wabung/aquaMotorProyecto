package aquaMotor.aquaMotorProyecto;

import database.crud.CrudClients;
import database.crud.CrudOffer;
import database.crud.CrudVehicle;
import database.model.Client;
import database.model.Offer;
import database.model.SalesPerson;
import database.model.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OfferSaleController implements Initializable {

    // Instancias de CRUD
    private CrudVehicle crudVehicle = new CrudVehicle();
    private CrudClients crudClients = new CrudClients();
    private CrudOffer crudOffer = new CrudOffer();

    // Listas para almacenar datos
    private List<Vehicle> vehiculos = new ArrayList<>();
    private List<Client> clientes = new ArrayList<>();

    // Vehículo y cliente seleccionados
    private Vehicle vehiculoSeleccionado;
    private Client clienteSeleccionado;

    // Controlador del AppBar
    @FXML
    private AppBarController appBarController;

    // GridPane para vehículos
    @FXML
    private GridPane vehicleGrid;

    // VBox para clientes
    @FXML
    private VBox clientVBox;

    // Campos del formulario para la oferta
    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtPriceOffer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDeadline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el título del AppBar
        configurarAppBar();

        // Cargar vehículos y clientes
        cargarVehiculos();
        cargarClientes();
    }

    private void configurarAppBar() {
        if (appBarController != null) {
            appBarController.setTitle("OFFER SALE");
            appBarController.setOnBackAction(this::volverAHome);
        }
    }

    /**
     * Carga todos los vehículos desde la base de datos
     */
    private void cargarVehiculos() {
        try {
            vehiculos = crudVehicle.read();
            if (vehiculos == null) {
                vehiculos = new ArrayList<>();
            }
            mostrarVehiculos();
            System.out.println("Vehículos cargados: " + vehiculos.size());
        } catch (Exception e) {
            System.err.println("Error al cargar vehículos");
            e.printStackTrace();
            vehiculos = new ArrayList<>();
        }
    }

    /**
     * Muestra todos los vehículos en el GridPane
     */
    private void mostrarVehiculos() {
        if (vehicleGrid == null) return;

        vehicleGrid.getChildren().clear();

        int row = 0;
        int col = 0;

        for (int i = 0; i < vehiculos.size(); i++) {
            Vehicle vehiculo = vehiculos.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/vehicleCard.fxml"));
                javafx.scene.Node vehicleCard = loader.load();

                VehicleCardController controller = loader.getController();

                String type = vehiculo.getType() != null ? vehiculo.getType() : "N/A";
                String model = vehiculo.getModel() != null ? vehiculo.getModel() : "N/A";
                String registration = vehiculo.getRegistration() != null ? vehiculo.getRegistration() : "N/A";
                String year = vehiculo.getEntryDate() != null ? 
                    new SimpleDateFormat("yyyy").format(vehiculo.getEntryDate()) : "N/A";
                String price = vehiculo.getEstimatedPrice() != null ? 
                    "$" + vehiculo.getEstimatedPrice().toString() : "N/A";

                controller.setVehicleData(type, model, "N/A", year, registration, "N/A", price);

                // Hacer clickeable para seleccionar
                final int index = i;
                vehicleCard.setOnMouseClicked(event -> seleccionarVehiculo(index));

                vehicleGrid.add(vehicleCard, col, row);

                col++;
                if (col >= 2) {
                    col = 0;
                    row++;
                }

            } catch (IOException e) {
                System.err.println("Error al cargar tarjeta de vehículo");
                e.printStackTrace();
            }
        }
    }

    /**
     * Carga todos los clientes desde la base de datos
     */
    private void cargarClientes() {
        try {
            clientes = crudClients.read();
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
            mostrarClientes();
            System.out.println("Clientes cargados: " + clientes.size());
        } catch (Exception e) {
            System.err.println("Error al cargar clientes");
            e.printStackTrace();
            clientes = new ArrayList<>();
        }
    }

    /**
     * Muestra todos los clientes en el VBox
     */
    private void mostrarClientes() {
        if (clientVBox == null) return;

        clientVBox.getChildren().clear();

        for (int i = 0; i < clientes.size(); i++) {
            Client cliente = clientes.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/userCard.fxml"));
                javafx.scene.Node userCard = loader.load();

                UserCardController controller = loader.getController();
                controller.setUserData(
                    cliente.getName(),
                    cliente.getEmail(),
                    cliente.getPhoneNumber()
                );

                // Hacer clickeable para seleccionar
                final int index = i;
                userCard.setOnMouseClicked(event -> seleccionarCliente(index));

                clientVBox.getChildren().add(userCard);

            } catch (IOException e) {
                System.err.println("Error al cargar tarjeta de cliente");
                e.printStackTrace();
            }
        }
    }

    /**
     * Selecciona un vehículo para la oferta
     */
    private void seleccionarVehiculo(int index) {
        if (index < vehiculos.size()) {
            vehiculoSeleccionado = vehiculos.get(index);
            if (txtLicensePlate != null) {
                txtLicensePlate.setText(vehiculoSeleccionado.getRegistration());
            }
            System.out.println("Vehículo seleccionado: " + vehiculoSeleccionado.getRegistration());
        }
    }

    /**
     * Selecciona un cliente para la oferta
     */
    private void seleccionarCliente(int index) {
        if (index < clientes.size()) {
            clienteSeleccionado = clientes.get(index);
            if (txtContact != null) {
                txtContact.setText(clienteSeleccionado.getName());
            }
            System.out.println("Cliente seleccionado: " + clienteSeleccionado.getName());
        }
    }

    @FXML
    private void handleOfferSale() {
        // Validar que se haya seleccionado un vehículo y un cliente
        if (vehiculoSeleccionado == null) {
            System.out.println("ERROR: Debe seleccionar un vehículo");
            return;
        }

        if (clienteSeleccionado == null) {
            System.out.println("ERROR: Debe seleccionar un cliente");
            return;
        }

        // Obtener los valores del formulario
        String priceOfferStr = txtPriceOffer != null ? txtPriceOffer.getText().trim() : "";
        String deadlineStr = txtDeadline != null ? txtDeadline.getText().trim() : "";

        if (priceOfferStr.isEmpty() || deadlineStr.isEmpty()) {
            System.out.println("ERROR: Complete todos los campos (Price Offer y Deadline)");
            return;
        }

        try {
            // Obtener el usuario actual y su SalesPerson
            database.model.User currentUser = App.getCurrentUser();
            if (currentUser == null || currentUser.getSalesPerson() == null) {
                System.err.println("ERROR: No hay usuario logueado o no es un vendedor");
                return;
            }
            
            SalesPerson salesperson = currentUser.getSalesPerson();
            
            // Crear la oferta
            Offer nuevaOferta = new Offer();
            nuevaOferta.setVehicle(vehiculoSeleccionado);
            nuevaOferta.setClient(clienteSeleccionado);
            nuevaOferta.setSalesperson(salesperson);
            nuevaOferta.setPriceOffer(new BigDecimal(priceOfferStr));
            
            // Parsear la fecha (formato esperado: yyyy-MM-dd)
            LocalDate localDate = LocalDate.parse(deadlineStr, DateTimeFormatter.ISO_LOCAL_DATE);
            nuevaOferta.setDeadline(Date.valueOf(localDate));

            // Guardar en la base de datos
            crudOffer.create(nuevaOferta);

            System.out.println("Oferta creada exitosamente en la base de datos");
            System.out.println("  Vendedor: " + salesperson.getUser().getName());
            System.out.println("  Vehículo: " + vehiculoSeleccionado.getRegistration());
            System.out.println("  Cliente: " + clienteSeleccionado.getName());
            System.out.println("  Precio oferta: $" + priceOfferStr);
            System.out.println("  Fecha límite: " + deadlineStr);

            // Limpiar formulario
            limpiarFormulario();

        } catch (NumberFormatException e) {
            System.err.println("ERROR: El precio debe ser un número válido");
        } catch (Exception e) {
            System.err.println("ERROR: No se pudo crear la oferta");
            e.printStackTrace();
        }
    }

    /**
     * Limpia el formulario
     */
    private void limpiarFormulario() {
        if (txtLicensePlate != null) txtLicensePlate.clear();
        if (txtPriceOffer != null) txtPriceOffer.clear();
        if (txtContact != null) txtContact.clear();
        if (txtDeadline != null) txtDeadline.clear();
        
        vehiculoSeleccionado = null;
        clienteSeleccionado = null;
    }

    private void volverAHome() {
        try {
            App.setRoot("sales/views/homeSales");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al volver a la pantalla principal");
        }
    }

    /**
     * Recarga los vehículos y clientes
     */
    public void recargarDatos() {
        cargarVehiculos();
        cargarClientes();
    }
}
