public class NoRubroNegro {
    private int valorNo;
    private NoRubroNegro noEsquerdo;
    private NoRubroNegro noDireito;
    private boolean vermelho;
    private int repeticao;

    // Construtor
    public NoRubroNegro(int valorNo) {
        this.valorNo = valorNo;
        this.noEsquerdo = null;
        this.noDireito = null;
        this.vermelho = true;
        this.repeticao = 1;
    }

    // Encapsulamento
    public int getValorNo() {
        return valorNo;
    }

    public void setValorNo(int valorNo) {
        this.valorNo = valorNo;
    }

    public NoRubroNegro getNoEsquerdo() {
        return noEsquerdo;
    }

    public void setNoEsquerdo(NoRubroNegro noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
    }

    public NoRubroNegro getNoDireito() {
        return noDireito;
    }

    public void setNoDireito(NoRubroNegro noDireito) {
        this.noDireito = noDireito;
    }

    public boolean isVermelho() {
        return vermelho;
    }

    public void setVermelho(boolean vermelho) {
        this.vermelho = vermelho;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void incrementaRepeticao() {
        this.repeticao++;
    }

    public void decrementaRepeticao() {
        if(this.repeticao > 0) {
            this.repeticao--;
        }
    }
}
