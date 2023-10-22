import java.util.*;

class Notebook {
    private String model;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Notebook(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        Set<Notebook> filteredNotebooks = new HashSet<>(notebooks);

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String criterion = entry.getKey();
            Object value = entry.getValue();

            filteredNotebooks.removeIf(notebook -> {
                switch (criterion) {
                    case "ram":
                        return notebook.getRam() < (int) value;
                    case "storage":
                        return notebook.getStorage() < (int) value;
                    case "os":
                        return !notebook.getOs().equals(value);
                    case "color":
                        return !notebook.getColor().equals(value);
                    default:
                        return false;
                }
            });
        }
        return filteredNotebooks;
    }
}



public class Main {
    
    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Model1", 8, 512, "Windows", "Black"));
        notebooks.add(new Notebook("Model2", 16, 1024, "MacOS", "Silver"));
        notebooks.add(new Notebook("Model3", 4, 256, "Windows", "Red"));
        notebooks.add(new Notebook("Model4", 16, 1024, "Linux", "Grey"));
        notebooks.add(new Notebook("Model5", 32, 2048, "MacOS", "SpaceGrey"));

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println();
        System.out.println("0 - Показать результат поиска");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");


        while (true) {
            int criterion = scanner.nextInt();
            if (criterion == 0) {
                break; // Выход из цикла при вводе 0
            }
            switch (criterion) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    int ram = scanner.nextInt();
                    filters.put("ram", ram);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    int storage = scanner.nextInt();
                    filters.put("storage", storage);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    scanner.nextLine(); 
                    String os = scanner.nextLine();
                    filters.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    scanner.nextLine();
                    String color = scanner.nextLine();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Неверный критерий фильтрации.");
                    break;
            }
        }
        

       // Фильтрация ноутбуков и вывод результатов
 Set<Notebook> filteredNotebooks = Notebook.filterNotebooks(notebooks, filters);
        System.out.println("Результаты фильтрации:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println("Модель: " + notebook.getModel());
            System.out.println("ОЗУ: " + notebook.getRam() + "GB");
            System.out.println("Объем ЖД: " + notebook.getStorage() + "GB");
            System.out.println("ОС: " + notebook.getOs());
            System.out.println("Цвет: " + notebook.getColor());
            System.out.println();
        }

        scanner.close();
    }
}