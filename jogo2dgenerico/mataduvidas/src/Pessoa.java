public /*final*/ class Pessoa
/*       ^
 final define que a classe
    não terá subclasses.
*/
        {
    String nome;
    int idade;

    Pessoa(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    String dizerNome(){
            return "Meu nome é " + nome + " e possuo " + this.idade + " anos";

    }

    }