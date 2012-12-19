package br.com.caelum.neo4j.pesquisas;

import java.util.Iterator;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Pesquisa11 {

	public static void main(String[] args) {
		GraphDatabaseService db = criaDatabase();

		// 4 é a Maria
//		String query = "start n=node(4) match n-[a]->(cidade)<-[r]-(pessoas) where type(r) = \"VIAJOU_PARA\" or type(r) = \"MOROU_EM\" return distinct pessoas";
		
		String query = "start n=node(4) match n-[a:VIAJOU_PARA|MOROU_EM]->(cidade)<-[r:VIAJOU_PARA|MOROU_EM]-(pessoas) return distinct pessoas";
		
		ExecutionEngine engine = new ExecutionEngine(db);
		ExecutionResult result = engine.execute(query);

		Iterator<Node> cidades = result.columnAs("pessoas");
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
