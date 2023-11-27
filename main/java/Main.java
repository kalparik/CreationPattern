public class Main {
    public static void main(String[] args) {

        Person mom = new Person.Builder()
                .setName("Анна")
                .setSurName("Вольф")
                .setAge(21)
                .setAddress("Сидней")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);
    }
}
