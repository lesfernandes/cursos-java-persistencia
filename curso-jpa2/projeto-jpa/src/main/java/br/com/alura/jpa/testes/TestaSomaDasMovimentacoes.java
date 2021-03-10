package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.dao.MovimentacaoDAO;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();

		BigDecimal mediaDasMovimentacoes = new MovimentacaoDAO(em).getSomaDasMovimentacoes();

		System.out.println("A média das movimentações é: R$" + mediaDasMovimentacoes);
	}

}
