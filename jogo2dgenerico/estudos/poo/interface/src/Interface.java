public interface Interface { /*Note que ao invés de uma public class, estamos declarando uma public interface

    Por estar trabalhando com um interface, não se pode implementar o código, fazendo algo como:

   String andar(String direcao){
    ***implementacoes
   }

        Métodos em interfaces não podem ter corpo.
            Quem vai implementar o corpo do método é a classe que irá a implementar.
    */
    abstract String andar(String direcao); //não é obrigatório escrever o 'abstract' no começo,
    //porque é um método abstrato, ou seja, não implementado.
    final int quadrupede = 4; // já é obrigatóriamente uma váriavel do tipo final, ou seja, uma constante, a escrita 'final'também é opcional. Ele vai ser final declarado ou não.
                        // Por ser final, ele não pode ser definido sem valor, ele ja deve ser declarado com um.
                        // ele não poderá ser alterado por ninguém mais


}
