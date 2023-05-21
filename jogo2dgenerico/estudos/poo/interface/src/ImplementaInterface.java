import java.io.Serializable;

public class ImplementaInterface implements Interface /*, Comparable<Object>, Serializable*/ { // a própria IDE já liga a lampada quando você implementa uma interface com a ação de trazer tudo o que não foi implementado ainda
    // Lembrando que TUDO dentro de uma interface precisa ser chamado na classe que com ela trabalhará.
    @Override
    public String andar(String direcao) {
        return "indo para o " + direcao;
    } // é possível implementar várias classes ao mesmo tempo.

}
