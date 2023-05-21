public class PrincipalInterface {

    public static void main(String[] args) {
        ImplementaInterface carinha = new ImplementaInterface(); //Você instancia uma interface comunmente, assim como faz com qualquer outra classe.
        // Você também pode fazer isso:
        //Interface carinha = new ImplementaInterface();

        String resp = carinha.andar("Norte"); // Já que a interface retorna uma String, armazenamos o output em uma váriavel de tipo igual do retorno do método da interface.

        System.out.println(resp);
    }
}
