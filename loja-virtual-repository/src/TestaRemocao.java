import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM produto WHERE id > ?");
		stm.setInt(1, 1);
		stm.execute();
		
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

	}

}
