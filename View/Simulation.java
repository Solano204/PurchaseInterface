package View;
import java.util.Random;

public class Simulation {
    

    public static void main(String[] args) {
        Random random = new Random();
        
        System.out.println("Números aleatorios discretos:");
        for (int i = 0; i < 5; i++) {
            int numeroAleatorio = random.nextInt(10) + 1; // Genera un número aleatorio entre 1 y 10
            System.out.println("Número aleatorio discreto: " + numeroAleatorio);
        }
        
        System.out.println("\nNúmeros aleatorios continuos:");
        for (int i = 0; i < 5; i++) {
            double numeroAleatorio = random.nextDouble(); // Genera un número aleatorio entre 0.0 y 1.0
            System.out.println("Número aleatorio continuo: " + numeroAleatorio);
        }
    }
}
