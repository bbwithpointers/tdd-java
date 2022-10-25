package junit;

import org.estudostdd.desconto.*;
import org.estudostdd.model.ResumoPedido;
import org.estudostdd.model.ItemPedido;
import org.estudostdd.model.Pedido;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PedidoTest {
    Pedido pedido;

    @Before
    public void setup() {
        CalculadoraFaixaDesconto calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                        new CalculadoraDescontoSegundaFaixa(
                                new CalculadoraDescontoPrimeiraFaixa(
                                        new SemDesconto(null))));
        pedido = new Pedido(calculadoraFaixaDesconto);
    }

    private void assertResumoPedido(double valorTotal, double desconto) {
        ResumoPedido resumoPedido = pedido.resumo();
        assertEquals(valorTotal, resumoPedido.getValorTotal(), 0.0001);
        assertEquals(desconto, resumoPedido.getDesconto(), 0.0001);
    }

    @Test
    public void addItemNoPedidoTest() {
        ItemPedido itemPedido = new ItemPedido("Sabonete", 2.75, 10);
        pedido.adicionarItem(itemPedido);
    }

    @Test
    public void calculaValorTotalEDescontoParaPedidoVazioTest() {
        assertResumoPedido(0.0, 0.0);
    }

    @Test
    public void calculaResumoParaItemSemDescontoTest() {
        ItemPedido itemPedido = new ItemPedido("Sabonete", 5.0, 5);
        pedido.adicionarItem(itemPedido);
        assertResumoPedido(25.0, 0.0);
    }

    @Test
    public void calcularResumoParaDoisItensSemDesconto() {
        pedido.adicionarItem(new ItemPedido("sabonete", 3.0, 3));
        pedido.adicionarItem(new ItemPedido("Pasta dental", 4.0, 3));
        assertResumoPedido(21.0, 0);
    }

    @Test
    public void aplicarDescontoNaPrimeiraFaixaTest() {
        // acima de 300 reais - 4% de desconto
        pedido.adicionarItem(new ItemPedido("Creme", 20.0, 20));
        assertResumoPedido(400.0, 16.0);
    }

    @Test
    public void aplicarDescontoNaSegundaFaixaTest() {
        pedido.adicionarItem(new ItemPedido("Shampoo", 15.0, 30));
        pedido.adicionarItem(new ItemPedido("Creme", 15.0, 30));
        // aplica 6% de desconto acima de 800
        assertResumoPedido(900.0, 54.0);
    }

    @Test
    public void aplicarDescontoTerceiraFaixaTest() {
        pedido.adicionarItem(new ItemPedido("Shampoo", 15.0, 30));
        pedido.adicionarItem(new ItemPedido("Creme", 15.0, 30));
        pedido.adicionarItem(new ItemPedido("Sabonete", 10.0, 30));
        // acima de 1000 aplica 8% de desconto
        assertResumoPedido(1200.0, 96.0);
    }

}
