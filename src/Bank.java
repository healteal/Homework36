public class Bank {
    private volatile double bankMoney = 1000;
    private int sumAllOperations = 0;

    public int getSumAllOperations() {
        return sumAllOperations;
    }

    public double getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(double bankMoney) {
        this.bankMoney = bankMoney;
    }

    public void withdraw(Client client) {
        if (getBankMoney() - client.getSum() >= 0) {
            setBankMoney(getBankMoney() - client.getSum());
            client.setPocket(client.getPocket() + client.getSum());
            sumAllOperations += client.getSum();
        } else {
            System.out.println("Недостаточно средст в банке для операции " + client.getOperation() + " клиенту " + client.getName());
            client.setInformation(false);
        }
    }

    public void deposit(Client client) {
        if (client.getPocket() - client.getSum() >= 0) {
            setBankMoney(getBankMoney() + client.getSum());
            client.setPocket(client.getPocket() - client.getSum());
            sumAllOperations += client.getSum();
        } else {
            System.out.println("Недостаточно средст у клиента" + client.getName());
            client.setInformation(false);
        }
    }

    public void send(Client sender, Client getter) {
        if (sender.getClient() != null || sender.getSum() > 0) {
            sender.setPocket(sender.getPocket() - sender.getSum());
            getter.setPocket(getter.getPocket() + sender.getSum());
            sumAllOperations += sender.getSum();
        } else {
            System.out.println("Не был введён получатель или недостаточно средств");
            sender.setInformation(false);
        }
    }
}
