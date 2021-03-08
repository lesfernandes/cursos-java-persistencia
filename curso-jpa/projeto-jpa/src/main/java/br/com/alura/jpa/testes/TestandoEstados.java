package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {

	public static void main(String[] args) {
		// Transient - existe na memória, possui informações
		// não possui id, mas pode ser managed
		Conta conta = new Conta();
		conta.setTitular("Almiro");
		conta.setAgencia(664662);
		conta.setNumero(66266);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		// Transient -> Managed
		// sincronização automática com o banco
		em.persist(conta);
		
		// Managed -> Removed
		// remover do banco de dados (delete)
		em.remove(conta);
		
		em.getTransaction().commit();
	}

}
