import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCodes{

    // Node class for Huffman Tree
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char character;
        HuffmanNode left, right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(HuffmanNode node) {
            return this.frequency - node.frequency;
        }
    }

    private HuffmanNode root;
    private Map<Character, String> huffmanCodes;

    public HuffmanCodes(Map<Character, Integer> frequencies) {
        this.root = buildHuffmanTree(frequencies);
        this.huffmanCodes = new HashMap<>();
        generateCodes(root, "");
    }

    private HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode sum = new HuffmanNode('-', left.frequency + right.frequency);
            sum.left = left;
            sum.right = right;
            priorityQueue.add(sum);
        }

        return priorityQueue.poll();
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        generateCodes(node.left, code + '0');
        generateCodes(node.right, code + '1');
    }

    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public String encode(String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(character));
        }
        return encodedText.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode currentNode = root;
        for (char bit : encodedText.toCharArray()) {
            currentNode = (bit == '0') ? currentNode.left : currentNode.right;
            if (currentNode.left == null && currentNode.right == null) {
                decodedText.append(currentNode.character);
                currentNode = root;
            }
        }
        return decodedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to be encoded:");
        String text = scanner.nextLine();

        Map<Character, Integer> frequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        HuffmanCodes huffmanCode = new HuffmanCodes(frequencies);

        long startTime, endTime;

        startTime = System.nanoTime();
        String encodedText = huffmanCode.encode(text);
        endTime = System.nanoTime();
        System.out.println("Encoded Text: " + encodedText);
        System.out.println("Time taken to encode text: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        String decodedText = huffmanCode.decode(encodedText);
        endTime = System.nanoTime();
        System.out.println("Decoded Text: " + decodedText);
        System.out.println("Time taken to decode text: " + (endTime - startTime) + " ns");
    }
}
