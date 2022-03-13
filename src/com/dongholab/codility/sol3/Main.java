package com.dongholab.codility.sol3;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class NumberIterator implements Iterator<Integer> {
    private BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished = false;
    private static Pattern zeroStart = Pattern.compile("^[+|-]?0\\d{1,}");

    public NumberIterator(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
    }

    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        } else if (finished) {
            return false;
        } else {
            try {
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        finished = true;
                        return false;
                    } else {
                        line = line.trim();
                        if (isValidLine(line)) {
                            cachedLine = line;
                            return true;
                        }
                    }
                }
            } catch (IOException ioe) {
                close();
                throw new IllegalStateException(ioe.toString());
            }
        }
    }

    protected boolean isValidLine(String line) {
        try {
            int check = Integer.parseInt(line);
            if (zeroStart.matcher(line).find()) {
                return false;
            } else if (Math.abs(check) > 1000000000) {
                return false;
            }
        } catch (Exception nfe) {
            return false;
        }
        return true;
    }

    public void close() {
        finished = true;
        cachedLine = null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String currentLine = cachedLine;
        cachedLine = null;

        return Integer.parseInt(currentLine);
    }
}

class SolutionIter implements Iterable<Integer> {
    private NumberIterator numberIterator;

    public SolutionIter(Reader inp) {
        this.numberIterator = new NumberIterator(inp);
    }

    public Iterator<Integer> iterator() {
        return this.numberIterator;
    }
}

public class Main {
    public static void main(String[] args) {
        SolutionIter solutionIter = new SolutionIter(new StringReader("137\n-104\n2 58\n  +0\n++3\n+1\n 23.9\n2000000000\n-0\nfive\n -1\n"));
        for (Integer x : solutionIter) {
            System.out.println(x);
        }

        System.out.println("-----------------------------------");

        solutionIter = new SolutionIter(new StringReader("1\n2\n-3434\n20.0\n2.0\n20.\n2u1\n2000000000000000000000000\n23.9\n#12\n00\n+00\n++1\n3\n"));
        for (Integer x : solutionIter) {
            System.out.println(x);
        }
    }
}
