package utilsProject;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;

public class HashUtils {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verificarPassword(String passwordIntroducida, String hashAlmacenado) {
        return BCrypt.checkpw(passwordIntroducida, hashAlmacenado);
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // --- REGISTRO ---
//        System.out.print("Crea tu contraseña: ");
//        String password = scanner.nextLine();
//
//        String hash = hashPassword(password);
//        System.out.println("\nHash generado: " + hash);
//
//        // --- LOGIN ---
//        System.out.print("\nIntroduce la contraseña para verificar: ");
//        String intento = scanner.nextLine();
//
//        if (verificarPassword(intento, hash)) {
//            System.out.println("✔ Contraseña correcta.");
//        } else {
//            System.out.println("✘ Contraseña incorrecta.");
//        }
//
//        // --- PRUEBA CON CONTRASEÑA INCORRECTA ---
//        System.out.println("\n--- Prueba automática con contraseña incorrecta ---");
//        boolean resultadoFallo = verificarPassword("contraseñaFalsa123", hash);
//        System.out.println("¿'contraseñaFalsa123' es correcta? " + resultadoFallo);
//
//        scanner.close();
//    }
public static void main(String[] args) {
    String hash = "$2a$12$RwT2rxtRyy1aGLTcC0pigeWCyQtc7yjO6ahTV8k7f0OZEI.9naXZW";
    String intento = "usermech";

    System.out.println(BCrypt.checkpw(intento, hash)); // true si coincide
}
}