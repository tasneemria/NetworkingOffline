/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.graphdatabase;


import java.io.File;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


/**
 *
 * @author a
 */
public class Main {
    public enum NodeType implements Label{
        Person, Course;
    }
    
    public enum RelationType implements RelationshipType{
        Knows, BelongsTo;
    }
    
    
    public static void main(String[] args){
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb1;
        File ff= new File("/home/a/Desktop/default.graphdb");
        GraphDatabaseService graphDb = dbFactory.newEmbeddedDatabase(ff);
        try(Transaction tx = graphDb.beginTx()){
            Node bobNode = graphDb.createNode(NodeType.Person);
            bobNode.setProperty("PId", 5001);
            bobNode.setProperty("Name", "Bob");
            bobNode.setProperty("Age", 23);
            
            Node aliceNode = graphDb.createNode(NodeType.Person);
            aliceNode.setProperty("PId",5002);
            aliceNode.setProperty("Name","Alice");
            
            Node eveNode = graphDb.createNode(NodeType.Person);
            eveNode.setProperty("Name", "Eve");
            
            Node itNode = graphDb.createNode(NodeType.Course);
            itNode.setProperty("Id", 1);
            itNode.setProperty("Name","IT Beginners");
            itNode.setProperty("Location", "Room 153");
            
            Node electronicNode = graphDb.createNode(NodeType.Course);
            electronicNode.setProperty("Name", "Electronics Advanced");
            
            
            bobNode.createRelationshipTo(aliceNode, RelationType.Knows);
            
            Relationship bobRelIt = bobNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            bobRelIt.setProperty("function", "Student");
            
            Relationship bobRelElectronics = bobNode.createRelationshipTo(electronicNode, RelationType.BelongsTo);
            bobRelElectronics.setProperty("function", "Supply Teacher");
            
            Relationship aliceRelIt = aliceNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            aliceRelIt.setProperty("function", "Teacher");
            
            tx.success();
        }
        graphDb.shutdown();
    }
            
}
