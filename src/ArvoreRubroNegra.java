import java.util.LinkedList;
import java.util.Queue;

public class ArvoreRubroNegra {
    private NoRubroNegro raiz;

    // Construtor
    public ArvoreRubroNegra() {
        this.raiz = null;
    }

    public NoRubroNegro getRaiz() {
        return raiz;
    }

    public void setRaiz(int valNo) {
        raiz = setNovoNo(raiz, valNo);
        if (raiz != null) {
            raiz.setVermelho(false); // A raiz deve ser preta
        }
    }

    private NoRubroNegro setNovoNo(NoRubroNegro no, int valor) {
        if (no == null) {
            return new NoRubroNegro(valor);
        }

        if (valor < no.getValorNo()) {
            no.setNoEsquerdo(setNovoNo(no.getNoEsquerdo(), valor));
        } else if (valor > no.getValorNo()) {
            no.setNoDireito(setNovoNo(no.getNoDireito(), valor));
        } else {
            no.incrementaRepeticao(); // Atualiza a contagem de repetições
        }

        // Atualiza a arvore rubro negra
        if (isVermelho(no.getNoDireito()) && !isVermelho(no.getNoEsquerdo())) {
            no = rotacaoEsquerda(no);
        }
        if (isVermelho(no.getNoEsquerdo()) && isVermelho(no.getNoEsquerdo().getNoEsquerdo())) {
            no = rotacaoDireita(no);
        }
        if (isVermelho(no.getNoEsquerdo()) && isVermelho(no.getNoDireito())) {
            inverterCores(no);
        }

        return no;
    }

    public int buscar(int valor) {
        return buscar(this.raiz, valor);
    }

    private int buscar(NoRubroNegro no, int valor) {
        if (no == null) {
            return 0;
        }
        if (valor < no.getValorNo()) {
            return buscar(no.getNoEsquerdo(), valor);
        } else if (valor > no.getValorNo()) {
            return buscar(no.getNoDireito(), valor);
        } else {
            return no.getRepeticao();
        }
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
        if (raiz != null) {
            raiz.setVermelho(false); // A raiz deve ser preta
        }
    }

    private NoRubroNegro remover(NoRubroNegro no, int valor) {
        if (no == null) {
            return null;
        }

        if (valor < no.getValorNo()) {
            if (no.getNoEsquerdo() == null || (!isVermelho(no.getNoEsquerdo()) && (no.getNoEsquerdo().getNoEsquerdo() == null || !isVermelho(no.getNoEsquerdo().getNoEsquerdo())))) {
                no = moverDoisParaDireita(no);
            }
            no.setNoEsquerdo(remover(no.getNoEsquerdo(), valor));
        } else {
            if (isVermelho(no.getNoEsquerdo())) {
                no = rotacaoDireita(no);
            }
            if (valor == no.getValorNo() && no.getNoDireito() == null) {
                return no.getNoEsquerdo();
            }
            if (no.getNoDireito() == null || (!isVermelho(no.getNoDireito()) && (no.getNoDireito().getNoEsquerdo() == null || !isVermelho(no.getNoDireito().getNoEsquerdo())))) {
                no = moverDoisParaDireita(no);
            }
            if (valor == no.getValorNo()) {
                if (no.getNoDireito() == null) {
                    return no.getNoEsquerdo();
                }
                NoRubroNegro x = minimo(no.getNoDireito());
                no.setValorNo(x.getValorNo());
                no.setNoDireito(remover(no.getNoDireito(), x.getValorNo()));
            } else {
                no.setNoDireito(remover(no.getNoDireito(), valor));
            }
        }

        return balancear(no);
    }

    private NoRubroNegro balancear(NoRubroNegro no) {
        if (isVermelho(no.getNoDireito())) {
            no = rotacaoEsquerda(no);
        }
        if (isVermelho(no.getNoEsquerdo()) && isVermelho(no.getNoEsquerdo().getNoEsquerdo())) {
            no = rotacaoDireita(no);
        }
        if (isVermelho(no.getNoEsquerdo()) && isVermelho(no.getNoDireito())) {
            inverterCores(no);
        }

        return no;
    }

    private NoRubroNegro moverDoisParaDireita(NoRubroNegro no) {
        inverterCores(no);
        if (no.getNoEsquerdo() != null && isVermelho(no.getNoEsquerdo().getNoEsquerdo())) {
            no = rotacaoDireita(no);
            inverterCores(no);
        }
        return no;
    }

    private NoRubroNegro minimo(NoRubroNegro no) {
        while (no.getNoEsquerdo() != null) {
            no = no.getNoEsquerdo();
        }
        return no;
    }

    private NoRubroNegro rotacaoEsquerda(NoRubroNegro no) {
        NoRubroNegro filhoDireito = no.getNoDireito();
        no.setNoDireito(filhoDireito.getNoEsquerdo());
        filhoDireito.setNoEsquerdo(no);
        filhoDireito.setVermelho(no.isVermelho());
        no.setVermelho(true);
        return filhoDireito;
    }

    private NoRubroNegro rotacaoDireita(NoRubroNegro no) {
        NoRubroNegro filhoEsquerdo = no.getNoEsquerdo();
        no.setNoEsquerdo(filhoEsquerdo.getNoDireito());
        filhoEsquerdo.setNoDireito(no);
        filhoEsquerdo.setVermelho(no.isVermelho());
        no.setVermelho(true);
        return filhoEsquerdo;
    }

    private void inverterCores(NoRubroNegro no) {
        no.setVermelho(!no.isVermelho());
        if (no.getNoEsquerdo() != null) {
            no.getNoEsquerdo().setVermelho(!no.getNoEsquerdo().isVermelho());
        }
        if (no.getNoDireito() != null) {
            no.getNoDireito().setVermelho(!no.getNoDireito().isVermelho());
        }
    }

    private boolean isVermelho(NoRubroNegro no) {
        return no != null && no.isVermelho();
    }

    public void imprimirNivel(NoRubroNegro raiz) {
        if (raiz == null) {
            return;
        }
        Queue<NoRubroNegro> nos = new LinkedList<>();
        nos.add(raiz);

        while (!nos.isEmpty()) {
            NoRubroNegro no = nos.poll();
            System.out.printf(no.getValorNo() + " - ");
            if(no.isVermelho()) {
                System.out.println("No Vermelho");
            } else {
                System.out.println("No Preto");
            }

            if (no.getNoEsquerdo() != null) {
                nos.add(no.getNoEsquerdo());
            }

            if (no.getNoDireito() != null) {
                nos.add(no.getNoDireito());
            }
        }
    }
}
