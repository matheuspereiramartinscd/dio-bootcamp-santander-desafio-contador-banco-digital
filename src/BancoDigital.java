import java.util.Scanner;

class Conta {
    protected String numeroConta;
    protected double saldo;

    public Conta(String numeroConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado com sucesso.");
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println(
                    "Transferência de " + valor + " realizada com sucesso para a conta " + destino.numeroConta);
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public double getSaldo() {
        return saldo;
    }
}

class ContaCorrente extends Conta {
    public ContaCorrente(String numeroConta, double saldoInicial) {
        super(numeroConta, saldoInicial);
    }
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(String numeroConta, double saldoInicial) {
        super(numeroConta, saldoInicial);
    }
}

public class BancoDigital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContaCorrente contaCorrente = new ContaCorrente("1234", 1000.0);
        ContaPoupanca contaPoupanca = new ContaPoupanca("5678", 500.0);

        int escolha;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Transferir");
            System.out.println("4. Ver saldo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor para depositar na conta corrente: ");
                    double valorDeposito = scanner.nextDouble();
                    contaCorrente.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.print("Digite o valor para sacar da conta corrente: ");
                    double valorSaque = scanner.nextDouble();
                    contaCorrente.sacar(valorSaque);
                    break;
                case 3:
                    System.out.print("Digite o valor para transferir da conta corrente para a poupança: ");
                    double valorTransferencia = scanner.nextDouble();
                    contaCorrente.transferir(contaPoupanca, valorTransferencia);
                    break;
                case 4:
                    System.out.println("Saldo da conta corrente: " + contaCorrente.getSaldo());
                    System.out.println("Saldo da conta poupança: " + contaPoupanca.getSaldo());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolha != 0);

        scanner.close();
    }
}
