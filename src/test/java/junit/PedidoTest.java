package junit;

import org.estudostdd.model.ItemPedido;
import org.estudostdd.model.Pedido;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PedidoTest {
    Pedido pedido;

    @Before
    public void setup() {
        pedido = new Pedido();
    }

    private void assertResumoPedido(double valorTotal, double desconto) {
        assertEquals(valorTotal, pedido.valorTotal(), 0.0001);
        assertEquals(desconto, pedido.desconto(), 0.0001);
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


}
