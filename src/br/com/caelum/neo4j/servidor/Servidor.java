package br.com.caelum.neo4j.servidor;

import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.server.WrappingNeoServerBootstrapper;

public class Servidor {

	public static void main(String[] args) throws InterruptedException {
		final EmbeddedGraphDatabase db = new EmbeddedGraphDatabase("database");

		final WrappingNeoServerBootstrapper server = new WrappingNeoServerBootstrapper(db);
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				server.stop();
				db.shutdown();
			}
		});

	}

}
