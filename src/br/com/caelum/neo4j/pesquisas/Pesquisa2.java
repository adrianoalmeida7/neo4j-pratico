package br.com.caelum.neo4j.pesquisas;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.Traversal;

import br.com.caelum.neo4j.relacionamento.Relacionamentos;

public class Pesquisa2 {

	public static void main(String[] args) {
		GraphDatabaseService db = criaDatabase();

		Node lugarDeOrigem = db.getNodeById(4);

		Iterable<Node> nos = Traversal.description()
				.evaluator(Evaluators.excludeStartPosition())
				.relationships(Relacionamentos.MOROU_EM, Direction.BOTH)
				.evaluator(Evaluators.atDepth(2)).traverse(lugarDeOrigem)
				.nodes();

		for (Node no : nos) {
			System.out.println(no.getProperty("nome"));
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
