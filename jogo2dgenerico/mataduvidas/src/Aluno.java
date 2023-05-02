public class Aluno extends Pessoa //o erro se dá por conta que a "ClasseFinal" é do tipo final.
                                                    // Apagando o "final" da classe, o erro some.
{
    int matricula;

    Aluno(String nome, int idade, int matricula){ // método construtor antigo.
        super(nome, idade); // A chamada do super precisa vir primeiro que tudo, se não dá erro.
        this.matricula = matricula;
    }
    String dizerMatricula(){
            return " Minha matricula é " + this.matricula;
    }
    String dizerNome(){ // sobrescrita de métodos: (método antigo)
        return super.dizerNome() + this.dizerMatricula();
    }
}
