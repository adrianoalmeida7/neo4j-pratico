package br.com.caelum.neo4j.relacionamento;

import org.neo4j.graphdb.RelationshipType;

public enum Relacionamentos implements RelationshipType{
	VIAJOU_PARA, MOROU_EM
}
