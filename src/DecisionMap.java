import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class DecisionMap {

    DecisionNode head;
    DecisionNode tail;


    public DecisionMap() throws FileNotFoundException {
        //Load file into the program
        Scanner inFile = connectDataSet("src/CSV/SimpleMap.csv");
        //This breaks down the file into its parts by commas
        buildUnorderedList(inFile);
        //
        buildOrderedMap();
        //unorderedMap = null;
    }

    private void append(DecisionNode newNode) {

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.tail.setLinkedNode(null);

            return;
        }

        this.tail.setLinkedNode(newNode);
        this.tail = newNode;
    }


    public Scanner connectDataSet(String pathName) throws FileNotFoundException {
        File prc = new File(pathName);
        return new Scanner(prc);
    }

    public void buildUnorderedList(Scanner dataSet) {

        dataSet.useDelimiter(",");
        DecisionNode node;
        do {
            String line = dataSet.nextLine();
            node = buildNode(line);
            append(node);
        }
        while (dataSet.hasNext());
        dataSet.close();

    }

    private void buildOrderedMap() {

        if (head == null) {
            return;
        }

        DecisionNode nodeLinker;
        nodeLinker = head;

        while (nodeLinker != null) {

            int yesID = nodeLinker.getYesID();
            int noID = nodeLinker.getNoID();

            DecisionNode yesNode = nodeFetch(yesID);
            DecisionNode noNode = nodeFetch(noID);

            nodeLinker.setYesNode(yesNode);
            nodeLinker.setNoNode(noNode);

            nodeLinker = nodeLinker.getLinkedNode();

        }

        cleanup();

    }

    private void cleanup(){
        if (head == null) {
            return;
        }

        DecisionNode currentNode = head;
        DecisionNode nextNode = head.getLinkedNode();

        while (nextNode != null) {

            currentNode.setLinkedNode(null);

            currentNode = nextNode;
            nextNode = currentNode.getLinkedNode();
        }
    }

    //this takes each line and turns it into an array
    //then splits it up.
    private DecisionNode buildNode(String line) {
        String[] stringArray = line.split(",");
        DecisionNode n = new DecisionNode();

        //n.setNodeID(parseInt(stringArray[0]));

        //the methods below are using the integer class
        //since there is a static in the import we don't need
        //to use integer.valeOf.
        n.setNodeID(valueOf(stringArray[0]));
        n.setNoID(valueOf(stringArray[1]));
        n.setYesID(valueOf(stringArray[2]));

        n.setDescription(stringArray[3]);
        n.setQuestion(stringArray[4]);

        //added to display the options for the user
        n.setOptions(stringArray[5]);

        return n;
    }

    public DecisionNode entryPoint() {
        return head;
    }

    private DecisionNode nodeFetch(int nodeID) {

        DecisionNode nodeLinker = head;

        while (nodeLinker != null) {
            if(nodeLinker.getNodeID() == nodeID){
                break ;
            }
            nodeLinker = nodeLinker.getLinkedNode();
        }

        return nodeLinker;
    }


    private boolean isEmpty() {
        return this.head == null;
    }
}