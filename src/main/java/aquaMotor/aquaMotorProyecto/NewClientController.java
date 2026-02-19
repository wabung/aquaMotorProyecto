package aquaMotor.aquaMotorProyecto;

import database.crud.CrudClients;
import database.model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewClientController implements Initializable {

    // Instancia de CRUD para clientes
    private CrudClients crudClients = new CrudClients();

    // Controlador del AppBar
    @FXML
    private AppBarController appBarController;

    // Campos del formulario
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    // Botones
    @FXML
    private Button btnSave;

    @FXML
    private Button btnClear;

    // ScrollPane y VBox para la lista de clientes
    @FXML
    private ScrollPane clientScrollPane;

    @FXML
    private VBox clientListContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el título del AppBar
        configurarAppBar();
        
        // Inicializar el ScrollPane si existe
        if (clientScrollPane != null && clientListContainer == null) {
            clientListContainer = new VBox(10); // Espaciado de 10 entre elementos
            clientListContainer.setStyle("-fx-padding: 10;");
            clientScrollPane.setContent(clientListContainer);
            clientScrollPane.setFitToWidth(true);
        }
        
        // Cargar y mostrar todos los clientes
        cargarClientes();
    }

    private void configurarAppBar() {
        if (appBarController != null) {
            appBarController.setTitle("CUSTOMER SIGN UP");
            appBarController.setOnBackAction(this::volverAHome);
        }
    }

    /**
     * Carga todos los clientes desde la base de datos y los muestra en la lista scrolleable
     */
    private void cargarClientes() {
        if (clientListContainer == null) {
            return; // Si no hay contenedor, no podemos mostrar clientes
        }
        
        // Limpiar la lista actual
        clientListContainer.getChildren().clear();
        
        try {
            // Obtener todos los clientes de la base de datos
            List<Client> clientes = crudClients.read();
            
            if (clientes == null || clientes.isEmpty()) {
                System.out.println("No hay clientes en la base de datos");
                return;
            }
            
            // Crear una tarjeta para cada cliente
            for (Client cliente : clientes) {
                try {
                    // Cargar el FXML de la tarjeta de usuario
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/userCard.fxml"));
                    HBox userCard = loader.load();
                    
                    // Obtener el controlador y configurar los datos
                    UserCardController controller = loader.getController();
                    controller.setUserData(
                        cliente.getName(),
                        cliente.getEmail(),
                        cliente.getPhoneNumber()
                    );
                    
                    // Agregar la tarjeta al contenedor
                    clientListContainer.getChildren().add(userCard);
                } catch (IOException e) {
                    System.err.println("Error al cargar la tarjeta de usuario");
                    e.printStackTrace();
                }
            }
            
            System.out.println("Clientes cargados: " + clientes.size());
        } catch (Exception e) {
            System.err.println("Error al cargar los clientes desde la base de datos");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSave() {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        // Validar que todos los campos estén completos
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            System.out.println("ERROR: Por favor, complete todos los campos");
            // TODO: Mostrar una alerta visual al usuario
            return;
        }

        try {
            // Crear un nuevo cliente
            Client nuevoCliente = new Client(name, email, phone);
            
            // Guardar en la base de datos
            crudClients.create(nuevoCliente);
            
            System.out.println("Cliente guardado exitosamente:");
            System.out.println("  Nombre: " + name);
            System.out.println("  Email: " + email);
            System.out.println("  Teléfono: " + phone);
            
            // Limpiar el formulario
            handleClear();
            
            // Recargar la lista de clientes para mostrar el nuevo cliente
            cargarClientes();
            
        } catch (Exception e) {
            System.err.println("ERROR: No se pudo guardar el cliente en la base de datos");
            e.printStackTrace();
            // TODO: Mostrar una alerta de error al usuario
        }
    }

    @FXML
    private void handleClear() {
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        System.out.println("Formulario limpiado");
    }

    private void volverAHome() {
        try {
            App.setRoot("sales/views/homeSales");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al volver a la pantalla principal");
        }
    }

    // Métodos públicos para acceder a los datos del formulario
    public String getName() {
        return txtName.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }
}
