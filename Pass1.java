import java.io.*;
import java.util.*;

class Pass1 {
    static int lc = 0;
    static int symIndex = 1, litIndex = 1, poolIndex = 1;
    static Map<String, Integer> symtab = new LinkedHashMap<>();
    static List<String> littab = new ArrayList<>();
    static List<Integer> pooltab = new ArrayList<>(List.of(1));

    static Map<String, String> IS = Map.ofEntries(
        Map.entry("STOP", "(IS,00)"),
        Map.entry("ADD", "(IS,01)"),
        Map.entry("SUB", "(IS,02)"),
        Map.entry("MULT", "(IS,03)"),
        Map.entry("MOVER", "(IS,04)"),
        Map.entry("MOVEM", "(IS,05)"),
        Map.entry("COMP", "(IS,06)"),
        Map.entry("BC", "(IS,07)"),
        Map.entry("DIV", "(IS,08)"),
        Map.entry("READ", "(IS,09)"),
        Map.entry("PRINT", "(IS,10)")
    );

    static Map<String, String> AD = Map.ofEntries(
        Map.entry("START", "(AD,01)"),
        Map.entry("END", "(AD,02)"),
        Map.entry("ORIGIN", "(AD,03)"),
        Map.entry("EQU", "(AD,04)"),
        Map.entry("LTORG", "(AD,05)")
    );

    static Map<String, String> REG = Map.ofEntries(
        Map.entry("AREG", "1"),
        Map.entry("BREG", "2"),
        Map.entry("CREG", "3"),
        Map.entry("DREG", "4")
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter ic = new BufferedWriter(new FileWriter("ic.txt"));
        String line;

        while ((line = br.readLine()) != null) 
        {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 0) continue;

            if (parts[0].equalsIgnoreCase("START")) {
                lc = Integer.parseInt(parts[1]);
                ic.write(AD.get("START") + " " + parts[1] + "\n");
                continue;
            }

            if (IS.containsKey(parts[0])) {
                ic.write(lc + " " + IS.get(parts[0]) + " ");
                if (parts.length > 1) {
                    for (int i = 1; i < parts.length; i++) {
                        String op = parts[i].replace(",", "");
                        if (REG.containsKey(op))
                            ic.write("(R," + REG.get(op) + ") ");
                        else if (op.startsWith("=")) {
                            littab.add(op);
                            ic.write("(L," + litIndex++ + ") ");
                        } else {
                            symtab.putIfAbsent(op, 0);
                            ic.write("(S," + getSymIndex(op) + ") ");
                        }
                    }
                }
                ic.write("\n");
                lc++;
            }

            else if (AD.containsKey(parts[0])) {
                if (parts[0].equals("LTORG") || parts[0].equals("END")) {
                    for (int i = pooltab.get(pooltab.size() - 1) - 1; i < littab.size(); i++) {
                        ic.write(lc + " (DL,01) (C," + littab.get(i).substring(1) + ")\n");
                        lc++;
                    }
                    pooltab.add(litIndex);
                }
                ic.write(AD.get(parts[0]) + "\n");
            }

            else { // Label case
                symtab.put(parts[0].replace(":", ""), lc);
                if (parts.length > 1) {
                    String[] newParts = Arrays.copyOfRange(parts, 1, parts.length);
                    line = String.join(" ", newParts);
                    lc--; // to handle same line instruction
                }
            }

            lc++;
        }

        br.close();
        ic.close();

        writeTables();
        System.out.println("PASS1 Completed. Files generated: ic.txt, symtab.txt, littab.txt, pooltab.txt");
    }

    static int getSymIndex(String sym) {
        int idx = 1;
        for (String s : symtab.keySet()) {
            if (s.equals(sym)) return idx;
            idx++;
        }
        return idx;
    }

    static void writeTables() throws IOException {
        BufferedWriter st = new BufferedWriter(new FileWriter("symtab.txt"));
        int idx = 1;
        for (Map.Entry<String, Integer> e : symtab.entrySet())
            st.write(idx++ + "\t" + e.getKey() + "\t" + e.getValue() + "\n");
        st.close();

        BufferedWriter lt = new BufferedWriter(new FileWriter("littab.txt"));
        idx = 1;
        for (String l : littab)
            lt.write(idx++ + "\t" + l + "\n");
        lt.close();

        BufferedWriter pt = new BufferedWriter(new FileWriter("pooltab.txt"));
        for (int p : pooltab)
            pt.write(p + "\n");
        pt.close();
    }
}

