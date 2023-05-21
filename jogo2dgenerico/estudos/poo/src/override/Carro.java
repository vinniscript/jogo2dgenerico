package override;

public class Carro {
    public void iniciar() {
        System.out.println("Início padrão");
    }

    public void acelerar() {
        System.out.println("Aceleração padrão");
    }

    public void frear() {
        System.out.println("Frenagem padrão");
    }

    public static void main(String[] args) {
        Carro gol = new Gol();
        Carro uno = new Uno();

        gol.iniciar();
        gol.acelerar();
        gol.frear();

        System.out.println();

        uno.iniciar();
        uno.acelerar();
        uno.frear();
    }
}

class Gol extends Carro {
@java.lang.Override // Nada mais é do que uma garantia, uma prevenção de futuras mudanças na classe pai (nesse caso a classe Carro)
                    // Ao usar o método @Override, o java entende que algo deve ser alterado, ou seja, tem que bater os nomes da classe pai.
    public void iniciar() {
        System.out.println("Iniciando Gol");
    }
//@java.lang.Override // dá erro, pois o método "mudarMarcha()" não existe na classe pai, ele exige uma alteração, ele exige um método já definido antes, nada novo ou com diferenças no nome.
    public void mudarMarcha(){ // normalmente não teria erro aqui se não usassemos o @Override, mas como não existe esse método na classe pai, ai sim ele retorna o erro.
                                // Isso acontece para caso você eventualmente altere o nome ou coisa parecida, e como sem o override não tem retorno de erro, fica muito difícil o rastreio do erro.

    }
@java.lang.Override
    public void acelerar() {
        System.out.println("Acelerando Gol");
    }
@java.lang.Override
    public void frear() {
        System.out.println("Freando Gol");
    }
}

class Uno extends Carro {
    public void iniciar() {
        System.out.println("Iniciando Uno");
    }

    public void acelerar() {
        System.out.println("Acelerando Uno");
    }

    public void frear() {
        System.out.println("Freando Uno");
    }
}

