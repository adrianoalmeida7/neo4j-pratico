package br.com.caelum.neo4j.pesquisas;

import java.util.Iterator;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Pesquisa3 {

	public static void main(String[] args) {
		GraphDatabaseService db = criaDatabase();

		String query = "start n=node(1) return n";

		ExecutionEngine engine = new ExecutionEngine(db);
		ExecutionResult result = engine.execute(query);

		Iterator<Node> cidades = result.columnAs("n");
		while (cidades.hasNext()) {
			Node cidade = cidades.next();
			System.out.println(cidade.getProperty("nome"));
		}

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
