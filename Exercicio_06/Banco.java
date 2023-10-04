import java.util.ArrayList;

class Banco {

    private ArrayList<Conta> contas = new ArrayList<>();

    void inserir(Conta conta){
        boolean contaExiste = false;

        for(Conta c : contas){
            if(c.getNumero().equals(conta.getNumero())){
                contaExiste = true;
                break;
            }
        }
        if(!contaExiste) {
            contas.add(conta);
        }
    }

    Conta consultar(String numero){
        Conta contaProcurada = null;
        for(int i = 0; i < this.contas.size(); i++){
            if(this.contas.get(i).getNumero().equals(numero)){
                contaProcurada = this.contas.get(i);
                break;
            }
        }
        return contaProcurada;
    }

    private int consultarPorIndice(String numero){
        int indiceProcurado = -1;

        for(int i = 0; i < this.contas.size(); i++){
            if(this.contas.get(i).getNumero().equals(numero)){
                indiceProcurado = i;
                break;
            }
        }
        return indiceProcurado;
    }

    void alterar(Conta conta){
        int indiceProcurado = this.consultarPorIndice(conta.getNumero());
        if(indiceProcurado != -1){
            contas.set(indiceProcurado, conta);
        }
    }

    void excluir(String numero){
        int indiceProcurado = this.consultarPorIndice(numero);

        if(indiceProcurado != -1){
            this.contas.remove(indiceProcurado);
        }
    }

    void sacar(String numero, int valor){
        int indiceProcurado = this.consultarPorIndice(numero);

        if(indiceProcurado != -1){
            Conta conta = this.contas.get(indiceProcurado);
            conta.sacar(valor);
        }
    }

    void transferir(String numeroCredito, String numeroDebito, double valor){
        Conta contaCredito = consultar(numeroCredito);
        Conta contaDebito = consultar(numeroDebito);
        
        if (contaCredito != null && contaDebito != null) {
            contaCredito.transferir(contaDebito, valor);
        }
    }

    int consultarTamanho(ArrayList<Conta> contas){
        return contas.size();
    }

    double obterTotal(ArrayList<Conta> contas){
        double soma = 0;
        for(Conta c : contas){
            soma += c.getSaldo();
        }

        return soma;
    }

    double obterMedia(ArrayList<Conta> contas){
        return obterTotal(contas)/consultarTamanho(contas);
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        Conta conta1 = new Conta("1", 100, "Bianca");
        Conta conta2 = new Conta("2", 100, "Ruan");
        Conta conta3 = new Conta("3", 150, "Paulo");

        banco.inserir(conta1);
        banco.inserir(conta2);
        banco.inserir(conta3);

        System.out.println(conta1.getSaldo());
        System.out.println(conta1.getNumero());
        System.out.println(conta1.getNome());

    }
}