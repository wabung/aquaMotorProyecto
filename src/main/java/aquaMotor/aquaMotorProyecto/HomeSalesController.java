package aquaMotor.aquaMotorProyecto;

import database.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import utils.InitializableWithUser;

public class HomeSalesController implements Initializable, InitializableWithUser {

    // Controlador del título
    @FXML
    private TitleCardController titleCardController;

    // Controladores de las tarjetas de información
    @FXML
    private InformationCardController infoCard1Controller;
    
    @FXML
    private InformationCardController infoCard2Controller;
    
    @FXML
    private InformationCardController infoCard3Controller;

    // Controladores de los botones
    @FXML
    private ButtonController button1Controller;
    
    @FXML
    private ButtonController button2Controller;
    
    @FXML
    private ButtonController button3Controller;
    
    @FXML
    private ButtonController button4Controller;
    
    @FXML
    private ButtonController button5Controller;

    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar título principal
        configurarTitulo();
        
        // Configurar las tarjetas de información
        configurarTarjetasInformacion();
        
        // Configurar los botones
        configurarBotones();
        
        System.out.println("HomeSalesController inicializado correctamente");
    }

    private void configurarTitulo() {
        if (titleCardController != null) {
            titleCardController.setTitle("SALES");
        }
    }

    private void configurarTarjetasInformacion() {
        // Primera tarjeta - SALES OF THE MONTH / PROFITS
        if (infoCard1Controller != null) {
            infoCard1Controller.setTitle("SALES OF THE MONTH");
            infoCard1Controller.setResult("PROFITS");
        }
        
        // Segunda tarjeta - ACTIVE PROPOSALS / ACTIVE NUMBER
        if (infoCard2Controller != null) {
            infoCard2Controller.setTitle("ACTIVE PROPOSALS");
            infoCard2Controller.setResult("ACTIVE NUMBER");
        }
        
        // Tercera tarjeta - TOTAL STOCK / VEHICLE NUMBER
        if (infoCard3Controller != null) {
            infoCard3Controller.setTitle("TOTAL STOCK");
            infoCard3Controller.setResult("VEHICLE NUMBER");
        }
    }

    private void configurarBotones() {
        // Botón 1 - NEW CLIENT REGISTRATION
        if (button1Controller != null) {
            button1Controller.setText("NEW CLIENT REGISTRATION");
            button1Controller.setOnClickAction(this::handleNewClient);
        }
        
        // Botón 2 - FULL INVENTORY
        if (button2Controller != null) {
            button2Controller.setText("FULL INVENTORY");
            button2Controller.setOnClickAction(this::handleFullInventory);
        }
        
        // Botón 3 - OFFER SALE
        if (button3Controller != null) {
            button3Controller.setText("OFFER SALE");
            button3Controller.setOnClickAction(this::handleOfferSale);
        }
        
        // Botón 4 - NEW CAR REGISTRATION
        if (button4Controller != null) {
            button4Controller.setText("NEW CAR REGISTRATION");
            button4Controller.setOnClickAction(this::handleNewCar);
        }
        
        // Botón 5 - SHOW OFFERS
        if (button5Controller != null) {
            button5Controller.setText("SHOW OFFERS");
            button5Controller.setOnClickAction(this::handleShowOffers);
        }
    }

    // Métodos de acción para los botones
    private void handleNewClient() {
        System.out.println("Navegando a: New Client Registration");
        try {
            App.setRoot("sales/views/newClient");
            System.out.println("Vista cargada exitosamente");
        } catch (Exception e) {
            System.err.println("ERROR al cargar newClient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleFullInventory() {
        System.out.println("Navegando a: Full Inventory");
        try {
            App.setRoot("sales/views/showInventory");
            System.out.println("Vista cargada exitosamente");
        } catch (Exception e) {
            System.err.println("ERROR al cargar showInventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleOfferSale() {
        System.out.println("Navegando a: Offer Sale");
        try {
            App.setRoot("sales/views/offerSale");
            System.out.println("Vista cargada exitosamente");
        } catch (Exception e) {
            System.err.println("ERROR al cargar offerSale: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleNewCar() {
        System.out.println("Navegando a: New Car Registration");
        try {
            App.setRoot("sales/views/newCar");
            System.out.println("Vista cargada exitosamente");
        } catch (Exception e) {
            System.err.println("ERROR al cargar newCar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleShowOffers() {
        System.out.println("Navegando a: Show Offers");
        try {
            App.setRoot("sales/views/showOffers");
            System.out.println("Vista cargada exitosamente");
        } catch (Exception e) {
            System.err.println("ERROR al cargar showOffers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Métodos públicos para actualizar dinámicamente los valores
    public void actualizarVentasMes(String titulo, String resultado) {
        if (infoCard1Controller != null) {
            if (titulo != null) infoCard1Controller.setTitle(titulo);
            if (resultado != null) infoCard1Controller.setResult(resultado);
        }
    }

    public void actualizarPropuestasActivas(String titulo, String resultado) {
        if (infoCard2Controller != null) {
            if (titulo != null) infoCard2Controller.setTitle(titulo);
            if (resultado != null) infoCard2Controller.setResult(resultado);
        }
    }

    public void actualizarStockTotal(String titulo, String resultado) {
        if (infoCard3Controller != null) {
            if (titulo != null) infoCard3Controller.setTitle(titulo);
            if (resultado != null) infoCard3Controller.setResult(resultado);
        }
    }

    public void initData(User user) {
        this.currentUser = user;
        titleCardController.setTitle(
                "SALES: " + user.getName().toUpperCase()
        );
        System.out.println("Dashboard cargado para: " + user.getName());
    }
}
