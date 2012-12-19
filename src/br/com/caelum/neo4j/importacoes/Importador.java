package br.com.caelum.neo4j.importacoes;

import java.util.Calendar;
import java.util.Map;

import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

import br.com.caelum.neo4j.relacionamento.Relacionamentos;

public class Importador {
	public static void main(String[] args) {
		Importador importador = new Importador();
		BatchInserter inserter = BatchInserters.inserter("database");
		
		System.out.println("Importa dados");
		
		importador.importaPessoas(inserter);
		importador.importaLugares(inserter);
		importador.importaViagens(inserter);
		importador.importaMorou(inserter);
		
		inserter.shutdown();
		System.out.println("Fim da importação");
	}

	private void importaMorou(BatchInserter inserter) {
		long de = Calendar.getInstance().getTimeInMillis();
		long ate = Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 24 * 700; // mais ou menos dois anos 
		inserter.createRelationship(1, 12, Relacionamentos.MOROU_EM, MapUtil.map("de", de, "ate", ate));
		inserter.createRelationship(2, 7, Relacionamentos.MOROU_EM, MapUtil.map("de", de, "ate", ate));
		inserter.createRelationship(2, 11, Relacionamentos.MOROU_EM, MapUtil.map("de", de, "ate", ate));
		inserter.createRelationship(4, 9, Relacionamentos.MOROU_EM, MapUtil.map("de", de, "ate", ate));
		inserter.createRelationship(5, 9, Relacionamentos.MOROU_EM, MapUtil.map("de", de, "ate", ate));
	}

	private void importaLugares(BatchInserter inserter) {
		Map<String, Object> toronto = MapUtil.map("nome", "Toronto");
		Map<String, Object> roma = MapUtil.map("nome", "Roma");
		Map<String, Object> berlim = MapUtil.map("nome", "Berlim");
		Map<String, Object> bruxelas = MapUtil.map("nome", "Bruxelas");
		Map<String, Object> paris = MapUtil.map("nome", "Paris");
		Map<String, Object> novaiorque = MapUtil.map("nome", "Nova Iorque");
		Map<String, Object> toquio = MapUtil.map("cidade", "Toquio");
		
		inserter.createNode(7, toronto);
		inserter.createNode(8, roma);
		inserter.createNode(9, berlim);
		inserter.createNode(10, bruxelas);
		inserter.createNode(11, paris);
		inserter.createNode(12, novaiorque);
		inserter.createNode(13, toquio);
	}

	private void importaViagens(BatchInserter inserter) {
		long hora = Calendar.getInstance().getTimeInMillis();
		inserter.createRelationship(1, 11, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(2, 8, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(2, 10, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(3, 8, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(4, 12, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(4, 13, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(5, 10, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(5, 12, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(5, 13, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
		inserter.createRelationship(6, 12, Relacionamentos.VIAJOU_PARA, MapUtil.map("em", hora));
	}
	
	private void importaPessoas(BatchInserter inserter) {
		Map<String, Object> joao = MapUtil.map("nome", "Joao");
		Map<String, Object> ricardo = MapUtil.map("nome", "Ricardo");
		Map<String, Object> carolina = MapUtil.map("nome", "Carolina");
		Map<String, Object> maria = MapUtil.map("nome", "Maria");
		Map<String, Object> fernando = MapUtil.map("nome", "Fernando");
		Map<String, Object> fabio = MapUtil.map("nome", "Fabio");
		
		inserter.createNode(1, joao);
		inserter.createNode(2, ricardo);
		inserter.createNode(3, carolina);
		inserter.createNode(4, maria);
		inserter.createNode(5, fernando);
		inserter.createNode(6, fabio);
	}
}
