package org.estudostdd.model;

import org.estudostdd.ResumoPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {


    private List<ItemPedido> itens = new ArrayList<>();

    public ResumoPedido resumo() {
        double valorTotal = itens.stream().mapToDouble(item -> item.getValorUnitario() * item.getQuantidade()).sum();
        double desconto = 0;

        if (valorTotal > 300.0 && valorTotal <= 800.0) {
            desconto = valorTotal * 0.04;
        }
        if (valorTotal > 800 && valorTotal <= 1000.0) {
            desconto = valorTotal * 0.06;
        }
        if (valorTotal > 1000.0) {
            desconto = valorTotal * 0.08;
        }
        return new ResumoPedido(valorTotal, desconto);
    }

    public void adicionarItem(ItemPedido itemPedido) {
        itens.add(itemPedido);
    }

}
