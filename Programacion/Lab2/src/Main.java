import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("Menú Principal:");
                System.out.println("1. Ejercicio 3.1 - Cálculos con Arreglos");
                System.out.println("2. Ejercicio 3.2 - Operaciones con Cadenas");
                System.out.println("3. Ejercicio 3.3 - Ordenamiento de Números");
                System.out.println("4. Ejercicio 3.4 - Gestión de Carros");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consumir el salto de línea

                switch (choice) {
                    case 1 -> calculosArreglos(scanner);
                    case 2 -> operacionesCadenas(scanner);
                    case 3 -> ordenamientoNumeros();
                    case 4 -> SwingUtilities.invokeLater(Main::gestionCarros);
                    case 5 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida.");
                }
            } while (choice != 5);
        }
    }

    // Ejercicio 3.1 - Cálculos con Arreglos
    public static void calculosArreglos(Scanner scanner) {
        Random random = new Random();

        System.out.print("Ingrese el número de filas del arreglo: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas del arreglo: ");
        int columnas = scanner.nextInt();
        
        double[][] arr = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                arr[i][j] = random.nextDouble() * 100; // Números aleatorios entre 0 y 100
            }
        }

        System.out.println("Arreglo generado: ");
        for (double[] row : arr) {
            System.out.println(Arrays.toString(row));
        }

        double[] elementos = new double[filas * columnas];
        int index = 0;
        for (double[] row : arr) {
            for (double v : row) {
                elementos[index++] = v;
            }
        }

        double media = calcularMedia(elementos);
        double mediana = calcularMediana(elementos);
        double varianza = calcularVarianza(elementos, media);
        double desviacionEstandar = Math.sqrt(varianza);
        double moda = calcularModa(elementos);

        System.out.println("Media: " + media);
        System.out.println("Mediana: " + mediana);
        System.out.println("Varianza: " + varianza);
        System.out.println("Desviación Estándar: " + desviacionEstandar);
        System.out.println("Moda: " + moda);
    }

    private static double calcularMedia(double[] arr) {
        double suma = 0;
        for (double v : arr) {
            suma += v;
        }
        return suma / arr.length;
    }

    private static double calcularMediana(double[] arr) {
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            return (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
        } else {
            return arr[arr.length / 2];
        }
    }

    private static double calcularVarianza(double[] arr, double media) {
        double suma = 0;
        for (double v : arr) {
            suma += Math.pow(v - media, 2);
        }
        return suma / arr.length;
    }

    private static double calcularModa(double[] arr) {
        Arrays.sort(arr);
        double moda = arr[0];
        int maxCount = 1, count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > maxCount) {
                maxCount = count;
                moda = arr[i];
            }
        }
        return moda;
    }

    // Ejercicio 3.2 - Operaciones con Cadenas
    public static void operacionesCadenas(Scanner scanner) {
        System.out.print("Ingrese una línea de caracteres: ");
        String linea = scanner.nextLine();

        char caracterMasRepetido = obtenerCaracterMasRepetido(linea);
        String lineaModificada = reemplazarVocales(linea, caracterMasRepetido);
        String lineaInvertida = invertirCadena(linea);

        System.out.println("Cadena con vocales reemplazadas: " + lineaModificada);
        System.out.println("Cadena invertida: " + lineaInvertida);
    }

    private static char obtenerCaracterMasRepetido(String linea) {
        Map<Character, Integer> frecuencia = new HashMap<>();
        for (char c : linea.toCharArray()) {
            frecuencia.put(c, frecuencia.getOrDefault(c, 0) + 1);
        }

        char maxChar = linea.charAt(0);
        int maxFrecuencia = 1;
        for (Map.Entry<Character, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxChar = entry.getKey();
                maxFrecuencia = entry.getValue();
            }
        }
        return maxChar;
    }

    private static String reemplazarVocales(String linea, char reemplazo) {
        return linea.replaceAll("[aeiouAEIOU]", String.valueOf(reemplazo));
    }

    private static String invertirCadena(String linea) {
        return new StringBuilder(linea).reverse().toString();
    }

    // Ejercicio 3.3 - Ordenamiento de Números
    public static void ordenamientoNumeros() {
        int[] tamaños = {100, 500, 1000, 5000, 10000};
        StringBuilder resultado = new StringBuilder();
        resultado.append(String.format("%-15s %-25s %-25s %-25s %-25s%n", "Tamaño", "Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort"));
        resultado.append(String.format("%-15s %-25s %-25s %-25s %-25s%n", "------", "------------", "--------------", "--------------", "----------"));

        for (int tamaño : tamaños) {
            double[] arreglo = generarArregloAleatorio(tamaño);

            long tiempoInicio = System.nanoTime();
            bubbleSort(arreglo.clone());
            long tiempoFin = System.nanoTime();
            long tiempoBubbleSort = tiempoFin - tiempoInicio;

            tiempoInicio = System.nanoTime();
            insertionSort(arreglo.clone());
            tiempoFin = System.nanoTime();
            long tiempoInsertionSort = tiempoFin - tiempoInicio;

            tiempoInicio = System.nanoTime();
            selectionSort(arreglo.clone());
            tiempoFin = System.nanoTime();
            long tiempoSelectionSort = tiempoFin - tiempoInicio;

            tiempoInicio = System.nanoTime();
            mergeSort(arreglo.clone(), 0, arreglo.length - 1);
            tiempoFin = System.nanoTime();
            long tiempoMergeSort = tiempoFin - tiempoInicio;

            resultado.append(String.format("%-15d %-25d %-25d %-25d %-25d%n", tamaño, tiempoBubbleSort, tiempoInsertionSort, tiempoSelectionSort, tiempoMergeSort));
        }

        System.out.println(resultado);
    }

    private static double[] generarArregloAleatorio(int tamaño) {
        Random random = new Random();
        double[] arreglo = new double[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextDouble() * 100;
        }
        return arreglo;
    }

    private static void bubbleSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void insertionSort(double[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            double key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    private static void selectionSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            double temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    private static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] L = new double[n1];
        double[] R = new double[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Ejercicio 3.4 - Gestión de Carros
    public static void gestionCarros() {
        JFrame frame = new JFrame("Gestión de Carros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        JTextField campoMarca = new JTextField();
        JTextField campoModelo = new JTextField();
        JTextField campoColor = new JTextField();
        JTextField campoKilometraje = new JTextField();
        JTextField campoCantidad = new JTextField();

        JButton botonAgregar = new JButton("Agregar Carros");

        DefaultListModel<Carro> modeloLista = new DefaultListModel<>();
        JList<Carro> listaCarros = new JList<>(modeloLista);
        listaCarros.setCellRenderer(new CarroRenderer());

        botonAgregar.addActionListener(e -> {
            int cantidad;
            try {
                cantidad = Integer.parseInt(campoCantidad.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Cantidad inválida.");
                return;
            }

            for (int i = 0; i < cantidad; i++) {
                String marca = campoMarca.getText();
                String modelo = campoModelo.getText();
                String color = campoColor.getText();
                int kilometraje;

                try {
                    kilometraje = Integer.parseInt(campoKilometraje.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Kilometraje inválido.");
                    return;
                }

                Carro carro = new Carro(marca, modelo, color, kilometraje);
                modeloLista.addElement(carro);
            }

            campoMarca.setText("");
            campoModelo.setText("");
            campoColor.setText("");
            campoKilometraje.setText("");
            campoCantidad.setText("");
        });

        frame.add(new JLabel("Marca:"));
        frame.add(campoMarca);
        frame.add(new JLabel("Modelo:"));
        frame.add(campoModelo);
        frame.add(new JLabel("Color:"));
        frame.add(campoColor);
        frame.add(new JLabel("Kilometraje:"));
        frame.add(campoKilometraje);
        frame.add(new JLabel("Cantidad de Carros a Agregar:"));
        frame.add(campoCantidad);
        frame.add(botonAgregar);
        frame.add(new JScrollPane(listaCarros));

        frame.setVisible(true);
    }
}

class Carro {
    private final String marca;
    private final String modelo;
    private final String color;
    private final int kilometraje;

    public Carro(String marca, String modelo, String color, int kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.kilometraje = kilometraje;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color + ", Kilometraje: " + kilometraje;
    }
}

class CarroRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Carro) {
            setText(value.toString());
        }
        return component;
    }
}