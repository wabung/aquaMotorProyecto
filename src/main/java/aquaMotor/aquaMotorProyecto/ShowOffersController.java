package aquaMotor.aquaMotorProyecto;

import database.crud.CrudOffer;
import database.model.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShowOffersController implements Initializable {

    // Instancia de CRUD para ofertas
    private CrudOffer crudOffer = new CrudOffer();

    // Listas para almacenar ofertas
    private List<Offer> todasLasOfertas = new ArrayList<>();
    private List<Offer> ofertasFiltradas = new ArrayList<>();

    // Controlador del AppBar
    @FXML
    private AppBarController appBarController;

    // Filtros
    @FXML
    private ComboBox<String> cboStatus;

    @FXML
    private TextField txtSearch;

    // VBox para las ofertas (dentro del ScrollPane en FXML)
    @FXML
    private VBox offerListContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el título del AppBar
        configurarAppBar();

        // Configurar el ComboBox de estado
        configurarComboBox();

        // Configurar la búsqueda
        configurarBusqueda();

        // Cargar ofertas
        cargarOfertas();
    }

    private void configurarAppBar() {
        if (appBarController != null) {
            appBarController.setTitle("SHOW OFFERS");
            appBarController.setOnBackAction(this::volverAHome);
        }
    }

    private void configurarComboBox() {
        if (cboStatus != null) {
            cboStatus.getItems().addAll(
                "All Status",
                "Pending",
                "Accepted",
                "Rejected",
                "Expired"
            );
            cboStatus.setValue("All Status");
            cboStatus.setOnAction(event -> aplicarFiltrosYBusqueda());
        }
    }

    private void configurarBusqueda() {
        if (txtSearch != null) {
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                aplicarFiltrosYBusqueda();
            });
        }
    }

    /**
     * Carga todas las ofertas desde la base de datos
     */
    private void cargarOfertas() {
        try {
            todasLasOfertas = crudOffer.read();
            if (todasLasOfertas == null) {
                todasLasOfertas = new ArrayList<>();
            }

            System.out.println("Ofertas cargadas: " + todasLasOfertas.size());

            // Mostrar todas las ofertas inicialmente
            ofertasFiltradas = new ArrayList<>(todasLasOfertas);
            mostrarOfertas();

        } catch (Exception e) {
            System.err.println("Error al cargar ofertas");
            e.printStackTrace();
            todasLasOfertas = new ArrayList<>();
            ofertasFiltradas = new ArrayList<>();
        }
    }

    /**
     * Muestra las ofertas filtradas en el VBox
     */
    private void mostrarOfertas() {
        if (offerListContainer == null) {
            System.err.println("offerListContainer es null");
            return;
        }

        offerListContainer.getChildren().clear();

        if (ofertasFiltradas.isEmpty()) {
            System.out.println("No hay ofertas para mostrar");
            return;
        }

        // Crear una fila por cada oferta
        for (Offer oferta : ofertasFiltradas) {
            try {
                // Crear HBox para la fila
                HBox row = new HBox(10);
                row.getStyleClass().add("status-row");
                row.setPrefHeight(140);
                row.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5;");

                // Tarjeta de vehículo
                if (oferta.getVehicle() != null) {
                    FXMLLoader vehicleLoader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/vehicleCard.fxml"));
                    javafx.scene.Node vehicleCard = vehicleLoader.load();

                    VehicleCardController vehicleController = vehicleLoader.getController();
                    
                    String type = oferta.getVehicle().getType() != null ? oferta.getVehicle().getType() : "N/A";
                    String model = oferta.getVehicle().getModel() != null ? oferta.getVehicle().getModel() : "N/A";
                    String registration = oferta.getVehicle().getRegistration() != null ? oferta.getVehicle().getRegistration() : "N/A";
                    String year = oferta.getVehicle().getEntryDate() != null ? 
                        new SimpleDateFormat("yyyy").format(oferta.getVehicle().getEntryDate()) : "N/A";
                    String price = oferta.getVehicle().getEstimatedPrice() != null ? 
                        "$" + oferta.getVehicle().getEstimatedPrice().toString() : "N/A";

                    vehicleController.setVehicleData(type, model, "N/A", year, registration, "N/A", price);
                    row.getChildren().add(vehicleCard);
                }

                // Tarjeta de cliente
                if (oferta.getClient() != null) {
                    FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("/aquaMotor/sales/elements/userCard.fxml"));
                    javafx.scene.Node userCard = clientLoader.load();

                    UserCardController userController = clientLoader.getController();
                    userController.setUserData(
                        oferta.getClient().getName(),
                        oferta.getClient().getEmail(),
                        oferta.getClient().getPhoneNumber()
                    );
                    row.getChildren().add(userCard);
                }

                // Botón de estado
                Button statusBtn = new Button(determinarEstado(oferta));
                statusBtn.getStyleClass().add("status-button");
                statusBtn.setStyle("-fx-min-width: 100px; -fx-min-height: 30px;");
                HBox.setMargin(statusBtn, new Insets(110, 0, 0, 20));
                row.getChildren().add(statusBtn);

                offerListContainer.getChildren().add(row);

            } catch (IOException e) {
                System.err.println("Error al cargar tarjetas de oferta");
                e.printStackTrace();
            }
        }

        System.out.println("Mostrando " + ofertasFiltradas.size() + " ofertas");
    }

    /**
     * Determina el estado de una oferta basado en la fecha límite
     */
    private String determinarEstado(Offer oferta) {
        if (oferta.getDeadline() == null) {
            return "UNKNOWN";
        }

        long hoy = System.currentTimeMillis();
        long deadline = oferta.getDeadline().getTime();

        if (deadline < hoy) {
            return "EXPIRED";
        } else if (deadline - hoy < 7 * 24 * 60 * 60 * 1000L) {
            return "URGENT";
        } else {
            return "ACTIVE";
        }
    }

    /**
     * Aplica filtros de estado y búsqueda
     */
    private void aplicarFiltrosYBusqueda() {
        String estadoSeleccionado = cboStatus != null ? cboStatus.getValue() : "All Status";
        String textoBusqueda = txtSearch != null ? txtSearch.getText().trim().toLowerCase() : "";

        ofertasFiltradas = todasLasOfertas.stream()
            .filter(o -> {
                // Filtro por estado
                boolean cumpleEstado = estadoSeleccionado == null || 
                                      estadoSeleccionado.equals("All Status") ||
                                      determinarEstado(o).equalsIgnoreCase(estadoSeleccionado);

                // Filtro por búsqueda (busca en cliente, vehículo, etc.)
                boolean cumpleBusqueda = textoBusqueda.isEmpty() ||
                    (o.getClient() != null && o.getClient().getName() != null && 
                     o.getClient().getName().toLowerCase().contains(textoBusqueda)) ||
                    (o.getVehicle() != null && o.getVehicle().getRegistration() != null && 
                     o.getVehicle().getRegistration().toLowerCase().contains(textoBusqueda)) ||
                    (o.getVehicle() != null && o.getVehicle().getModel() != null && 
                     o.getVehicle().getModel().toLowerCase().contains(textoBusqueda));

                return cumpleEstado && cumpleBusqueda;
            })
            .collect(Collectors.toList());

        System.out.println("Filtrado: " + ofertasFiltradas.size() + " ofertas");
        mostrarOfertas();
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
     * Recarga las ofertas desde la base de datos
     */
    public void recargarOfertas() {
        cargarOfertas();
    }
}
