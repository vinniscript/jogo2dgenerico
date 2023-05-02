public class Main {
    public static void main(String[] args) {

        // Instanciamento com o construtor.

        Aluno aluno = new Aluno("João", 23, 2128);

        /* - Método de instanciameno sem o construtor.

        aluno.nome = "Gleison";
//           ^ Se o método é retornado em negrito, significa que é da mesma classe referenciada, sem heranças.
                // Todos os métodos que aparecem, de 'equals()' para baixo, é herança da classe pai de todas, a 'Object'
        aluno.idade = 20;
        aluno.matricula = 2128;

*/
        System.out.println(aluno.dizerNome());
    }
}
