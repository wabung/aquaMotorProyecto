package aquaMotor.aquaMotorProyecto;

import database.crud.CrudVehicle;
import database.model.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShowInventoryController implements Initializable {

    // Instancia de CRUD para vehículos
    private CrudVehicle crudVehicle = new CrudVehicle();
    
    // Lista para almacenar todos los vehículos
    private List<Vehicle> todosLosVehiculos = new ArrayList<>();
    
    // Lista filtrada de vehículos
    private List<Vehicle> vehiculosFiltrados = new ArrayList<>();

    // Controlador del AppBar
    @FXML
    private AppBarController appBarController;

    // Filtro de tipo
    @FXML
    private ComboBox<String> filterCombo;

    // Campo de búsqueda
    @FXML
    private TextField searchField;
    
    // GridPane para las tarjetas de vehículos
    @FXML
    private GridPane vehicleGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el título del AppBar
        configurarAppBar();

        // Configurar el ComboBox de filtros
        configurarFiltros();

        // Configurar el campo de búsqueda
        configurarBusqueda();

        // Cargar vehículos desde la base de datos
        cargarVehiculos();
    }

    private void configurarAppBar() {
        if (appBarController != null) {
            appBarController.setTitle("SHOW INVENTORY");
            appBarController.setOnBackAction(this::volverAHome);
        }
    }

    private void configurarFiltros() {
        if (filterCombo != null) {
            // Agregar opciones al ComboBox
            filterCombo.getItems().addAll(
                "All Types",
                "car",
                "motorbike",
                "van",
                "truck",
                "moped"

            );
            
            // Seleccionar "All Types" por defecto
            filterCombo.setValue("All Types");
            
            // Configurar listener para filtrar cuando cambie la selección
            filterCombo.setOnAction(event -> aplicarFiltrosYBusqueda());
        }
    }

    private void configurarBusqueda() {
        if (searchField != null) {
            // Configurar listener para búsqueda en tiempo real
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                aplicarFiltrosYBusqueda();
            });
        }
    }

    /**
     * Carga todos los vehículos desde la base de datos
     */
    private void cargarVehiculos() {
        try {
            // Obtener todos los vehículos de la base de datos
            todosLosVehiculos = crudVehicle.read();
            
            if (todosLosVehiculos == null) {
                todosLosVehiculos = new ArrayList<>();
            }
            
            System.out.println("Vehículos cargados: " + todosLosVehiculos.size());
            
            // Mostrar todos los vehículos inicialmente
            vehiculosFiltrados = new ArrayList<>(todosLosVehiculos);
            mostrarVehiculos();
            
        } catch (Exception e) {
            System.err.println("Error al cargar los vehículos desde la base de datos");
            e.printStackTrace();
            todosLosVehiculos = new ArrayList<>();
            vehiculosFiltrados = new ArrayList<>();
        }
    }
    
    /**
     * Muestra los vehículos filtrados en el GridPane
     */
    private void mostrarVehiculos() {
        if (vehicleGrid == null) {
            System.err.println("vehicleGrid es null");
            return;
        }
        
        // Limpiar el GridPane
        vehicleGrid.getChildren().clear();
        
        // Mostrar mensaje si no hay vehículos
        if (vehiculosFiltrados.isEmpty()) {
            System.out.println("No hay vehículos para mostrar");
            return;
        }
        
        // Crear tarjetas para cada vehículo
        int row = 0;
        int col = 0;
        int maxCols = 3; // 3 columnas
        
        for (Vehicle vehiculo : vehiculosFiltrados) {
            try {
                // Cargar el FXML de la tarjeta de vehículo
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/vehicleCard.fxml"));
                javafx.scene.Node vehicleCard = loader.load();
                
                // Obtener el controlador y configurar los datos
                VehicleCardController controller = loader.getController();
                
                // Formatear los datos del vehículo
                String type = vehiculo.getType() != null ? vehiculo.getType() : "N/A";
                String model = vehiculo.getModel() != null ? vehiculo.getModel() : "N/A";
                String color = "N/A"; // No disponible en el modelo actual
                String year = vehiculo.getEntryDate() != null ? 
                    new SimpleDateFormat("yyyy").format(vehiculo.getEntryDate()) : "N/A";
                String registration = vehiculo.getRegistration() != null ? vehiculo.getRegistration() : "N/A";
                String needRepair = "N/A"; // Podría calcularse con relaciones a Repairment
                String price = vehiculo.getEstimatedPrice() != null ? 
                    "$" + vehiculo.getEstimatedPrice().toString() : "N/A";
                
                controller.setVehicleData(type, model, color, year, registration, needRepair, price);
                
                // Agregar la tarjeta al GridPane
                vehicleGrid.add(vehicleCard, col, row);
                
                // Actualizar posición para la siguiente tarjeta
                col++;
                if (col >= maxCols) {
                    col = 0;
                    row++;
                }
                
            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de vehículo");
                e.printStackTrace();
            }
        }
        
        System.out.println("Mostrando " + vehiculosFiltrados.size() + " vehículos");
    }

    /**
     * Aplica filtros de tipo y búsqueda simultáneamente
     */
    private void aplicarFiltrosYBusqueda() {
        // Obtener el tipo seleccionado
        String tipoSeleccionado = filterCombo != null ? filterCombo.getValue() : "All Types";
        
        // Obtener el texto de búsqueda
        String textoBusqueda = searchField != null ? searchField.getText().trim().toLowerCase() : "";
        
        // Filtrar vehículos
        vehiculosFiltrados = todosLosVehiculos.stream()
            .filter(v -> {
                // Filtro por tipo
                boolean cumpleTipo = tipoSeleccionado == null || 
                                    tipoSeleccionado.equals("All Types") || 
                                    (v.getType() != null && v.getType().equalsIgnoreCase(tipoSeleccionado));
                
                // Filtro por búsqueda (busca en type, model, registration)
                boolean cumpleBusqueda = textoBusqueda.isEmpty() ||
                    (v.getType() != null && v.getType().toLowerCase().contains(textoBusqueda)) ||
                    (v.getModel() != null && v.getModel().toLowerCase().contains(textoBusqueda)) ||
                    (v.getRegistration() != null && v.getRegistration().toLowerCase().contains(textoBusqueda));
                
                return cumpleTipo && cumpleBusqueda;
            })
            .collect(Collectors.toList());
        
        System.out.println("Filtrado: " + vehiculosFiltrados.size() + " vehículos (Tipo: " + tipoSeleccionado + ", Búsqueda: '" + textoBusqueda + "')");
        
        // Mostrar los vehículos filtrados
        mostrarVehiculos();
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
     * Recarga los vehículos desde la base de datos (útil para actualizar después de cambios)
     */
    public void recargarVehiculos() {
        cargarVehiculos();
    }
}
