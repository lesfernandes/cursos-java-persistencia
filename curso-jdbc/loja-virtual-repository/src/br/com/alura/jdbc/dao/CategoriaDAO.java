package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class CategoriaDAO {
	
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException {
		System.out.println("Executando a query de listar categorias.");
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT id, nome FROM categoria";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					Categoria categoria = new Categoria(
							rst.getInt(1), 
							rst.getString(2));
					
					categorias.add(categoria);		
				}
			}
		}
		
		
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException{
		Categoria ultima = null;
		System.out.println("Executando a query de listar categorias.");
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT categoria.id, categoria.nome, p.id, p.nome, p.descricao FROM categoria"
				+ " INNER JOIN produto as p ON categoria.id = p.categoria_id";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(
								rst.getInt(1), 
								rst.getString(2));
						ultima = categoria;
						categorias.add(categoria);	
					}	
					Produto produto = new Produto(
							rst.getInt(3), rst.getString(4), rst.getString(5));
					ultima.adicionar(produto);
				}
			}
		}
		
		
		return categorias;
	}
}
