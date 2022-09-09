public class Client implements Runnable {
    private final String name;
    private double pocket;
    private Operation operation;
    private final double sum;
    private final Bank bank;
    private Client client;
    private boolean information = true;

    public Client(String name, double pocket, Operation operation, double sum, Bank bank, Client client) {
        this.name = name;
        this.pocket = pocket;
        this.operation = operation;
        this.sum = sum;
        this.bank = bank;
        this.client = client;
    }

    public Client(String name, double pocket, Operation operation, double sum, Bank bank) {
        this.name = name;
        this.pocket = pocket;
        this.operation = operation;
        this.sum = sum;
        this.bank = bank;
    }


    public double getSum() {
        return sum;
    }


    public String getName() {
        return name;
    }

    public double getPocket() {
        return pocket;
    }

    public Operation getOperation() {
        return operation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setPocket(double pocket) {
        this.pocket = pocket;
    }

    public boolean getInformation() {
        return information;
    }

    public void setInformation(boolean information) {
        this.information = information;
    }

    @Override
    public void run() {

            if (this.getOperation() == Operation.DEPOSIT) {
                bank.deposit(this);
            }
            if (this.getOperation() == Operation.WITHDRAW) {
                bank.withdraw(this);
            }
            if (this.getOperation() == Operation.SEND) {
                bank.send(this, client);
            }
            if (getInformation()) {
                System.out.println(this.getName()
                        + " выполнил операцию "
                        + this.getOperation()
                        + " в размере: "
                        + this.getSum()
                        + ", на балансе: "
                        + this.getPocket()
                        + "\nВ банке осталось: "
                        + bank.getBankMoney());
            }
        setInformation(true);
    }
}
