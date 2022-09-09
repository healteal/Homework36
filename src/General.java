import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class General {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Client client1 = new Client("Kiryl", 300, Operation.DEPOSIT, 200, bank);
        Client client2 = new Client("Valery", 500, Operation.WITHDRAW, 700, bank);
        Client client3 = new Client("Petr", 1000, Operation.SEND, 100, bank, client1);
        Client[] clients = {client1, client2, client3};
        ExecutorService singleCashRegister = Executors.newSingleThreadExecutor();
        System.out.println("Работа одиночной кассы");
        for (Client client : clients) {
            singleCashRegister.execute(client);
        }
        while (!singleCashRegister.isTerminated()) {
            singleCashRegister.shutdown();
        }
        clients[0].setOperation(Operation.WITHDRAW);
        clients[2].setClient(clients[0]);
        clients[2].setOperation(Operation.SEND);
        clients[1].setOperation(Operation.WITHDRAW);
        ExecutorService fewCashRegisters = Executors.newFixedThreadPool(3);
        System.out.println("\nРабота трёх касс");
        for (Client client : clients) {
            fewCashRegisters.execute(client);
        }
        while (!fewCashRegisters.isTerminated()) {
            fewCashRegisters.shutdown();
        }
        System.out.println("Все операции составили: " + bank.getSumAllOperations());
    }
}
