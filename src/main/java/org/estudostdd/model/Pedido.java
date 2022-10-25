package org.estudostdd.model;

import org.estudostdd.desconto.CalculadoraFaixaDesconto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {


    private CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }

    private List<ItemPedido> itens = new ArrayList<>();

    public ResumoPedido resumo() {
        double valorTotal = itens.stream().mapToDouble(item -> item.getValorUnitario() * item.getQuantidade()).sum();
        double desconto = calculadoraFaixaDesconto.desconto(valorTotal);
        return new ResumoPedido(valorTotal, desconto);
    }

    public void adicionarItem(ItemPedido itemPedido) {
        itens.add(itemPedido);
    }

}
