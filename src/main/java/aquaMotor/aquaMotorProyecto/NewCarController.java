package aquaMotor.aquaMotorProyecto;

import database.crud.CrudVehicle;
import database.model.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewCarController implements Initializable {

    // Instancia de CRUD para vehículos
    private CrudVehicle crudVehicle = new CrudVehicle();

    // Lista para almacenar vehículos
    private List<Vehicle> vehiculos = new ArrayList<>();

    // Controlador del AppBar
    @FXML
    private AppBarController appBarController;

    // Campos del formulario
    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtColor;

    @FXML
    private ComboBox<String> cboType;

    // GridPane para mostrar vehículos
    @FXML
    private GridPane vehicleGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el título del AppBar
        configurarAppBar();

        // Configurar el ComboBox de tipos
        configurarComboBox();

        // Cargar vehículos
        cargarVehiculos();
    }

    private void configurarAppBar() {
        if (appBarController != null) {
            appBarController.setTitle("NEW CAR");
            appBarController.setOnBackAction(this::volverAHome);
        }
    }

    private void configurarComboBox() {
        if (cboType != null) {
            cboType.getItems().addAll(
                "Sedan",
                "SUV",
                "Truck",
                "Coupe",
                "Van",
                "Hatchback",
                "Convertible"
            );
        }
    }

    /**
     * Carga todos los vehículos desde la base de datos
     */
    private void cargarVehiculos() {
        try {
            vehiculos = crudVehicle.readAll();
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
     * Muestra los vehículos en el GridPane (máximo 6)
     */
    private void mostrarVehiculos() {
        if (vehicleGrid == null) return;

        vehicleGrid.getChildren().clear();

        int maxVehiculos = Math.min(6, vehiculos.size());
        int row = 0;
        int col = 0;
        int maxCols = 3;

        for (int i = 0; i < maxVehiculos; i++) {
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

                vehicleGrid.add(vehicleCard, col, row);

                col++;
                if (col >= maxCols) {
                    col = 0;
                    row++;
                }

            } catch (IOException e) {
                System.err.println("Error al cargar tarjeta de vehículo");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleSave() {
        // Obtener valores del formulario
        String licensePlate = txtLicensePlate != null ? txtLicensePlate.getText().trim() : "";
        String model = txtModel != null ? txtModel.getText().trim() : "";
        String type = cboType != null ? cboType.getValue() : "";

        // Validar campos obligatorios
        if (licensePlate.isEmpty() || model.isEmpty() || type == null || type.isEmpty()) {
            System.out.println("ERROR: Debe completar License Plate, Model y Type");
            return;
        }

        try {
            // Crear nuevo vehículo
            Vehicle nuevoVehiculo = new Vehicle();
            nuevoVehiculo.setRegistration(licensePlate);
            nuevoVehiculo.setModel(model);
            nuevoVehiculo.setType(type);
            
            // Establecer fecha de entrada como hoy
            nuevoVehiculo.setEntryDate(Date.valueOf(LocalDate.now()));
            
            // Precio estimado por defecto (puede editarse después)
            nuevoVehiculo.setEstimatedPrice(new BigDecimal("0.00"));

            // Guardar en la base de datos
            crudVehicle.create(nuevoVehiculo);

            System.out.println("Vehículo registrado exitosamente:");
            System.out.println("  Matrícula: " + licensePlate);
            System.out.println("  Modelo: " + model);
            System.out.println("  Tipo: " + type);

            // Limpiar formulario y recargar vehículos
            handleClear();
            cargarVehiculos();

        } catch (Exception e) {
            System.err.println("ERROR: No se pudo registrar el vehículo");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClear() {
        if (txtLicensePlate != null) txtLicensePlate.clear();
        if (txtModel != null) txtModel.clear();
        if (txtBrand != null) txtBrand.clear();
        if (txtColor != null) txtColor.clear();
        if (cboType != null) cboType.setValue(null);
        
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

    /**
     * Recarga los vehículos desde la base de datos
     */
    public void recargarVehiculos() {
        cargarVehiculos();
    }
}
