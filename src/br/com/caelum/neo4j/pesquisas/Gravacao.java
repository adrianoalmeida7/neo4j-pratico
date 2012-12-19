package br.com.caelum.neo4j.pesquisas;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import br.com.caelum.neo4j.relacionamento.Relacionamentos;

public class Gravacao {
	public static void main(String[] args) {
		GraphDatabaseService db = criaDatabase();
		Transaction tx = db.beginTx();
		
		Node adriano = db.createNode();
		adriano.setProperty("nome", "Adriano Almeida");
		
		System.out.println(adriano.getId());
		
		Node palestra = db.createNode();
		palestra.setProperty("titulo", "introducao pratica ao neo4j");
		
		adriano.createRelationshipTo(palestra, Relacionamentos.APRESENTA);
		
		tx.success();
		tx.finish();
		
	}
	
	private static GraphDatabaseService criaDatabase() {
		final EmbeddedGraphDatabase db = new EmbeddedGraphDatabase("database");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				db.shutdown();
			}
		});
		return db;
	}
}
