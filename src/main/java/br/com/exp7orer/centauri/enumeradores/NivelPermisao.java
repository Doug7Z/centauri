package br.com.exp7orer.centauri.enumeradores;

public enum NivelPermisao {
    ZERO(0), UM(1), DOIS(2), TRES(3), QUATRO(4), CINCO(5);
    private final int nivel;

    private NivelPermisao(int nivel) {
        this.nivel = nivel;
    }

    public int getNivelPermissao() {
        return nivel;
    }
}
