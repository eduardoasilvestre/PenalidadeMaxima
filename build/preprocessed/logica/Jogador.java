/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author a1042258
 */
public class Jogador {

    private String nome; /*nome do jogado*/

    private int gols; /*nro de gols marcados*/

    private int defesas; /*nro de penaltis defendidos*/

    private int penaltisBatidos;/*nro de penaltis batidos ate o momento*/


    public Jogador() {
        defesas = 0;
        gols = 0;
        penaltisBatidos = 0;
    }

    public int getPenaltisBatidos() {
        return penaltisBatidos;
    }

    public void setPenaltisBatidos(int penaltisBatidos) {
        this.penaltisBatidos += penaltisBatidos;
    }

    public int getDefesas() {
        return defesas;
    }

    public void setDefesas(int defesas) {
        this.defesas += defesas;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols += gols;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
